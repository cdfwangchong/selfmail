package com.cdfg.selfmail.pojo.dto;

public class PostItemDto {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPick_num() {
        return pick_num;
    }

    public void setPick_num(String pick_num) {
        this.pick_num = pick_num;
    }

    public double getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(double trans_amount) {
        this.trans_amount = trans_amount;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    private static final long serialVersionUID = 1L;

    private String pick_num;
    private double trans_amount;
    private String mark;
}
