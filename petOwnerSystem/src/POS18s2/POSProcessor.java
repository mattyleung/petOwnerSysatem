package POS18s2;

import java.util.*;

import java.io.*;

public class POSProcessor {
	private ArrayList<Resident> residentList;
	private StringBuilder queryOutput = new StringBuilder();
	
	/**
	 * Constructor of POS Processor
	 */
	public POSProcessor() {
		residentList = new ArrayList<Resident>();

	}
	/**
	 *  Method to add resident into arraylist.
	 * @param k
	 * name for resident
	 */
	public void addResident(Resident k) {
		residentList.add(k);
	}
	/**
	 *  Method to read the member file
	 *  @param fileName
	 *  name of the file to invoke read member.txt
	 */
	public void readMember(String fileName) {
		try {
			Resident resi = new Resident();
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			String address = "";
			while (scan.hasNextLine()) {
				String temp = scan.nextLine();
//				System.out.println("The current line is: ===> " + temp);

				String[] temp1 = temp.split("\\s+");
				String temp2 = temp.substring(temp.indexOf(" ") + 1);
				System.out.println(temp2);

				if (temp.trim().isEmpty()) { // the beginning and ending of temp is a space. 
					if (resi.isValid()) {
//						if (!address.isEmpty()) {
//							resi.setAddress(address);
//							address = "";
//						}
						residentList.add(resi);
					}
					resi = new Resident();
					continue; // do not do the below code , but go back to while loop.
				}

//				System.out.println(temp);
//				System.out.println(temp1[0]);

				else if (temp1[0].equals("birthday")) {
					resi.setBirthday(temp2);
				} else if (temp1[0].equals("name")) {
					resi.setName(temp2);
				} else if (temp1[0].equals("address")) {
//					address += temp2.trim();
					System.out.println("ABC:" +temp2);
					resi.setAddress(temp2);
					
				} else if (temp1[0].equals("phone")) {
					resi.setPhone(temp2);
				} else if (temp1[0].equals("postcode")) {
					resi.setPostcode(temp2);
				} else if (temp1[0].equals("pet")) {
					resi.setPet(temp2.split("\\s+"));
				} else {
					address += " " + temp.trim();
//					resi.setAddress(newAddress);
//					System.out.println(resi.getAddress());
				}

			}

			if (!resi.getName().isEmpty()) {
				if (!address.isEmpty()) {
					resi.setAddress(address);
					address = "";
				}
				if (resi.isValid()) {
					residentList.add(resi);
				}
			}

		} catch (Exception e) {
//		break;
			System.out.println("End");
//		e.printStackTrace();
		}
	}
	/**
	 * Method to execute Update function.
	 * @param temp
	 * temporary resident to check whether it exist or not.
	 */
	public void executeUpdate(String temp) {
		if (!temp.contains("name") || !temp.contains("phone")) {
			return;
		}

		String temp2 = temp.trim().replace("update", "");
		String[] temp3 = temp2.split(";");
		String[] temp4 = new String[6];

		// System.out.println("we are able to search now.");

		for (int i = 0; i < temp3.length; i++) {
			temp3[i] = temp3[i].trim();
//			System.out.println(temp3[i]);
//				System.out.println("We are looking at :" + temp3[i]);
//				System.out.println((temp3[i].replaceFirst("\\s+", "")));
			if (temp3[i].startsWith("name")) {
				String tempName = temp3[i].replace("name", "").replaceFirst("\\s+", "");
//						System.out.println(tempName);
				temp4[0] = tempName;
				System.out.println("temp4[0]" + temp4[0]);
			}

			else if (temp3[i].startsWith("phone")) {
				String tempPhone = temp3[i].replace("phone", "").replaceFirst("\\s+", "");

//						System.out.println("Scanned phone");
//						System.out.println(tempPhone);
				temp4[1] = tempPhone;
				System.out.println("temp4[1]" + temp4[1]);

			} else if (temp3[i].startsWith("address")) {
				String tempAddress = temp3[i].replace("address", "").replaceFirst("\\s+", "");
				temp4[2] = tempAddress;
				System.out.println("temp4[2]: " + temp4[2]);

			} else if (temp3[i].startsWith("postcode")) {
				String tempPostcode = temp3[i].replace("postcode", "").replaceFirst("\\s+", "");
				temp4[3] = tempPostcode;
				System.out.println("temp4[3]:" + temp4[3]);

			} else if (temp3[i].startsWith("pet")) {
				String tempPet = temp3[i].replace("pet", "").replaceFirst("\\s+", "");
				temp4[4] = tempPet;
			} else if (temp3[i].startsWith("birthday")) {
				String tempBirthday = temp3[i].replace("birthday", "").replaceFirst("\\s+", "");
				temp4[5] = tempBirthday;
				System.out.println("TempBirthday is: " + temp4[5]);

			}
		}

		System.out.println(residentList.size());

		boolean foundExisting = false;
		for (int i = 0; i < residentList.size(); i++) {
			Resident resi = residentList.get(i);
			if (resi.getName().equals(temp4[0]) && resi.getPhone().equals(temp4[1])) {
				foundExisting = true;
				System.out.println("Existing resident");

				if (temp4[2] != null && !temp4[2].isEmpty()) {
					System.out.println("Old: " + resi.getAddress());
					resi.setAddress(temp4[2]);
					System.out.println(resi.getAddress());
				}
				if (temp4[5] != null && !temp4[5].isEmpty()) {
					System.out.println("Old: " + resi.getBirthday());
					resi.setBirthday(temp4[5]);
					System.out.println(resi.getBirthday());
				}
//
				if (temp4[3] != null && !temp4[3].isEmpty()) {
					System.out.println("Old: " + resi.getPostcode());
					resi.setPostcode(temp4[3]);
					System.out.println(resi.getPostcode());
				}
				if (temp4[4] != null && !temp4[4].isEmpty()) {
					resi.setPet(temp4[4].split("\\s+"));
				}
				break;
			}

		}
		// != null <==== Hasn't been initialized.
		// .isEmpty <==== Empty set.
		if (!foundExisting) {
			// insert
			Resident newResi = new Resident();
			System.out.println("Non-existing resident");
			if (temp4[0] != null) {
				newResi.setName(temp4[0]);
			}
			if (temp4[1] != null) {
				newResi.setPhone(temp4[1]);
			}
			if (temp4[2] != null) {
				newResi.setAddress(temp4[2]);
			}
			if (temp4[3] != null) {
				newResi.setPostcode(temp4[3]);
			}
			if (temp4[4] != null) {
				newResi.setPet(temp4[4].split("\\s+"));
			}
			if (temp4[5] != null) {
				newResi.setBirthday(temp4[5]);
			}
			if (newResi.isValid()) {
				residentList.add(newResi);
			}
		}
	}
	/**
	 * Method to execute Delete function.
	 * @param temp
	 * temporary string to invoke delete method. 
	 */
	public void executeDelete(String temp) {
		String temp2 = temp.replace("delete", "");
		System.out.println(temp2);
		String[] temp3 = temp2.split(";");
		String[] temp4 = new String[temp3.length];
		for (int i = 0; i < temp3.length; i++) {
			temp4[i] = temp3[i].replaceFirst("\\s+", "");
//			System.out.println(temp4[i]);

		}
		for (int i = 0; i < residentList.size(); i++) {
			Resident resi = residentList.get(i);
			System.out.println(residentList.size());
//			if(resi.getName().equals(temp4[0])&&resi.getPhone().equals(temp4[1])) {
			if (resi.getName().equals(temp4[0]) && resi.getPhone().equals(temp4[1])) {
				System.out.println(residentList.get(i).getName());
				residentList.remove(i);
				if (residentList.isEmpty()) {
					System.out.println("Empty");
				}
			}
		}
	}
	/**
	 * Method to execute Sort function. 
	 */
	public void executeSort() {
		System.out.println(residentList.size());
		for (int x = 0; x < residentList.size(); x++) {
			for (int sorted = 1; sorted < residentList.size(); sorted++) {
				int before = sorted - 1;
//			while(before >=0&& residentList.get(before).getName().compareTo(residentList.get(sorted).getName()))>0 {
				if (residentList.get(before).compareTo(residentList.get(sorted)) > 0) {
					Resident tempResident = residentList.get(sorted - 1);
					residentList.set(sorted - 1, residentList.get(sorted));
					residentList.set(sorted, tempResident);
				}
				
//				if (residentList.get(before).getName().compareTo(residentList.get(sorted).getName()) > 0) {
//					Resident tempResident = residentList.get(sorted - 1);
//					residentList.set(sorted - 1, residentList.get(sorted));
//					residentList.set(sorted, tempResident);
//				} else if (residentList.get(before).getName().equals(residentList.get(sorted).getName())) {
//					if (residentList.get(before).getPhone().compareTo(residentList.get(sorted).getPhone()) > 0) {
//						Resident tempResident = residentList.get(sorted - 1);
//						residentList.set(sorted - 1, residentList.get(sorted));
//						residentList.set(sorted, tempResident);
//					}
//				}
			}
		}
		for (int k = 0; k < residentList.size(); k++) {
			System.out.println(residentList.get(k).getName());
			System.out.println(residentList.get(k).getPhone());
		}
	}
	/**
	 * Method to execute Query pet function.
	 * @param sb
	 * temporary Stringbuilder to invoke execute query pet method.
	 */
	public void executeQueryPet(StringBuilder sb) {
		// collect all pet
		ArrayList<String> allPets = new ArrayList<String>();
		for (int i = 0; i < residentList.size(); i++) {
			Resident resi = residentList.get(i);
			if (resi.getPet() == null) {
				continue;
			}
			for (String pet : resi.getPet()) {
				if (!allPets.contains(pet)) {
					allPets.add(pet);
				}
			}
		}

		for (String pet : allPets) {
			HashMap<String, Integer> counter = new HashMap<String, Integer>();
			for (Resident resi : residentList) {
				if (resi.getPet() == null) {
					continue;
				}

				if (Arrays.asList(resi.getPet()).contains(pet)) {
					if (counter.containsKey(resi.getPostcode())) {
						counter.put(resi.getPostcode(), counter.get(resi.getPostcode()) + 1);
					} else {
						// first time
						counter.put(resi.getPostcode(), 1);
					}
				}
			}

			// find suburb with max number
			int max = Integer.MIN_VALUE;
			ArrayList<String> maxSuburbs = new ArrayList<String>();
			for (String suburb : counter.keySet()) {
				int count = counter.get(suburb);
				if (count == max) {
					maxSuburbs.add(suburb);
				} else if (count > max) {
					max = count;
					maxSuburbs = new ArrayList<String>();
					maxSuburbs.add(suburb);
				}
			}

//			Dog: 70; postcode 2050
//			Cat: 50; postcode 2018
//			Bird: 35; postcode 2000
//			Reptile: 10; postcode 2006, 2050
			String suburbStr = maxSuburbs.toString();
			suburbStr = suburbStr.replace("[", "").replace("]", "");  // replace use normal char, replaceAll and replaceFIrst use regex.
			sb.append(pet + ": " + max + "; postcode: " + suburbStr + "\r\n");
		}
	}
	/**
	 * Method to execute Query Age fucntion.
	 * @param tempWord
	 * @param sb
	 * two parameters to invoke method query age
	 */
	public void executeQueryAge(String tempWord[], StringBuilder sb) {
		int numberOfResident = 0;
		int below = 0;
		int between = 0;
		int larger = 0;
		int unknown = 0;
		float percentBelow = 0;
		float percentBetween = 0;
		float percentLarger = 0;
		float percentUnknown = 0;
		for (int i = 0; i < residentList.size(); i++) {
			if (tempWord[1].equals(residentList.get(i).getPostcode())) {
				numberOfResident++;
				if (residentList.get(i).getBirthday() == null || residentList.get(i).getBirthday().isEmpty()) {
					unknown++;
					continue;
				}
				String[] tempString = residentList.get(i).getBirthday().split("-");
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				int bornYear = Integer.parseInt(tempString[2]);
				int age = currentYear - bornYear;
				if (age <= 18 && age > 0) {
					below++;
				} else if (age > 18 && age < 65) {
					between++;
				} else if (age >= 65 && age <= 100) {
					larger++;
				}
			}
		}

//		------query 2057 age------
//		Available pet owner size: 100
//		Age profile
//		Below 18: 20%
//		Over 18 and Below 65: 50%
//		Over 65: 30%
//		Unknown: 0
//		---------------------------
		percentBelow = (below * 100.0f) / numberOfResident;
		percentBetween = (between * 100.0f) / numberOfResident;
		percentLarger = (larger * 100.0f) / numberOfResident;
		percentUnknown = (unknown * 100.0f) / numberOfResident;

		sb.append("Available pet owner size: " + numberOfResident + "\r\n");
		sb.append("Age profile:\r\n");
		sb.append(String.format("Below 18: %.2f%%\r\n", percentBelow));
		sb.append(String.format("Over 18 and Below 65: %.2f%%\r\n", percentBetween));
		sb.append(String.format("Over 65: %.2f%%\r\n", percentLarger));
		sb.append(String.format("Unknown: %.2f%%\r\n", percentUnknown));

	}
	/**
	 * Method to execute Query function.
	 * @param queryString
	 * parameters to invoke query.
	 */
	public void executeQuery(String queryString) {
		String[] tempWord = queryString.split("\\s+");
		StringBuilder sb = new StringBuilder();
		if (tempWord[1].equals("name")) {
			String tempName = queryString.replace("query ", "").replace("name ", "");
			for (int i = 0; i < residentList.size(); i++) {
				if (tempName.equals(residentList.get(i).getName())) {
					sb.append(residentList.get(i));
				}
			}
		} else if (tempWord[1].equals("pet")) {
			executeQueryPet(sb);
		} else if (tempWord[2].equals("age")) {
			executeQueryAge(tempWord, sb);
		}

		if (sb.length() != 0) {
			queryOutput.append("----" + queryString + "----\r\n");
			queryOutput.append(sb.toString());
			queryOutput.append("-------------------------\r\n\r\n");
		}

	}
	/**
	 * Method to execute Instruction function.
	 * @param inName
	 * parameters to invoke instruction.
	 */
	public void executeInstructions(String inName) {
		try {
			File file = new File(inName);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String temp = reader.nextLine();

				if (temp.startsWith("update")) {
					executeUpdate(temp);
				} else if (temp.startsWith("delete")) {
					// delete work fine.
					executeDelete(temp);
				} else if (temp.startsWith("sort")) {
					executeSort();
				} else if (temp.startsWith("query")) {
					executeQuery(temp);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Method to find Resident index.
	 * @param name
	 * @param phone
	 * two parameters to invoke method.
	 * @return boolean
	 */
	public boolean findResidentIndex(String name, String phone) {
		for (int i = 0; i < residentList.size(); i++) {
			Resident resi = residentList.get(i);
			if (resi.getName().equals(name) && resi.getPhone().equals(phone)) {
				System.out.println("Existing resident");
				return true;
			}
		}
		System.out.println("Non-Existing resident");
		return false;
	}
	/**
	 * Method to generate result.
	 * @param outName
	 * parameters to invoke method.
	 */
	public void writeResult(String outName) {
		try {
			File file = new File(outName);
			PrintWriter out = new PrintWriter(file);
			for (int i = 0; i < residentList.size(); i++) {
				Resident resi = residentList.get(i);
				out.print(resi.toString());
				if (i != residentList.size() - 1) {
					out.println();
				}

			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to write Report.
	 * @param outName
	 * parameters to invoke method
	 */
	public void writeReport(String outName) { 
		try {
			File file = new File(outName);
			PrintWriter out = new PrintWriter(file);
			out.print(queryOutput.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}