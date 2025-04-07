package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Zi Feng (Alex) Ou
 * @version 2025-03-02
 */
public class CAS extends Professor {

    // your code here

    private String term;

    /**
     * CAS Contructor
     *
     * @param lastName   CAS last name
     * @param firstName  CAS first name
     * @param department CAS department
     * @param term       CAS contract term
     */
    public CAS(String lastName, String firstName, String department, String term) {
	super(lastName, firstName, department);
	this.term = term;
    }

    /**
     * Term getter
     *
     * @return this.term
     */
    public String getTerm() {
	return this.term;
    }

    /**
     * Formatted string of CAS
     */
    @Override
    public String toString() {
	return super.toString() + "\nTerm: " + this.term;
    }

}
