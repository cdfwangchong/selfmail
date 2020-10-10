package com.cdfg.selfmail.controller;


import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.SelfMailNotFoundException;
import com.cdfg.selfmail.pojo.dto.PickBillDto;
import com.cdfg.selfmail.pojo.dto.PickNumDto;
import com.cdfg.selfmail.pojo.until.Result;
import com.cdfg.selfmail.service.SellDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cdfg.selfmail.pojo.until.Constant.*;
import static com.cdfg.selfmail.pojo.until.Constant.errMsg;
/*
* 提货单明细接口
* */
@RestController
@RequestMapping("/cdfg")
public class QrySellDetailController {

    @Autowired
    private SellDetailService selldetailService=null;
    Logger logger = Logger.getLogger(QrySellDetailController.class);

    @RequestMapping(value={"/qryselldetail"}, method = RequestMethod.POST,produces="text/html; charset=UTF-8")
    @ResponseBody
    public Result<List<PickBillDto>> qryselldetail(@RequestBody PickNumDto picknumdto) {
        String pick_num = picknumdto.getPick_num();//提货单号

        int resultCode = 0;
        String msg = null;
        List<PickBillDto> orderItem;

        logger.info("提货单明细接口收到的数据："+pick_num);

        try{
            orderItem = selldetailService.getselldetail(picknumdto);
            logger.info("取到提货单明细接口返回值："+orderItem);

            if (orderItem != null) {
                resultCode = sucCode;
                msg = sucMsg;
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("提货单明细接口返回值异常");
            throw new SelfMailNotFoundException(errCode,errMsg);
        }
        return new Result<List<PickBillDto>>(resultCode,msg,orderItem);
    }
}
