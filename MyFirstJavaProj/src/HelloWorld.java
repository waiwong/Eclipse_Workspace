import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world.");
		List<String> list = new ArrayList<>();
		list.add("vogella.com");
		list.add("google.com");
		list.add("heise.de");
		list.forEach(s -> System.out.println(s));
		//
		System.out.println("Another method for lembdas");
		list.forEach(System.out::println);

		// printout the numbers from 1 to 100
		IntStream.range(1, 10).forEach(s -> System.out.println(s));

		// create a list of integers for 1 to 100
		List<Integer> listInt = new ArrayList<>();
		IntStream.range(1, 10).forEach(it -> listInt.add(it));
		System.out.println("Size " + listInt.size());
		String str = "1";
		// Conversion from String to integer
		int i = Integer.parseInt(str);
		System.out.println(i);
		// Conversion from String to float
		float f = Float.parseFloat(str);
		System.out.println(f);
		// Conversion from String to double
		double d = Double.parseDouble(str);
		System.out.println(d);

		// HelloWorld date = new HelloWorld();
		// System.out.println(date.convertDateToSQL());
	}

	private java.sql.Date convertDateToSQL() {
		SimpleDateFormat template = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar enddate = new GregorianCalendar(1999, 10, 31);
		return java.sql.Date.valueOf(template.format(enddate));
	}
}
