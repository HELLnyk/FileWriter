import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriterListing {

    private int counter = 1;
    private String someResource = "File resource";
    private String fileNameForReading;

    public FileWriterListing(String fileNameForReading) {
        this.fileNameForReading = fileNameForReading;
    }

    private String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return format.format(new Date());
    }

    private void fileWork(String inFileName, String outFileName) throws FileNotFoundException, IOException{

        File file1 = new File(inFileName);
        File file2 = new File(outFileName);

        try {
            if (!file1.exists()){
                throw new FileNotFoundException(file1.getName());
            }
            if (!file2.exists()){
                file2.createNewFile();
            }

            BufferedReader in = new BufferedReader(new FileReader(file1.getAbsoluteFile()));
            PrintWriter out = new PrintWriter(file2);

            try {
                out.write(someResource + "  " + inFileName);
                out.write('\n');
                out.write(getDate());
                out.write('\n');
                String line;
                while ((line = in.readLine()) != null){
                    if (counter <= 9){
                        out.write(counter + ":   " + line);
                        out.write('\n');
                    }
                    else if (counter > 10 && counter < 100) out.write(counter + ":  " + line + '\n');
                    else if (counter >= 100) out.write(counter + ": " + line + '\n');
                    counter++;
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            finally {
                in.close();
                out.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

//    public static void main (String[] args) throws IOException {
//
//        String fileNameForReading = "D:\\KPI\\IV_kurs\\II_semestr\\Multithreading_programming_in_Java\\Lab1\\src\\Lab1\\Client.java";
//        String fileForWriting = "D:\\KPI\\IV_kurs\\II_semestr\\Multithreading_programming_in_Java\\client.txt";
//
//        FileWriterListing fileWriterListing = new FileWriterListing();
//        fileWriterListing.fileWork(fileNameForReading, fileForWriting);
//    }
}
