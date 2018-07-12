package com.sunwuyou.swymcx.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin
 * 2018/7/11.
 * content
 */

public class JSONUtil {
    public static boolean isJson(String paramString) {
        return (paramString != null) && (!TextUtils.isEmpty(paramString)) && ((paramString.startsWith("{")) || (paramString.startsWith("[")));
    }

    // json 转 list hashMap
    public static List<HashMap<String, String>> parse2ListMap(String paramString) {
        try {
            List<HashMap<String, String>> list_map = new ArrayList<HashMap<String, String>>();
            JSONArray localJSONArray = new JSONArray(paramString);

            for (int i = 0; i < localJSONArray.length(); i++) {
                list_map.add(parse2Map(localJSONArray.get(i).toString()));
            }
            return list_map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // json 转 hashMap
    public static HashMap<String, String> parse2Map(String paramString) {
        HashMap<String, String> localHashMap = new HashMap<String, String>();
        try {
            JSONObject localJSONObject = new JSONObject(paramString);
            Iterator<String> localIterator = localJSONObject.keys();
            while ((localIterator.hasNext())) {
                String str = (String) localIterator.next();
                localHashMap.put(str, localJSONObject.getString(str));
            }
            return localHashMap;
        } catch (JSONException localJSONException) {
            localJSONException.printStackTrace();
        }

        return null;
    }
}
