package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TfIdfService {

    public void getPathFiles() {

        try (Stream<Path> paths = Files.walk(Paths.get("/Users/Mik/Documents/tfidf"))) {
            paths.filter(Files::isRegularFile).forEach(v -> {
                if (v.toString().contains(".txt")) leer(v);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leer(Path ruta) {

        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            System.out.println(br.lines().collect(Collectors.joining("\n")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

