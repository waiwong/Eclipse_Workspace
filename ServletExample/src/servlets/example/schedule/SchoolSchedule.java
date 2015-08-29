package servlets.example.schedule;

import java.util.ArrayList;
import java.util.List;

public class SchoolSchedule {
	private List<SchoolClass> classes = new ArrayList<SchoolClass>();

	public void addClass(SchoolClass schoolClass) {
		this.classes.add(schoolClass);
	}

	public List<SchoolClass> getClasses() {
		return classes;
	}
}
