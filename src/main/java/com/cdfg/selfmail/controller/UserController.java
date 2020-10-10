package com.cdfg.selfmail.controller;

import java.util.HashMap;
import java.util.Map;

import com.cdfg.selfmail.pojo.until.AuthUtil;
import com.cdfg.selfmail.pojo.until.Login;
import com.cdfg.selfmail.pojo.until.RegUser;
import com.cdfg.selfmail.pojo.until.Vercode;
import com.cdfg.selfmail.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;

import com.alibaba.fastjson.JSONObject;
;
/*
 * project name :项目名称我也不知道，还没取
 * for:用户注册
 * to：王冲
 * time：2020-10-10
 *
 * */

@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService registerservice=null;
    Logger logger = Logger.getLogger(UserController.class);
    //注册
    @RequestMapping(value={"/getregister.do"}, method = RequestMethod.POST)
    @ResponseBody
    public String cdfgdeposit(@RequestBody RegUser reguser) throws NullPointerException {

        String id_type = reguser.getId_type();//证件类型
        String card_id = reguser.getCard_id();//证件号
        String tel_num = reguser.getTel_num();//手机号
        String open_id = reguser.getOpen_id();//密码

        logger.info("取到注册用户信息"+id_type+card_id+tel_num+open_id);

        Map<String,String> param=new HashMap<String,String>();
        param.put("id_type", id_type);
        param.put("card_id", card_id);
        param.put("tel_num", tel_num);
        param.put("open_id", open_id);

        JSONObject json = new JSONObject();

        try {

            registerservice.getCoupon(param);

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取注册用户信息存储过程的返回值异常");
            return json.put("ret_flag", "-1").toString();
        }

        json.put("ret_flag", param.get("ret_flag"));
        //写入日志
        logger.info("ret_flag（返回标志）:"+ param.get("ret_flag"));
        return json.toString();
    }

    //登录
    @RequestMapping(value={"/getlogin.do"}, method = RequestMethod.POST,produces="json/html; charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody Login login) {

        String open_id = login.getOpen_id();//密码

        logger.info("取到登录用户信息"+open_id);

        Map<String,String> param=new HashMap<String,String>();
        param.put("open_id", open_id);

        String user_name = null;
        String card_id = null;
        String ret_flag = null;
        String tel_num = null;
        String id_type = null;

        JSONObject json = new JSONObject();

        try {

            registerservice.login(param);
            //获得返回值
            ret_flag=param.get("ret_flag");
            card_id=param.get("card_id");
            tel_num=param.get("tel_num");
            user_name=param.get("user_name");
            id_type=param.get("id_type");

            if (ret_flag.equals("2002")) {
                ret_flag="0";
                json.put("ret_flag", ret_flag);
                json.put("user_name", "");
                json.put("card_id","");
                json.put("tel_num", "");
                json.put("id_type", "");

            }else {
                ret_flag="1";
                json.put("ret_flag", ret_flag);
                json.put("user_name", user_name);
                json.put("card_id",card_id);
                json.put("tel_num", tel_num);
                json.put("id_type", id_type);
            }

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("取到登录用户信息存储过程的返回值异常");
            ret_flag="-1";
            json.put("ret_flag", "-1");
            json.put("user_name", null);
            return json.toString();
        }

//		System.out.println(user_name);
        logger.info("获取登录信息返回标志："+ret_flag+"用户名："+user_name+"身份证："+card_id+"电话："+tel_num);
        return json.toString();
    }

    @RequestMapping(value={"/getvercode.do"}, method = RequestMethod.POST)
    @ResponseBody
    public String cdfgdeposit(@RequestBody Vercode vercode) {

        String tel_num = vercode.getTel_num();//取到电话代码
        String ver_code = vercode.getVer_code();//验证码
        String ret_flag=null;
        JSONObject json = new JSONObject();

        try{
            //获取到客人最新的购物时间
            Map<String,String> param=new HashMap<String,String>();
            param.put("ver_code", ver_code);
            param.put("tel_num", tel_num);

            logger.info("获取到客人电话号码："+tel_num+" 验证码："+ver_code);

            registerservice.getVercode(param);
            //获得返回值
            ret_flag=param.get("ret_info");//身份证

            if (ret_flag == null) {
                logger.info(tel_num+"验证码写入后获取存储过程的返回值异常");
            }

            //组装报文,返回
            json.put("ret_flag",ret_flag);

            //写入日志
            logger.info("ret_flag（返回标志）:"+ret_flag);

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取存储过程的返回值异常");
            json.put("ret_flag","0");
            return json.toString();
        }

        return json.toString();
    }

    @RequestMapping(value={"/getopenid.do"}, method = RequestMethod.POST)
    @ResponseBody
    public String wechatopenid(@RequestBody Vercode code) {

        String vercode = code.getVer_code();

        logger.info("取到code"+vercode);
        String ret_flag = null;
        String card_id = null;
        String tel_num = null;
        String user_name = null;

        JSONObject jsonObject = new JSONObject();
        JSONObject json = new JSONObject();
        try{
            jsonObject = AuthUtil.doGetJson(vercode);

            //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
            if (jsonObject.containsKey("access_token")) {
                String token =  jsonObject.getString("access_token");
                String openid = jsonObject.getString("openid");

                logger.info("token:"+token+";"+"openid:"+openid);

                //获取到客人信息
                Map<String,String> param=new HashMap<String,String>();
                param.put("open_id", openid);

                logger.info("获取到客人openid："+openid);

                registerservice.weChat(param);
                //获得返回值
                ret_flag=param.get("ret_flag");
                card_id=param.get("card_id");
                tel_num=param.get("tel_num");
                user_name=param.get("user_name");
                //写入日志
                logger.info("ret_result（返回结果）:"+ret_flag+card_id+tel_num+openid+user_name);

                json.put("ret_flag",ret_flag);//获取openid失败
                json.put("open_id",openid);
                json.put("tel_num",tel_num);
                json.put("card_id",card_id);
                json.put("user_name", user_name);

            }else {
                String errcode =  jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");

                logger.info("errcode:"+errcode+";"+"errmsg:"+errmsg);
                json.put("ret_flag","2003");//获取openid失败
                json.put("open_id","");
                json.put("tel_num","");
                json.put("card_id","");
                json.put("user_name","");

                logger.info("ret_result（返回结果）:"+"errcode:"+errcode+"errmsg:"+errmsg);
                return json.toString();
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取openid存储过程的返回值异常");
            json.put("ret_flag","2004");//获取存储过程的返回值异常
            json.put("open_id","");
            json.put("tel_num","");
            json.put("card_id","");
            json.put("user_name","");

            return json.toString();
        }

        return json.toString();
    }
}
