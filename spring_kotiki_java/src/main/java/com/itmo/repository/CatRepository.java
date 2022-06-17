package com.itmo.repository;

import com.itmo.enums.CatColor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.itmo.entity.Cat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatRepository extends JpaRepository<Cat, Integer> {


    List<Cat> findAllByColor(CatColor color);

    List<Cat> findAllByName(String name);

    @Query("FROM Cat t")
    List<Cat> getAll();
}
