package in.antany.learning.java.concurrency;

public class InterruptedExceptionTest{
    public static void main(String args[]){
        
    	Thread th = new Thread(new SubThread());
    	th.start();
    	try {
    		Thread.sleep(5000);
    	}catch(InterruptedException ie) {
    		System.out.println("MainThread-- Interrupted Exception");
    	}
    }
}

class SubThread implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(500);
		}catch(InterruptedException ie) {
			System.out.println("SubThread-- Interrupted Exception");
		}
	}
}