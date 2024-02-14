package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public void writer(String message, BigDecimal amount, BigDecimal newBalance) {

        File logFile = new File("log.txt");
        PrintWriter writer = null;

        if(logFile.exists()){
            try {
                writer = new PrintWriter( new FileOutputStream(logFile,true) );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                writer = new PrintWriter(logFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        writer.append(dtf.format(now) + " " + message + " $" + amount + " $" + newBalance + "\n");
        writer.flush();
        writer.close();
    }
}
