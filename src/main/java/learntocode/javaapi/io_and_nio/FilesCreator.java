package learntocode.javaapi.io_and_nio;

import java.io.File;
import java.io.IOException;

public class FilesCreator {

    private static String dirIOFiles = "/tmp/test_files_io";
    private static String dirNIOFiles = "/tmp/test_files_nio";

    private static String fileNameTemplate = "file_";

    public static String getDirIOFiles() { return dirIOFiles; }
    public static String getDirNIOFiles() { return dirNIOFiles; }
    public static String getFileNameTemplate() { return fileNameTemplate; }



    public static void createFilesIO () {
        createFiles(dirIOFiles);
    }

    public static void createFilesNIO() {
        createFiles(dirNIOFiles);
    }

    private static void createFiles(String dir) {
        int numOfFiles = 10;

        File directoryFile = new File(dirIOFiles);
        directoryFile.mkdirs();

        for (int i =0; i < numOfFiles; i++) {
            try {
                File f = new File(dirIOFiles, fileNameTemplate + i);
                if (f.exists()) {
                    f.delete();
                }
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    System.out.println("Could not create file " + fileNameTemplate + i + " in directory " + dirIOFiles);
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
