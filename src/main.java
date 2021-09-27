import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLOutput;

public class main {
    public static void main(String[] args) {
        Memory memory = new Memory(24);
        //System.out.println("Memory constructor works");
        memory.put("Hello");
        memory.put("goodbye");
        //System.out.println("put method works");
        String print = memory.getString(2);
        System.out.println(print);
    }
}
