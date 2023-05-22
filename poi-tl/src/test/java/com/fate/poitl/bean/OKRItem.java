package com.fate.poitl.bean;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/19 15:11
 */
public class OKRItem {
    int index;
    Objective object;
    KeyResult kr1;
    KeyResult kr2;
    KeyResult kr3;

    public Objective getObject() {
        return object;
    }

    public void setObject(Objective object) {
        this.object = object;
    }

    public KeyResult getKr1() {
        return kr1;
    }

    public void setKr1(KeyResult kr1) {
        this.kr1 = kr1;
    }

    public KeyResult getKr2() {
        return kr2;
    }

    public void setKr2(KeyResult kr2) {
        this.kr2 = kr2;
    }

    public KeyResult getKr3() {
        return kr3;
    }

    public void setKr3(KeyResult kr3) {
        this.kr3 = kr3;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
