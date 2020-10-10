package com.cdfg.selfmail.pojo.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * POSTADDRESS
 * @author 
 */
@Data
public class PostAddressDto implements Serializable {
    /**
     * 收件人
     */
    private String consignee;

    /**
     * 电话号码
     */
    private String telNum;

    /**
     * 证件号
     */
    private String cardId;

    /**
     * 收件地址
     */
    private String postAddress;

    /**
     * 备用一
     */
    private String str1;

    private static final long serialVersionUID = 1L;
}