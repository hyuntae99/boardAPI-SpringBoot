package com.study.boardAPI.repository;

import com.study.boardAPI.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> { // JpaRepository<Entity타입, Primary Key의 타입>

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

}
