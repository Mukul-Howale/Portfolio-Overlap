package com.example.geektrust;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)  {
        try(Stream<String> lines = Files.lines(new File(args[0]).toPath())){
            List<String> commands = lines.map(String::trim)
                    .filter(string -> !string.matches(" "))
                    .collect(Collectors.toList());
            readCommands(commands);
        }
        catch (IOException ioException){
            System.out.println("IOException handled");
        }
    }

    private static void readCommands(List<String> commands){
        
    }
}
