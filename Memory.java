
public class Memory {
	
	private static Register[] RegisterFile =  {
		new Register("ra", 0), 
	    new Register("sp", 0), 
	    new Register("a0", 0),
	    new Register("v0", 0),
	    new Register("t0", 0) ,
	    new Register("t1", 0) ,
	    new Register("t2", 0) ,
	    new Register("load", 0)
	};
	
	public static int[] mainMemory = new int[144]; //each unit of memory has 16 bits
	
	private static Register programcounter = new Register("pc", 0); 
	//PC as an int?
	
	public void setProgramCounter(int offset) {
		int current = programcounter.getContent();
		programcounter.setContent(current+offset);
	
	
	}
	public int getProgramCounter() { 
		return programcounter.getContent(); 
	}
	public void setReturnAddress(int value) {
		RegisterFile[0].setContent(value);
	}
	public int getReturnAddress() {
		return RegisterFile[0].getContent();
	}
	public Register getReg(int index) { 
	    return RegisterFile[index]; 	
	}
	public void updateRegister(Register r1, int n) { 
		r1.setContent(n);
		
	}
	
		
		
		
	   

}
