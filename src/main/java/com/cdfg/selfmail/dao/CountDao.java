package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.CardInfoDto;
import com.cdfg.selfmail.pojo.dto.UserAndOrderItemDto;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDao {
    UserAndOrderItemDto getLotrycount(CardInfoDto cardinfo);
}
