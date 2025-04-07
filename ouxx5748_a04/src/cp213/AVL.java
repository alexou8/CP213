package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Zi Feng (Alex) Ou
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	// your code here

	if (node == null) {
	    return 0;
	}

	int lHeight;
	int rHeight;

	if (node.getLeft() == null) {
	    lHeight = 0;
	} else {
	    lHeight = node.getLeft().getHeight() + 1;
	}

	if (node.getRight() == null) {
	    rHeight = 0;
	} else {
	    rHeight = node.getRight().getHeight() + 1;
	}

	return lHeight - rHeight;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	// your code here

	if (node == null) {
	    return null;
	}

	int balancer = balance(node);

	if (balancer > 1) {
	    if (balance(node.getLeft()) >= 0) {
		node = rotateRight(node);
	    } else {
		node.setLeft(rotateLeft(node.getLeft()));
		node = rotateRight(node);
	    }
	}

	else if (balancer < -1) {
	    if (balance(node.getRight()) <= 0) {
		node = rotateLeft(node);
	    } else {
		node.setRight(rotateRight(node.getRight()));
		node = rotateLeft(node);
	    }
	}

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here

	if (node == null || node.getRight() == null) {
	    return node;
	}

	TreeNode<T> pivotLeft = node.getRight();

	node.setRight(pivotLeft.getLeft());
	pivotLeft.setLeft(node);

	node.updateHeight();
	pivotLeft.updateHeight();

	return pivotLeft;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here

	if (node == null || node.getLeft() == null) {
	    return node;
	}

	TreeNode<T> rightPivot = node.getLeft();

	node.setLeft(rightPivot.getRight());
	rightPivot.setRight(node);

	node.updateHeight();
	rightPivot.updateHeight();

	return rightPivot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	// your code here

	if (node == null) {
	    node = new TreeNode<>(countedEntity);
	    node.getCountedEntity().incrementCount();
	    this.size++;
	    return node;
	}

	final int value = countedEntity.compareTo(node.getCountedEntity());

	if (value < 0) {
	    node.setLeft(this.insertAux(node.getLeft(), countedEntity));
	} else if (value > 0) {
	    node.setRight(this.insertAux(node.getRight(), countedEntity));
	} else {
	    node.getCountedEntity().incrementCount();
	    return node;
	}

	node.updateHeight();

	return rebalance(node);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here

	if (node == null) {
	    return true;
	}

	if ((minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0)
		|| (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0)) {
	    return false;
	}

	int balancer = balance(node);
	if (balancer < -1 || balancer > 1) {
	    return false;
	}

	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	// your code here

	if (node == null) {
	    return null;
	}

	final int result = countedEntity.compareTo(node.getCountedEntity());

	if (result < 0) {
	    node.setLeft(removeAux(node.getLeft(), countedEntity));
	} else if (result > 0) {
	    node.setRight(removeAux(node.getRight(), countedEntity));
	} else {
	    if (node.getLeft() == null) {
		return node.getRight();
	    } else if (node.getRight() == null) {
		return node.getLeft();
	    } else {
		TreeNode<T> parent = node;
		TreeNode<T> successor = node.getRight();

		while (successor.getLeft() != null) {
		    parent = successor;
		    successor = successor.getLeft();
		}

		if (parent != node) {
		    parent.setLeft(successor.getRight());
		    successor.setRight(node.getRight());
		}

		successor.setLeft(node.getLeft());

		node = successor;
	    }
	}

	node.updateHeight();

	return rebalance(node);
    }

}
