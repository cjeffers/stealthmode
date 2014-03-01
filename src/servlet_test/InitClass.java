package servlet_test;

import java.util.*;

public class InitClass {
	private static List<String> l = null;
	
	public static void init() {
		l = new ArrayList<String>();
		l.add("Server context liiiiiivvvvvessss");
	}
	
	public static String getFirstString() {
		if (l == null) return "Uninitialized";
		return l.get(0);
	}
}
