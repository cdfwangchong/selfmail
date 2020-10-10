package com.cdfg.selfmail.service.impl;

import com.cdfg.selfmail.dao.SellDetailDao;
import com.cdfg.selfmail.pojo.dto.OrderListDto;
import com.cdfg.selfmail.pojo.dto.PickBillDto;
import com.cdfg.selfmail.pojo.dto.PickNumDto;
import com.cdfg.selfmail.service.SellDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SellDetailServiceImpl implements SellDetailService {
    @Autowired
    SellDetailDao selldetaildao;

    @Override
    public List<PickBillDto> getselldetail(PickNumDto picknumdto) {
        return selldetaildao.QrySellDetail(picknumdto);
    }
}
