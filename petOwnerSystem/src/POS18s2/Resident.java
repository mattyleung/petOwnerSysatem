package POS18s2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * instance field of Resident class.
 * 
 * @author matthew
 *
 */
public class Resident {

	private String name;
	private String birthday;
	private String address;
	private String postcode;
	private String phone;
	private String[] pet;

	/**
	 * constructor
	 */
	public Resident() {

	}

	/**
	 * Required constructor when there are only three instance fields.
	 * 
	 * @param nm
	 * @param post
	 * @param phoneNum
	 */
	public Resident(String nm, String post, String phoneNum) {
		setName(nm);
		setPostcode(post);
		setPhone(phoneNum);
	}

	/**
	 * Required constructor when there are all six instance fields.
	 * 
	 * @param nm
	 * @param post
	 * @param phoneNum
	 * @param bday
	 * @param add
	 * @param type
	 */
	public Resident(String nm, String post, String phoneNum, String bday, String add, String[] type) {
		setName(nm);
		setPostcode(post);
		setPhone(phoneNum);
		setBirthday(bday);
		setAddress(add);
		setPet(type);
	}

	// Retreat name from Resident object.
	/** 
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	// Set name for Resident object.
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// Retreat birthday from Resident object.
	/**
	 * 
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	// Set Birthday for a Resident object
	/**
	 * 
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		if (Resident.validateBirthday(birthday)) {
			// TODO: add 0
			if (birthday.contains("-")) {
				String nums[] = birthday.split("-");
				int day = Integer.parseInt(nums[0]);
				int month = Integer.parseInt(nums[1]);
				this.birthday = String.format("%02d-%02d-%s", day, month, nums[2]);
			} else {
				String nums[] = birthday.split("/");
				int day = Integer.parseInt(nums[0]);
				int month = Integer.parseInt(nums[1]);
				this.birthday = String.format("%02d-%02d-%s", day, month, nums[2]);
			}
		}
	}

	// Retreat address from Resident object
	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	// Set address for Resident object
	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
//		validateAddress(address);
		if (validateAddress(address)) {
			this.address = address;
			System.out.println("THIS IS A TEST");
		}
	}

	// Retreat post code from Resident object
	/**
	 * 
	 * @return postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	// Set post code for Resident object
	/**
	 * 
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		if (Resident.validatePostcode(postcode)) {
			this.postcode = postcode;
		}
	}

	// Retreat phone from Resident object
	/**
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	// Set phone for resident object
	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		if (validatePhone(phone)) {
			if (phone.length() == 7) {
				phone = "0" + phone;
			}
			this.phone = phone;
		}
	}

	// Retreat pet from resident object
	/**
	 * 
	 * @return pet
	 */
	public String[] getPet() {

		return pet;

	}

	// Set pet for a resident object
	/**
	 * 
	 * @param String[]pet
	 */
	public void setPet(String[] pet) {

		this.pet = pet;
	}

	// Validate name, true when string consists only upper, lower case and space
	// only.
	/**
	 * Validate Name
	 * @param name
	 * @return true/false
	 */
	public static boolean validateName(String name) {
		char[] charArray = name.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!Character.isUpperCase(ch) && !Character.isLowerCase(ch) && !Character.isWhitespace(ch)) {
//				System.out.println("It is not a valid name format");
				return false;
			}
		}
		System.out.println("Correct");
		return true;
	}

	// Validate address, true when there is suburb include in it.
	/**
	 * Validate Address
	 * @param name
	 * @return true/ false
	 */
	public static boolean validateAddress(String name) {
		System.out.println(name);

		String[] tempList = name.split(",");
		String[] stateList = { "NSW", "QLD", "SA", "TAS", "VIC", "WA" };
//		for(int j =0; j<tempList.length;j++) {
//			System.out.println(tempList[j]);
//		}
		if (tempList.length >= 3) {

			System.out.println("ABCDEFGHIJ");
			for (int i = 0; i < stateList.length; i++) {
				if (tempList[tempList.length - 1].trim().equals(stateList[i])) {
					if (tempList[tempList.length - 2].matches("^[a-zA-Z]+$"))
						;
					{
						System.out.println("True");
						return true;

					}
				}
			}
		} else if (tempList.length == 2) {
			System.out.println("GOT IN");
			String[] tempList2 = tempList[tempList.length - 1].trim().split("\\s+");
			for (int a = 0; a < tempList2.length; a++) {
				System.out.println(tempList2[a]);
			}
			for (int k = 0; k < stateList.length; k++) {
				if (tempList2[1].equals(stateList[k]) && tempList2[0].matches("^[a-zA-Z]+$")) {
					System.out.println("True");
					return true;
				}
			}
		}
		return false;
	}

	// Validate birthday.
	/**
	 * Validate Birthday
	 * @param birthday
	 * @return true/false
	 */
	public static boolean validateBirthday(String birthday) {

		// TODO: remove 0

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		if (birthday.contains("-")) {
			System.out.println("We are in");
			try {
				formatter.setLenient(false);
				Date dob = formatter.parse(birthday);
				System.out.println(dob);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			try {
				formatter.setLenient(false);
				Date dob = formatter2.parse(birthday);
				System.out.println(dob);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	// Method - validate Phone.
	/**
	 * Validate Phone
	 * @param name
	 * @return true / false
	 */
	public static boolean validatePhone(String name) {

		if (name.length() == 7) {
			name = "0" + name;
		}

		if (name.length() != 8) {
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!Character.isDigit(ch)) {
				return false;
			}
		}

		return true;

	}

	// Method - validate Postcode
	/**
	 * Validate Postcode
	 * @param name
	 * @return true/false
	 */
	public static boolean validatePostcode(String name) {
		if (name.length() != 4) {
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!Character.isDigit(ch)) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Check resident is valid or not
	 * @return true/false
	 */
	public boolean isValid() {
		return name != null && postcode != null && phone != null;
	}
	/**
	 * Method to output resident's instance field to file
	 * @return str
	 */
	public String toString() {
		String str = "";
		str += "name " + getName() + "\r\n";
		if (this.birthday != null) {
			str += "birthday " + getBirthday() + "\r\n";
		}
		if (this.address != null) {
			str += "address " + getAddress() + "\r\n";
		}
		if (this.postcode != null) {
			str += "postcode " + getPostcode() + "\r\n";
		}
		str += "phone " + getPhone() + "\r\n";

		if (getPet() != null) {
			str += "pet ";
			for (int j = 0; j < getPet().length; j++) {
				str += getPet()[j] + " ";
			}
			str += "\r\n";
		}
		return str;
	}
	/**
	 * 
	 * @param resi
	 * @return diff
	 */
	public int compareTo(Resident resi) {
		int diff = this.name.compareTo(resi.getName());
		if (diff != 0) {
			return diff;
		}

		diff = this.phone.compareTo(resi.getPhone());
		return diff;
	}
}