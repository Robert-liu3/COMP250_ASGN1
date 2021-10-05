import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLOutput;

public class main {
    public static void main(String[] args) {
        Memory memory = new Memory(24);
        //System.out.println("Memory constructor works");
        memory.put("Hello");
        memory.put("goodbye");
        memory.put("cya");
        memory.put("ohboy");
        //System.out.println("put method works");
        //memory.remove(2);
        memory.remove("Hello");
        String print = memory.get(1);

        int printNum = memory.get("cya");
        System.out.println(printNum);
    }
}
