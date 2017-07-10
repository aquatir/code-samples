package learntocode.javaapi.io_and_nio.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provide methods to create directories with files on you machine.
 * Is used in every class io and nio packages examples
 * <br>
 * Change directories in this file to ones you like better
 */ /*
public class FilesCreatorNIO {

    private static String dirIOFiles;
    private static String dirNIOFiles;

    private static String fileNameTemplate = "file_";

    public static String getDirNIOFiles() { return dirNIOFiles; }
    public static String getFileNameTemplate() { return fileNameTemplate; }

    public static void createFilesNIO() {
        createFiles(dirNIOFiles);
    }

    private static File[] createFiles(String dir) {
        int numOfFiles = 10;
        Path[] files = new Path[numOfFiles];
        Path directoryFile = Paths.get(dirIOFiles);
        //directoryFile.

        for (int i =0; i < numOfFiles; i++) {
            try {
                File f = new File(dirIOFiles, fileNameTemplate + i);
                if (f.exists()) {
                    f.delete();
                }
                System.out.print("Creating file " + f.getAbsolutePath() + " ... ");
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    System.out.println(" ERROR! Could not create file " + fileNameTemplate + i + " in directory " + dirIOFiles);
                    break;
                } else {
                    files[i] = f;
                    System.out.println("Success!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Initialization finished. Your output: \n");
        return files;
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
*/