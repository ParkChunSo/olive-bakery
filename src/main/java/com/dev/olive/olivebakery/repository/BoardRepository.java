package com.dev.olive.olivebakery.repository;

import com.dev.olive.olivebakery.model.entity.Board;
import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.model.enums.BoardType;
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

    Optional <List<Board>> findByUser(@Param("user") User user);

    @Query("select b from Board b where b.boardType = :boardType")
    Page<Board> findAll(@Param("pageRequest") Pageable pageable, @Param("boardType") BoardType boardType);
}
