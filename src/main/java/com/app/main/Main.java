package com.app.main;

import com.app.model.DataSummary;
import com.app.service.template.FileOperationsTemplate;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

public class Main {

    @Inject
    static FileOperationsTemplate fileOperationsTemplate = new FileOperationsTemplate();

    public static void main(String args[]) {

        String filePath = inputFile();
        String date = inputDate();
        List<DataSummary> dataSummaryList = fileOperationsTemplate.read(filePath, date);
        fileOperationsTemplate.write(dataSummaryList);

    }

    private static String inputFile() {
        String filePath = "Input.txt";


        boolean validData = false;
        do {
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            System.out.println("New Input txt file: Y|N");
            String choice = scanner.next();
            if ((choice.contains("Y") || choice.contains("y")) && (choice.contains("N") || choice.contains("n"))) {
                System.out.println("Please select ONE option : " + "Y | N");
            } else if (choice.contains("Y") || choice.contains("y")) {
                System.out.println("Please enter the path of Input text file");
                filePath = scanner.next();
                validData = true;
            } else if (choice.contains("N") || choice.contains("n")) {
                validData = true;
            } else {
                System.out.println("Invalid choice");
            }

        } while (!validData);
        return filePath;
    }

    private static String inputDate() {
        boolean validData = false;
        String date = null;
        do {
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Enter the Date for Day summary report in YYYYMMDD format");
            date = scanner.next();
            date = date.replaceAll("\\s+", "");
            if (date.length() != 8) {
                System.out.println("Invalid input: Please Enter the Date for Day summary report in YYYYMMDD format");
                validData = false;
            } else {
                validData = true;
            }
        } while (!validData);
        return date;

    }


}
