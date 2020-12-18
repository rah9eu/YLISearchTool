import java.util.Comparator;
public class CompareByZipCode implements Comparator<School>{
	public int compare(School a, School b) {
		if(a.getZipCode() < b.getZipCode()) {
			return -1;
		}
		else if(a.getZipCode() > b.getZipCode()) {
			return 1;
		}
		else {
			return a.getName().compareTo(b.getName());
		}
	}
}
