package com.cdfg.selfmail.service;

import com.cdfg.selfmail.pojo.dto.PickBillDto;
import com.cdfg.selfmail.pojo.dto.PickNumDto;

import java.util.List;
import java.util.Map;

public interface SellDetailService {

    public List<PickBillDto> getselldetail(PickNumDto picknumdto);
}
