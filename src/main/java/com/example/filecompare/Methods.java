package com.example.filecompare;

import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Methods {

    public static Scanner userInput = new Scanner(System.in);

    public static String[] filesCompareByLine(String file1Name, String file2Name) throws IOException {

        BufferedReader reader1 = new BufferedReader(new FileReader(file1Name));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2Name));

        String file1 = reader1.readLine();
        String file2 = reader2.readLine();


        String[] difference = new String[3];
        difference[1] = "";
        difference[2] = "";

        boolean areEqual = true;

        int lineNum = 1;

        while (file1 != null && file2 != null) {
            if (file1 == null && file2 == null) {
                areEqual = false;

                if (areEqual) {
                    System.out.println("Two files have same content.");


                    System.out.println("File1 has " + file1 + " and File2 has " + file2 + " at line " + lineNum);
                } else {
                    System.out.println("Two files have different content. They differ at line " + lineNum);
                    difference[1] += lineNum + ": " + file1 + "\n";
                    difference[2] += lineNum + ": " + file2 + "\n";

                    System.out.println("File1 has " + file1 + " and File2 has " + file2 + " at line " + lineNum);

                }
//                break;
            } else if (!file1.equalsIgnoreCase(file2)) {
                areEqual = false;

                if (areEqual) {
                    System.out.println("Two files have same content.");


                    System.out.println("File1 has " + file1 + " and File2 has " + file2 + " at line " + lineNum);
                } else {
                    System.out.println("Two files have different content. They differ at line(s) " + lineNum + "\n");
                    difference[1] += lineNum + ": " + file1 + "\n";
                    difference[2] += lineNum + ": " + file2 + "\n";

                    System.out.println("File1 has " + file1 + " and File2 has " + file2 + " at line " + lineNum);

                }
//                break;

            }


            file1 = reader1.readLine();

            file2 = reader2.readLine();

            lineNum++;
        }

        reader1.close();

        reader2.close();

        return difference;
    }

    public static String[] filesCompareByLineSimilar(String file1Name, String file2Name) throws IOException {

        BufferedReader reader1 = new BufferedReader(new FileReader(file1Name));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2Name));

        String file1 = reader1.readLine();
        String file2 = reader2.readLine();


        String[] similar = new String[3];
        similar[1] = "";
        similar[2] = "";


        boolean areEqual = true;

        int lineNum = 1;

        while (file1 != null && file2 != null) {
            if (file1.equalsIgnoreCase(file2)) {
                System.out.println("Two files have same content.");
                    similar[1] += lineNum + ": " + file1 + "\n";
                    similar[2] += lineNum + ": " + file2 + "\n";

//                break;
                similar[1] += lineNum + ": " + file1 + "\n";
                similar[2] += lineNum + ": " + file2 + "\n";
            } else if (!file1.equalsIgnoreCase(file2)) {
                System.out.println("Two files have different content. They differ at line(s) ");
            }



            file1 = reader1.readLine();

            file2 = reader2.readLine();

            lineNum++;
        }

        reader1.close();

        reader2.close();

        return similar;
    }

    public static String readFile(String file) {
        String fileText = "";
        int lineNum = 1;
        try {
            try (Scanner sc = new Scanner(new File((file)))) {
                if (!sc.hasNext()) {
                    fileText = "File is empty";
                } else {
                    while (sc.hasNext()) {
                        String line = sc.nextLine();
                        fileText += lineNum + ": " + line + "\n";
                        lineNum++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("readFile failed: " + e.getMessage());
        }
        return fileText;
    }

    public static String getFile(String files) {
        Path filePath = Paths.get(files);
        String file = filePath.toString();
        Path fileName = filePath.getFileName();

        return file;
    }

    public static void main(String[] args) throws IOException {
        int i = 1;
        String file1 = null;
        String file2 = null;

        while (i <= 2) {
            System.out.println("Enter the path to file: ");
//            getFile(userInput.next());
            if (i == 1) {
                file1 = readFile(getFile(userInput.next()));
            } else if (i == 2) {
                file2 = readFile(getFile(userInput.next()));
            }
            i++;
        }

        System.out.println(file1);
        System.out.println(file2);

    }

}


