package com.example.geektrust;

import java.util.*;

public class Portfolio {
    private final int fundName = 1;
    private final Map<String, List<String>> portfolioFunds = new HashMap<>();
    private final List<String> initialFunds = new ArrayList<>();
    Map<String, List<String>> funds;

    public Portfolio(Map<String, List<String>> funds){
        this.funds = funds;
    }

    // Creating a new portfolio with added funds
    public void createPortfolio(List<String> commands){
        for(int i=1; i<commands.size(); i++){
            if(!funds.containsKey(commands.get(i))) {
                System.out.println("FUND_NOT_FOUND");
                break;
            }
            else {
                portfolioFunds.put(commands.get(i), funds.get(commands.get(i)));
                initialFunds.add(commands.get(i));
            }
        }
    }

    // Adding stocks to the funds in the users portfolio
    public void addStock(List<String> commands){
        if(commands.size() > 3) commands = Helper.removeSpaceInFundName(commands);
        if(!portfolioFunds.containsKey(commands.get(fundName))) {
            System.out.println("FUND_NOT_FOUND");
        }
        else {
            List<String> stocks = portfolioFunds.get(commands.get(fundName));
            int stockName = 2;
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
            if(commonStocks == 0) continue;
            Helper.printOverlapPercentage(commands, funds, commonStocks, currentStocks.size(), totalStocks.size());
            commonStocks = 0;
        }
    }
}
