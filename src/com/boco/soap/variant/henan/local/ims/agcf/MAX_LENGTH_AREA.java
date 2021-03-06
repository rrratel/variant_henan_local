package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MAX_LENGTH_AREA extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        if (data.get("FUNCTION_CODE").toString().equals("禁止主叫呼入")) {
            return new String[] { "255" };
        }
        return new String[] { String.valueOf(Integer.valueOf(data.get("MAXIMUM_LENGTH").toString()) + 4) };
    }
}
