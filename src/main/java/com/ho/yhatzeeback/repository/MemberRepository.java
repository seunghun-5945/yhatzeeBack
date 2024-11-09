package com.ho.yhatzeeback.repository;

import com.ho.yhatzeeback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
    @Query("SELECT u FROM Member u ORDER BY u.score DESC")
    List<Member> findTop10ByOrderByScoreDesc();
}