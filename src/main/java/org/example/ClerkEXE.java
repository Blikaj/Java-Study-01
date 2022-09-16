package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClerkEXE {

    public static void main(String[] args) {
        ArrayList<Clerk> clerkArrayList; ///ArrayList for Clerk class, can be filled from converted string array if needed, but I will use only string arrays
        Scanner in = new Scanner(System.in); /// Scanner for input commands
        clerkArrayList = new ArrayList<>();
        ArrayList<String> tmpclerk = new ArrayList<String>(); ///Array that will contain contents of the file
        String ch = "-1"; ///Choise from the menu
        try (FileReader reader = new FileReader("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) { ///Reading the contents of the file
            Scanner scan = new Scanner(reader);
            while (scan.hasNext()){
                String[] tmpinfo = (scan.nextLine().split(";"));
                Clerk tmpclerkinfo = new Clerk(tmpinfo[0], tmpinfo[1], tmpinfo[2], Double.parseDouble(tmpinfo[3]));
                tmpclerkinfo.setID(tmpinfo[0]);

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

                case "1": { ///Printing out clerk array element by element
                    tmpclerk.forEach(clerkk ->{
                        System.out.println(clerkk);
                    });
                    break;
                }
                case "2": {
                    try (FileWriter writer = new FileWriter("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) {
                        ///This is the writer needed to overwrite the existing file with clerks
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
                                String tmpdat = in.next()+"\n"; ///reading the clerks info to add to the array, \n is essential due to the formation of the file
                                int c = 0;
                                for (String clerkk : tmpclerk) {
                                    if (clerkk.contains(tmpdat.split(";")[0])) {
                                        System.out.print("Err, ID already exists!"); ///will occur if entry with this ID already exists
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    tmpclerk.add(tmpdat);///adding new clerk to the clerk array
                                    tmpclerk.forEach(clerk ->{
                                        try {
                                            writer.write(String.valueOf(clerk+"\n"));///overwriting the file
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
                    ArrayList<String> outList = new ArrayList<String>(); ///list for the all clerks with /filter (inchar) option in any or all of the characteristics
                    String inchar = in.next();
                    tmpclerk.forEach(clerkk ->{
                        ///System.out.println("dot"); ///I used this to test if clerks were added correctly
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
                            tmpclerk.set(tmpclerk.indexOf(clerkk), inchar+";"+alter); ///setting the new info clerk with current ID
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
                    }///Summarising all the salaries, needed to add parseDouble as entries are String
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

    public void method1(ArrayList<Clerk> tmpclerk){
        tmpclerk.forEach(clerkk ->{
            clerkk.printInfo();
        });
    }
    public void method2(ArrayList<Clerk> tmpclerk, Scanner in){
        try (FileWriter writer = new FileWriter("C:\\Users\\Bocus\\IdeaProjects\\Java-Study-01\\src\\main\\java\\org\\example\\clerks.txt")) {
            ///This is the writer needed to overwrite the existing file with clerks
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
                    String tmpdat = in.next()+"\n"; ///reading the clerks info to add to the array, \n is essential due to the formation of the file
                    int c = 0;
                    for (Clerk clerkk : tmpclerk) {
                        if (clerkk.getID() == tmpdat.split(";")[0]) {
                            System.out.print("Err, ID already exists!"); ///will occur if entry with this ID already exists
                            c++;
                        }
                    }
                    if (c == 0) {
                        String[] newdata = tmpdat.split(";");
                        Clerk newclerk = new Clerk(newdata[0], newdata[1], newdata[2], Double.parseDouble(newdata[3]));
                        tmpclerk.add(newclerk);///adding new clerk to the clerk array
                        tmpclerk.forEach(clerk ->{
                            try {
                                writer.write(String.valueOf(clerk+"\n"));///overwriting the file
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
    }
    public void method3(){

    }
    public void method4(){

    }
    public void method5(){

    }

    }