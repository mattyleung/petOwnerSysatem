package POS18s2;

public class POS {
	/**
	 * /**  
	 * This is the main method in the system. 
	 * It will execute all four of the required function in the assignment. 
	 * 
	 *  @author Matthew Leung
	 *  @since 2018-10-22 
	 *  @param args
	 *  parameters to invoke main method. 
	 */
	public static void main(String[] args) {
		POSProcessor pos1 = new POSProcessor();

		pos1.readMember(args[0]);

		pos1.executeInstructions(args[1]);
		pos1.writeResult(args[2]);
		pos1.writeReport(args[3]);

		System.out.println("finish");

//		if ("abc".compareTo("abcd") < 0) {
//			System.out.println("smaller");
//		}

//	POS.readMember(args[0]);
//	pos1.addResident();
//	POS.writeResult(args[1]);
//	POS.executeInstructions(args[2]);
//	POS.writeReport(args[3]);

	}
}
