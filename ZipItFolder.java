import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipItFolder {
    List fileList;
    private static String OUTPUT_ZIP_FILE = "C:\\Users\\Luka\\Desktop\\რაღაცები\\ენების დოკუმენტაციები\\blah.zip";
    private static String SOURCE_FOLDER = "C:\\Users\\Luka\\Desktop\\რაღაცები\\ენების დოკუმენტაციები\\blah";
    ZipItFolder(){
        fileList = new ArrayList();
    }
    public static void main( String[] args )
    {
        File folder = new File("C:\\Users\\Luka\\Documents\\XuanZhi\\Pictures\\Bleach – Full Color — {Official TL} (EN)");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            OUTPUT_ZIP_FILE = listOfFiles[i]+".zip";
            SOURCE_FOLDER = String.valueOf(listOfFiles[i]);

            System.out.println(OUTPUT_ZIP_FILE);
            System.out.println(SOURCE_FOLDER);

            ZipItFolder appZip = new ZipItFolder();
            appZip.generateFileList(new File(SOURCE_FOLDER));
            appZip.zipIt(OUTPUT_ZIP_FILE);

        }

    }
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile){
        byte[] buffer = new byte[1024];
        try{
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            System.out.println("Output to Zip : " + zipFile);
            for(Object file : this.fileList){
                System.out.println("File Added : " + file);
                ZipEntry ze= new ZipEntry(file.toString());
                zos.putNextEntry(ze);
                FileInputStream in =
                        new FileInputStream(SOURCE_FOLDER + File.separator + file);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();
            //remember close it
            zos.close();
            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Traverse a directory and get all files,
     * and add the file into fileList
     * @param node file or directory
     */
    public void generateFileList(File node){
        //add file only
        if(node.isFile()){
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }
        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                generateFileList(new File(node, filename));
            }
        }
    }
    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file){
        return file.substring(SOURCE_FOLDER.length()+1, file.length());
    }
}
