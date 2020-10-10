package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.GoodsMailDto;

public interface GoodsmailDao {
    int deleteByPrimaryKey(String postId);

    int insert(GoodsMailDto record);

    int insertSelective(GoodsMailDto record);

    GoodsMailDto selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(GoodsMailDto record);

    int updateByPrimaryKey(GoodsMailDto record);
}