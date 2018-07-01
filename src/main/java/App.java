import algorithm.BoyerMoore;
import service.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        if (args.length > 0) {
            System.out.println(args[0]);
        }


        FileUtils service2 = new FileUtils();
        List<String> contents = new ArrayList<>();

        try {
            List<Path> paths = service2.getPathFiles();

            for (Path path : paths) {
                contents.add(service2.getFileContent(path));
            }

            String pat = "Lorem ipsum";

            BoyerMoore boyermoore = new BoyerMoore(pat);

            Integer[] offset1 = boyermoore.search(contents.get(1));

            System.out.print("Se han encontrado " + offset1.length + " coincidencias.");

        } catch (IOException e) {
            System.out.println("ERROR!!!!!!!");
            System.out.println("MESSAGE -> + " + e.getMessage());
            System.out.println("CAUSE -> " + e.getCause());
        }
    }

}
