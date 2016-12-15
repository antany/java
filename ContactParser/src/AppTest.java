import java.lang.reflect.Method;


public class AppTest{
 
	private int counter;
 
	public void printIt(){
		System.out.println("printIt() no param");
	}
 
	public void printItString(String temp){
		System.out.println("printIt() with param String : " + temp);
	}
 
	public void printItInt(int temp){
		System.out.println("printIt() with param int : " + temp);
	}
 
	public void setCounter(int counter){
		this.counter = counter;
		System.out.println("setCounter() set counter to : " + counter);
	}
 
	public void printCounter(){
		System.out.println("printCounter() : " + this.counter);
	}
}
@SuppressWarnings("unchecked")
class ReflectApp{
	
	public static void main(String[] args) {
 
	//no paramater
	@SuppressWarnings("rawtypes")
	Class noparams[] = {};
 
	//String parameter
	@SuppressWarnings("rawtypes")
	Class[] paramString = new Class[1];	
	paramString[0] = String.class;
 
	//int parameter
	@SuppressWarnings("rawtypes")
	Class[] paramInt = new Class[1];	
	paramInt[0] = Integer.TYPE;
 
	try{
	        //load the AppTest at runtime
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName("AppTest");
		Object obj = cls.newInstance();
 
		//call the printIt method
		Method method = cls.getDeclaredMethod("printIt", noparams);
		method.invoke(obj,  (Object[])null);
 
		//call the printItString method, pass a String param 
		method = cls.getDeclaredMethod("printItString", paramString);
		method.invoke(obj, new String("mkyong"));
 
		//call the printItInt method, pass a int param
		method = cls.getDeclaredMethod("printItInt", paramInt);
		method.invoke(obj, 123);
 
		//call the setCounter method, pass a int param
		method = cls.getDeclaredMethod("setCounter", paramInt);
		method.invoke(obj, 999);
 
		//call the printCounter method
		method = cls.getDeclaredMethod("printCounter", noparams);
		method.invoke(obj, (Object[])null);
 
	}catch(Exception ex){
		ex.printStackTrace();
	}
   }
}
