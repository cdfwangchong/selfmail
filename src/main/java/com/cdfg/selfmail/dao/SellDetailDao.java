package com.cdfg.selfmail.dao;

import com.cdfg.selfmail.pojo.dto.OrderListDto;
import com.cdfg.selfmail.pojo.dto.PickBillDto;
import com.cdfg.selfmail.pojo.dto.PickNumDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SellDetailDao {
    List<PickBillDto> QrySellDetail(PickNumDto picknumdto);
}
