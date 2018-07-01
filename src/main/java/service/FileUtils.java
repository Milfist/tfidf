package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    /**
     * @return
     * @throws IOException
     */
    public List<Path> getPathFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get("/Users/Mik/Documents/tfidf"))) {
            return paths.filter(Files::isRegularFile).collect(Collectors.toList());
        }
    }

    /**
     * @param path
     * @return
     */
    public String getFileContent(Path path) {

        String content = "";

        if (path.toString().contains(".txt")) {

            try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

                content = br.lines().collect(Collectors.joining("\n"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
