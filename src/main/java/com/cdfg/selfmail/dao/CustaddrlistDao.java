package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.Custaddrlist;
import com.cdfg.selfmail.pojo.dto.InsertCustAddrDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustaddrlistDao {

    int insert(List<InsertCustAddrDto> record);

    Custaddrlist selectByPrimaryKey(BigDecimal seqno);

}