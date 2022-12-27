import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.copy;
import static java.nio.file.Path.*;

public class Main {
    public static void main(String[] args) {

        //Path path = Paths.get("C:\\Users\\Luka\\Desktop\\რაღაცები\\ენების დოკუმენტაციები");

        File folder = new File("C:\\Users\\Luka\\Desktop\\რაღაცები\\ენების დოკუმენტაციები");
        File[] listOfFiles = folder.listFiles();

        System.out.println(listOfFiles[1].toPath());
        System.out.println(listOfFiles[1].toPath());
        System.out.println(listOfFiles[1].toPath());
        System.out.println(listOfFiles[1].toPath());

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }

            try {
                Files.copy(listOfFiles[i].toPath(), of(listOfFiles[i].toPath().getParent() + "\\folder\\"+i+".pdf"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}