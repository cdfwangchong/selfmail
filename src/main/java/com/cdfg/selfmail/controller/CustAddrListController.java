package com.cdfg.selfmail.controller;



import com.cdfg.selfmail.pojo.dto.InsertCustAddrAndListDto;
import com.cdfg.selfmail.pojo.until.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/cdfg")

public class CustAddrListController {

    /*
     * 商品邮寄接口
     * */
    @PostMapping("insertCustAddr")
    public Result<String> insertCustAddrList(InsertCustAddrAndListDto ica){



        return null;
    }
}
