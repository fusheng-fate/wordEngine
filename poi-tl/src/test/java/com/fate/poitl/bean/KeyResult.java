package com.fate.poitl.bean;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/19 15:11
 */
public class KeyResult {
    private String desc;
    private String progress;

    public KeyResult(String desc, String progress) {
        this.desc = desc;
        this.progress = progress;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

}
