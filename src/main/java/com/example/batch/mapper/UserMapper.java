package com.example.batch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.batch.domain.User;

@Mapper
public interface UserMapper {
    List<User> findEligibleUsers();

    void updateLastNotified(Long id);
}
