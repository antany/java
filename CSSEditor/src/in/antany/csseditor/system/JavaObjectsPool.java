package in.antany.csseditor.system;

import java.util.HashMap;

public class JavaObjectsPool {
	private static HashMap<String, Object> objectPoolMap;
	static {
		objectPoolMap = new HashMap<>();
	}

	public static void putObject(Object obj) {
		objectPoolMap.put(obj.getClass().getName(), obj);
	}

	public static Object getObject(Class<? extends Object> className) {
		return objectPoolMap.get(className.getName());
	}
}
