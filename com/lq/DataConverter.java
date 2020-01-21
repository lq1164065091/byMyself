package com.lq;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataConverter {

    /**
     *
     * @param dataObjs 开发者自己定义的数据对象
     * @param clazz  数据对象的class对象
     * @return
     */
    public static List< Map<String, ?>> ObjToDataMap(List dataObjs,Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        ArrayList<Map<String, ?>> mapArrayList = new ArrayList<>();
        try {
            for (Object dataObj : dataObjs) {
                HashMap<String, Object> dataMap = new HashMap<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    dataMap.put(field.getName(),field.get(dataObj));
                }
                mapArrayList.add(dataMap);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return mapArrayList;
    }



}
