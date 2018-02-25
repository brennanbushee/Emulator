
public class Register {
	String name; 
	private int content; 

	public Register(String s, int c) {
	   this.name = s; 
	   this.content = c; 
	}
	public void setContent (int n) {
		this.content = n; 
	}
	public int getContent() 
	{ 
		return this.content;
	}
}
