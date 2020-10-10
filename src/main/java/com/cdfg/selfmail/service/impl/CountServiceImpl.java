package com.cdfg.selfmail.service.impl;

import com.cdfg.selfmail.dao.CountDao;
import com.cdfg.selfmail.pojo.dto.CardInfoDto;
import com.cdfg.selfmail.pojo.dto.UserAndOrderItemDto;
import com.cdfg.selfmail.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService {
    @Autowired
    private CountDao countDao=null;

    @Override
    public UserAndOrderItemDto getShopingTime(CardInfoDto cardinfo) {
        return countDao.getLotrycount(cardinfo);
    }
}
