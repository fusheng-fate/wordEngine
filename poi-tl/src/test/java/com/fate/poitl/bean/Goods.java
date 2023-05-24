package com.fate.poitl.bean;

import java.util.List;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/24 10:40
 */
public class Goods {
    //数量
    private int count;
    //商品名称
    private String name;
    //商品描述
    private String desc;
    //折扣
    private int discount;
    //单价
    private int price;
    //总价
    private int totalPrice;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static List<Goods> randomLists() {
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
        return List.of(goods1, goods);
    }
}
