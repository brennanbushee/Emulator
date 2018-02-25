
public class InstructionSet {
	
	Memory memory; 
	
	public void connectMemory(Memory m) {
		this.memory = m; 
	}
	
	public void add( int i1, int i2) {
		Register t1 = memory.getReg(i1);
		Register t2 = memory.getReg(i2);	
		
		memory.updateRegister(t1, t1.getContent() + t2.getContent()); 
		
		memory.setProgramCounter(1);
	}

	public void addi(int i1, int imm) {
		Register t1 = memory.getReg(i1);
		
		memory.updateRegister(t1,t1.getContent()+imm);
		memory.setProgramCounter(1);


	}
	public void is0 (int i1, int imm) {
		Register r1 = memory.getReg(i1);
		if (r1.getContent() == 0) {
			memory.setProgramCounter(1 + imm);
		}	
		else
			memory.setProgramCounter(1);
			


	}
	
	/*
	public void lb(Register r1) {
		int temp = r1.getContent() % (2^8); //lower 8 bits
		r1.setContent(temp);
	}

	public void sb(Register r1) {

	}
	*/
	
	public void jal(int target) {

		int current = memory.getProgramCounter();
		memory.setReturnAddress(current);
		memory.setProgramCounter(1 + target);
	}

	public void jr(int i1) {
		Register r1 = memory.getReg(i1);
		memory.setProgramCounter((r1.getContent())+1); //?
	}
	
	public void j(int target) { 
		memory.setProgramCounter(1 + target); 
	}
	public void push(int regNum) {
		Register reg = memory.getReg(regNum);
		Memory.mainMemory[memory.getReg(1).getContent()+1] = reg.getContent();
		memory.getReg(1).setContent(memory.getReg(1).getContent() + 1);
		memory.setProgramCounter(1);
  
	}

	public void pop(int regNum) {
		Register reg = memory.getReg(regNum);
		reg.setContent(Memory.mainMemory[memory.getReg(1).getContent()]);
		memory.getReg(1).setContent(memory.getReg(1).getContent() - 1);
		memory.setProgramCounter(1);

	}
	
	public void shiftleft(int i1, int imm) { 
		Register r1 = memory.getReg(i1);
		int current = r1.getContent();
		memory.updateRegister(r1, current*((int) Math.pow(2, imm)));
		memory.setProgramCounter(1);

	}
	public void shiftright(int i1, int imm) {
		Register r1 = memory.getReg(i1);
		int current = r1.getContent();
		memory.updateRegister(r1, current / ((int) Math.pow(2, imm))); 
		memory.setProgramCounter(1);

	}
	public void halt() { 
		System.exit(0);
	}

}


