package com.example.geektrust;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Portfolio_Implementation portfolioImplementation;

    @BeforeEach
    void setUp(){
        List<String> stocks = new ArrayList<>();
        stocks.add("INDRAPRASTHA GAS LIMITED");
        stocks.add("COLGATE - PALMOLIVE (INDIA) LIMITED");
        stocks.add("INDUS TOWERS LIMITED");
        stocks.add("INTERGLOBE AVIATION LIMITED");
        Map<String, List<String>> funds = new HashMap<>();
        funds.put("ICICI_PRU_NIFTY_NEXT_50_INDEX",stocks);
        portfolioImplementation = new Portfolio_Implementation(funds);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testNoFunds_whileCreatingPortfolio(){
        List<String> commands = new ArrayList<>();
        commands.add("CURRENT_PORTFOLIO");
        commands.add("AXIS_BLUECHIP");
        portfolioImplementation.createPortfolio(commands);
        assertEquals("FUND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }

    @Test
    void testNoFunds_whileAddingStock(){
        List<String> commands = new ArrayList<>();
        commands.add("ADD_STOCK");
        commands.add("AXIS_BLUECHIP");
        commands.add("TCS");
        portfolioImplementation.addStock(commands);
        assertEquals("FUND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }

    @Test
    void testNoFunds_whileCalculatingOverlap(){
        List<String> commands = new ArrayList<>();
        commands.add("ADD_STOCK");
        commands.add("AXIS_BLUECHIP");
        commands.add("TCS");
        portfolioImplementation.calculateOverlap(commands);
        assertEquals("FUND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}