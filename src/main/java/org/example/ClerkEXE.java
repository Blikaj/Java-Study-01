package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ClerkEXE {

    public static void main(String[] args) {
        ArrayList<Clerk> clerkArrayList;
        Scanner in = new Scanner(System.in);
        clerkArrayList = new ArrayList<>();
        ArrayList<String> tmpclerk = new ArrayList<String>();
        String ch = "-1";
        try (FileReader reader = new FileReader("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNext()){
                tmpclerk.add(scan.nextLine());
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (ch != "6") {
            System.out.print(
                    """
                            ============Text Main Menu =============
                                                    
                            1- print clerks list
                            2- add clerk to list
                            3- find clerk
                            4- alter clerk info
                            5- print salary sum
                            6- exit
                            """);
            ch = in.next();
            switch (ch) {

                case "1": {
                    tmpclerk.forEach(clerkk ->{
                        System.out.println(clerkk);
                    });
                    break;
                }
                case "2": {
                    ch="2";
                    try (FileWriter writer = new FileWriter("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) {
                        System.out.println("""
                                Hello, this is the clerk adding algorithm.
                                To add clerk type "add".
                                
                                If you are done with adding new clerks, type 'done' """);
                        String inchar = in.next();
                        switch (inchar) {
                            case "add": {
                                System.out.println("""
                                        Type clerk info as follows:
                                        ID(String);Name(String);Birthday(Date);Salary(Double)""");
                                String tmpdat = in.next()+"\n";
                                int c = 0;
                                for (String clerkk : tmpclerk) {
                                    if (clerkk.contains(tmpdat.split(";")[0])) {
                                        System.out.print("Err, ID already exists!");
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    tmpclerk.add(tmpdat);
                                    tmpclerk.forEach(clerk ->{
                                        try {
                                            writer.write(String.valueOf(clerk+"\n"));
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    System.out.print("Clerk added");
                                    }
                                writer.close();
                                break;
                            }
                            case "done": {
                                writer.close();
                                break;
                            }
                            default: {
                                System.out.print("Wrong choise, my friend!");
                                break;
                            }
                        }

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "3": {
                    System.out.println("Type ID, name or birthday date of an employee:");
                    ArrayList<String> outList = new ArrayList<String>();
                    String inchar = in.next();
                    tmpclerk.forEach(clerkk ->{
                        ///System.out.println("dot");
                        if (clerkk.contains(inchar)){
                            outList.add(clerkk);
                        }
                    });
                    System.out.println(outList);
                    break;
                }
                case "4":{
                    System.out.println("Type ID of the employee:");
                    String inchar = in.next();
                    for (String clerkk : tmpclerk){
                        if (clerkk.contains(inchar)){
                            System.out.println("Type new info: Name;Birthday;Salary");
                            String alter = in.next();
                            tmpclerk.set(tmpclerk.indexOf(clerkk), inchar+";"+alter);
                        }
                    }
                    try (FileWriter writer = new FileWriter("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) {
                        tmpclerk.forEach(clerkk ->{
                            try {
                                writer.write(String.valueOf(clerkk+"\n"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        System.out.print("Clerk info altered!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }
                case "5": {
                    System.out.println("The sum of the salaries is:");
                    Double outSal = Double.valueOf(0);
                    for (String clerkk : tmpclerk){
                        outSal += Double.parseDouble(clerkk.split(";")[3]);
                    }
                    System.out.println(outSal);
                    break;
                }

                case "6": {
                    ch = "6";
                    System.out.print("Exiting...");
                    break;
                }
                default: {
                    ch = "-1";
                    System.out.print("Wrong choise, my friend!");
                    break;
                }
            }
        }

    }

    }