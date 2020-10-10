package com.cdfg.selfmail.controller;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.SelfMailNotFoundException;
import com.cdfg.selfmail.pojo.dto.CardInfoDto;
import com.cdfg.selfmail.pojo.dto.UserAndOrderItemDto;
import com.cdfg.selfmail.pojo.until.Result;
import com.cdfg.selfmail.service.CountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cdfg.selfmail.pojo.until.Constant.*;
import static com.cdfg.selfmail.pojo.until.Constant.sucMsg;

/*
* 未邮寄提货单接口
* */

@RestController
@RequestMapping("/cdfg")
public class GetLotterryCountController {

    @Autowired
    private CountService countService=null;
    Logger logger = Logger.getLogger(GetLotterryCountController.class);

    @RequestMapping(value={"/getshoppinginfo"}, method = RequestMethod.POST,produces="text/html; charset=UTF-8")
    @ResponseBody
    public Result<UserAndOrderItemDto> cdfgLotrycount(@RequestBody CardInfoDto cardinfo) {
        String cardId = cardinfo.getCard_id();//证件ID
        String telNum = cardinfo.getTel_num();//电话

        int resultCode = 0;
        String msg = null;
        UserAndOrderItemDto orderItem;

        logger.info("未邮寄提货单接口收到的数据："+cardId+"@"+telNum);

        try{
            orderItem = countService.getShopingTime(cardinfo);
            logger.info("取到未邮寄提货单接口返回值："+orderItem);

            if (orderItem != null) {
                resultCode = sucCode;
                msg = sucMsg;
            }

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("未邮寄提货单接口返回值异常");
            throw new SelfMailNotFoundException(errCode,errMsg);
        }
        return new Result<UserAndOrderItemDto>(resultCode,msg,orderItem);
    }



}
