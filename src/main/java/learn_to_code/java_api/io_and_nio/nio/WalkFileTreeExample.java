package learn_to_code.java_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreeExample {
    public static void main(String[] args) {

        FilesCreatorNIO.createFiles();
        Path startDir = Paths.get(FilesCreatorNIO.getDirNIOFiles());
        System.out.println(startDir);

        /*walk files in this directory using MyFileVisitor() */
        try {
            Files.walkFileTree(startDir, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You can override methods in this class to determine how directory should be walked
     */
    public static class MyFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file);
            return FileVisitResult.CONTINUE;
        }
    }
}
