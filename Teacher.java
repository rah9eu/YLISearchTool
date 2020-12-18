//Arthur Harris, 2019
public class Teacher {

	private String state;
	private String lastName;
	private String firstName;
	private School school;
	private String schoolName;
	private String email;
	private boolean mMock;
	private boolean mEC;
	private boolean nMock;
	private boolean nEC;
	
	public Teacher(String last, String first, String pState, String pEmail) {
		state = pState;
		lastName = last;
		firstName = first;
		email = pEmail;
		school = null;
		schoolName = null;
		mMock = false;
		mEC = false;
		nMock = false;
		nEC = false;
	}
	
	public Teacher(String last, String first, String pState, String pEmail, 
			boolean pmMock, boolean pnMock, boolean pmEC, boolean pnEC) {
		state = pState;
		lastName = last;
		firstName = first;
		email = pEmail;
		school = null;
		schoolName = null;
		mMock = pmMock;
		nMock = pnMock;
		mEC = pmEC;
		nEC = pnEC;
	}
	
	public Teacher(boolean pmMock, boolean pnMock, boolean pmEC, boolean pnEC,
			String last, String first, String nameOfSchool, 
			String pState) {//search constructor
		state = pState;
		lastName = last;
		firstName = first;
		email = null;
		school = null;
		schoolName = nameOfSchool;
		mMock = pmMock;
		nMock = pnMock;
		mEC = pmEC;
		nEC = pnEC;
	}
	

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public boolean ismMock() {
		return mMock;
	}
	public void setmMock(boolean mMock) {
		this.mMock = mMock;
	}
	public boolean ismEC() {
		return mEC;
	}
	public void setmEC(boolean mEC) {
		this.mEC = mEC;
	}
	public boolean isnMock() {
		return nMock;
	}
	public void setnMock(boolean nMock) {
		this.nMock = nMock;
	}
	public boolean isnEC() {
		return nEC;
	}
	public void setnEC(boolean nEC) {
		this.nEC = nEC;
	}
	public String getSchoolName() {
		if(school == null) {
			return schoolName;
		}
		else {
			schoolName = school.getName();
			return school.getName();
		}
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public boolean equalState(Teacher toCompare) {
		if(toCompare.getState() == null) {
			return true;
		}
		if(toCompare.getState().trim().equals("")) {
			return true;
		}
		return getState().trim().toLowerCase().equals(toCompare.getState().trim().toLowerCase());
	}
		
	
	public boolean equalSchool(Teacher toCompare) {//pass in the search parameter teacher
		if(toCompare.getSchoolName() == null) {
			return true;
		}
		if(toCompare.getSchoolName().trim().equals("")) {
			return true;
		}
		return getSchoolName().trim().toLowerCase().contains(toCompare.getSchoolName().trim().toLowerCase());
	}
	
	public boolean equalFName(Teacher toCompare) {//pass in the search parameter teacher
		if(toCompare.getFirstName() == null) {
			return true;
		}
		if(toCompare.getFirstName().trim() == "") {
			return true;
		}
		return getFirstName().trim().toLowerCase().contains(toCompare.getFirstName().trim().toLowerCase());
	}
	
	public boolean equalLName(Teacher toCompare) {//pass in the search parameter teacher
		if(toCompare.getLastName() == null ) {
			return true;
		}
		if(toCompare.getLastName().trim() == "") {
			return true;
		}
		return getLastName().trim().toLowerCase().contains(toCompare.getLastName().trim().toLowerCase());
	}
	
	public boolean isMyMock(Teacher toCompare) {//pass in the search parameter teacher
		if(!(toCompare.ismMock())) {
			return true;
		}
		else {
			return (this.ismMock() == toCompare.ismMock());
		}
	}
	public boolean isNatMock(Teacher toCompare) {//pass in the search parameter teacher
		if(!(toCompare.isnMock())) {
			return true;
		}
		return (this.isnMock() == toCompare.isnMock());
	}
	public boolean isMyEC(Teacher toCompare) {//pass in the search parameter teacher
		if(!(toCompare.ismEC())) {
			return true;
		}
		return (this.ismEC() == toCompare.ismEC());
	}
	public boolean isNatEC(Teacher toCompare) {//pass in the search parameter teacher
		if(!(toCompare.isnEC())) {
			return true;
		}
		return (this.isnEC() == toCompare.isnEC());
	}

	public boolean equalSearch(Teacher toCompare) {//pass in the search parameter teacher
		if( (equalSchool(toCompare) && equalFName(toCompare) && equalLName(toCompare)
				&& isMyMock(toCompare) && isNatMock(toCompare) && isMyEC(toCompare) && 
				isNatEC(toCompare) && equalState(toCompare)) == true){
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		/*
		if(school != null) {
			return firstName + " " + lastName + ", " + email + ", " + school.getName();
		}
		else {
			return firstName + " " + lastName + ", " + email + ", " + schoolName;
		}
		*/
		return firstName + " " + lastName + ", " + email + ", " + schoolName + ", NEC = " + nEC + ", mEC = " + mEC + ", mMock = " + mMock + ", nMock = " + nMock;
	}
	
	public String[] toOutput() {
		String[] toReturn = {this.firstName, this.lastName, this.email, this.schoolName};
		return toReturn;
	}
}
