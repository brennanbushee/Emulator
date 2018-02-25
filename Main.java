import java.io.*;


public class Main {
	
	/**
	 * @param args
	 */

	
	
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InstructionSet iset = new InstructionSet(); 
		Memory memory = new Memory(); 
		iset.connectMemory(memory); 
		
		BufferedReader br = new BufferedReader(new FileReader("machine.txt"));

		int i = 0;
		String[] instructions = new String[100];
		while (br.ready()) {
			
			String currLine = br.readLine();
			if (currLine.equals(""))
				continue;
			
			instructions[i] = currLine;
			i++;
		}
		
		String currLine = instructions[0];
		
		while (currLine != null)
		{
			//convert opcode to int
			int opCode = Integer.parseInt(currLine.substring(0,3), 2);
			
			switch (opCode)
			{
				case 0:
					int r1_0 = Integer.parseInt(currLine.substring(4, 6),2);
					int r2_0 = Integer.parseInt(currLine.substring(7, 9),2);
					iset.add(r1_0, r2_0);
					break;
					
				case 1:
					int r1_1 = Integer.parseInt(currLine.substring(4, 6),2);
					int imm_1 = Integer.parseInt(currLine.substring(7, 9),2);
					iset.addi(r1_1, imm_1);
					break;
					

				case 2:
					int r1_2 = Integer.parseInt(currLine.substring(4, 6),2);
					int imm_2 = Integer.parseInt(currLine.substring(7, 9),2);
					iset.shiftleft(r1_2, imm_2);
					break;
					
				case 3:
					int r1_3 = Integer.parseInt(currLine.substring(4, 6),2);
					int imm_3 = Integer.parseInt(currLine.substring(7, 9),2);
					iset.shiftright(r1_3, imm_3);
					break;
					
				case 4:
					int r1_4 = Integer.parseInt(currLine.substring(4, 6),2);
					int imm_4 = Integer.parseInt(currLine.substring(7, 9),2);
					iset.is0(r1_4, imm_4);
					break;
					
				case 5:
					int r1_5 = Integer.parseInt(currLine.substring(4, 6),2);
					iset.pop(r1_5);
					break;
				case 6:
					int r1_6 = Integer.parseInt(currLine.substring(4, 6),2);
					iset.push(r1_6);
					break;
					
				//skip 7, 8 sb, lb

				case 9:
					int r1_9 = Integer.parseInt(currLine.substring(4, 9),2);
					iset.jr(r1_9);
					break;
					
				case 10:
					int imm_10 = Integer.parseInt(currLine.substring(4, 9),2);
					iset.jal(imm_10);
					break;
					
				default:
					System.out.println("Reached default case");	
					break;
					

			}
			
			currLine = instructions[memory.getProgramCounter()];
		} //end while
	
	}

}
