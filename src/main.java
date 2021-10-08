
public class main {
    public static void main(String[] args) {
		Memory m = new Memory(10);
		m.put("hello");
		m.put("hell");
		System.out.println(m.put("hello")); //-1
		m.remove(0);
		System.out.println(m.put("hello")); //2
		System.out.println(m.get("hello")); //2
		System.out.println(m.memoryArray);
    }
}
