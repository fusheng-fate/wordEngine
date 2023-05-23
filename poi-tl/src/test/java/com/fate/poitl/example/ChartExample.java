package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Charts;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author lgb
 * @Description 常用图表  (饼图 复合饼图 条形图 堆积条形图 柱状图 堆积柱状图 折线图)  ChartSingleSeriesRenderData  ChartMultiSeriesRenderData
 * @Date 2023/5/22 19:01
 */
@SpringBootTest
public class ChartExample {

    /**
     * 饼图
     * @throws IOException
     */
    @Test
    public void testChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("pieChart",
                        Charts.ofPie("简单饼状图测试", new String[] { "a","b","c","d","e" })
                                .series("SPP", new Number[] { 10, 20, 50, 52, 15 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }

    /**
     * 复合饼图
     * @throws IOException
     */
    @Test
    public void testCombineChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("combineChart",
                        Charts.ofPie("复合饼状图测试", new String[] { "a","b","c","d","e" })
                                .series("SPP", new Number[] { 200, 50, 52, 2, 1 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }


    /**
     * 簇状柱状图
     * @throws IOException
     */
    @Test
    public void testColumnBarChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("columnBarChart",
                        Charts.ofArea("簇状柱状图测试", new String[] { "a","b","c","d","e" })
                                .addSeries("SPP", new Number[] { 20, 50, 52, 3, 1 })
                                .addSeries("SPP1", new Number[] { 20, 10, 52, 4, 0 })
                                .addSeries("SPP2", new Number[] { 80, 40, 22, 2, 1 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }

    /**
     * 堆叠柱状图
     * @throws IOException
     */
    @Test
    public void testStackBarChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("stackBarChart",
                        Charts.ofBar("堆叠柱状图测试", new String[] { "a","b","c","d","e" })
                                .addSeries("SPP", new Number[] { 20, 50, 52, 3, 20 })
                                .addSeries("SPP1", new Number[] { 20, 10, 52, 4, 15 })
                                .addSeries("SPP2", new Number[] { 80, 40, 22, 20, 1 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }

    /**
     * 簇状条形图
     * @throws IOException
     */
    @Test
    public void testColumnChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("columnChart",
                        Charts.ofArea("簇状条形图测试", new String[] { "a","b","c","d","e" })
                                .addSeries("SPP", new Number[] { 20, 50, 52, 3, 1 })
                                .addSeries("SPP1", new Number[] { 20, 10, 52, 4, 0 })
                                .addSeries("SPP2", new Number[] { 80, 40, 22, 2, 1 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }


    /**
     * 堆积条形图
     * @throws IOException
     */
    @Test
    public void testStackColumnChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("stackColumnChart",
                        Charts.ofBar("堆积条形图测试", new String[] { "a","b","c","d","e" })
                                .addSeries("SPP", new Number[] { 20, 50, 52, 3, 10 })
                                .addSeries("SPP1", new Number[] { 20, 10, 52, 4, 15 })
                                .addSeries("SPP2", new Number[] { 80, 40, 22, 20, 2 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }

    /**
     * 折线图
     * @throws IOException
     */
    @Test
    public void testLineChartExample() throws IOException {
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartDemo.docx")).render(new HashMap<String, Object>() {
            {
                put("lineChart",
                        Charts.ofLine("折线图测试", new String[] { "a","b","c","d","e" })
                                .addSeries("SPP", new Number[] { 20, 50, 52, 3, 10 })
                                .addSeries("SPP1", new Number[] { 10, 20, 42, 13, 12 })
                                .create());
            }
        }).writeToFile("target/output_chartDemo.docx");
    }
}
