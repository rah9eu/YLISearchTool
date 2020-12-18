//Arthur Harris, 2019
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.Box;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;


public class GUI extends JFrame{

	private JTextField schoolFileName;
	private JTextField teacherFileName;
	private JScrollPane scrollPane;
	private JTable results;
	private String[] header;
	private String[] schoolHeader = {"Name", "City", "State", "Zip Code", "Type", "Year"};
	private String[] teacherHeader = {"First", "Last", "Email", "School"};
	private String[] resultsHeader;
	private String[][] tableDisplay;
	private String[][] searchResults;  //for list of the results
	private String[][] schools; //for list of schools, prevents rerun after first run
	private String[][] teachers; //for list of teachers, prevents rerun after first run
	private String[][] testData = {{"Import Data and click a button to get started", ""}, {}};
	private String[] testCol = {"", ""};
	private dataBase theDataBase;
	private JTextField sName;
	private JTextField sZip;
	private JTextField sCity;
	private JTextField sState;
	private JTextField sYear;
	private JTextField sID;
	private JTextField tFirstName;
	private JTextField tLastName;
	private JTextField tSchool;
	private JTextField tState;
	private JCheckBox mMock;
	private JCheckBox mEC;
	private JCheckBox nMock;
	private JCheckBox nEC;
	private JLabel notifications;
	
	public void addComponentsToPane(Container pane) {
		
		theDataBase = new dataBase();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		JPanel notify = new JPanel();
		notifications = new JLabel();
		notify.add(notifications);
		
		
		JPanel bottom = new JPanel(new GridLayout(0,2));
		
		//Start of Left side of GUI
		JPanel resultsPanel = new JPanel();
		BoxLayout resultsLayout = new BoxLayout(resultsPanel, BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		tableDisplay = testData;
		header = testCol;
		DefaultTableModel toBeResults = new DefaultTableModel(tableDisplay,header);
		results = new JTable(toBeResults);
		scrollPane = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultsPanel.add(scrollPane, BorderLayout.WEST);
		ButtonGroup selectTableData = new ButtonGroup();
		
		class resultsListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchResults != null) {
					tableDisplay = searchResults;
					header = resultsHeader;
					DefaultTableModel temp = new DefaultTableModel(tableDisplay, header);
					results.setModel(temp);
					results.setAutoCreateRowSorter(false);
					results.setAutoCreateRowSorter(true);
					TableColumnModel tempCol = results.getColumnModel();
					if(temp.getColumnCount() == 6) {
						tempCol.getColumn(0).setPreferredWidth(200);
						tempCol.getColumn(1).setPreferredWidth(100);
						tempCol.getColumn(2).setPreferredWidth(50);
						tempCol.getColumn(3).setPreferredWidth(50);
						tempCol.getColumn(4).setPreferredWidth(70);
						tempCol.getColumn(5).setPreferredWidth(50);
					}
					else {
						tempCol.getColumn(0).setPreferredWidth(100);
						tempCol.getColumn(1).setPreferredWidth(100);
						tempCol.getColumn(2).setPreferredWidth(200);
						tempCol.getColumn(3).setPreferredWidth(200);
					}
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					results.setPreferredSize(new Dimension(screenSize.width/2, results.getRowCount() * results.getRowHeight()));
				}
			}
			
		}
		
		class teacherListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e1) {
				if(teachers != null) {
					tableDisplay = teachers;
					header = teacherHeader;
					DefaultTableModel temp = new DefaultTableModel(tableDisplay, header);
					results.setModel(temp);
					results.setAutoCreateRowSorter(false);
					results.setAutoCreateRowSorter(true);
					TableColumnModel tempCol = results.getColumnModel();
					tempCol.getColumn(0).setPreferredWidth(100);
					tempCol.getColumn(1).setPreferredWidth(100);
					tempCol.getColumn(2).setPreferredWidth(200);
					tempCol.getColumn(3).setPreferredWidth(200);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					results.setPreferredSize(new Dimension(screenSize.width/2, results.getRowCount() * results.getRowHeight()));
				}
			}

		}
		
		class schoolListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e2) {
				// TODO Auto-generated method stub
				if(schools != null) {
					tableDisplay = schools;
					header = schoolHeader;
					DefaultTableModel temp = new DefaultTableModel(tableDisplay, header);
					results.setModel(temp);
					results.setAutoCreateRowSorter(false);
					results.setAutoCreateRowSorter(true);
					TableColumnModel tempCol = results.getColumnModel();
					tempCol.getColumn(0).setPreferredWidth(200);
					tempCol.getColumn(1).setPreferredWidth(200);
					tempCol.getColumn(2).setPreferredWidth(150);
					tempCol.getColumn(3).setPreferredWidth(80);
					tempCol.getColumn(4).setPreferredWidth(80);
					tempCol.getColumn(5).setPreferredWidth(70);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					results.setPreferredSize(new Dimension(screenSize.width/2, results.getRowCount() * results.getRowHeight()));
				}
			}

		}
		
		JRadioButton selectSchools = new JRadioButton("Show Schools");
		selectSchools.setActionCommand("Show Schools");
		selectSchools.addActionListener(new schoolListener());
		JRadioButton selectTeachers = new JRadioButton("Show Teachers");
		selectTeachers.setActionCommand("Show Teachers");
		selectTeachers.addActionListener(new teacherListener());
		JRadioButton selectResults = new JRadioButton("Show Results");
		selectResults.setActionCommand("Show Results");
		selectResults.addActionListener(new resultsListener());
	
		//add buttons to group and initialize them all to off
		selectTableData.add(selectSchools);
		selectTableData.add(selectTeachers);
		selectTableData.add(selectResults);
		selectSchools.setSelected(false);
		selectTeachers.setSelected(false);
		selectResults.setSelected(false);
		
		//create a panel with the buttons
		JPanel tableSelector = new JPanel();
		//GridLayout tableButtons = new GridLayout(0,4);
		//tableButtons.setHgap(2);
		//tableSelector.setLayout(tableButtons);
		//tableSelector.add(selectSchools, BorderLayout.WEST);
		//tableSelector.add(selectTeachers, BorderLayout.CENTER);
		//tableSelector.add(selectResults, BorderLayout.EAST);
		JPanel tableSelectorLayer = new JPanel();
		tableSelectorLayer.setLayout(new BorderLayout());
		tableSelectorLayer.add(tableSelector, BorderLayout.WEST);
		JPanel tableSelectorLayer3 = new JPanel();
		tableSelectorLayer3.setLayout(new GridLayout(0,4));
		//tableSelectorLayer3.add(tableSelectorLayer);
		tableSelectorLayer3.add(selectSchools);
		tableSelectorLayer3.add(selectTeachers);
		tableSelectorLayer3.add(selectResults);
		JButton toExport = new JButton("Export");
		
		class exportListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				theDataBase.outputResults();
			}
			
		}
		toExport.addActionListener(new exportListener());
		tableSelectorLayer3.add(toExport);
		//tableSelector.add(Box.createHorizontalStrut(5));
		
		//create a left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(tableSelectorLayer3);
		leftPanel.add(resultsPanel);
		
		//pane.add(leftPanel);
		bottom.add(leftPanel);
		
		//End Left side of GUI
		
		//Start Top of GUI
		
		JPanel top = new JPanel();
		JPanel exporter = new JPanel(new GridLayout(0,4));
		JLabel blank4 = new JLabel("");
		/*
		JButton toExport = new JButton("Export");
		
		class exportListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				theDataBase.outputResults();
			}
			
		}
		toExport.addActionListener(new exportListener());
		exporter.add(toExport);
		*/
		JLabel instructions = new JLabel("Move the files into the YLI Search Folder and then import in order the files (Schools, then Teachers) using the import tools");

		exporter.add(blank4);
		top.add(exporter);
		top.add(new JLabel(""));
		top.add(instructions);
		
		top.setLayout(new GridLayout(0,4));

		class schoolFileListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String schoolFile = (String) schoolFileName.getText();
				schoolFile.trim();
				if(schoolFile.contains("xlsx")) {
					try {
					notifications.setText("");
					theDataBase.parseSchools(schoolFile);
					theDataBase.makeSchools();
					schools = theDataBase.getSchoolsArray();
					}
					catch(NullPointerException e4) {
						notifications.setText("File Not Found. Make Sure the File is in this Folder");
					}
				}
				else if(!schoolFile.contains("xlsx")) {
					notifications.setText("Make sure your file is in XLSX format and try again");
				}

			}
		}

		class teacherFileListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String teacherFile = (String) teacherFileName.getText();
				teacherFile.trim();
				if(teacherFile.contains("xlsx") && schools != null) {
					try {
					notifications.setText("");
					theDataBase.parseTeachers(teacherFile);
					theDataBase.makeTeachers();
					theDataBase.makeTeacherSchools();
					teachers = theDataBase.getTeachersArray();
					}
					catch(NullPointerException e1) {
						notifications.setText("File Not Found. Make Sure the File is in this Folder");
					}
				}
				else if(schools == null){
					notifications.setText("Import Schools first and try again");
				}
				else if(!teacherFile.contains("xlsx")) {
					notifications.setText("Make sure your file is in XLSX format and try again");
				}
			}

		}

		schoolFileName = new JTextField();
		teacherFileName = new JTextField();
		
		JButton addSchools = new JButton("Import Schools (.xlsx File Type)");
		addSchools.addActionListener(new schoolFileListener());
		
		JButton addTeachers = new JButton("Import Teachers (.xlsx File Type)");
		addTeachers.addActionListener(new teacherFileListener());
		
		top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
		JLabel blank = new JLabel(" ");
		
		top.add(blank);
		top.add(blank);
		JPanel topImport = new JPanel();
		topImport.setLayout(new GridLayout(2,2));
		topImport.add(schoolFileName);
		topImport.add(addSchools);
		topImport.add(teacherFileName);
		topImport.add(addTeachers);
		top.add(topImport);
		pane.add(top);
		//End of Top
		
		pane.add(notify);
		
		//
		//Right Panel / Search Panel
		//
		
		JTabbedPane rightTabs = new JTabbedPane();
		
		//Start School Search Pad
		JPanel schoolSearchPanel = new JPanel(new GridLayout(0,2, 20, 0));
		JPanel schoolBox = new JPanel();
		schoolBox.setLayout(new BoxLayout(schoolBox, BoxLayout.Y_AXIS));
		
		JButton schoolSearchButton = new JButton("Search");
		
		class schoolSearchListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) sName.getText();
				String zip = (String) sZip.getText();
				String city = (String) sCity.getText();
				String state = (String) sState.getText();
				String year = (String) sYear.getText();
				String id = (String) sID.getText();
				
				if(name == "" || name == null) {
					name = null;
				}
				if( zip == "" || zip == null) {
					zip = null;
				}
				if(city == "" || city == null) {
					city = null;
				}
				if(state == "" || state == null) {
					state = null;
				}
				if(year == "" || year == null) {
					year = null;
				}
				if(id == "" || id == null) {
					id = null;
				}
				theDataBase.searchSchool(id, name, city, state, zip, year);
				searchResults = theDataBase.getResultsArray();
				resultsHeader = schoolHeader;
				DefaultTableModel temp = (DefaultTableModel) results.getModel();
				temp.fireTableDataChanged();
			}
		}
		Dimension buttonSize = new Dimension(1, 1);
		schoolSearchButton.addActionListener(new schoolSearchListener());
		schoolSearchButton.setPreferredSize(buttonSize);
		
		class clearListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sName.setText("");
				sZip.setText("");
				sCity.setText("");
				sState.setText("");
				sYear.setText("");
				sID.setText("");
			}
			
		}
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new clearListener());
		clearButton.setPreferredSize(buttonSize);
		
		schoolSearchPanel.add(schoolSearchButton);

		schoolSearchPanel.add(clearButton);

		schoolBox.add(schoolSearchPanel);
		
		JPanel schoolSearchParam = new JPanel(new GridLayout(0,1));
		JLabel schoolName = new JLabel("School Name: ");
		JLabel zipCode = new JLabel("Zip Code: ");
		JLabel schoolCity = new JLabel("City: ");
		JLabel schoolState = new JLabel("State (Full Name, not Abbreviation):");
		JLabel schoolYear = new JLabel("Year: ");
		JLabel schoolID = new JLabel("YLI ID Number: ");
		
		sName = new JTextField();
		sZip = new JTextField();
		sCity = new JTextField();
		sState = new JTextField();
		sYear = new JTextField();
		sID = new JTextField();
		
		schoolSearchParam.add(schoolName);
		schoolSearchParam.add(sName);
		schoolSearchParam.add(schoolCity);
		schoolSearchParam.add(sCity);
		schoolSearchParam.add(schoolState);
		schoolSearchParam.add(sState);
		schoolSearchParam.add(zipCode);
		schoolSearchParam.add(sZip);
		schoolSearchParam.add(schoolYear);
		schoolSearchParam.add(sYear);
		schoolSearchParam.add(schoolID);
		schoolSearchParam.add(sID);
		
		schoolBox.add(schoolSearchParam);
		rightTabs.add(schoolBox, "Search for Schools");
		//End School Search Tab
		
		//Start Teacher Tab
		JPanel teacherBox = new JPanel();
		teacherBox.setLayout(new BoxLayout(teacherBox, BoxLayout.Y_AXIS));
		
		JPanel teacherSearch = new JPanel(new GridLayout(0,2, 20, 0));
		JButton tSearch = new JButton("Search");
		
		JButton tClear = new JButton("Clear");
		
		class tSearchListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fName = tFirstName.getText();
				String lName = tLastName.getText();
				String tSchoolName = tSchool.getText();
				String tStateName = tState.getText();
				boolean tmMock = mMock.isSelected();
				boolean tnMock = nMock.isSelected();
				boolean tmEC = mEC.isSelected();
				boolean tnEC = nEC.isSelected();
				//constructor is last,first, school Name, mMock, nMock, mEC, nEC
				//Issue is somehow the reading in of input, causing issues, because the system works in dataBase
				//System.out.println(fName);
				//System.out.println(lName);
				//System.out.println(tSchoolName);
				//System.out.println(tmMock);
				//System.out.println(tnMock);
				//System.out.println(tmEC);
				//System.out.println(tnEC);
				Teacher toSearch = new Teacher(tmMock, tnMock,
						tmEC, tnEC, lName, fName, tSchoolName, tStateName);
				theDataBase.searchTeacher(toSearch);
				searchResults = theDataBase.getResultsArray();
				resultsHeader = teacherHeader;
				DefaultTableModel temp = (DefaultTableModel) results.getModel();
				temp.fireTableDataChanged();
			}
			
		}
		
		
		class tClearListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				tFirstName.setText("");
				tLastName.setText("");
				tSchool.setText("");
				mMock.setSelected(false);
				mEC.setSelected(false);
				nMock.setSelected(false);
				nEC.setSelected(false);
			}
			
		}

		tSearch.addActionListener(new tSearchListener());
		tClear.addActionListener(new tClearListener());
		JLabel tFirst = new JLabel("First Name: ");
		tFirstName = new JTextField();
		JLabel tLast = new JLabel("Last Name: ");
		tLastName = new JTextField();
		JLabel tS = new JLabel("School: ");
		tSchool = new JTextField();
		JLabel tStateAbbv = new JLabel("State (Full Name, not Abbreviation):");
		tState = new JTextField();
		
		teacherSearch.add(tSearch);
		teacherSearch.add(tClear);
		teacherBox.add(teacherSearch, BorderLayout.NORTH);
		
		JPanel teacherSearchParam = new JPanel();
		teacherSearchParam.setLayout(new GridLayout(0,1));
		
		teacherSearchParam.add(tFirst);
		teacherSearchParam.add(tFirstName);
		teacherSearchParam.add(tLast);
		teacherSearchParam.add(tLastName);
		teacherSearchParam.add(tS);
		teacherSearchParam.add(tSchool);
		teacherSearchParam.add(tStateAbbv);
		teacherSearchParam.add(tState);
		teacherBox.add(teacherSearchParam);
		
		JPanel searchButtons = new JPanel(new GridLayout(0,2));
		
		nEC = new JCheckBox("National E-Congress", false);
		mEC = new JCheckBox("My E-Congress", false);
		nMock = new JCheckBox("National Mock Election", false);
		mMock = new JCheckBox("My Mock Election", false);
		
		searchButtons.add(mEC);
		searchButtons.add(mMock);
		searchButtons.add(nEC);
		searchButtons.add(nMock);
		teacherBox.add(searchButtons);
		
		rightTabs.add(teacherBox, "Search for Teachers");
		//End Teacher Search Tab
		
		bottom.add(rightTabs);
		//End Right Panel
		
		pane.add(bottom);
		//End Bottom Panel
	}
	

	private static void createAndShowGUI() {//Courtesy of CS 2110 at UVa
		//Create and set up the window.

		GUI frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {     
				createAndShowGUI();
			}
		});
	}
}
