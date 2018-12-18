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
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * @author Chang
 * @description 获取除了铁通的呼叫源
 */
public class CSCNAMEWTLTTTDXDXC extends VariantValueInvoke {
	private Map<String, String> map=null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("BUSI_CITY");
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String gtrcStr = map.get(o.toString()) + "WT,"+map.get(o.toString()) + "TT,"+map.get(o.toString()) + "LT,"+map.get(o.toString()) + "DX,"+map.get(o.toString()) + "DXC";
		return gtrcStr.split(",");
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME")
					.toString());

		}
	}
}