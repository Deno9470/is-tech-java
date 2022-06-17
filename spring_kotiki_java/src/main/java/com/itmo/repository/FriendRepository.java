package com.itmo.repository;

import com.itmo.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query("FROM Friend ")
    List<Friend> findAll();

}
