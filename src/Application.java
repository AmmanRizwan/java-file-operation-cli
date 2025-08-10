import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    private static final String PATHNAME = System.getProperty("user.dir");
    private static Scanner SCANNER = new Scanner(System.in);

    // The user can check how many files present into their directory
    public static void ShowFiles() {
        try {
            File file = new File(PATHNAME);

            String[] allFiles = file.list();

            if (allFiles == null) {
                System.out.println("There is no files found into your directory please create one.");
                System.out.println(PATHNAME);
            }
            else {
                System.out.println("All File Present in your directory: \n");
                for (String files : allFiles) {
                    System.out.println("- " + files);
                }
                System.out.println("\nTotal Files: " + allFiles.length);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong please try again!");
            e.printStackTrace();
        }
    }

    // The user can create a new file that is not present into their directory
    public static void CreateFile() {
        try {
            System.out.print("Enter name of the file: ");
            String fileName = SCANNER.nextLine();
            System.out.println("It only support text file right now:");
            System.out.print("Enter a extension (txt): ");
            String extensionName = SCANNER.nextLine().replace(".", "");

            File file = new File(PATHNAME + "/" + fileName + "." + extensionName);

            if (!extensionName.equals("txt")) {
                System.out.println("Please provide an text file format extension");
            }
            else if (file.createNewFile()) {
                System.out.println("File Created: ");
                System.out.println(file.getPath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    // The user can append the file using the Writing file
    public static void WriteFile() {
        FileWriter writer = null;

        System.out.print("Enter the File name: ");
        String fileName = SCANNER.nextLine();
        File file = new File(PATHNAME + "/" + fileName + ".txt");

        if (!file.exists()) {
            System.out.println("File doesn't exists. Please check the name of the file.");
        } else {
            try {
                writer = new FileWriter(file.getName(), true);

                System.out.println("To exit - :q");
                while (true) {
                    String line = SCANNER.nextLine();
                    if (line.equals(":q")) {
                        break;
                    }

                    writer.write(line + "\n");
                    writer.flush();
                }
            } catch (IOException e) {
                System.err.println("Error writting to file: " + e.getMessage());
                e.printStackTrace();
            } finally {
                SCANNER.close();
            }
        }
    }

    // The user can read the file from the existing file
    public static void ReadFile() {
        FileReader reader;
        System.out.print("Enter the File name without extension: ");
        String fileName = SCANNER.nextLine();
        File file = new File(PATHNAME + "/" + fileName + ".txt");

        if (!file.exists()) {
            System.out.println("File doesn't exits into your directory.");
        } else {
            try {
                int character;
                reader = new FileReader(file);
                System.out.println("");

                while ((character = reader.read()) != -1) {
                    System.out.print((char) character);
                }

            } catch (IOException e) {
                System.err.println("Error on file Reading: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // The function for getting the size in the format of Bytes, KiloBytes, etc..
    private static String getReadableFileSize(long sizeInBytes) {
        if (sizeInBytes < 1024) return sizeInBytes + " B";
        if (sizeInBytes < 1024 * 1024) return String.format("%.2f KB", sizeInBytes / 1024.0);
        if (sizeInBytes < 1024 * 1024 * 1024) return String.format("%.2f MB", sizeInBytes / (1024.0 * 1024.0));
        return String.format("%.2f GB", sizeInBytes / (1024.0 * 1024.0 * 1024.0));
    }

    // The user can check the file information or metadata
    public static void InfoFile() {
        System.out.println("Enter the File name without extension: ");
        String fileName = SCANNER.nextLine();

        File file = new File(PATHNAME + "/" + fileName + ".txt");

        if (!file.exists()) {
            System.out.println("File doesn't exists into your directory");
        } else {
            System.out.println("File Metadata:\n");
            System.out.println("Name: " + file.getName());
            System.out.println("Size: " + getReadableFileSize(file.length()));
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Read: " + (file.canRead() ? "Yes" : "No") + ", Write: " + (file.canWrite() ? "Yes" : "No") +", Execute: " + (file.canExecute() ? "Yes" : "No"));
            System.out.println("Hidden: " + (file.isHidden() ? "Yes": "No"));
        }
    }

    // The user can delete the existing file.
    public static void DeleteFile() {
        System.out.print("Enter the File name without extension: ");

        String fileName = SCANNER.nextLine();

        File file = new File(PATHNAME + "/" + fileName + ".txt");

        if (!file.exists()) {
            System.out.println("File doesn't exists into your directory");
        } else {
            System.out.print("Do you want to Delete your text file? [Y/n]: ");
            String agree = SCANNER.nextLine().toLowerCase();

            if (agree.equals("y")) {
                boolean deleted = file.delete();

                if (deleted) {
                    System.out.println("File was deleted Successfully Deleted");
                } else {
                    System.out.println("Error on deleting file please check...");
                }
            } else {
                System.out.println("We are not going to delete your file. Fine :)");
            }
        }
    }
}
