package com.cdfg.selfmail.service.impl;

import com.cdfg.selfmail.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeDao codeDao=null;

   @Override
    public Map<String,String> getVercoode(Map<String,String> param) {
        return codeDao.getVercode(param);
    }
}
