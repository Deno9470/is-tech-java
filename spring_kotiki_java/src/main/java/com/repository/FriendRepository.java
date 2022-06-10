package com.repository;

import com.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query("FROM Cat")
    List<Friend> findAll();
}
