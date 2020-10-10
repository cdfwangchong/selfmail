package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.PostAddressDto;

public interface PostaddressDao {
    int insert(PostAddressDto record);

    int insertSelective(PostAddressDto record);
}