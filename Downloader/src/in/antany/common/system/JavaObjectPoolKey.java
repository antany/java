package in.antany.common.system;


public class JavaObjectPoolKey {
	private String className = null;
	private int identifier = 1;

	JavaObjectPoolKey(String className, int identifer) {
		this.className = className;
		this.identifier = identifer;
	}

	@Override
	public boolean equals(Object obj) {
		JavaObjectPoolKey jop = (JavaObjectPoolKey) obj;
		return this.className.equals(jop.getClassName());
	}

	@Override
	public int hashCode() {
		return identifier;
	}

	public String getClassName() {
		return className;
	}
}
