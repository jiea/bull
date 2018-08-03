package com.jiea.bull.dao;

import com.jiea.bull.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    Set<String> getPermsByUserId(Long userId);

    Set<String> getAllPerms();

    Set<String> getRolesByUserId(Long userId);
}