import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipIt {
    public static void zipOneFile(){
        byte[] buffer = new byte[1024];
        Path path = Paths.get("C:\\Users\\Luka\\Desktop\\რაღაცები\\ენების დოკუმენტაციები");
        File[] listOfFiles = path.toFile().listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                FileOutputStream fos = new FileOutputStream(listOfFiles[i]+".zip");
                ZipOutputStream zos = new ZipOutputStream(fos);
                ZipEntry ze = new ZipEntry("something");
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(Objects.requireNonNull(listOfFiles)[i]);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();
                zos.close();
                System.out.println("done");
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        }


    public static void main(String[] args) {
        ZipIt.zipOneFile();
    }

}
