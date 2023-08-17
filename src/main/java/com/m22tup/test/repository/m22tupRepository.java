package com.m22tup.test.repository;


import com.m22tup.test.entity.m22tupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface m22tupRepository extends JpaRepository<m22tupEntity,Long> {


}
