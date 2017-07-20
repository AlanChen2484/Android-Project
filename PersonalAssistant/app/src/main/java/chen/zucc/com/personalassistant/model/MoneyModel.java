package chen.zucc.com.personalassistant.model;

import java.io.Serializable;

/**
 * Created by 陈若韬 on 2017/7/13.
 */

public class MoneyModel implements Serializable {

    private int id;
    private String Money_assetName;
    private double Money_asset;
    private double Money_yield;
    private String Money_beginDate;
    private String Money_endDate;
    private String Money_remark;
    private int Money_state;
    private double Money_anticipated_income;
    private double Money_accumulated_income;

    public String getMoney_assetName() {
        return Money_assetName;
    }

    public void setMoney_assetName(String money_assetName) {
        Money_assetName = money_assetName;
    }

    public double getMoney_asset() {
        return Money_asset;
    }

    public void setMoney_asset(double money_asset) {
        Money_asset = money_asset;
    }

    public double getMoney_yield() {
        return Money_yield;
    }

    public void setMoney_yield(double money_yield) {
        Money_yield = money_yield;
    }

    public String getMoney_beginDate() {
        return Money_beginDate;
    }

    public void setMoney_beginDate(String money_beginDate) {
        Money_beginDate = money_beginDate;
    }

    public String getMoney_endDate() {
        return Money_endDate;
    }

    public void setMoney_endDate(String money_endDate) {
        Money_endDate = money_endDate;
    }

    public String getMoney_remark() {
        return Money_remark;
    }

    public void setMoney_remark(String money_remark) {
        Money_remark = money_remark;
    }

    public int getMoney_state() {
        return Money_state;
    }

    public void setMoney_state(int money_state) {
        Money_state = money_state;
    }

    public double getMoney_anticipated_income() {
        return Money_anticipated_income;
    }

    public void setMoney_anticipated_income(double money_anticipated_income) {
        Money_anticipated_income = money_anticipated_income;
    }

    public double getMoney_accumulated_income() {
        return Money_accumulated_income;
    }

    public void setMoney_accumulated_income(double money_accumulated_income) {
        Money_accumulated_income = money_accumulated_income;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
