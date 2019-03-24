package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BoardDto;
import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 * 1. 게시물 하나씩 가져오기.
 * 2. 게시물
 */

@RestController
@RequestMapping(value = "/olive/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{type}/page/{num}")
    public Page<Board> getBoards(@PathVariable("type") BoardType boardType, @PathVariable("num") int pageNum) {
        return boardService.getBoards(boardType, pageNum);
    }

    @PostMapping
    public void saveBoard(@RequestBody BoardDto.SaveDto saveDto) {
        boardService.saveBoard(saveDto);
    }

    @PutMapping
    public void updateBoard(@RequestBody BoardDto.Update updateDto) {
        boardService.updateBoard(updateDto);
    }

    @DeleteMapping("/{num}")
    public void deleteBoard(@PathVariable("num") Long boardId) {
        boardService.deleteBoard(boardId);
    }



}
