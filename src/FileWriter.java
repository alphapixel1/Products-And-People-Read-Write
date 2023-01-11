import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileWriter {
    private BufferedWriter writer;
    public FileWriter(String dir,String fileName) throws IOException {
        Path file = Paths.get(dir +"\\"+fileName);
        if(file.toFile().exists())
            file.toFile().delete();
        OutputStream out =new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        writer = new BufferedWriter(new OutputStreamWriter(out));
    }
    public void Close() throws IOException {
        writer.close();
    }
    public void WriteLine(String s) throws IOException {
        writer.write(s+"\n");
    }
    public void Write(String s) throws IOException {
        writer.write(s);
    }
}
