package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Portfolio_Implementation{
    List<Funds_POJO> funds;
    List<Funds_POJO> portfolioFunds;

    public Portfolio_Implementation(List<Funds_POJO> funds){
        this.funds = funds;
        portfolioFunds = new ArrayList<>();
    }

    public void createPortfolio(List<String> commands){
        for(int i=1; i<commands.size(); i++){
            for(Funds_POJO fund : funds){
                if(commands.get(i).equals(fund.getName())) portfolioFunds.add(fund);
            }
        }
    }
    public void addStock(List<String> commands){
        for(Funds_POJO fund : portfolioFunds){
            if(commands.get(1).equals(fund.getName())){
                List<String> words = commands.subList(2,commands.size());
                String stock = String.join(" ",words);
                fund.addStock(stock);
                break;
            }
            else{
                System.out.println("FUND_NOT_FOUND");
            }
        }
    }
    public void calculateOverlap(List<String> commands){
        float commonStocks = 0;
        HashSet<String> totalStocks = new HashSet<>();

        for(Funds_POJO fund : funds){
            if(fund.getName().equals(commands.get(1))) {
                totalStocks.addAll(fund.getStocks());
                break;
            }
        }
        for(Funds_POJO fund : portfolioFunds){
            HashSet<String> currentStocks = new HashSet<>(fund.getStocks());
            for(String stock : currentStocks){
                if(totalStocks.contains(stock)) commonStocks++;
            }
            float totalStockSize = currentStocks.size() + totalStocks.size();
            float overlappingPercentage = (2*(commonStocks)/totalStockSize)*100;
            System.out.println(commands.get(1) + " " + fund.getName() + " " + String.format("%.2f",overlappingPercentage) + "%");
            commonStocks = 0;
        }
    }
}
