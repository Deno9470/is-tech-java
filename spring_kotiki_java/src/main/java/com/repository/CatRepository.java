package com.repository;

import com.entity.Owner;
import com.enums.CatColor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Cat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {


    List<Cat> findAllByColor(CatColor color);

    List<Cat> findAllByName(String name);


    @Query("select e from Cat e where e.passportOwner = :code")
    List<Cat> findOwnerCats(@Param("code") int code);

    List<Cat> findAllByOwner_id(int id);

    @Query("FROM CatEntity t")
    List<Cat> getAll();
}
