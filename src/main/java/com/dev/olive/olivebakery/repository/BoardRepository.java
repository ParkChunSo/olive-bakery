package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.domain.entity.Board;
import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.domain.enums.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-11.
 */

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitle(String title, Pageable pageable);




/*    Optional<List<Board>> findByMember (@Param("member") Member member);

    @Query("select b from Board b where b.boardType = :boardType")
    Page<Board> findAll(@Param("pageRequest") Pageable pageable, @Param("boardType") BoardType boardType);*/
}
