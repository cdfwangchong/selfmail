package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.Userlist;
import org.springframework.stereotype.Repository;

@Repository
public interface UserlistDao {

    Userlist selectByPrimaryKey(String openId);

}