package com.app.service.template;

import au.com.bytecode.opencsv.CSVWriter;
import com.app.model.*;
import com.app.service.api.FileOperations;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * Reads and Modifies CSV Files
 */
@Named("csvTemplate")
public class FileOperationsTemplate implements FileOperations {

    private Logger logger;

    public FileOperationsTemplate() {
        logger = logger();
    }

    @Override
    public List<DataSummary> read(String fileName, String date) {
        AtomicReference<Client> clientData = new AtomicReference<>(new Client());
        AtomicReference<Account> accountData = new AtomicReference<>(new Account());
        AtomicReference<Product> productData = new AtomicReference<>(new Product());
        AtomicReference<Transaction> transactionData = new AtomicReference<>(new Transaction());
        List<DataSummary> dataSummaryList = new ArrayList<>();


        try {

            logger.info("Reading the Input Data");
            StringBuilder contentBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> stream = br.lines().collect(Collectors.toList());
            //List<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).collect(Collectors.toList());
            stream.forEach(s -> contentBuilder.append(s).append("\n"));

            stream.forEach(s ->
                    {
                        if ((s.substring(121, 129)).equalsIgnoreCase(date)) {
                            clientData.set(new Client.Builder()
                                    .type(s.substring(3, 7))
                                    .number(s.substring(7, 11))
                                    .build());
                            accountData.set(new Account.Builder()
                                    .number(s.substring(11, 15))
                                    .subAccountNumber(s.substring(15, 19))
                                    .build());
                            productData.set(new Product.Builder()
                                    .exchangeCode(s.substring(27, 31))
                                    .groupCode(s.substring(25, 27))
                                    .symbol(s.substring(31, 37))
                                    .expirationDate(s.substring(37, 45))
                                    .build());
                            transactionData.set(new Transaction.Builder()
                                    ._long(Long.parseLong(s.substring(52, 62)))
                                    ._short(Long.parseLong(s.substring(63, 73)))
                                    .build());
                            //transactionAmount.set(String.valueOf(Integer.parseInt(s.substring(52, 62)) - Integer.parseInt(s.substring(63, 73))));
                            DataSummary dataSummary = new DataSummary.Builder()
                                    .client(clientData.get())
                                    .product(productData.get())
                                    .account(accountData.get())
                                    .transaction(transactionData.get())
                                    .build();
                            dataSummaryList.add(dataSummary);
                        }
                    }

            );
        } catch (IOException e) {
            System.out.println("Invalid file/file path");
            logger.severe("IO Exception occurred");
        }
        return dataSummaryList;
    }


    @Override
    public void write(List<DataSummary> dataSummaryList) {

        if (dataSummaryList.size() > 0) {
            String csv = "Output.csv";
            logger.info("Writing data to CSV");
            CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter(csv));
                //Header of CSV
                String[] entries = {"Client Information (CLIENT TYPE; CLIENT NUMBER; ACCOUNT NUMBER; SUBACCOUNT NUMBER), Product_Information(EXCHANGE CODE; PRODUCT GROUP CODE; SYMBOL; EXPIRATION DATE), Total_Transaction_Amount"};
                writer.writeNext(entries);
                //Create data entries
                for (DataSummary dataSummary : dataSummaryList) {
                    String[] record = ("" + dataSummary.getClient().getType() + " " + dataSummary.getClient().getNumber() + " " + dataSummary.getAccount().getNumber() + " " + dataSummary.getAccount().getSubAccountNumber() + ","
                            + dataSummary.getProduct().getExchangeCode() + " " + dataSummary.getProduct().getGroupCode() + " " + dataSummary.getProduct().getSymbol() + " " + dataSummary.getProduct().getExpirationDate() + ","
                            + (dataSummary.getTransaction().get_long() - dataSummary.getTransaction().get_short())).split(",");
                    //Write the record to file
                    writer.writeNext(record);
                }

                System.out.println("Data successfully extracted to Output.csv file");
                logger().info("Data extracted to CSV successfully");
                //close the writer
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.severe("IO exception has occurred");
            }
        }
    }

    private Logger logger() {
        FileHandler handler = null;
        Logger logger = Logger.getLogger("com.app");
        try {
            handler = new FileHandler("default.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(handler);
        return logger;

    }
/*
    private static String reverse(String original) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(original.substring(6, 7));
        stringBuilder.append(original.substring(7));
        stringBuilder.append(original.substring(4, 6));
        stringBuilder.append(original.substring(0, 4));
        return stringBuilder.toString();
    }*/
}
