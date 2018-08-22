package com.jiea.bull.dao;

import com.jiea.bull.domain.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}