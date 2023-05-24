package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.expression.Name;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import com.fate.poitl.bean.Goods;
import com.fate.poitl.bean.Labor;
import com.fate.poitl.utils.PathUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lgb
 * @Description 动态表格生成，对于复杂的表格，多张表格组合的场景，十分好用，可以自定义自己的表格插件。
 * @Date 2023/5/24 11:16
 */
@SpringBootTest
public class ComplexDynamicTableExample {

    @Test
    public void testComplexTable() throws IOException {

        //绑定表格插件
        ComplexTablePolicy complexTablePolicy = new ComplexTablePolicy();
        Configure configure = Configure.builder().bind("detail_table", complexTablePolicy).build();

        //生成 data-model
        DetailData detailData = new DetailData();
        detailData.setGoods(detailData.createGoods());
        detailData.setLabors(detailData.createLabors());
        ComplexTableData complexTableData = new ComplexTableData();
        complexTableData.setDetailData(detailData);

        //编译、渲染、生成
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("complexTable.docx"), configure)
                .render(complexTableData).writeToFile("target/output_complex_table.docx");
    }

    static class ComplexTableData {

        @Name("detail_table")
        private DetailData detailData;

        public DetailData getDetailData() {
            return detailData;
        }

        public void setDetailData(DetailData detailData) {
            this.detailData = detailData;
        }
    }


    static class DetailData {

        private List<Labor> labors;

        private List<Goods> goods;

        public List<Labor> getLabors() {
            return labors;
        }

        public void setLabors(List<Labor> labors) {
            this.labors = labors;
        }

        public List<Goods> getGoods() {
            return goods;
        }

        public void setGoods(List<Goods> goods) {
            this.goods = goods;
        }

        public List<Labor> createLabors() {
            Labor labor = new Labor();
            labor.setPeople(2);
            labor.setCategory("木工");
            labor.setPrice(80);
            labor.setTotalPrice(160);
            return List.of(labor, labor, labor);
        }

        public List<Goods> createGoods() {
            Goods goods = new Goods();
            goods.setName("陕西红富士");
            goods.setCount(2);
            goods.setDesc("又脆又甜");
            goods.setDiscount(8);
            goods.setPrice(6);
            goods.setTotalPrice(12);

            Goods goods1 = new Goods();
            goods1.setName("陕西红富士");
            goods1.setCount(3);
            goods1.setDesc("又脆又甜");
            goods1.setDiscount(8);
            goods1.setPrice(6);
            goods1.setTotalPrice(18);
            return List.of(goods1, goods, goods, goods1);
        }
    }


   static class ComplexTablePolicy extends DynamicTableRenderPolicy {
       int goodsStartRow = 2;
       int laborsStartRow = 5;

       @Override
       public void render(XWPFTable table, Object data) throws Exception {
           if (null == data) return;
           DetailData detailData = (DetailData) data;

           List<RowRenderData> labors = new ArrayList<>();
           detailData.getLabors().forEach(x -> {
               labors.add(Rows.of(String.valueOf(x.getPeople()), String.valueOf(x.getCategory()),
                       String.valueOf(x.getPrice()), String.valueOf(x.getTotalPrice())).center().create());
           });
           if (!labors.isEmpty()) {
               table.removeRow(laborsStartRow);
               // 循环插入行
               for (RowRenderData labor : labors) {
                   XWPFTableRow insertNewTableRow = table.insertNewTableRow(laborsStartRow);
                   for (int j = 0; j < 6; j++) insertNewTableRow.createCell();

                   // 合并单元格  [0,2] 从0号单元格开始 合并 0,1,2三个单元格
                   TableTools.mergeCellsHorizonal(table, laborsStartRow, 0, 2);
                   // 单行渲染
                   TableRenderPolicy.Helper.renderRow(table.getRow(laborsStartRow), labor);
               }
           }
           List<RowRenderData> goods = new ArrayList<>();
           detailData.getGoods().forEach(x -> {
               goods.add(Rows.of(String.valueOf(x.getCount()),String.valueOf(x.getName()),String.valueOf(x.getDesc()),
                       String.valueOf(x.getDiscount()), String.valueOf(x.getPrice()), String.valueOf(x.getTotalPrice())).center().create());
           });
           if (!goods.isEmpty()) {
               table.removeRow(goodsStartRow);
               for (RowRenderData good : goods) {
                   XWPFTableRow insertNewTableRow = table.insertNewTableRow(goodsStartRow);
                   for (int j = 0; j < 6; j++) insertNewTableRow.createCell();
                   TableRenderPolicy.Helper.renderRow(table.getRow(goodsStartRow), good);
               }
           }
       }
    }
}
