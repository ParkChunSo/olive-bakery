package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.BoardType;
import com.dev.olive.olivebakery.repository.BoardRepository;
import com.dev.olive.olivebakery.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertBoard(){
        List<Board> boards = new ArrayList<>();
        for(int i = 1 ; i<=30 ; i++){
            if(i%5 == 0){
                boards.add(
                        Board.builder()
                                .title(i + "번 게시물")
                                .context(i+ "번째 게시물입니다.")
                                .boardType(BoardType.BOARD)
                                .isSecret(false)
                                .isNotice(true)
                                .member(memberRepository.findByEmail("chunso@email.com").get())
                                .build()
                );
                continue;
            }
            boards.add(
                Board.builder()
                        .title(i + "번 게시물")
                        .context(i+ "번째 게시물입니다.")
                        .boardType(BoardType.BOARD)
                        .isSecret(false)
                        .isNotice(false)
                        .member(memberRepository.findByEmail("chunso@email.com").get())
                        .build()
            );
        }
        boardRepository.saveAll(boards);

    }

}