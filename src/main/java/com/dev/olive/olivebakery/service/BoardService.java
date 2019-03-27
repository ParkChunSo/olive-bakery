package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.CommentDto;
import com.dev.olive.olivebakery.domain.entity.Comment;
import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.dto.BoardDto;
import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final SignService signService;

    public BoardService(BoardRepository boardRepository, SignService signService) {
        this.boardRepository = boardRepository;
        this.signService = signService;
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new UserDefineException("해당 번호의 게시글은 존재하지 않습니다."));
    }

    /*
     * 공지 or 질문 게시판 타입에 맞게 페이징
     */
    public Page<Board> getBoards(BoardType boardType, int pageNum) {
//        PageRequest pageRequest = new PageRequest(pageNum - 1, 10, Sort.Direction.DESC, "boardId");
//        return boardRepository.findAll(pageRequest, boardType);
        Pageable pageable = PageRequest.of(1, 10, new Sort(Sort.Direction.DESC, "boardId"));
        return boardRepository.findAll(pageable);
    }

    public void saveBoard(BoardDto.Save saveDto) {
        Member member = signService.findById(saveDto.getUserId());

        Board board = saveDto.toEntity(member);
        boardRepository.save(board);
    }

    public void updateBoard(BoardDto.Update updateDto) {
        Board board = findBoardById(updateDto.getBoardId());
        board.updateBoard(updateDto);
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        Board board = findBoardById(boardId);
        boardRepository.delete(board);
    }

    public void saveComment(CommentDto.Save comment) {
        Board board = findBoardById(Long.valueOf(comment.getBoardId()));
        List<Comment> comments = board.getComments();
        comments.add(comment.toEntity());
        board.setComments(comments);
        boardRepository.save(board);
    }

    public void updateComment(CommentDto.Update updateComment) {
        Board board = findBoardById(Long.valueOf(updateComment.getBoardId()));
        List<Comment> comments = board.getComments();
        comments.forEach(comment->{
            if(Long.valueOf(updateComment.getCommentId()).equals(comment.getCommentId())){
                if(!comment.getName().equals(updateComment.getName()))
                    throw new UserDefineException("수정은 작성자만 가능합니다.");

                comment.setContent(updateComment.getContent());
            }
        });
        board.setComments(comments);
        boardRepository.save(board);
    }

}
