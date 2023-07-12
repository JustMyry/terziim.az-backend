package com.terziim.backend.guard.constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GuardConstants {



    public static List<String> getUnusalWords(){
        List<String> unusualwords = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\survi\\OneDrive\\Desktop\\Projects\\Terziim\\terziim-back\\terziim\\src\\main\\resources\\static\\unusualwords.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                unusualwords = Arrays.stream(data.split(",")).toList();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when Terziim Politeness Guard tried to open UnUsualWords.txt");
            e.printStackTrace();
        }
        return unusualwords;
    }


}
