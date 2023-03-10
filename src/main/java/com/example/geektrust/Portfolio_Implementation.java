package com.example.geektrust;

import java.util.*;

public class Portfolio_Implementation{
    private final int fundName = 1;
    private final int stockName = 2;
    private final Map<String, List<String>> portfolioFunds;
    private final List<String> initialFunds;
    Map<String, List<String>> funds;

    public Portfolio_Implementation(Map<String, List<String>> funds){
        this.funds = funds;
        portfolioFunds = new HashMap<>();
        initialFunds = new ArrayList<>();
    }

    // Creating a new portfolio with added funds
    public void createPortfolio(List<String> commands){
        for(int i=1; i<commands.size(); i++){
            if(!funds.containsKey(commands.get(i))) System.out.println("FUND_NOT_FOUND");
            else {
                portfolioFunds.put(commands.get(i), funds.get(commands.get(i)));
                initialFunds.add(commands.get(i));
            }
        }
    }

    // Adding stocks to the funds in the users portfolio
    public void addStock(List<String> commands){
        if(!portfolioFunds.containsKey(commands.get(fundName))) System.out.println("FUND_NOT_FOUND");
        else {
            List<String> stocks = portfolioFunds.get(commands.get(fundName));
            stocks.add(commands.get(stockName));
            portfolioFunds.put(commands.get(fundName), stocks);
        }
    }

    // Calculating overlap of stocks in funds
    public void calculateOverlap(List<String> commands){
        float commonStocks = 0;
        HashSet<String> totalStocks;

        if(!funds.containsKey(commands.get(fundName))) {
            System.out.println("FUND_NOT_FOUND");
            return;
        }
        else totalStocks = new HashSet<>(funds.get(commands.get(fundName)));

        for(String funds : initialFunds){
            HashSet<String> currentStocks = new HashSet<>(portfolioFunds.get(funds));
            for(String stock : currentStocks){
                if(totalStocks.contains(stock)) commonStocks++;
            }
            float totalStockSize = currentStocks.size() + totalStocks.size();
            float overlappingPercentage = (2*(commonStocks)/totalStockSize)*100;
            System.out.println(commands.get(1) + " " + funds + " " + String.format("%.2f",overlappingPercentage) + "%");
            commonStocks = 0;
        }
    }
}
