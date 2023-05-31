package com.fate.poitl.utils;

import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.data.Charts;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import java.util.*;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/30 10:19
 */
public class ReportChartUtil {

    /**
     * 创建饼图
     * @param label 图表可选文字
     * @param chartTitle 图表标题
     * @param dataMap 原始数据
     * @return
     */
    public static KeyValue<String, Object> createPieChart(String label, String chartTitle, Map<String, Number> dataMap) {
       KeyValue<String[], Number[]> keyValue = convertPieData(dataMap);
       return new DefaultKeyValue<>(label, Charts.ofPie(chartTitle, keyValue.getKey()).series("categories", keyValue.getValue()).create());
    }


    /**
     * 柱状图/条形图
     * @param label 图表可选文字
     * @param chartTitle 图表标题
     * @param categories 图表柱子分类
     * @param linkedHashMap 图表原始数据
     * @return
     */
    public static KeyValue<String, Object> createColumnChart(String label, String chartTitle, String[] categories, LinkedHashMap<String, JSONObject> linkedHashMap) {
        Charts.ChartMultis chartMul = Charts.ofArea(chartTitle, categories);
        convertData(chartMul, categories, linkedHashMap);
        return new DefaultKeyValue<>(label, chartMul.create());
    }

    /**
     * 折线图
     * @param label 图表可选文字
     * @param chartTitle 图表标题
     * @param categories 图表柱子分类
     * @param linkedHashMap 图表原始数据
     * @return
     */
    public static KeyValue<String, Object> createLineChart(String label, String chartTitle, String[] categories, LinkedHashMap<String, JSONObject> linkedHashMap) {
        Charts.ChartMultis chartMul = Charts.ofLine(chartTitle, categories);
        convertData(chartMul, categories, linkedHashMap);
        return new DefaultKeyValue<>(label, chartMul.create());
    }

    /**
     * 转换原始数据
     * @param map 数据
     * @return
     */
    private static KeyValue<String[], Number[]> convertPieData(Map<String, Number> map) {
        Set<String> stringSet = map.keySet();
        String[] strings = new String[stringSet.size()];
        Number[] doubles = new Number[stringSet.size()];
        int i = 0;
        for (String key : map.keySet()) {
            strings[i] = key;
            doubles[i] = map.get(key);
            i ++;
        }
        return new DefaultKeyValue<>(strings, doubles);
    }

    public static void convertData(Charts.ChartMultis chartMul, String[] categories, LinkedHashMap<String, JSONObject> linkedHashMap) {
        for (String key : linkedHashMap.keySet()) {
            JSONObject object = linkedHashMap.get(key);
            List<Number> list = new ArrayList<>();
            for (String category : categories) {
                Number o = (Number) object.get(category);
                list.add(o == null ? 0 : o);
            }
            chartMul.addSeries(key, list.toArray(new Number[0]));
        }
    }
}
