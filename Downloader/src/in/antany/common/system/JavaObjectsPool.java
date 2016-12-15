package in.antany.common.system;

import in.antany.common.system.exception.ObjectAlreadyExistsException;

import java.util.HashMap;

public class JavaObjectsPool {
	private static HashMap<JavaObjectPoolKey, Object> objectPoolMap;
	static {
		objectPoolMap = new HashMap<>();
	}

	public static void putObject(Object obj) {
		putObject(obj, 1);
	}

	public static Object getObject(Class<? extends Object> className) {
		return getObject(className, 1);
	}

	public static Object getObject(Class<? extends Object> className,
			int identifier) {

		JavaObjectPoolKey jobsKey = new JavaObjectPoolKey(className.getName(),
				identifier);
		return objectPoolMap.get(jobsKey);
	}

	public static void putObject(Object obj, int identifier) {
		JavaObjectPoolKey jobsKey = new JavaObjectPoolKey(obj.getClass()
				.getName(), identifier);

		if (objectPoolMap.get(jobsKey) != null) {
			throw new ObjectAlreadyExistsException();
		}
		objectPoolMap.put(jobsKey, obj);
	}

}
