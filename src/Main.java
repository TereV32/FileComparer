import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {


    public static void filesCompareByLine(Path path1, Path path2) throws IOException {

        BufferedReader reader1 = new BufferedReader(new FileReader(String.valueOf(path1)));

        BufferedReader reader2 = new BufferedReader(new FileReader(String.valueOf(path2)));

        String line1 = reader1.readLine();

        String line2 = reader2.readLine();

        boolean areEqual = true;

        int lineNum = 1;

        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                if (areEqual) {
                    System.out.println("Two files have same content.");
                } else {
                    System.out.println("Two files have different content. They differ at line " + lineNum);

                    System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);

                }
//                break;
            } else if (!line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                if (areEqual) {
                    System.out.println("Two files have same content.");
                } else {
                    System.out.println("Two files have different content. They differ at line " + lineNum);

                    System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);

                }
//                break;
            }

            line1 = reader1.readLine();

            line2 = reader2.readLine();

            lineNum++;
        }

//        if (areEqual) {
//            System.out.println("Two files have same content.");
//        } else {
//            System.out.println("Two files have different content. They differ at line " + lineNum);
//
//            System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
//
//        }

        reader1.close();

        reader2.close();
    }

    public static void readFile(String fileName) {
        try {
            try (Scanner sc = new Scanner(new File((fileName)))) {
                if (!sc.hasNext()) {
                    System.out.println("File is empty");
                } else {
                    while (sc.hasNext()) {
                        String line = sc.nextLine();
                        System.out.println(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("readFile failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        int i = 1;
        String file1 = null;
        String file2 = null;
        Path pathFile1 = null;
        Path pathFile2 = null;
        Path file1Name = null;
        Path file2Name = null;


        while (i <= 2) {
            System.out.println("Enter the path to file: ");
            Scanner s1 = new Scanner(System.in);
            if (i == 1) {
//                file1Path = s1.next();
                pathFile1 = Paths.get(s1.next());
                file1 = pathFile1.toString();
                file1Name = pathFile1.getFileName();
            } else if (i == 2) {
                pathFile2 = Paths.get(s1.next());
                file2 = pathFile2.toString();
                file2Name = pathFile2.getFileName();
            }
            i++;
        }

        System.out.println(file1Name.toString());
        readFile(file1);
        System.out.println(file2Name.toString());
        readFile(file2);

        filesCompareByLine(pathFile1, pathFile2);
    }

    //This is a comment
    //Hello
}

