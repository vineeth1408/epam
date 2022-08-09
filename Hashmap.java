package demo;
import java.util.HashMap;
import java.util.Map;

class Hashmap {
	public static void main(String[] epam) {
		HashMap<Integer,String> hm = new HashMap<>();
		hm.put(1,"vineeth");
		hm.put(2,"suresh");
		System.out.println(hm);
		for(Map.Entry m:hm.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
	}
}