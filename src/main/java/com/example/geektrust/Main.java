package com.example.geektrust;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)  {
        try{
            //Converting input taken from command line to arraylist
            List<List<String>> inputList = Files.lines(new File(args[0]).toPath())
                    .map(line -> Arrays.asList(line.split(" ")))
                    .collect(Collectors.toList());

            //Converting json data to string
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new URL("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json"));
            List<Funds> funds = mapper.convertValue(jsonNode.findParents("name"), new TypeReference<List<Funds>>(){});
            readCommands(inputList,funds);
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException ioException) {
            System.out.println("IOException handled");
        }
    }

    private static void readCommands(List<List<String>> inputList, List<Funds> funds) {
        System.out.println(inputList);
        System.out.println(funds.get(0).getName());
    }
}
