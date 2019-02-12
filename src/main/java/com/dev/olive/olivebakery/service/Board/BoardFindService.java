package com.dev.olive.olivebakery.service.Board;

import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.repository.BoardRepository;
import com.dev.olive.olivebakery.service.user.UserFindService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-11.
 */

@Service
public class BoardFindService {

    private final BoardRepository boardRepository;
    private UserFindService userFindService;

    public BoardFindService(BoardRepository boardRepository, UserFindService userFindServicel) {
        this.boardRepository = boardRepository;
        this.userFindService = userFindServicel;
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new UserDefineException("해당 번호의 게시글이 없습니다."));
    }

    public List<Board> findByUser(String userId) {
        User user = userFindService.findById(userId);
        return boardRepository.findByUser(user)
                .orElseThrow(() -> new UserDefineException("해당 유저의 게시글이 없습니다."));
    }

}
