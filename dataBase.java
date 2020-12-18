//Arthur Harris, 2019

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.impl.StaticLoggerBinder;

import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.*;

public class dataBase{
	private Object data[][];
	private Object teacherData[][];
	private ArrayList<School> schools;
	private ArrayList<Teacher> teachers;
	private String[][] teachersArray;
	private String[][] schoolsArray;
	private String[][] resultsArray;
	public ArrayList<School> schoolResults;
	public ArrayList<Teacher> teacherResults;

	public String[][] getResultsArray() {
		return resultsArray;
	}

	public String[][] getTeachersArray() {
		return teachersArray;
	}

	public void setTeachersArray(String[][] teachersArray) {
		this.teachersArray = teachersArray;
	}

	public String[][] getSchoolsArray() {
		return schoolsArray;
	}

	public void setSchoolsArray(String[][] schoolsArray) {
		this.schoolsArray = schoolsArray;
	}

	public dataBase(Object pData[][], Object p2Data[][]) {
		data = pData;
		teacherData = p2Data;
		schools = new ArrayList<>();
		teachers = new ArrayList<>();
		teachersArray = null;
		schoolsArray = null;
	}

	public dataBase() {
		data = null;
		teacherData = null;
		schools = new ArrayList<>();
		teachers = new ArrayList<>();
		teachersArray = null;
		schoolsArray = null;
		schoolResults = new ArrayList<School>();
		teacherResults = new ArrayList<Teacher>();
	}
	
	public Object[][] getData() {
		return data;
	}

	public Object[][] getTeacherData() {
		return teacherData;
	}

	public ArrayList<School> getSchools() {
		return schools;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public ArrayList<School> getSchoolResults() {
		return schoolResults;
	}

	public ArrayList<Teacher> getTeacherResults() {
		return teacherResults;
	}

	public void makeSchools() {
		int id = -1;
		int name = -1;
		int city = -1;
		int state = -1;
		int zipCode = -1;
		int type = -1;
		int year = -1;
		int address = -1;
		//Initialize indexes for each header column
		
		for(int col = 0; col < data[0].length; col++) {
			String header =(String) data[0][col];
			if(header.toLowerCase().replaceAll("\\s","").equals("id")) {
				id = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("schoolname") || header.toLowerCase().replaceAll("\\s","").equals("name")) {
				name = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("city")) {
				city = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("state")) {
				state = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("zipcode")) {
				zipCode = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("type")) {
				type = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("yearcreated")) {
				year = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").contains("address1")){
				address = col;
			}
			//Check for and set header index values
		}
		/*
		System.out.println(id);
		System.out.println(name);
		System.out.println(city);
		System.out.println(state);
		System.out.println(zipCode);
		System.out.println(type);
		System.out.println(year);
		System.out.println(address);
		*/
		
		try {
		for(int i = 1; i < data.length; i++) {
			boolean testPublic = true;
			String puborpriv;
			if(data[i][type] !=null) {
				puborpriv = (String) data[i][type];
			}
			else {
				puborpriv = "Unknown";
			}
			if(puborpriv.toLowerCase().replaceAll("\\s","").equals("private")) {
				testPublic = false;
			}
			//converts public to boolean
			
			//needs to take in the address
			//System.out.println(data[i][address]); //checks for the address
			
			School toAdd = null;
			//try {
			toAdd = new School((double) data[i][id], (String) data[i][name], (String) data[i][city], (String) data[i][state], (double) data[i][zipCode], testPublic, (String) data[i][year], String.valueOf(data[i][address]));
			//}
			/*
			catch(ClassCastException e17) {
				toAdd = new School((double) data[i][id], (String) data[i][name], (String) data[i][city], (String) data[i][state], (double) data[i][zipCode], testPublic, (String) data[i][year], (String.valueOf((Object) data[i][address])));
			}
			*/
			schools.add(toAdd); //format is ID, Name, city, state, zip, public, year, adds school type to the database
			//System.out.println(toAdd); use to check the data imported
		}
		//System.out.println(data[11832][address]); //checks for the address of a school used during testing
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("XLSX File Not Formatted Correctly. Make sure your headers are ID, School Name, City, State, Zip Code, Type, and Year Created");
		//Catches if the headers are not properly handled
		}
		populateSchools();
		
	}
	
	public void makeTeachers(){//need to consider a different data structure for making the teachers before storing to a 2darray
		int first = -1;
		int last = -1;
		int email = -1;
		int school = -1;
		int state = -1;
		int nEC = -1;
		int mEC = -1;
		int nMock = -1;
		int mMock = -1;
		//System.out.println(teacherData[0].length);  //number of columns
		//System.out.println(Arrays.toString(teacherData[0])); //prints the headers
		for(int col = 0; col < teacherData[0].length; col++) {
			String header =(String) teacherData[0][col];
			header.trim().toLowerCase();
			if(header.toLowerCase().replaceAll("\\s","").equals("firstname")) {
				first = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("lastname")) {
				last = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("schoolname")) {
				school = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").contains("state")) {
				state = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").equals("email")) {
				email = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").trim().contains("nationalmockelection")) {
				nMock = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").trim().contains("mymockelection")) {
				mMock = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").replaceAll("-", "").contains("nationalecongress")) {
				nEC = col;
			}
			if(header.toLowerCase().replaceAll("\\s","").replaceAll("-", "").contains("myecongress")) {
				mEC = col;
			}
			//Check for and set header index values
		}
		System.out.println(first +  ", " + last + ", " + email + ", "+ school + ", " + state + ", " + nEC + ", " + mEC + ", " + mMock + ", " + nMock);
		//prints out header indexes
		String stringState = "";
		//int errors = 0;	
		for(int i = 1; i < data.length; i++) {		
			if(((String) teacherData[i][state]).equals("VA")) {
				stringState = "Virginia";
			}
			else if(((String) teacherData[i][state]).equals("AL")) {
				stringState = "Alabama";
			}
			else if(((String) teacherData[i][state]).equals("AK")) {
				stringState = "Alaska";
			}
			else if(((String) teacherData[i][state]).equals("AZ")) {
				stringState = "Arizona";
			}
			else if(((String) teacherData[i][state]).equals("CA")) {
				stringState = "California";
			}
			else if(((String) teacherData[i][state]).equals("CO")) {
				stringState = "Colorado";
			}
			else if(((String) teacherData[i][state]).equals("CT")) {
				stringState = "Connecticut";
			}
			else if(((String) teacherData[i][state]).equals("DE")) {
				stringState = "Delaware";
			}
			else if(((String) teacherData[i][state]).equals("FL")) {
				stringState = "Florida";
			}
			else if(((String) teacherData[i][state]).equals("GA")) {
				stringState = "Georgia";
			}
			else if(((String) teacherData[i][state]).equals("HI")) {
				stringState = "Hawaii";
			}
			else if(((String) teacherData[i][state]).equals("ID")) {
				stringState = "Idaho";
			}
			else if(((String) teacherData[i][state]).equals("IL")) {
				stringState = "Illinois";
			}
			else if(((String) teacherData[i][state]).equals("IN")) {
				stringState = "Indiana";
			}
			else if(((String) teacherData[i][state]).equals("IA")) {
				stringState = "Iowa";
			}
			else if(((String) teacherData[i][state]).equals("KS")) {
				stringState = "Kansas";
			}
			else if(((String) teacherData[i][state]).equals("LA")) {
				stringState = "Louisiana";
			}
			else if(((String) teacherData[i][state]).equals("ME")) {
				stringState = "Maine";
			}
			else if(((String) teacherData[i][state]).equals("MA")) {
				stringState = "Massachusetts";
			}
			else if(((String) teacherData[i][state]).equals("MI")) {
				stringState = "Michigan";
			}
			else if(((String) teacherData[i][state]).equals("MN")) {
				stringState = "Minnesota";
			}
			else if(((String) teacherData[i][state]).equals("MS")) {
				stringState = "Mississippi";
			}
			else if(((String) teacherData[i][state]).equals("MO")) {
				stringState = "Missouri";
			}
			else if(((String) teacherData[i][state]).equals("MT")) {
				stringState = "Montana";
			}
			else if(((String) teacherData[i][state]).equals("NE")) {
				stringState = "Nebraska";
			}
			else if(((String) teacherData[i][state]).equals("NV")) {
				stringState = "Nevada";
			}
			else if(((String) teacherData[i][state]).equals("NH")) {
				stringState = "New Hampshire";
			}
			else if(((String) teacherData[i][state]).equals("NJ")) {
				stringState = "New Jersey";
			}
			else if(((String) teacherData[i][state]).equals("NM")) {
				stringState = "New Mexico";
			}
			else if(((String) teacherData[i][state]).equals("NY")) {
				stringState = "New York";
			}
			else if(((String) teacherData[i][state]).equals("NC")) {
				stringState = "North Carolina";
			}
			else if(((String) teacherData[i][state]).equals("ND")) {
				stringState = "North Dakota";
			}
			else if(((String) teacherData[i][state]).equals("OH")) {
				stringState = "Ohio";
			}
			else if(((String) teacherData[i][state]).equals("OK")) {
				stringState = "Oklahoma";
			}
			else if(((String) teacherData[i][state]).equals("OR")) {
				stringState = "Oregon";
			}
			else if(((String) teacherData[i][state]).equals("PA")) {
				stringState = "Pennsylvania";
			}
			else if(((String) teacherData[i][state]).equals("RI")) {
				stringState = "Rhode Island";
			}
			else if(((String) teacherData[i][state]).equals("SC")) {
				stringState = "South Carolina";
			}
			else if(((String) teacherData[i][state]).equals("SD")) {
				stringState = "South Dakota";
			}
			else if(((String) teacherData[i][state]).equals("TN")) {
				stringState = "Tennessee";
			}
			else if(((String) teacherData[i][state]).equals("TX")) {
				stringState = "Texas";
			}
			else if(((String) teacherData[i][state]).equals("UT")) {
				stringState = "Utah";
			}
			else if(((String) teacherData[i][state]).equals("VT")) {
				stringState = "Vermont";
			}
			else if(((String) teacherData[i][state]).equals("WA")) {
				stringState = "Washington";
			}
			else if(((String) teacherData[i][state]).equals("WV")) {
				stringState = "West Virginia";
			}
			else if(((String) teacherData[i][state]).equals("WI")) {
				stringState = "Wisconsin";
			}
			else if(((String) teacherData[i][state]).equals("WY")) {
				stringState = "Wyoming";
			}
			else {
				stringState = (String) teacherData[i][state];
			}

			boolean isMMock = false;
			boolean isNMock = false;
			boolean isMEC = false;
			boolean isNEC = false;

			if(teacherData[i][mMock].toString().toLowerCase().trim().contains("t")) {
				isMMock = true;
			}
			if(teacherData[i][nMock].toString().toLowerCase().trim().contains("t")) {
				isNMock = true;
			}
			if(teacherData[i][mEC].toString().toLowerCase().trim().contains("t")) {
				isMEC = true;
			}
			if(teacherData[i][nEC].toString().toLowerCase().trim().contains("t")) {
				isNEC = true;
			}

			Teacher toAdd = new Teacher((String) teacherData[i][last], (String) teacherData[i][first], (String) stringState, (String) teacherData[i][email], isMMock, isNMock, isMEC, isNEC);
			String schoolName = (String) teacherData[i][school];
			toAdd.setSchoolName(schoolName);
			//int schoolIndex = schools.indexOf(new School(pName, stringState));
			teachers.add(toAdd); //format is name, state, email to add to the database
			//System.out.println(toAdd); use to check the data imported
		}
		
		//System.out.println(errors);
		populateTeachers();
	}


	public void findInCity(String cityCheck) {
		//System.out.println("Looking into the database...");
		String toCompare = cityCheck.toLowerCase().replaceAll("\\s", "");
		ArrayList<School> goodSchools = new ArrayList<>();
		for(int i = 0; i < schools.size(); i++) {
			if(schools.get(i).getCity().toLowerCase().equals(toCompare.toLowerCase())){
				goodSchools.add(schools.get(i));
			}
		}
		
		Collections.sort(goodSchools, new CompareBySchoolName());
		//Sorts the found schools by state then name
		
		for(int i = 0; i<goodSchools.size(); i++) {
			System.out.println(goodSchools.get(i));
		}
		
		System.out.println("Number of Schools: " + goodSchools.size());
		System.out.println();
	}
	
	public void parseSchools(String fileName) throws NullPointerException {
		//String fileNameDefined = "fileName"; //name of the file to import from
		
		
		File file = new File(fileName);
		XSSFWorkbook myWorkbook; //intialize workbook
		Object schoolData[][] = null; //initialize dataset
		try {
			FileInputStream myStream = new FileInputStream(file);
			myWorkbook = new XSSFWorkbook(myStream); //create a workbook based on the file
			XSSFSheet activeSchools = myWorkbook.getSheetAt(0); //get the first sheet of the workbook
			int numRows = activeSchools.getLastRowNum()+1;
			int numCols = activeSchools.getRow(0).getLastCellNum();
			//set the dimensions
			
			schoolData = new Object[numRows][numCols];
			for(int j = 0; j < numRows; j++) {
				XSSFRow row = activeSchools.getRow(j); //get the row values (whats up/down index)
				for(int i = 0; i < numCols; i++) {
					XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); //get the cell at that position
					double valueN;
					String valueS;
					if(cell != null) {
						if(cell.getCellType() == CellType.NUMERIC) {
							valueN = cell.getNumericCellValue();
							schoolData[j][i] = valueN; //parse in and convert to int in the 2D Array
							}
							else{
								cell.setCellType(CellType.STRING);
								valueS = cell.getStringCellValue();
								schoolData[j][i] = valueS; //parse in and convert to string in the 2D Array
							}
					}
				}
			}
			this.data = schoolData;
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			//if the file is not findable by the system
		}
		//catch(BiffException e1) {
		//	System.out.println("JLX Error");
		//	e1.printStackTrace();
		//} 
		catch (IOException e) {
			System.out.println("Workbook error");
		}
	}
	
	public void parseTeachers(String fileName) throws NullPointerException{
		//For the teachers
		
		File file = new File(fileName);
		Object theData[][] = null; //initialize dataset
		Workbook myWorkbook; //intialize workbook
		
		
		try {
			myWorkbook = WorkbookFactory.create(file); //create a workbook based on the file
			Sheet activeSchools = myWorkbook.getSheetAt(0); //get the first sheet of the workbook
			int numRows = activeSchools.getLastRowNum()+1;
			int numCols = activeSchools.getRow(0).getLastCellNum();
			//set the dimensions
			
			theData = new Object[numRows][numCols];
			for(int j = 0; j < numRows; j++) {
				Row row = activeSchools.getRow(j); //get the row values (whats up/down index)
				for(int i = 0; i < numCols; i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); //get the cell at that position
					double valueN;
					String valueS;
					if(cell != null) {
						if(cell.getCellType() == CellType.NUMERIC) {
							valueN = cell.getNumericCellValue();
							theData[j][i] = valueN; //parse in and convert to int in the 2D Array
							}
							else{
								cell.setCellType(CellType.STRING);
								valueS = cell.getStringCellValue();
								theData[j][i] = valueS; //parse in and convert to string in the 2D Array
							}
					}
				}
			}
			this.teacherData = theData;
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			//if the file is not findable by the system
		}
		//catch(BiffException e1) {
		//	System.out.println("JLX Error");
		//	e1.printStackTrace();
		//} 
		catch (IOException e) {
			System.out.println("Workbook error");
		}
		
		/*
		String[][] teacherData = null;
		try {
		File ftest = new File(fileName); //must feed csv filetype
		Scanner fSource = new Scanner(ftest);
		String thisLine;
		List<String[]> lines = new ArrayList<String[]>();
		while (fSource.hasNextLine()) {
			thisLine = fSource.nextLine();
		     lines.add(thisLine.split(","));
		}
		
		// convert our list to a String array.
		teacherData = new String[lines.size()][0];
		lines.toArray(teacherData);
		fSource.close();
		this.teacherData = teacherData;
		}
		catch(FileNotFoundException e3) {
			//no reason for e3, just to avoid other exceptions thrown having the same name
		}
		*/
		/*
		try {
		 InputStream is = new FileInputStream(file);
		 Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		 Sheet sheet = workbook.getSheetAt(0);
		 int i = 0;
		 int j = 0;
		 for(Row r : sheet) {
			 j = 0;
			 for(Cell c : r) {
				theData[i][j] = c.getStringCellValue();
				j++;
			 }
			 i++;
		 }
		System.out.println(i);
		System.out.println(j);
		}
		catch(FileNotFoundException e66) {
			System.out.println("File not Found");
		}
		teacherData = theData;
		*/
	}
	
	public ArrayList<Teacher> findTeachersInSchool(String schoolName){
		ArrayList<Teacher> toReturn = new ArrayList<>();
		for(School a : schools) {
			if(a.getName().toLowerCase().trim().equals(schoolName.toLowerCase().trim())) {
				for(Teacher t : teachers) {
					if(t.getSchool().equals(a)) {
						toReturn.add(t);
					}
				}
			}
		}
		return toReturn;
	}
	
	public ArrayList<Teacher> findTeachersInSchool(String schoolName, String state){
		ArrayList<Teacher> toReturn = new ArrayList<>();
		for(School a : schools) {
			if(a.getName().toLowerCase().trim().equals(schoolName.toLowerCase().trim())){
				if(a.getState().toLowerCase().trim().equals(state.toLowerCase().trim())){
					for(Teacher t : teachers) {
						if(t.getSchool().equals(a)) {
							toReturn.add(t);
						}
					}
				}
			}
		}
		return toReturn;
	}
	
	public void populateSchools(){
		int rows = schools.size();
		String[][] toReturn = new String[rows][6];
		for(int i = 0; i < rows; i++) {
			toReturn[i][0] = schools.get(i).getName();
			toReturn[i][1] = schools.get(i).getCity();
			toReturn[i][2] = schools.get(i).getState();
			toReturn[i][3] = Integer.toString(schools.get(i).getZipCode());
			toReturn[i][5] = schools.get(i).getStartedYear();
			if(schools.get(i).isPublic() == false) {
			toReturn[i][4] = "Private";
			}
			else {
				toReturn[i][4] = "Public";
			}	
		}
		schoolsArray = toReturn;
	}

	public void populateTeacherSearch() {
//implement here
		resultsArray = null;
		int rows = teacherResults.size();
		String[][] toReturn = new String[rows][4];
		for(int row = 0; row < rows; row++) {
			toReturn[row][0] = teacherResults.get(row).getFirstName();
			toReturn[row][1] = teacherResults.get(row).getLastName();
			toReturn[row][2] = teacherResults.get(row).getEmail();
			toReturn[row][3] = teacherResults.get(row).getSchoolName();
		}
		resultsArray = toReturn;
	}

	public void populateSchoolSearch() {
		resultsArray = null;
		int rows = schoolResults.size();
		String[][] toReturn = new String[rows][6];
		for(int i = 0; i < rows; i++) {
			toReturn[i][0] = schoolResults.get(i).getName();
			toReturn[i][1] = schoolResults.get(i).getCity();
			toReturn[i][2] = schoolResults.get(i).getState();
			toReturn[i][3] = Integer.toString(schoolResults.get(i).getZipCode());
			toReturn[i][5] = schoolResults.get(i).getStartedYear();
			if(schoolResults.get(i).isPublic() == false) {
			toReturn[i][4] = "Private";
			}
			else {
				toReturn[i][4] = "Public";
			}	
		}
		resultsArray = toReturn;
	}

	public void populateTeachers(){
		int rows = teachers.size();
		String[][] toReturn = new String[rows][4];
		for(int row = 0; row < rows; row++) {
			toReturn[row][0] = teachers.get(row).getFirstName();
			toReturn[row][1] = teachers.get(row).getLastName();
			toReturn[row][2] = teachers.get(row).getEmail();
			toReturn[row][3] = teachers.get(row).getSchoolName();
		}
		teachersArray = toReturn;
	}

	
	//zipCode needs to be fixed, converted back to string
	public void searchSchool(String id, String name, String city, String state, String zip, String year) {
		ArrayList<School> results = new ArrayList<>();
		//need to go in and add case in final if statement for if name is null for all cases of other null
		if(id.equals("")) {
			if(zip.equals("")) {

				if(year.equals("")) {
					//if year is null

					if(state.equals("")) {

						if(city.equals("")) {
							for(School s : schools) {
								if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim())){
									results.add(s);
								}
							}
						}
						else {

							if(!(name.equals(""))) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim())){
										results.add(s);
									}
								}
							}

						}
					}

					else{
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getState().toLowerCase().trim().equals(state.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
						else{
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
					}
				}
				//end if year is null
				else {
					if(state.equals("")) {
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s : schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s : schools) {
									if(s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
						else {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
					}
					else{
						if(city.equals("")) {
							if(name != "") {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else{
								for(School s: schools) {
									if(s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
						else{
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
					}
				}//end year is not null
			}
			else {//start zip is not null
				if(year.equals("")) {//zip's year is null
					if(state.equals("")) {
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s : schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
							else {
								for(School s : schools) {
									if(zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
						}
						else {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
						}
					}
					else{
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}		
							}
						}
						else{
							if(name != null) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
						}
					}
				}//end year is not null
				else {//if zip's year isnot null
					if(state.equals("")) {
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s : schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode())) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s : schools) {
									if(zip.trim().equals(Integer.toString(s.getZipCode())) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
						else {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode())) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode())) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
					}
					else{
						if(city.equals("")) {
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(zip.trim().equals(Integer.toString(s.getZipCode())) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}		
							}
						}
						else{
							if(!name.equals("")) {
								for(School s: schools) {
									if(s.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode()))){
										results.add(s);
									}
								}
							}
							else {
								for(School s: schools) {
									if(s.getCity().toLowerCase().trim().equals(city.toLowerCase().trim()) && s.getState().toLowerCase().trim().equals(state.toLowerCase().trim()) && zip.trim().equals(Integer.toString(s.getZipCode())) && s.getStartedYear().toLowerCase().trim().equals(year.toLowerCase().trim())){
										results.add(s);
									}
								}
							}
						}
					}
				
					
				}
			}//end zip is not null
		}
		else if(!id.trim().equals("")){//ID not null start
			for(School s : schools) {
				if(Integer.toString(s.getId()).equals(id.trim())){
					results.add(s);
				}
		}

	}
	schoolResults = results;
	populateSchoolSearch();
	}

	public void searchTeacher(Teacher toSearch) {
		ArrayList<Teacher> results = new ArrayList<>();
		for(Teacher t : teachers) {
			if(t.equalSearch(toSearch)) {
				results.add(t);
			}
		}
		teacherResults = results;
		populateTeacherSearch();
	}
	
	public void makeTeacherSchools(){
		for(Teacher t : teachers) {
			for(School s : schools) {
				if(s.getName() == null) {
					//drop out of loop if the school is nameless
				}
				else if(t.getSchoolName() == null) {
					//drop out of loop if the teacher's school isn't specified
				}
				else if(t.getSchoolName().trim().length() == s.getName().trim().length()) {
					if(t.getSchoolName().trim().toLowerCase().equals(s.getName().trim().toLowerCase())) {
					t.setSchool(s);
					}
				}
			}
		}
	}
	
	public void outputResults() {
		File file = new File("Output.csv");
		try {
			FileWriter outputFile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputFile);
			String[] toWrite;
			if(resultsArray[0].length == 6) {
				String[] header = {"School", "Address"};
				writer.writeNext(header);
				for(School s : schoolResults) {
					toWrite = s.schoolOutput();
					writer.writeNext(toWrite);
				}
				writer.close();
			}
			else if(resultsArray[0].length == 4) {
				String[] header = {"First Name", "Last Name", "E-mail", "School"};
				writer.writeNext(header);
				for(Teacher t : teacherResults) {
					toWrite = t.toOutput();
					writer.writeNext(toWrite);
				}
				writer.close();
			}
			else {
				String[] error = {"Error in Writing, see code or contact admin"};
				writer.writeNext(error);
				writer.close();
			}
		}
		catch(IOException e36) {
			e36.printStackTrace();
		}
	}
	
	/*
	public static void main(String args[]) {

			dataBase activeSchools = new dataBase();
			activeSchools.parseSchools("Active_School.xlsx");
			activeSchools.makeSchools();
			activeSchools.parseTeachers("all_teachers_sorted.xlsx");
			activeSchools.makeTeachers();
			activeSchools.makeTeacherSchools();
			Teacher toLookUpSearch = new Teacher(true, false, false, false, "","", "", "");
			/* boolean pmMock, boolean pnMock, boolean pmEC, boolean pnEC,
			String last, String first, String nameOfSchool, 
			String pState
	 */
	//Teacher lookup2 = new Teacher("","","","",true, false, false, false);
	/*
	 * String last, String first, String pState, String pEmail, 
			boolean pmMock, boolean pnMock, boolean pmEC, boolean pnEC
	 */
	//activeSchools.searchTeacher(toLookUpSearch);
	//activeSchools.outputResults();
	//activeSchools.searchSchool("", "Potomac", "", "", "", "");
	//System.out.println(activeSchools.schoolResults.get(0));
	//System.out.println(activeSchools.schoolResults.get(0).getAddress());
	/*
			int counter = 0;
			for(Teacher t : activeSchools.teacherResults) {
				//System.out.println(t);
				counter++;
			}
	 */
	//activeSchools.searchTeacher(lookup2);
	/*
			int counter2 = 0;
			for(Teacher t : activeSchools.teacherResults) {
				//System.out.println(t);
				counter2++;
			}
			System.out.println(counter);
			System.out.println(counter2);
	 */

	//}
	}
