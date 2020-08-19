package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    @Override
    public String maskSensitiveData() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("sensitive_data.txt");

        if (resource != null) {
            File file = new File(resource.getFile());

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Pattern p = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}");
                Pattern pIn = Pattern.compile("\\s\\d{4}\\s\\d{4}\\s");
                StringBuffer buffer = new StringBuffer();

                String line = reader.readLine();

                while (line != null) {
                    Matcher matcher = p.matcher(line);
                    while (matcher.find()) {
                        Matcher matcher1 = pIn.matcher(matcher.group());
                        String newStr = "";

                        if (matcher1.find()) {
                            newStr = matcher1.replaceFirst(" **** **** ");
                        }
                        matcher.appendReplacement(buffer, newStr);
                    }
                    matcher.appendTail(buffer);
                    line = reader.readLine();
                }

                return new String(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("sensitive_data.txt");

        if (resource != null) {
            File file = new File(resource.getFile());

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                StringBuilder strBuilder = new StringBuilder();

                while (line != null) {
                    strBuilder.append(line);
                    line = reader.readLine();
                }

                String placeHolder1 = paymentAmount == (int) paymentAmount ? Integer.toString((int) paymentAmount) : String.valueOf(paymentAmount);
                String placeHolder2 = balance == (int) balance ? Integer.toString((int) balance) : String.valueOf(balance);

                String result = new String(strBuilder).replaceFirst("\\$\\{payment_amount\\}",
                        placeHolder1).replaceFirst("\\$\\{balance\\}", placeHolder2);

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
