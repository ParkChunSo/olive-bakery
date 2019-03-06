package com.dev.olive.olivebakery.Board;

import com.dev.olive.olivebakery.model.dto.ReservationSaveDto;
import com.dev.olive.olivebakery.model.entity.Bread;
import com.dev.olive.olivebakery.model.enums.BoardType;
import com.dev.olive.olivebakery.model.dto.BoardSaveDto;
import com.dev.olive.olivebakery.model.dto.BoardUpdateDto;
import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.service.BoardCrudService;
import com.dev.olive.olivebakery.service.BoardFindService;
import com.dev.olive.olivebakery.service.BreadFindService;
import com.dev.olive.olivebakery.service.ReservationService;
import com.dev.olive.olivebakery.service.UserCrudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by YoungMan on 2019-02-11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardCurdServiceTest {

    @Autowired
    BoardCrudService boardCrudService;

    @Autowired
    BoardFindService boardFindService;

    @Autowired
    UserCrudService userCrudService;

    @Autowired
    BreadFindService breadFindService;

    @Autowired
    ReservationService reservationService;


    @Test
    public void saveBoard() {
        BoardSaveDto boardSaveDto = BoardSaveDto.builder()
                .userId("testid")
                .context("test1")
                .title("test2")
                .boardType(BoardType.QUESTION)
                .build();

        boardCrudService.saveBoard(boardSaveDto);
    }

    @Test
    public void updateBoard() {
        BoardUpdateDto boardUpdateDto = BoardUpdateDto.builder()
                .boardId(5L)
                .context("update")
                .title("update2")
                .build();

        boardCrudService.updateBoard(boardUpdateDto);
    }

    @Test
    public void deleteBoard() {
        boardCrudService.deleteBoard(26L);
    }

    @Test
    public void getBoards() {
        Page<Board> noticeBoards = boardCrudService.getBoards(BoardType.NOTICE, 1);
        noticeBoards.forEach(s -> System.out.println(s.getBoardId()));

        System.out.println("====================================");

        Page<Board> questionBoards = boardCrudService.getBoards(BoardType.QUESTION, 2);
        questionBoards.forEach(s -> System.out.println(s.getBoardId()));
    }

    @Test
    public void boardFind() {
        List<Board> boards = boardFindService.findByUser("testid");
        System.out.println(boards.size());
    }

    @Test
    public void deleteUser() {
        userCrudService.deleteUser("testid2");
    }

    @Test
    public void searchBreads() {
        List<String> breads = new ArrayList<>();
        breads.add("소보로");
        breads.add("단팥빵");

        List<Bread> breadss = breadFindService.findsByNames(breads);
        System.out.println(breadss.get(0).getName());
        System.out.println(breadss.get(0).getDescription());
        System.out.println(breadss.get(1).getName());
    }

    @Test
    public void test() {
        Map<String, Integer> maps = new HashMap<>();
        maps.put("소보로",3);
        maps.put("단팥빵",2);

        List<String> list = new ArrayList<String>(maps.keySet());
        List<Integer> counts = new ArrayList<Integer>(maps.values());

        System.out.println(list.get(0) + "++++" + list.get(1));
        System.out.println(counts.get(0).toString() + "++++" + counts.get(1).toString());

    }

    @Test
    public void saveDtoToEntity() {
        //빵정보
        /*List<String> breads = new ArrayList<>();
        breads.add("소보로");
        breads.add("단팥빵");*/

        LinkedHashMap<String, Integer> maps = new LinkedHashMap<>();
        maps.put("소보로",1);
        maps.put("단팥빵",5);

        //가져갈 시간
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ReservationSaveDto reservationSaveDto = ReservationSaveDto.builder()
                .bringTime(timestamp)
                .userId("testid")
                .breadInfo(maps)
                .build();

        reservationService.saveReservation(reservationSaveDto);
    }
}
