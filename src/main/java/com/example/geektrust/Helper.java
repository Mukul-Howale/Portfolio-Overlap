package com.example.geektrust;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Helper {
    static List<String> removeSpaceInFundName(List<String> commands){
        List<String> newCommands = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<commands.size(); i++){
            sb.append(commands.get(i));
            if(i != commands.size()-1) sb.append(" ");
        }
        int command = 0;
        int fundName = 1;
        newCommands.add(0, commands.get(command));
        newCommands.add(1, commands.get(fundName));
        newCommands.add(2, sb.toString());
        return newCommands;
    }

    static Map<String, List<String>> convertJSONToMap() throws IOException {
        // Converting json data to map
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new URL("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json"));
        List<Funds> list = mapper.convertValue(jsonNode.findParents("name"), new TypeReference<List<Funds>>(){});
        return list.stream().collect(Collectors.toMap(Funds::getName, Funds::getStocks));
    }

    static void printOverlapPercentage(List<String> commands, String funds, float commonStocks, float currentStocksSize, float totalStocksSize){
        float totalStockSize = currentStocksSize + totalStocksSize;
        float overlappingPercentage = (2 * (commonStocks)/totalStockSize) * 100;
        System.out.println(commands.get(1) + " " + funds + " " + String.format("%.2f",overlappingPercentage) + "%");
    }
}
