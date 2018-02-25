
import java.io.*;
import java.util.StringTokenizer;

public class Assembler {

	public static String toBinary(int num, int size)
	{
		String retS = Integer.toBinaryString(num);
		boolean neg = true;
		
		if (num < 0)
		{
			neg = true;
			num *= -1;
			int numOfBits = (int) Math.ceil((Math.log(num) / Math.log(2)))+1;
			retS = retS.substring(retS.length()-numOfBits);
		}

		int numZeroes = size-retS.length();
		for (int i = 0; i < numZeroes; i++)
		{
			if (neg)
				retS="1"+retS;
			else		
				retS = "0" + retS;
		}
		return retS;
	}
	
	public static String regToBin(String reg)
	{
		if (reg.equals("$ra"))
			return "000";
		else if (reg.equals("$sp"))
			return "001";
		else if (reg.equals("$a0"))
			return "010";
		else if (reg.equals("$v0"))
			return "011";
		else if (reg.equals("$t0"))
			return "100";
		else if (reg.equals("$t1"))
			return "101";
		else if (reg.equals("$t2"))
			return "110";
		else
			return "ERROR";
	}
	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("instructions.txt"));
		PrintWriter pw = new PrintWriter(new FileOutputStream("machine.txt",false));
		String toWrite ="";

		while (br.ready())
		{
			String currLine = br.readLine();
			if (currLine.equals(""))
				continue;
			
			StringTokenizer str = new StringTokenizer(currLine, " ,");
			String opCode = str.nextToken();
						
			if (opCode.equals("add")){
				toWrite += "0000" + regToBin(str.nextToken()) + regToBin(str.nextToken());
			}
			else if (opCode.equals("addi")){
				toWrite += "0001" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);																//convert the immediate to binary and concat.
			}
			else if (opCode.equals("sl")){
				toWrite += "0010" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);
			}
			else if (opCode.equals("sr")){
				toWrite += "0011" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);
			}
			else if (opCode.equals("is0")){
				toWrite += "0100" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);
			}
			else if (opCode.equals("pop")){
				toWrite += "0101" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);	
			}
			else if (opCode.equals("push")){
				toWrite += "0110" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);					
			}
			else if (opCode.equals("lb")){
				toWrite += "0111" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);	
			}
			else if (opCode.equals("sb")){
				toWrite += "1000" + regToBin(str.nextToken()) + toBinary(Integer.parseInt(str.nextToken()), 3);	
			}
			else if (opCode.equals("jr")){
				toWrite += "1001" + toBinary(Integer.parseInt(str.nextToken()), 6);	
			}
			else if (opCode.equals("jal")){
				toWrite += "1011" + toBinary(Integer.parseInt(str.nextToken()), 6);	
			}
			else if (opCode.equals("tbd")){
				toWrite += "1011000000";
			}
			else if (opCode.equals("halt")){
				toWrite += "1100000000";				
			}

			toWrite += "\n";

			
		}
		
		br.close();
		pw.println(toWrite);
		pw.close();
	}
}


