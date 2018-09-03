package codesample.java_se_api.io_and_nio.nio;


import codesample.java_se_api.io_and_nio.io.FilesCreatorIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DirectoryStreamExample {
    public static void main (String[] args) {

        /* This 2 calls will create directories for both nio and io files.
        * We will try to filter out only directory with 'nio' as part of the name*/
        FilesCreatorNIO.createFiles();
        FilesCreatorIO.createFiles();

        Path startPath = Paths.get(FilesCreatorNIO.getDirNIOFiles()).getParent();

        /* Only accept directories which contain NIO. DirectoryStream can be worked with using foreach cycle */
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(startPath,
                e -> e.toString().toLowerCase().contains("nio"))) {
            for (Path path: ds) {
                System.out.println(path.toAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
