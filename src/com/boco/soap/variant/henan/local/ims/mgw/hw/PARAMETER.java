package com.boco.soap.variant.henan.local.ims.mgw.hw;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;

public class PARAMETER
  extends VariantValueInvoke
{
  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3)
  {
    Object objNeType = data.get("NE_TYPE");
    Object objActTbName = data.get("ACT_TBNAME");
    Object objVendorName = data.get("VENDOR_NAME");
    String result = "[NULL]";
    if ((objNeType.toString().trim().toUpperCase().equals("MGW")) && 
      (objActTbName.toString().trim().toUpperCase().equals("PRITODSCP")) && 
      (objVendorName.toString().trim().toUpperCase().equals("华为")))
    {
      result = data.get("PARAM_NAME") == null ? "未配置参数值" : data.get("PARAM_NAME").toString().trim();
      return new String[] { result };
    }
    return null;
  }
}