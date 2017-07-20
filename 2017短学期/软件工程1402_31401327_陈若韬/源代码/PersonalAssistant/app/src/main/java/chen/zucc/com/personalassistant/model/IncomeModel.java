package chen.zucc.com.personalassistant.model;

import java.io.Serializable;

/**
 * Created by chenchongkang on 2017/7/13.
 */

public class IncomeModel implements Serializable {
    private int id;
    private float income;
    private float expense;
    private String classess;
    private String incomeTime;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public String getClassess() {
        return classess;
    }
    public void setClassess(String classess) {
        this.classess = classess;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }
}
