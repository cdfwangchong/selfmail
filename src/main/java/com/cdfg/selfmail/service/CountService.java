package com.cdfg.selfmail.service;

import com.cdfg.selfmail.pojo.dto.CardInfoDto;
import com.cdfg.selfmail.pojo.dto.UserAndOrderItemDto;

public interface CountService {

    public UserAndOrderItemDto getShopingTime(CardInfoDto cardinfo);
}
