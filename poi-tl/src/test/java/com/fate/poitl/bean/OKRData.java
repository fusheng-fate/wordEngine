package com.fate.poitl.bean;

import java.util.List;

public class OKRData {

    private User user;
    private List<OKRItem> objectives;

    private List<OKRItem> manageObjectives;

    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OKRItem> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<OKRItem> objectives) {
        this.objectives = objectives;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<OKRItem> getManageObjectives() {
        return manageObjectives;
    }

    public void setManageObjectives(List<OKRItem> manageObjectives) {
        this.manageObjectives = manageObjectives;
    }

}

