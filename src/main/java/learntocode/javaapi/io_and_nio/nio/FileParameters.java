package learntocode.javaapi.io_and_nio.nio;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Show some methods
 */
public class FileParameters {
    public static void main(String[] args) {
        Path path = FilesCreatorNIO.createFiles()[0];

        checkAndPrint("Writable", Files::isWritable, path);
        checkAndPrint("Readable", Files::isReadable, path);
        checkAndPrint("Executable", Files::isExecutable, path);
        checkAndPrint("Symbolic Link", Files::isSymbolicLink, path);
        checkAndPrint("Regular file", Files::isRegularFile, path);
        checkAndPrint("Directory", Files::isDirectory, path);
    }

    private static void checkAndPrint(String checkName, BooleanByPathFuncInterface function, Path p) {
        if (function.check(p))
            System.out.println("File IS " + checkName );
        else
            System.out.println("File IS NOT " + checkName);
    }

    private interface BooleanByPathFuncInterface {
        boolean check(Path p);
    }
}
