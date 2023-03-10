package com.example.geektrust;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)  {
        try{
            // Converting input taken from command line to arraylist
            List<List<String>> inputList = Files.lines(new File(args[0]).toPath())
                    .map(line -> Arrays.asList(line.split(" ")))
                    .collect(Collectors.toList());

            // Converting json data to map
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new URL("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json"));
            List<Funds_POJO> list = mapper.convertValue(jsonNode.findParents("name"), new TypeReference<List<Funds_POJO>>(){});
            Map<String, List<String>> funds = list.stream().collect(Collectors.toMap(Funds_POJO::getName, Funds_POJO::getStocks));
            executeCommands(inputList,funds);
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException ioException) {
            System.out.println("IOException handled");
        }
    }

    // Executing each query
    private static void executeCommands(List<List<String>> inputList, Map<String, List<String>> funds) {
        Portfolio_Implementation portfolio = new Portfolio_Implementation(funds);
        for(List<String> commands : inputList){
            String command = commands.get(0);
            if(command.equals(Commands.CURRENT_PORTFOLIO.toString())) portfolio.createPortfolio(commands);
            else if(command.equals(Commands.ADD_STOCK.toString())) portfolio.addStock(commands);
            else if(command.equals(Commands.CALCULATE_OVERLAP.toString()))portfolio.calculateOverlap(commands);
        }
    }
}
