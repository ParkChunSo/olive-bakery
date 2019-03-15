package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.dto.BoardDto;
import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-03-05.
 */

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final SignService signService;

    public BoardService(BoardRepository boardRepository, SignService signService) {
        this.boardRepository = boardRepository;
        this.signService = signService;
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new UserDefineException("해당 번호의 게시글이 없습니다."));
    }

    public List<Board> findByUser(String userId) {
        Member member = signService.findById(userId);
        return boardRepository.findByMember(member)
                .orElseThrow(() -> new UserDefineException("해당 유저의 게시글이 없습니다."));
    }

    /*
     * 공지 or 질문 게시판 타입에 맞게 페이징
     */
    public Page<Board> getBoards(BoardType boardType, int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, 10, Sort.Direction.DESC, "boardId");
        return boardRepository.findAll(pageRequest, boardType);
    }

    public void saveBoard(BoardDto.Save saveDto) {
        Member member = signService.findById(saveDto.getUserId());

        Board board = saveDto.toEntity(member);
        boardRepository.save(board);
    }

    public void updateBoard(BoardDto.Update updateDto) {
        Board board = findById(updateDto.getBoardId());
        board.updateBoard(updateDto);
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        Board board = findById(boardId);
        boardRepository.delete(board);
    }
}
