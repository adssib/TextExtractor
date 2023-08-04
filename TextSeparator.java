import java.io.*;
import java.util.Scanner;

public class TextSeparator {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("Enter the output directory path: ");
        String outputDirectory = userInputScanner.nextLine();

        String inputFileName = "C:\\Users\\adib\\Desktop\\anime shit\\translation\\tsukihime_OG\\input.txt";
        Scanner scanner = null;
        int fileCounter = 0;

        String[] asteriskStrings = new String[10];

        for (int i = 0; i < 10; i++) {
            asteriskStrings[i] = "*s" + i;
        }

        try {
            scanner = new Scanner(new FileInputStream(inputFileName));
            System.out.println("File Found!");

            BufferedWriter currentWriter = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String asteriskString : asteriskStrings) {
                    if (line.startsWith(asteriskString)) {
                        fileCounter++;
                        System.out.println("Starting with scene: " + line); // Print lines starting with an asterisk

                        // Close the current writer if it exists
                        if (currentWriter != null) {
                            currentWriter.close();
                        }

                        // Create a new writer with the appropriate file name and specified directory
                        String sceneTitle = line.substring(1, line.length() ).replace("*", "_"); // Remove the asterisks and replace with underscore
                        String fileName = outputDirectory + File.separator + sceneTitle + ".txt";
                        currentWriter = new BufferedWriter(new FileWriter(fileName));
                    }
                }

                // Write the line to the current file
                if (currentWriter != null) {
                    currentWriter.write(line);
                    currentWriter.newLine();
                }
            }

            // Close the last writer if it exists
            if (currentWriter != null) {
                currentWriter.close();
            }

            System.out.println("Number of files found is: " + fileCounter);

        } catch (FileNotFoundException e) {
            System.out.println("File Not found! Could Not open it!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            // Close the scanner
            if (scanner != null) {
                scanner.close();
            }
            // Close the user input scanner
            userInputScanner.close();
        }
    }
}
