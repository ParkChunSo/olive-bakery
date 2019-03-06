package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.model.dto.BoardDto;
import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.enums.BoardType;
import com.dev.olive.olivebakery.service.BoardCrudService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YoungMan on 2019-02-13.
 */

@RestController
@RequestMapping(value = "/olive/board")
public class BoardController {

    private BoardCrudService boardCrudService;

    public BoardController(BoardCrudService boardCrudService) {
        this.boardCrudService = boardCrudService;
    }

    @PostMapping
    public void saveBoard(@RequestBody BoardDto.Save saveDto) {
        boardCrudService.saveBoard(saveDto);
    }

    @GetMapping("/{type}/page/{num}")
    public Page<Board> getBoards(@PathVariable("type") BoardType boardType, @PathVariable("num") int pageNum) {
        return boardCrudService.getBoards(boardType, pageNum);
    }

    @PutMapping
    public void updateBoard(@RequestBody BoardDto.Update updateDto) {
        boardCrudService.updateBoard(updateDto);
    }

    @DeleteMapping("/{num}")
    public void deleteBoard(@PathVariable("num") Long boardId) {
        boardCrudService.deleteBoard(boardId);
    }







}
