package cp213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Zi Feng (Alex) Ou
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class Menu {

    // Attributes.

    // define a List of MenuItem objects
    // Note that this must be a *List* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

    // your code here

    private List<MenuItem> mList;

    /**
     * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
     * into the Menu List.
     *
     * @param items an existing List of MenuItems.
     */
    public Menu(List<MenuItem> items) {

	// your code here

	mList = new ArrayList<>(items);

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	// your code here

	mList = new ArrayList<>();

	while (fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine().trim();
	    String[] parts = line.split(" ", 2);
	    if (parts.length == 2) {
		try {
		    double price = Double.parseDouble(parts[0]);
		    String name = parts[1].trim();
		    mList.add(new MenuItem(name, price));
		} catch (NumberFormatException e) {

		}
	    }
	}

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	// your code here

	if (i >= 0 && i < mList.size()) {
	    MenuItem original = mList.get(i);
	    return new MenuItem(original.getEntity(), original.getAmount());
	}

	return null;

    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	// your code here

	return mList.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

	// your code here

	StringBuilder mItem = new StringBuilder();

	for (int i = 0; i < mList.size(); i++) {
	    mItem.append(i + 1).append(") ").append(mList.get(i).toString()).append("\n");
	}

	return mItem.toString();
    }
}