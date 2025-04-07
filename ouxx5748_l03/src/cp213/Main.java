package cp213;

import java.time.LocalDate;

/**
 * Tests the Student class.
 *
 * @author Zi Feng (Alex) Ou
 * @version 2022-02-03
 */
public class Main {

    public static void main(String[] args) {
	final String line = "-".repeat(40);
	int id = 123456;
	String surname = "Brown";
	String forename = "David";
	LocalDate birthDate = LocalDate.parse("1962-10-25");
	Student student = new Student(id, surname, forename, birthDate);
	System.out.println("New Student:");
	System.out.println(student);

	// call getters here
	System.out.println(line);
	System.out.println("Test Getters");

	System.out.println("ID: " + student.getId());
	System.out.println("Surname: " + student.getSurname());
	System.out.println("Forename: " + student.getForename());
	System.out.println("Birthdate: " + student.getBirthDate());
	System.out.println(line);

	// call setters here
	System.out.println(line);
	System.out.println("Test Setters");

	student.setId(888888);
	student.setSurname("Alex");
	student.setForename("Ou");
	student.setBirthDate(LocalDate.parse("2004-03-12"));

	System.out.println("Updated Student:");
	System.out.println(student);
	System.out.println(line);
	System.out.println("Test compareTo");

	// create new Students - call comparisons here

	Student student0 = new Student(000000, "Mary", "Jane", LocalDate.of(2000, 2, 3));
	Student student1 = new Student(111111, "Cole", "Kane", LocalDate.of(2001, 1, 2));
	Student student2 = new Student(000000, "Mary", "Smoke", LocalDate.of(2001, 1, 2));

	System.out.println("Comparing (0 to 1): " + student0.compareTo(student1));
	System.out.println("Comparing (0 to 2): " + student0.compareTo(student2));
	System.out.println("Comparing (1 to 2): " + student1.compareTo(student2));
    }

}
