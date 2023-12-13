package fileConfig;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileConfiguration {

    public static File getFileFomPath(String path) {
        return new File(path);
    }

    public static String readFileAsString(File file)
    {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
