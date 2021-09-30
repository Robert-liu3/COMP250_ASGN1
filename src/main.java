import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLOutput;

public class main {
    public static void main(String[] args) {
        Memory memory = new Memory(24);
        //System.out.println("Memory constructor works");
        memory.put("Hello");
        memory.put("goodbye");
        //System.out.println("put method works");
        memory.removeWithId(2);
        String print = memory.getString(1);

        int printNum = memory.getId("Hello");
        System.out.println(print + printNum);
    }
}
