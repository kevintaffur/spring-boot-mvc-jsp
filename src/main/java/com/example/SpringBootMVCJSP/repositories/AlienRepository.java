package com.example.SpringBootMVCJSP.repositories;

import com.example.SpringBootMVCJSP.models.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlienRepository extends JpaRepository<Alien, Integer> {
    // Query DSL
    // List<Alien> findByNameOrderByIdDesc(String name);

    // JPQL
    @Query("from Alien where name=:name order by id desc")
    List<Alien> find(@Param("name") String name);
}
