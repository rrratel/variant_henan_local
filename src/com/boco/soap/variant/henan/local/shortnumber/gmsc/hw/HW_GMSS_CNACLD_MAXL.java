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

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 最大位长（MAXL）
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class HW_GMSS_CNACLD_MAXL extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("MAXL");
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			result = o.toString();
		}
		
		return new String[]{result};
		
	}
}