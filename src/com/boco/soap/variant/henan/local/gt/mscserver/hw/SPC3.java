/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved.
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note:
 */
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.SPC16;

/**
 *
 *
 *
 *
 */
public class SPC3 extends VariantValueInvoke {

    private final static Logger LOGGER = LoggerFactory.getLogger(SPC3.class);

    private Map<String, String> map = null;
    private final Map<String, Map<String, String>> refrenceMap = new HashMap<String, Map<String, String>>();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com
     * .boco.soap.common.pojo.INeElement,
     * com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map,
     * java.lang.String)
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String busiStr = arg0.getPhysicalAddr();
        String e164No = data.get("E164").toString();
        /*if (busiStr.contains("商丘"))
        	return new String[] { "" };*/
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        if (!busiStr.equals("")) {
            String result1 = null;
            if (this.map.get(busiStr) != null) {
                String[] spcArr = this.map.get(busiStr).split(",");
                String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                result1 = SPC16.spc10to16(spcStr);
            }
            if ((result1 != null) && !result1.equals("")) {
                //连续号段
                Map<String, String> hlrMap = this.refrenceMap.get(data.get("BUSI_CITY").toString());
                if ((hlrMap != null) && hlrMap.containsKey(e164No)) {
                    if (StringUtils.isNotBlank(hlrMap.get(e164No))) {
                        LOGGER.info("连续号段且不为第一个，{}", e164No);
                        result1 = hlrMap.get(e164No);
                    } else {
                        String parentNo = e164No.substring(0, e164No.length() - 1);
                        LOGGER.info("连续号段且为第一个，{},父号码为：{},存入信令{}", e164No, parentNo, result1);
                        for (int i = 0; i < 10; i++) {
                            hlrMap.put(parentNo + i, result1);
                            this.refrenceMap.put(data.get("BUSI_CITY").toString(), hlrMap);
                        }
                    }
                }
                return new String[] { result1 };
            } else {
                return new String[] { "[NULL]" };
            }

        } else {

            return new String[] { "[NULL]" };
        }

    }

    private void getSequenceMap(String dbFile) {
        LOGGER.info("初始化连续号段Map...");
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT E164,BUSI_CITY FROM TCM_LOCAL_GTT_LIST ORDER BY BUSI_CITY,E164 ASC ";
        List<Map<String, ?>> searchList = utils.getLocalData(sql, dbFile);
        int count = 0;
        for (int i = 0; i < searchList.size(); i++) {
            Map<String, ?> curMap = searchList.get(i);
            String curValue = curMap.get("E164").toString();
            if (curValue.endsWith("0")) {
                count = 0;
                LOGGER.info("发现第一个为0的号段为：{" + curValue + "}");
                count++;
                continue;
            }
            if (count == 0) {
                continue;
            }
            if (((Long.parseLong(curValue) - Long.parseLong(searchList.get(i - 1).get("E164").toString())) == 1) && curMap.get("BUSI_CITY").equals(searchList.get(i - 1).get("BUSI_CITY").toString())) {
                count++;
                LOGGER.info("发现连续号段为：{}", curValue);
            } else {
                count = 0;
                LOGGER.info("发现不连续号段为：{}，计数置0", curValue);
            }
            if (count == 10) {
                LOGGER.info("发现10个连续号段为：{}", curValue);
                Map<String, String> tempMap = this.refrenceMap.get(curMap.get("BUSI_CITY").toString());
                if (tempMap == null) {
                    tempMap = new HashMap<String, String>();
                }
                for (int j = 0; j < 10; j++) {
                    tempMap.put(searchList.get(i - j).get("E164").toString(), "");
                    this.refrenceMap.put(curMap.get("BUSI_CITY").toString(), tempMap);
                }
                //this.refrenceMap.put(curValue.substring(0, curValue.length() - 1), "");
                count = 0;
            }
        }

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select COVER_CITY,STP1_SPC||','||STP2_SPC SPC from TCM_LOCAL_LSTP ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("COVER_CITY").toString(), temp.get("SPC").toString());

        }
    }

}
