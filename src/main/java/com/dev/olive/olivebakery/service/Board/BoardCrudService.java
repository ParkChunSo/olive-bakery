package com.dev.olive.olivebakery.service.Board;

import com.dev.olive.olivebakery.model.enums.BoardType;
import com.dev.olive.olivebakery.model.dto.BoardSaveDto;
import com.dev.olive.olivebakery.model.dto.BoardUpdateDto;
import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.repository.BoardRepository;
import com.dev.olive.olivebakery.service.user.UserFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Service
public class BoardCrudService {

    private final BoardRepository boardRepository;
    private BoardFindService boardFindService;
    private UserFindService userFindService;

    public BoardCrudService(BoardRepository boardRepository, BoardFindService boardFindService, UserFindService userFindService) {
        this.boardRepository = boardRepository;
        this.boardFindService = boardFindService;
        this.userFindService = userFindService;
    }

    public void saveBoard(BoardSaveDto boardSaveDto) {
        Board board = convertSaveDtoToEntity(boardSaveDto);
        boardRepository.save(board);
    }

    public Page<Board> getBoards(BoardType boardType, int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, 10, Sort.Direction.DESC, "boardId");
        return boardRepository.findAll(pageRequest, boardType.toString());
    }

    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        Board board = convertUpdateDtoToEntity(boardUpdateDto);
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        Board board = boardFindService.findById(boardId);
        boardRepository.delete(board);
    }

    private Board convertSaveDtoToEntity(BoardSaveDto boardSaveDto) {
        User user = userFindService.findById(boardSaveDto.getUserId());

        return Board.builder()
                .context(boardSaveDto.getContext())
                .title(boardSaveDto.getTitle())
                .boardType(boardSaveDto.getBoardType().toString())
                .user(user)
                .build();
    }

    private Board convertUpdateDtoToEntity(BoardUpdateDto boardUpdateDto) {
        Board board = boardFindService.findById(boardUpdateDto.getBoardId());
        board.setTitle(boardUpdateDto.getTitle());
        board.setContext(boardUpdateDto.getContext());

        return board;
    }


}
