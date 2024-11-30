package com.example.eloadas;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.AccountUnits;
import com.oanda.v20.primitives.Currency;
import com.oanda.v20.primitives.DateTime;

public class accountSummary {
//    String token = "ebef36cadce744cb18dbbb91447c1d41-403b56730435d46c0d7c5386cba6505f";
//    String account = "101-004-30457100-001";

    String alias;
    Currency currency;
    AccountUnits balance;
    DateTime openTime;
    AccountUnits commision;
    Long openTradeCount;
    Long openPositionCount;
    AccountUnits unrealized;
    AccountUnits value;
    AccountUnits withdrawLimit;
    String token;
    String account;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountUnits getBalance() {
        return balance;
    }

    public void setBalance(AccountUnits balance) {
        this.balance = balance;
    }

    public DateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(DateTime openTime) {
        this.openTime = openTime;
    }

    public AccountUnits getCommision() {
        return commision;
    }

    public void setCommision(AccountUnits commision) {
        this.commision = commision;
    }

    public Long getOpenTradeCount() {
        return openTradeCount;
    }

    public void setOpenTradeCount(Long openTradeCount) {
        this.openTradeCount = openTradeCount;
    }

    public Long getOpenPositionCount() {
        return openPositionCount;
    }

    public void setOpenPositionCount(Long openPositionCount) {
        this.openPositionCount = openPositionCount;
    }

    public AccountUnits getUnrealized() {
        return unrealized;
    }

    public void setUnrealized(AccountUnits unrealized) {
        this.unrealized = unrealized;
    }

    public AccountUnits getValue() {
        return value;
    }

    public void setValue(AccountUnits value) {
        this.value = value;
    }

    public AccountUnits getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(AccountUnits withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    accountSummary(String token,String account) {


        Context ctx = new Context("https://api-fxpractice.oanda.com", token);
        try{
            AccountSummary summary = ctx.account.summary(new AccountID(account)).getAccount();
            this.alias = summary.getAlias();
            this.currency = summary.getCurrency();
            this.balance = summary.getBalance();
            this.openTime = summary.getCreatedTime();
            this.commision = summary.getCommission();
            this.openTradeCount = summary.getOpenTradeCount();
            this.openPositionCount = summary.getOpenPositionCount();
            this.unrealized = summary.getUnrealizedPL();
            this.value = summary.getPositionValue();
            this.withdrawLimit = summary.getWithdrawalLimit();
            this.token = token;
            this.account = account;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

