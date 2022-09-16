package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClerkProcessor {


    public static void main(String[] args) {
        ArrayList<Clerk> clerkArrayList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String ch = "-1";
        try (FileReader reader = new FileReader("clerks.txt")) { ///Reading the contents of the file
            Scanner scan = new Scanner(reader);
            while (scan.hasNext()){
                String[] tmpinfo = (scan.nextLine().split(";"));
                Clerk tmpclerkinfo = new Clerk(tmpinfo[0], tmpinfo[1], tmpinfo[2], Double.parseDouble(tmpinfo[3]));
                clerkArrayList.add(tmpclerkinfo);

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
                case "1" -> {
                    method1(clerkArrayList);
                    break;
                }
                case "2" -> {
                    method2(clerkArrayList, in);
                    break;
                }
                case "3" -> {
                    method3(clerkArrayList, in);
                    break;
                }
                case "4" -> {
                    method4(clerkArrayList, in);
                    break;
                }
                case "5" -> {
                    method5(clerkArrayList);
                    break;
                }
                case "6" -> {
                    ch = "6";
                    System.out.print("Exiting...");
                    break;
                }
            }
        }
    }

    public static void method1(ArrayList<Clerk> tmpclerk){
        tmpclerk.forEach(clerkk ->{
            clerkk.printInfo();
        });
    }

    private static void method2(ArrayList<Clerk> tmpclerk, Scanner in) {
        System.out.println("Enter new clerk info as following: ID;Name;Birthday;Salary");
        String[] tmpdata = in.next().split(";");
        int c = 0;
        for (Clerk exclerk : tmpclerk){
            if (exclerk.getID().equals(tmpdata[0])){
                System.out.print("Err, ID already exists!"); ///will occur if entry with this ID already exists
                c++;
            }
        }
        if (c==0){
            Clerk newClerk = new Clerk(tmpdata[0], tmpdata[1], tmpdata[2], Double.parseDouble(tmpdata[3]));
            tmpclerk.add(newClerk);
        }
        try (FileWriter writer = new FileWriter("clerks.txt")) {
            tmpclerk.forEach(clerkk ->{
                try {
                    writer.write(String.valueOf(clerkk.getID()+";"+ clerkk.getName()+";"+clerkk.getBirthday()+";"+clerkk.getSalary()+"\n"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.print("Clerk info altered!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void method3(ArrayList<Clerk> tmpclerk, Scanner in){
        System.out.println("Type ID, name or birthday date of an employee:");
        ArrayList<String> outList = new ArrayList<String>(); ///list for the all clerks with /filter (inchar) option in any or all of the characteristics
        String inchar = in.next();
        for (Clerk clerkk : tmpclerk){
            ///System.out.println("dot"); ///I used this to test if clerks were added correctly
            if (clerkk.getID().contains(inchar)){
                outList.add(String.valueOf(clerkk.getID()+";"+ clerkk.getName()+";"+clerkk.getBirthday()+";"+clerkk.getSalary()));
            }
        }
        System.out.println(outList);
    }

    private static void method4(ArrayList<Clerk> tmpclerk, Scanner in){
        System.out.println("Type ID of the employee:");
        String inchar = in.next();
        for (Clerk clerkk : tmpclerk){
            if (clerkk.getID().equals(inchar)){
                System.out.println("Type new info: Name;Birthday;Salary");
                String[] alter = in.next().split(";");
                Clerk newinfo = new Clerk(inchar, alter[0], alter[1], Double.parseDouble(alter[2]));
                tmpclerk.set(tmpclerk.indexOf(clerkk), newinfo); ///setting the new info clerk with current ID
            }
        }
        try (FileWriter writer = new FileWriter("clerks.txt")) {
            tmpclerk.forEach(clerkk ->{
                try {
                    writer.write(String.valueOf(clerkk.getID()+";"+ clerkk.getName()+";"+clerkk.getBirthday()+";"+clerkk.getSalary()+"\n"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.print("Clerk info altered!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void method5(ArrayList<Clerk> tmpclerk){
        System.out.println("The sum of the salaries is:");
        Double outSal = Double.valueOf(0);
        for (Clerk clerk : tmpclerk){
            outSal += clerk.getSalary();
        }///Summarising all the salaries, needed to add parseDouble as entries are String
        System.out.println(outSal);
    }

}
