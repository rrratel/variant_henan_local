package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CSFBNOTPREIND2 extends VariantValueInvoke {

	@Override
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object objVendorName = data.get("VENDOR_NAME");
		String result ="[NULL]";

		if (objNeType.toString().trim().toUpperCase().equals("MME") 
				&& objVendorName.toString().trim().toUpperCase().equals("华为")&&
				(objActTbName.toString().trim().toUpperCase().indexOf("VOICEDEPLOY")>-1)) {
			if (objActTbName.toString().trim().toUpperCase().equals("VOICEDEPLOY_HOME_USER")) {
				result = "NO" ;
			}else if(objActTbName.toString().trim().toUpperCase().equals("VOICEDEPLOY_FOREIGN_USER")){
				result = "" ;
			}
		}
		return new String[]{result};
	}
}
