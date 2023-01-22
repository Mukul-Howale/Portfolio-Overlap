//This is a POJO class
package com.example.geektrust;

import java.util.List;

public class Funds_POJO {
    private String name;
    private List<String> stocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStocks() {
        return stocks;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }

    public void addStock(String stock){
        stocks.add(stock);
    }
}
