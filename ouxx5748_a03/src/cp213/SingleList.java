package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> object contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The object to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

	// your code here

	SingleNode<T> curr = this.front;
	SingleNode<T> prev = null;

	while (curr != null) {
	    if (curr.getEntity().compareTo(key) == 0) {
		return prev;
	    }
	    prev = curr;
	    curr = curr.getNext();
	}

	return null;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param entity The object to append.
     */
    public void append(final T entity) {

	// your code here

	SingleNode<T> node = new SingleNode<>(entity, null);

	if (this.front == null) {
	    this.front = node;
	} else {
	    this.rear.setNext(node);
	}

	this.rear = node;
	this.length++;

	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each object formerly present in this SingleList. The first occurrence of
     * each object is preserved.
     */
    public void clean() {

	// your code here

	SingleNode<T> curr = this.front;

	while (curr != null) {
	    SingleNode<T> prev = curr;
	    SingleNode<T> check = curr.getNext();

	    while (check != null) {
		if (check.getEntity().equals(curr.getEntity())) {
		    prev.setNext(check.getNext());
		    this.length--;
		} else {
		    prev = check;
		}
		check = check.getNext();
	    }

	    curr = curr.getNext();
	}

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	boolean LR = true;

	while (!left.isEmpty() || !right.isEmpty()) {
	    if (LR && !left.isEmpty()) {
		this.moveFrontToRear(left);
	    } else if (!right.isEmpty()) {
		this.moveFrontToRear(right);
	    }

	    LR = !LR;
	}

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key object to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {

	// your code here

	return this.linearSearch(key) != null;

    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The object to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	// your code here

	int count = 0;
	SingleNode<T> curr = this.front;

	while (curr != null) {
	    if (curr.getEntity().equals(key)) {
		count++;
	    }

	    curr = curr.getNext();
	}

	return count;
    }

    /**
     * Finds and returns the object in list that matches key.
     *
     * @param key The object to search for.
     * @return The object that matches key, null otherwise.
     */
    public T find(final T key) {

	// your code here

	SingleNode<T> node = this.linearSearch(key);
	T value;

	if (node == null) {
	    if (this.front != null && this.front.getEntity().equals(key)) {
		value = this.front.getEntity();
	    } else {
		value = null;
	    }

	} else {
	    value = (node.getNext() != null) ? node.getNext().getEntity() : null;
	}

	return value;
    }

    /**
     * Get the nth object in this SingleList.
     *
     * @param n The index of the object to return.
     * @return The nth object in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	// your code here

	if (n < 0 || n >= this.length) {
	    throw new ArrayIndexOutOfBoundsException("Index " + n + " : Not a valid index");
	}

	SingleNode<T> curr = this.front;
	int count = 0;

	while (count < n) {
	    curr = curr.getNext();
	    count++;
	}

	return curr.getEntity();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same objects in the same order
     *         as source, false otherwise.
     */
    public boolean equals(final SingleList<T> source) {

	// your code here

	if (this.length != source.length) {
	    return false;
	}

	SingleNode<T> currNode = this.front;
	SingleNode<T> srcCurrSource = source.front;

	while (currNode != null) {
	    if (!currNode.getEntity().equals(srcCurrSource.getEntity())) {
		return false; // Found a mismatch
	    }
	    currNode = currNode.getNext();
	    srcCurrSource = srcCurrSource.getNext();
	}

	return true;
    }

    /**
     * Finds the first location of a object by key in this SingleList.
     *
     * @param key The object to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	// your code here

	int index = 0;
	SingleNode<T> curr = this.front;

	while (curr != null) {
	    if (curr.getEntity().equals(key)) {
		return index; // Found key, return its index
	    }
	    curr = curr.getNext();
	    index++;
	}

	return -1;
    }

    /**
     * Inserts object into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i      The index to insert the new data at.
     * @param entity The new object to insert into this SingleList.
     */
    public void insert(int i, final T entity) {

	// your code here

	SingleNode<T> node = new SingleNode<>(entity, null);

	if (this.front == null || i <= 0) {
	    node.setNext(this.front);
	    this.front = node;

	    if (this.rear == null) {
		this.rear = node;
	    }

	} else if (i >= this.length) {
	    this.rear.setNext(node);
	    this.rear = node;

	} else {
	    SingleNode<T> curr = this.front;
	    SingleNode<T> prev = null;

	    for (int j = 0; j < i; j++) {
		prev = curr;
		curr = curr.getNext();
	    }

	    prev.setNext(node);
	    node.setNext(curr);
	}

	this.length++;

	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then objects from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	SingleNode<T> curr = left.front;

	while (curr != null) {
	    if (!this.contains(curr.getEntity()) && right.contains(curr.getEntity())) {
		this.append(curr.getEntity());
	    }
	    curr = curr.getNext();
	}

	curr = right.front;
	while (curr != null) {
	    if (!this.contains(curr.getEntity()) && left.contains(curr.getEntity())) {
		this.append(curr.getEntity());
	    }
	    curr = curr.getNext();
	}

	return;
    }

    /**
     * Finds the maximum object in this SingleList.
     *
     * @return The maximum object.
     */
    public T max() {

	// your code here

	if (this.front == null) {
	    return null;
	}

	T max = this.front.getEntity();
	SingleNode<T> curr = this.front.getNext();

	while (curr != null) {
	    if (curr.getEntity().compareTo(max) > 0) {
		max = curr.getEntity();
	    }
	    curr = curr.getNext();
	}

	return max;
    }

    /**
     * Finds the minimum object in this SingleList.
     *
     * @return The minimum object.
     */
    public T min() {

	// your code here

	if (this.front == null) {
	    return null;
	}

	T min = this.front.getEntity();
	SingleNode<T> current = this.front.getNext();

	while (current != null) {
	    if (current.getEntity().compareTo(min) < 0) {
		min = current.getEntity();
	    }
	    current = current.getNext();
	}

	return min;
    }

    /**
     * Inserts object into the front of this SingleList.
     *
     * @param entity The object to insert into the front of this SingleList.
     */
    public void prepend(final T entity) {

	// your code here

	SingleNode<T> node = new SingleNode<>(entity, this.front);
	this.front = node;

	if (this.rear == null) {
	    this.rear = node;
	}

	this.length++;

	return;
    }

    /**
     * Finds, removes, and returns the object in this SingleList that matches key.
     *
     * @param key The object to search for.
     * @return The object matching key, null otherwise.
     */
    public T remove(final T key) {

	// your code here

	if (this.front == null) {
	    return null;
	}

	if (this.front.getEntity().compareTo(key) == 0) {
	    T value = this.front.getEntity();
	    this.front = this.front.getNext();
	    this.length--;

	    if (this.front == null) {
		this.rear = null;
	    }

	    return value;
	}

	SingleNode<T> curr = this.front;

	while (curr.getNext() != null) {
	    if (curr.getNext().getEntity().compareTo(key) == 0) {
		T value = curr.getNext().getEntity();
		curr.setNext(curr.getNext().getNext());
		this.length--;

		if (curr.getNext() == null) {
		    this.rear = curr;
		}

		return value;
	    }

	    curr = curr.getNext();
	}

	return null;
    }

    /**
     * Removes the object at the front of this SingleList.
     *
     * @return The object at the front of this SingleList.
     */
    public T removeFront() {

	// your code here

	if (this.front == null) {
	    return null;
	}

	T value = this.front.getEntity();
	this.front = this.front.getNext();
	this.length--;

	if (this.front == null) {
	    this.rear = null;
	}

	return value;
    }

    /**
     * Finds and removes all objects in this SingleList that match key.
     *
     * @param key The object to search for.
     */
    public void removeMany(final T key) {

	// your code here

	if (this.front == null) {
	    return;
	}

	while (this.front != null && this.front.getEntity().compareTo(key) == 0) {
	    this.front = this.front.getNext();
	    this.length--;

	    if (this.front == null) {
		this.rear = null;
		return;
	    }
	}

	SingleNode<T> curr = this.front;

	while (curr != null && curr.getNext() != null) {
	    if (curr.getNext().getEntity().compareTo(key) == 0) {
		curr.setNext(curr.getNext().getNext());
		this.length--;

		if (curr.getNext() == null) {
		    this.rear = curr;
		}

	    } else {
		curr = curr.getNext();
	    }
	}

	return;
    }

    /**
     * Reverses the order of the objects in this SingleList.
     */
    public void reverse() {

	// your code here

	if (this.front == null || this.front.getNext() == null) {
	    return;
	}

	SingleNode<T> prev = null;
	SingleNode<T> curr = this.front;
	SingleNode<T> next = null;

	this.rear = this.front;

	while (curr != null) {
	    next = curr.getNext();
	    curr.setNext(prev);
	    prev = curr;
	    curr = next;
	}

	this.front = prev;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * object than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	int length = this.length;
	int rLength = length / 2;
	int lLength = length - rLength;

	while (lLength > 0) {
	    left.moveFrontToRear(this);
	    lLength--;
	}

	while (rLength > 0) {
	    right.moveFrontToRear(this);
	    rLength--;
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	boolean LR = true;

	while (this.front != null) {
	    if (LR) {
		left.moveFrontToRear(this);
	    } else {
		right.moveFrontToRear(this);
	    }
	    LR = !LR;
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies object
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then objects from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	SingleNode<T> rNode = right.front;
	SingleNode<T> lNode = left.front;

	while (lNode != null) {
	    if (!this.contains(lNode.getEntity())) {
		this.append(lNode.getEntity());
	    }
	    lNode = lNode.getNext();
	}

	while (rNode != null) {
	    if (!this.contains(rNode.getEntity())) {
		this.append(rNode.getEntity());
	    }
	    rNode = rNode.getNext();
	}

	return;
    }
}
