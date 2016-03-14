package logic;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriterListing {

    private int counter = 1;
    private String someResource = "File resource:";
    private String fileNameForReading;
    private String outFileName;

    public FileWriterListing(String fileNameForReading) {
        this.fileNameForReading = fileNameForReading;
        outFileName = createOutListingFile();
        try {
            fileWork();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String createOutListingFile(){
        String TXTexpansion = ".txt";
        String cangedStr = fileNameForReading.substring(0, fileNameForReading.lastIndexOf("."));
        return cangedStr + TXTexpansion;
    }

    private String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return format.format(new Date());
    }

    public String getOutFileName() {
        return outFileName;
    }

    private void fileWork() throws IOException{

        File file1 = new File(fileNameForReading);
        File file2 = new File(outFileName);

        try {

            BufferedReader in = new BufferedReader(new FileReader(file1.getAbsoluteFile()));
            PrintWriter out = new PrintWriter(file2);

            try {
                out.write(someResource + "  " + fileNameForReading);
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
}
