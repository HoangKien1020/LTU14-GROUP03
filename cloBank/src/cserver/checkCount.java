package cserver;

public class checkCount extends Thread {
	private int count = 0;
	private boolean stop=false;
    public void takeCount(int i)
    {	
    	if (i>this.count) {
    		this.count = i;
    	}
    	else {
    		this.stop=true;
    	}
        
    }
    public void run(){
    	while(!stop) {
    		System.out.println("Working thread, int : " +count);
    		
    	}
        
    }
}