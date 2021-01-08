package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Files {
    public static void create(HashMap<String, Integer > score) {
        try {
            File myObj = new File("src/savescore.txt");
            BufferedWriter bf = null;
            bf = new BufferedWriter(new FileWriter(myObj));

            for (Map.Entry<String, Integer> entry : score.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void read(HashMap<String, Integer > score) {
        try {
            File myObj = new File("src/savescore.txt");
            if (myObj.exists()) {
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] part = data.split(":",2);
                    score.put(part[0], Integer.parseInt(part[1]));
                }
                myReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}