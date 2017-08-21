package learn_to_code.javaSE_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provide methods to create directories with files on you machine.
 * Is used in NIO package examples
 * <br>
 * Change directories in this file to ones you like better
 */
public class FilesCreatorNIO {

    private static String dirNIOFiles;

    private static String fileNameTemplate = "file_";

    public static String getDirNIOFiles() { return dirNIOFiles; }
    public static String getFileNameTemplate() { return fileNameTemplate; }

    public static Path[] createFiles() {
        setDirsByOs();
        int numOfFiles = 10;
        Path[] filePaths = new Path[numOfFiles];
        Path directoryFile = Paths.get(dirNIOFiles);
        try {
            Files.createDirectories(directoryFile);
        } catch (IOException e) {
            System.out.println("Could not create directories to" + directoryFile.toString());
            e.printStackTrace();
        }

        for (int i =0; i < numOfFiles; i++) {
            try {
                Path f = Paths.get(dirNIOFiles, fileNameTemplate + i);
                if (Files.exists(f)) {
                    Files.delete(f);
                }
                System.out.println("Creating file " + f.toString() + " ... Success!");
                filePaths[i] = Files.createFile(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Initialization finished. Your output: \n");
        return filePaths;
    }


    private static void setDirsByOs() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            System.out.println("Current OS is Windows");
            String homeDrive = System.getProperty("user.home");
            dirNIOFiles = homeDrive + "\\test_files_nio";
        } else {
            dirNIOFiles = "/tmp/test_files_nio";
        }

        System.out.println("Setting test files directories to: \n" +
                "  NIO: " + dirNIOFiles);
    }
}
