import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class CsvInputOutput {
    private LinkedHashMap<String,ColumnType> columns;

    public CsvInputOutput(LinkedHashMap<String,ColumnType> columns){
        this.columns=columns;
    }
    public static String getWorkingPath(){
        return new File(System.getProperty("user.dir")).getPath()+"\\src";
    }
    public void Generate(String outputFileName){
        Scanner s= new Scanner(System.in);
        List<List<String>> data=new ArrayList<>();
        do{
            List<String> row=new ArrayList<>();
            for (Map.Entry<String, ColumnType> col : columns.entrySet()) {
                switch (col.getValue()){
                    case INT:
                       row.add(SafeInput.getInt(s,col.getKey())+"");
                        break;
                    case DOUBLE:
                        row.add(SafeInput.getDouble(s,col.getKey())+"");
                        break;
                    case STRING:
                        row.add(SafeInput.getNonZeroLenString(s,col.getKey()));
                        break;
                }
            }
            data.add(row);
        }while (SafeInput.getYNConfirm(s,"Add More?"));

        try{
            FileWriter fw = new FileWriter(getWorkingPath(), outputFileName);
            for (List<String> row:data) {
                String line=String.join(",",row.toArray(new String[0]));
                //System.out.println(line);
                fw.WriteLine(line);
            }
            fw.Close();
        }catch (IOException e){
            System.out.println(e.getCause());
        }

    }
    public void Read(String tableName){
        FileOpener.ShowFileDialog(f -> {
            if(f==null)
                Read(tableName);
            else {
                try {
                    PrintData(f,tableName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || ( f.getName().endsWith(".csv") || f.getName().endsWith(".txt"));
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }
    private void PrintData(File f,String tableName) throws IOException{
        InputStream in = new BufferedInputStream(Files.newInputStream(f.toPath()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));


        String l=reader.readLine();
        List<List<String>> data= new ArrayList<>();
        while(l!=null) {
            String[] s=l.split(",");
            if(s.length==columns.size()){
                data.add(Arrays.asList(s));
            }else{
                System.err.println("Line is not valid: \t"+l);
            }

            l = reader.readLine();
        }
        Object[] colnames = columns.keySet().toArray();
        ConsoleTable.PrintTable(tableName,
                Arrays.asList(Arrays.copyOf(colnames,colnames.length, String[].class)),
                data
        );
    }

}
