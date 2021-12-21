package org.spoto.utils;

import com.alibaba.fastjson.JSONObject;

public class WebUtils {
    public static JSONObject returnData(Object obj) {
        JSONObject data = (JSONObject) JSONObject.toJSON(obj);
        return data;
    }
}
