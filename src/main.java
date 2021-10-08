
public class main {
    public static void main(String[] args) {
		Memory m = new Memory(15);
		m.put("hello");
		m.put("hell");
		m.remove("hell");
		m.put("what");
		m.put("who");
		m.put("ten");
		m.remove("who");
		m.put("ayo");

		System.out.println(m.get(5));
    }
}
