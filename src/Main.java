import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {

        Scanner s=new Scanner(System.in);
        int input=0;
        do {
            System.out.println("1. Product Generate");
            System.out.println("2. Product Read");
            System.out.println("3. People Generate");
            System.out.println("4. People Read");
            System.out.println("5. Exit");
            input=SafeInput.getInt(s,"Selection");
        }while (input>5 || input<1);
        switch (input){
            case 1:
                ProductGenerate();
                break;
            case 2:
                ProductRead();
                break;
            case 3:
                PeopleGenerate();
                break;
            case 4:
                PeopleRead();
        }
    }
    public static void ProductGenerate(){
        CsvInputOutput cio=new CsvInputOutput(ProductColumns());
        cio.Generate("ProductGeneratorOutput.csv");
    }
    public static void ProductRead(){
        CsvInputOutput cio=new CsvInputOutput(ProductColumns());
        cio.Read("Products");
    }
    public static void PeopleGenerate(){
        CsvInputOutput cio=new CsvInputOutput(PeopleColumns());
        cio.Generate("PeopleGeneratorOutput.csv");
    }
    public static void PeopleRead(){
        CsvInputOutput cio=new CsvInputOutput(PeopleColumns());
        cio.Read("People");
    }
    private static LinkedHashMap<String,ColumnType> ProductColumns(){
        LinkedHashMap<String,ColumnType> columns=new LinkedHashMap<>();
        columns.put("ID#",ColumnType.STRING);
        columns.put("Name",ColumnType.STRING);
        columns.put("Description",ColumnType.STRING);
        columns.put("Price",ColumnType.DOUBLE);
        return columns;
    }
    private static LinkedHashMap<String,ColumnType> PeopleColumns(){
        LinkedHashMap<String,ColumnType> columns=new LinkedHashMap<>();
        columns.put("ID#",ColumnType.STRING);
        columns.put("First Name",ColumnType.STRING);
        columns.put("Last Name",ColumnType.STRING);
        columns.put("Title",ColumnType.STRING);
        columns.put("Year Of Birth",ColumnType.INT);
        return columns;
    }
}
