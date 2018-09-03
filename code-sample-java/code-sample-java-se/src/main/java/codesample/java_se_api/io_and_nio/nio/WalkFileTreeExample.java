package codesample.java_se_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class WalkFileTreeExample {
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
    static class MyFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            System.out.println(file);
            return FileVisitResult.CONTINUE;
        }
    }
}
