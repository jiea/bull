package com.jiea.bull.service.impl;

import com.jiea.bull.dao.LogMapper;
import com.jiea.bull.domain.Log;
import com.jiea.bull.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int saveLog(Log log) {
        return logMapper.insert(log);
    }
}
