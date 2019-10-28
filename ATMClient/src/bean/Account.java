/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author HK
 */
public class Account implements Serializable {

    private String full_name;
    private String address;
    private String phone;
    private String card_no;
    private String pin;
    private BigDecimal balance;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(String full_name, String address, String phone, String card_no, String pin, BigDecimal balance) {
        this.full_name = full_name;
        this.address = address;
        this.phone = phone;
        this.card_no = card_no;
        this.pin = pin;
        this.balance = balance;
    }

    public Account() {
    }

}
