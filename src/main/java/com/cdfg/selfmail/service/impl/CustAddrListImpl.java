package com.cdfg.selfmail.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.SelfMailNotFoundException;
import com.cdfg.selfmail.dao.CustaddrlistDao;
import com.cdfg.selfmail.dao.UserlistDao;
import com.cdfg.selfmail.pojo.dto.InsertCustAddrAndListDto;
import com.cdfg.selfmail.pojo.dto.InsertCustAddrDto;
import com.cdfg.selfmail.pojo.dto.PostItemDto;
import com.cdfg.selfmail.pojo.dto.Userlist;
import com.cdfg.selfmail.service.CustAddrListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdfg.selfmail.pojo.until.Constant.*;

@Service
public class CustAddrListImpl implements CustAddrListService {
    @Autowired
    UserlistDao ulDao = null;

    @Autowired
    CustaddrlistDao clDao = null;

    Logger logger = Logger.getLogger(CustAddrListImpl.class);

    @Override
    public boolean insertCustAddrList(InsertCustAddrAndListDto ica) {

        List<PostItemDto> PIlist = ica.getPostItemList();
        Map<String,String> Markmap = new HashMap<String,String>();
        Markmap.put("6868",null);
        Markmap.put("6921",null);
        Markmap.put("6922",null);
        Markmap.put("6127",null);

        //记录包裹数
        Map<String, Integer> pcgCntMap = new HashMap<String,Integer>();
        pcgCntMap.put("6868",0);
        pcgCntMap.put("6921",0);
        pcgCntMap.put("6922",0);
        pcgCntMap.put("6127",0);

        int pcgCntsy= 0;
        int pcgCntba= 0;
        int pcgCnthk= 0;
        int pcgCntml= 0;

        try {
            //拼接各门店的提货单号
            if (PIlist != null) {
                for (int i = 0; i < PIlist.size(); i++) {
                    PostItemDto pi = PIlist.get(i);
                    if ("6868".equals(pi.getMark()) || "6874".equals(pi.getMark())) {
                        String billno = Markmap.get("6868");
                        if (billno == null) {
                            Markmap.put("6868",pi.getPick_num());
                        }else {
                            billno = pi.getPick_num()+"|"+billno;
                            Markmap.put("6868",billno);
                        }
                        pcgCntsy++;
                        pcgCntMap.put("6868",pcgCntsy);

                    } else if("6921".equals(pi.getMark())) {
                        String billno = Markmap.get("6921");
                        if (billno == null) {
                            Markmap.put("6921",pi.getPick_num());
                        }else {
                            billno = pi.getPick_num()+"|"+billno;
                            Markmap.put("6921",billno);
                        }
                        pcgCntba++;
                        pcgCntMap.put("6921",pcgCntba);

                    }else if ("6922".equals(pi.getMark())) {
                        String billno = Markmap.get("6922");
                        if (billno == null) {
                            Markmap.put("6922",pi.getPick_num());
                        }else {
                            billno = pi.getPick_num()+"|"+billno;
                            Markmap.put("6922",billno);
                        }
                        pcgCnthk++;
                        pcgCntMap.put("6921",pcgCnthk);

                    }else if ("6127".equals(pi.getMark())){
                        String billno = Markmap.get("6127");
                        if (billno == null) {
                            Markmap.put("6127",pi.getPick_num());
                        }else {
                            billno = pi.getPick_num()+"|"+billno;
                            Markmap.put("6127",billno);
                        }
                        pcgCntml++;
                        pcgCntMap.put("6921",pcgCntml);
                    }else {
                        throw new SelfMailNotFoundException(errCode2,errMsg2);
                    }
                }
            }
            //查出顾客的购物卡号
            Userlist ul = ulDao.selectByPrimaryKey(ica.getOpen_id());
            String gwkh = ul.getIdseq();//客人的购物卡号
            List<InsertCustAddrDto> icadList = new ArrayList<InsertCustAddrDto>();

            //将有值的Markmap存入List
            for (Map.Entry<String,String> entry : Markmap.entrySet()) {
                if (entry.getValue() != null) {

                    InsertCustAddrDto icaDto = new InsertCustAddrDto();
                    icaDto.setRec_name(ica.getRec_name());
                    icaDto.setRec_phoneno(ica.getRec_phoneno());
                    icaDto.setRec_postcode(ica.getRec_postcode());
                    icaDto.setRec_provincename(ica.getRec_provincename());
                    icaDto.setRec_cityname(ica.getRec_cityname());
                    icaDto.setRec_areaname(ica.getRec_areaname());
                    icaDto.setRec_townname(ica.getRec_townname());
                    icaDto.setRec_detailaddress(ica.getRec_detailaddress());
                    icaDto.setType(ica.getType());
                    icaDto.setMarket(entry.getKey());
                    icaDto.setRec_xsdno(entry.getValue());
                    icaDto.setRec_pkgcnt(pcgCntMap.get(entry.getKey()));
                    icaDto.setGwkh(gwkh);
                    icaDto.setFlag("0");
                }
            }

            int ret = clDao.insert(icadList);
            if (ret != icadList.size()) {
                logger.error("List中的数据没有正确写入顾客地址列表");
                throw new SelfMailNotFoundException(errCode3,errMsg3);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("邮寄信息写入异常");
            throw new SelfMailNotFoundException(errCode3,errMsg3);
        } finally {
            pcgCntsy= 0;
            pcgCntba= 0;
            pcgCnthk= 0;
            pcgCntml= 0;
        }


        return true;
    }
}
