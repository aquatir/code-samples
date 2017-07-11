package learn_to_code.java_api.io_and_nio.io;

import java.io.File;
import java.io.IOException;

/**
 * Provide methods to create directories with files on you machine.
 * Is used in IO package examples
 * <br>
 * Change directories in this file to ones you like better
 */
public class FilesCreatorIO {

    private static String dirIOFiles;

    private static String fileNameTemplate = "file_";

    public static String getDirIOFiles() { return dirIOFiles; }
    public static String getFileNameTemplate() { return fileNameTemplate; }

    public static File[] createFiles() {
        setDirsByOs();
        int numOfFiles = 10;
        File[] files = new File[numOfFiles];
        File directoryFile = new File(dirIOFiles);
        directoryFile.mkdirs();

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
            dirIOFiles = homeDrive + "\\test_files_io";
        } else {
            dirIOFiles = "/tmp/test_files_io";
        }

        System.out.println("Setting test files directories to: \n" +
                "  IO: " + dirIOFiles);
    }
}
