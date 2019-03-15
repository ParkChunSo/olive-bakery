package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.BoardDto;
import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YoungMan on 2019-02-13.
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
    public void saveBoard(@RequestBody BoardDto.Save saveDto) {
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
