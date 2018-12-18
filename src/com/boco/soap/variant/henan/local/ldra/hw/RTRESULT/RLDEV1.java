package com.boco.soap.variant.henan.local.ldra.hw.RTRESULT;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RLDEV1 extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:35:43
	 * RTRESULT-RLDEV1:中继设备1
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("RLDEV1");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}
