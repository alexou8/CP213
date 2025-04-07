package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Zi Feng (Alex) Ou
 * @version 2025-03-02
 */
public class IA extends Student {

    // your code here

    private String course;

    /**
     * IA Constructor
     *
     * @param lastName  IA last name
     * @param firstName IA first name
     * @param id        Student ID
     * @param course    Course number
     */
    public IA(String lastName, String firstName, String id, String course) {
	super(lastName, firstName, id);
	this.course = course;

    }

    /**
     * Course getter
     *
     * @return this.course
     */
    public String getCourse() {
	return this.course;
    }

    /**
     * Formatted string of IA
     */
    @Override
    public String toString() {
	return super.toString() + "\nCourse: " + this.course;
    }

}
