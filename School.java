import java.util.ArrayList;

//Arthur Harris, 2019

public class School {

	private int id;
	private String name;
	private String city;
	private String state;
	private int zipCode;
	private boolean isPublic;
	private String startedYear;
	private String address;
	
	public School(double pID, String pName, String pCity, String pState, double zip, boolean pPublic, String pYear, String Address) {
		this.id = (int) pID;
		this.name = pName;
		this.city = pCity;
		this.state = pState;
		this.zipCode = (int) zip;
		this.isPublic = pPublic;
		this.startedYear = pYear;
		this.address = Address;
	}
	
	public School(String pName, String pState) {
		name= pName;
		state = pState;
	}
	
	public boolean equals(Object a) {
		if(!(a instanceof School)) {
			return false;
		}
		School test = (School) a;
		if(this.name.toLowerCase().replaceAll("\\s", "").equals(test.name.toLowerCase().replaceAll("\\s", ""))) {
			if(this.state.equals(test.state)) {
				return true;
			}
		}
		return false;
	}
			
	public String toString() {
		String schoolType;
		if(isPublic == true) {
			schoolType = "Public";
		}
		else {
			schoolType = "Private";
		}
		return name + ", " + city + ", " + state + ", " + zipCode + ". " + schoolType + " School. Joined in " + startedYear + ". Address: " + address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(double zipCode) {
		this.zipCode = (int) zipCode;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getStartedYear() {
		return startedYear;
	}

	public void setStartedYear(String startedYear) {
		this.startedYear = startedYear;
	}

	public String[] schoolOutput() {
		String[] toReturn = {this.name, this.address};
		return toReturn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

