package com.m22tup.test.repository;

import com.m22tup.test.entity.UserEntity;
import com.m22tup.test.entity.m22tupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {
}
