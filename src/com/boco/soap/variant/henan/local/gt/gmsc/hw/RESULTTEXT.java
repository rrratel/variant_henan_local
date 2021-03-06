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
package com.boco.soap.variant.henan.local.gt.gmsc.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt gmsc�˾� ��Ϊ   sccpgt E164
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class RESULTTEXT extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = arg0.getVersion();
		String sDest="SPC+原GT";
		if(o.toString().equals("MSOFTX_V200R009C02"))
			sDest="SPC+OLD GT";
		
		
		return new String[]{sDest};
		
	}
}