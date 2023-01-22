//This is a POJO class
package com.example.geektrust;

import java.util.List;

public class Funds {
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

    private String name;
    private List<String> stocks;
}
