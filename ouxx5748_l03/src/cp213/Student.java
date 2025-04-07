package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Zi Feng (Alex) Ou
 * @version 2022-02-03
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {

	// assign attributes here

	this.id = id;
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;

	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";

	// your code here

	string = String.format("Name:      %s, %s%nID:        %d%nBirthdate: %s", surname, forename, id,
		birthDate.toString());

	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;

	// your code here

	if (result == 0) {
	    result = this.forename.compareTo(target.forename);
	}
	if (result == 0) {
	    result = Integer.compare(this.id, target.id);
	}

	return result;
    }

    // getters and setters defined here

    /**
     * Gets Student ID
     *
     * @return
     */
    public int getId() {
	return id;
    }

    /**
     * Sets Student ID
     *
     * @param id
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Gets Student Surname
     *
     * @return
     */
    public String getSurname() {
	return surname;
    }

    /**
     * Sets Student Surname
     *
     * @param surname
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * Gets Student Forename
     *
     * @return
     */
    public String getForename() {
	return forename;
    }

    /**
     * Sets Student Forename
     *
     * @param forename
     */
    public void setForename(String forename) {
	this.forename = forename;
    }

    /**
     * Gets Student Birthdate
     *
     * @return
     */
    public LocalDate getBirthDate() {
	return birthDate;
    }

    /**
     * Sets Student Birthdate
     *
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

}
