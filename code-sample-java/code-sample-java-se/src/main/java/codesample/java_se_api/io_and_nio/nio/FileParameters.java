package codesample.java_se_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

/**
 * Shows some methods which you can query from files.
 * Also note static class references used here. For more of this check out codesample/java_se_api/lambdas
 */
public class FileParameters {
    public static void main(String[] args) {
        Path path = FilesCreatorNIO.createFiles()[0];

        System.out.println("File name: " + path.getFileName());
        System.out.println("File path: " + path);
        System.out.println("Absolute path: " + path.toAbsolutePath());
        System.out.println("Parent dir: " + path.getParent());

        checkAndPrint("Writable", Files::isWritable, path);
        checkAndPrint("Readable", Files::isReadable, path);
        checkAndPrint("Executable", Files::isExecutable, path);
        checkAndPrint("Symbolic Link", Files::isSymbolicLink, path);
        checkAndPrint("Regular file", Files::isRegularFile, path);
        checkAndPrint("Directory", Files::isDirectory, path);

        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("File creation time: " + attr.creationTime());
            System.out.println("File size: " + attr.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void checkAndPrint(String checkName, Predicate<Path> function, Path p) {
        if (function.test(p))
            System.out.println("File IS " + checkName );
        else
            System.out.println("File IS NOT " + checkName);
    }
}
