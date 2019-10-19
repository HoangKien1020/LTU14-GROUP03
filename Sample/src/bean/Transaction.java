package bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Transaction {
    private String card_no1;
    private String card_no2;
    private String type;
    private int money_no;
    private String tran_date;
    private String code;
    
    public Transaction(String card_no1, String card_no2, String type, int money_no, String tran_date, String code) {
        this.card_no1 = card_no1;
        this.card_no2 = card_no2;
        this.type = type;
        this.money_no = money_no;
        this.tran_date = tran_date;
        this.code = code;
    }

    public String getCard_no1() {
        return card_no1;
    }

    public void setCard_no1(String card_no1) {
        this.card_no1 = card_no1;
    }

    public String getCard_no2() {
        return card_no2;
    }

    public void setCard_no2(String card_no2) {
        this.card_no2 = card_no2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney_no() {
        return money_no;
    }

    public void setMoney_no(int money_no) {
        this.money_no = money_no;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
