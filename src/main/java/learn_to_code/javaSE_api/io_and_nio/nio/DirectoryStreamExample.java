package learn_to_code.javaSE_api.io_and_nio.nio;

import learn_to_code.javaSE_api.io_and_nio.io.FilesCreatorIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryStreamExample {
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
