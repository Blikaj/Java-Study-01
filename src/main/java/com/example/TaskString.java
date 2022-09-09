package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class TaskString {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Trying to create empty file");
        createFile("Input.txt");
        String ch = "-1";
        while (ch != "8") {
            System.out.print(
                    """
                            ============Text Main Menu =============
                                                    
                            0- print contents of 'Input.txt'
                            1- count number of words in 'Input.txt'
                            2- count upper and lower case symbols
                            3- count spaces
                            4- count number of int and double
                            5- count punctuation marks
                            6- save all results in 'Result.txt'
                            7- find pattern in content
                            8- exit
                            """);
            ch = in.next();
            switch (ch) {
                case "0" -> {
                    System.out.println("Trying to read file");
                    System.out.println(readFile("Input.txt"));
                    break;
                }
                case "1" -> {
                    String content = readFile("Input.txt");
                    numOfWords(content);
                    break;
                }
                case "2" -> {
                    String content = readFile("Input.txt");
                    upLow(content);
                    break;
                }
                case "3" -> {
                    String content = readFile("Input.txt");
                    numOfSpace(content);
                    break;
                }
                case "4" -> {
                    String content = readFile("Input.txt");
                    intDouble(content);
                    break;
                }
                case "5" -> {
                    String content = readFile("Input.txt");
                    numOfPunkt(content);
                    break;
                }
                case "6" -> {
                    Integer nWords, nPunct, nSpaces = 0;
                    Integer[] nSymb = new Integer[2];
                    Integer[] intDouble = new Integer[2];
                    String content = readFile("Input.txt");
                    nWords = numOfWords(content);
                    nSymb = upLow(content);
                    nSpaces = numOfSpace(content);
                    intDouble = intDouble(content);
                    nPunct = numOfPunkt(content);
                    String[] newContent = new String[6];
                    newContent[0] = "Information about Input.txt";
                    newContent[1] = "The number of words: " + nWords;
                    newContent[2] = "The number of Upper and lower symbols: " + nSymb[0] + ":" + nSymb[1];
                    newContent[3] = "The number of spaces: " + nSpaces;
                    newContent[4] = "The number of int and double numbers: " + intDouble[0] + ":" + intDouble[1];
                    newContent[5] = "The number of punctuation marks: " + nPunct;
                    writeFile("Result.txt", newContent);
                    break;
                }
                case "7" -> {
                    String content = readFile("Input.txt");
                    System.out.println("Enter pattern to find: ");
                    String pat = in.next();
                    matchFind(content, pat);
                    break;
                }
                case "8" -> {
                    ch = "8";
                    System.out.print("Exiting...");
                    break;
                }
                default -> {
                    ch = "-1";
                    System.out.print("Wrong choise, my friend!");
                    break;
                }
            }

        }
    }

    private static void createFile(String  emptyFileName) {
        System.out.println(String.format("Creating an empty file ",emptyFileName));
        File file = new File(emptyFileName);
try {
            if (file.createNewFile()) {
                System.out.println("File creation successful!");
                System.out.println("You can use this file to input information to analyze in this program");

            } else {
                System.out.println("File creation failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String textFileName){
        StringBuilder sb = new StringBuilder();;
        System.out.println(String.format("Reading file [%s] using FileReader",textFileName));
        try {
            FileReader myReader = new FileReader(textFileName);
            int character=myReader.read();
            while(character!=-1)
            {
//System.out.print((char) character);
                sb.append((char) character);
                character=myReader.read();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        if (sb.isEmpty()) {
            return ("System: File is empty!");
        } else
        {
            return sb.toString();
        }
    }

    private static void writeFile(String textFileName, String[] content) {
        System.out.println(String.format("Writing to file [%s]",textFileName));
        try {
            FileWriter myWriter = new FileWriter(textFileName);
            for (String line : content){
                myWriter.write(line + "\n");
            }
            myWriter.close();
            System.out.println("Text saved successfully.");
        } catch (Exception e) {
            System.out.println("Text saving failed.");
            e.printStackTrace();
        }
    }
    private static Integer numOfWords(String content) {
        int count = 0;

        if(content.length() != 0){
            count ++;
            String[] analyze = content.split(" ");
            for (String word : analyze ) {count++; System.out.println(word);}
        }
        System.out.println("The number of words: ");
        System.out.println(count);
        return count;
    }
    private static Integer[] upLow(String content) {
        int countUp = 0;
        int countLow = 0;
        if(content.length() != 0){
            for (int i = 0; i < content.length(); i++) {
                if(content.charAt(i) != ' '){
                    if(Character.isUpperCase(content.charAt(i))) {
                        countUp++;
                    } else if (Character.isLowerCase(content.charAt(i))) {
                       countLow++;
                    }
                }
            }
        }
        Integer[] countTotal = new Integer[3];
        countTotal[0] = countUp;
        countTotal[1] = countLow;
        countTotal[2] = countUp+countLow;
        String out = valueOf(countTotal[0]) + ":" + valueOf(countTotal[1]) + ":" + valueOf(countTotal[2]);
        System.out.println("The number of uppercase, lowercase and total amount of symbols: ");
        System.out.println(out);
        return countTotal;
    }
    private static Integer numOfSpace(String content) {
        int count = 0;

        if(content.length() != 0){
            for (int i = 0; i < content.length(); i++) {
                if(content.charAt(i) == ' '){
                    count++;
                }
            }
        }
        System.out.println("The number of space symbols: ");
        System.out.println(count);
        return count;
    }

    private static Integer[] intDouble(String content) {
        var count = new Integer[2];
        int nInt = 0;
        int nDouble = 0;
        if(content.length() != 0){
            String[] newcontent = content.split(" ");
            for (String x : newcontent) {
                if (x.isEmpty()){ } else {
                    try {
                        Integer z = Integer.parseInt((x));
                        ///System.out.println(z);
                        nInt++;
                    } catch (NumberFormatException nfe) {
                        try {
                            Double z = Double.parseDouble(x);
                            ///System.out.println(z);
                            nDouble++;
                        } catch (NumberFormatException nfe2) { }
                        }
                    }
                }
            }

        count[0] = nInt;
        count[1] = nDouble;
        String out = count[0] + ":" + count[1];
        System.out.println("The number of Int and Double numbers: ");
        System.out.println(out);
        return count;
    }

    private static Integer numOfPunkt(String content) {
        Integer count = 0;
        if(content.length() != 0){
            String[] newcontent = content.split("");
            for (String x : newcontent) {
                if ((x.isEmpty())){ } else {
                    if (x.matches("\\p{Punct}")) {
                        count++;
                    }

                }
            }
        }

        System.out.println("The number of punctuation symbols: ");
        System.out.println(count);
        return count;
    }

    private static Integer[] matchFind(String content, String patCont){
        Integer[] ind = new Integer[2];
        ind[0] = content.indexOf(patCont);
        ind[1] = ind[0];
        for (int index = content.indexOf(patCont);
             index >= 0;
             index = content.indexOf(patCont, index+1))
        {
            if (index > ind[1]) {
                ind[1] = index;
            }
            System.out.println(index);
        }
        ind[1] += patCont.length();
        String out = ind[0]+";"+ind[1];
        System.out.println("Index of first and last match");
        System.out.println(out);
        return ind;
    }

}
