package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClerkEXE {

    public static void main(String[] args) {
        ArrayList<Clerk> clerkArrayList;
        Scanner in = new Scanner(System.in);
        clerkArrayList = new ArrayList<>();
        int ch = -1;
        while (ch != 6) {
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
            ch = in.nextInt();
            switch (ch) {
                case 1: {
                    try (FileReader reader = new FileReader("C:\\Users\\alexv\\IdeaProjects\\MyFirstProject\\src\\main\\java\\org\\example\\clerks.txt")) {
                        while (reader.ready()){
                            Clerk tmpclerk = reader.readline();
                        }

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                case 6: {
                    System.out.print("Exiting...");
                    break;
                }
                default: {
                    ch = -1;
                    System.out.print("Wrong choise, my friend!");
                    break;
                }
            }
        }

    }


    }
