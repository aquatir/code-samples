package learntocode.javaapi.io_and_nio.io;

import learntocode.javaapi.io_and_nio.FilesCreator;

import java.io.File;
import java.util.Arrays;

/**
 * Class assumes that test files were already created with {@link learntocode.javaapi.io_and_nio.FilesCreator}
 */
public class FileFilters {
    public static void main(String[] args) {

        FilesCreator.createFilesIO();
        String dir = FilesCreator.getDirIOFiles();
        String template = FilesCreator.getFileNameTemplate();

        File startingDir = new File(dir);

        String[] listOfDirs = startingDir.list((dir1, name) -> {

            /* cut file name 'file_xxx' to 'xxx' then parse it as integer
            * Leave out only files which ends with even numbers */
            int fileNum = Integer.parseInt(name.substring(name.indexOf("_") + 1));
            return (fileNum % 2) == 0;
        });

        Arrays.sort(listOfDirs);
        Arrays.stream(listOfDirs).forEach(c -> System.out.println(c));
    }
}
