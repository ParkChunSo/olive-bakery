package com.dev.olive.olivebakery.Board;

import com.dev.olive.olivebakery.model.dto.BoardDto;
import com.dev.olive.olivebakery.model.dto.ReservationDto;
import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.entity.Bread;
import com.dev.olive.olivebakery.model.enums.BoardType;
import com.dev.olive.olivebakery.service.BoardService;
import com.dev.olive.olivebakery.service.BreadService;
import com.dev.olive.olivebakery.service.ReservationService;
import com.dev.olive.olivebakery.service.UserService;
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
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    BreadService breadService;

    @Autowired
    ReservationService reservationService;

    /*
     * 완료
     */
    @Test
    public void saveBoard() {
        BoardDto.Save saveDto = BoardDto.Save.builder()
                .userId("testid")
                .context("test_context")
                .title("test_title")
                .boardType("공지")
                .build();

        boardService.saveBoard(saveDto);
    }

    /*
     * 완료
     */
    @Test
    public void updateBoard() {
        BoardDto.Update updateDto = BoardDto.Update.builder()
                .boardId(1L)
                .context("update_context")
                .title("update_title")
                .build();

        boardService.updateBoard(updateDto);
    }

    /*
     * 완료
     */
    @Test
    public void deleteBoard() {
        boardService.deleteBoard(1L);
    }

    /*
     * 완료
     */
    @Test
    public void getBoards() {
        Page<Board> noticeBoards = boardService.getBoards(BoardType.NOTICE, 1);
        noticeBoards.forEach(s -> System.out.println(s.getBoardId()));

        System.out.println("======================----------==============");

        Page<Board> questionBoards = boardService.getBoards(BoardType.QUESTION, 2);
        questionBoards.forEach(s -> System.out.println(s.getBoardId()));
    }

    /*
     * 완료
     */
    @Test
    public void boardFind() {
        List<Board> boards = boardService.findByUser("testid");
        System.out.println(boards.size());
    }

    @Test
    public void deleteUser() {
        userService.deleteUser("testid");
    }

    @Test
    public void searchBreads() {
        List<String> breads = new ArrayList<>();
        breads.add("소보로");
        breads.add("단팥빵");

        List<Bread> breadss = breadService.findsByNames(breads);
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

    /*
     * 예약
     */
    @Test
    public void saveReservation() {

        LinkedHashMap<String, Integer> maps = new LinkedHashMap<>();
        maps.put("소보로",1);//소보로 1개구매
        maps.put("단팥빵",5);//단팥빵

        /*
         * test 시 가져갈 시간 임의생성
         */
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ReservationDto.Save saveDto = ReservationDto.Save.builder()
                .bringTime(timestamp)
                .userId("testid")
                .breadInfo(maps)
                .build();

        reservationService.saveReservation(saveDto);
    }
}