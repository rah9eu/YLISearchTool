import java.util.Comparator;
public class CompareBySchoolName implements Comparator<School>{
	public int compare(School a, School b) {
		if((a.getState().compareTo(b.getState())) == 0) {
			return a.getName().compareTo(b.getName());
		}
		else {
			return a.getState().compareTo(b.getState());
		}
	}
}
