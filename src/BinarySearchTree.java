public class BinarySearchTree<T extends Comparable<T>> {
    
    public static class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;

        public Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void insert(T key) {
        root = insertRecursive(root, key);
    }
    
    private Node<T> insertRecursive(Node<T> current, T key) {
        if (current == null) {
            return new Node<>(key);
        }
    
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = insertRecursive(current.left, key);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, key);
        }
        return current;
    }
    

    public void delete(T key) {
        root = deleteRecursive(root, key);
    }

    private Node<T> deleteRecursive(Node<T> current, T key) {
        if (current == null) {
            return null;
        }

        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = deleteRecursive(current.left, key);
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, key);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.key = findMin(current.right);
            current.right = deleteRecursive(current.right, current.key);
        }

        return current;
    }

    public boolean retrieve(T item) {
        return retrieveRecursive(root, item) != null;
    }

    private Node<T> retrieveRecursive(Node<T> current, T item) {
        if (current == null) {
            return null;
        }

        int cmp = item.compareTo(current.key);
        if (cmp <= 0) {
            return retrieveRecursive(current.left, item);
        } else if (cmp > 0) {
            return retrieveRecursive(current.right, item);
        } else {
            return current;
        }
    }

    public void inOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<T> current) {
        if (current != null) {
            inOrderTraversal(current.left);
            System.out.println(current.key);
            inOrderTraversal(current.right);
        }
    }

    private T findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    public void getSingleParent() {
        getSingleParentRecursive(root);
    }

    private void getSingleParentRecursive(Node<T> current) {
        if (current == null) {
            return;
        }
        int childCount = countChildren(current);
        if (childCount == 1) {
            System.out.println(current.key);
        }
        getSingleParentRecursive(current.left);
        getSingleParentRecursive(current.right);
    }

    private int countChildren(Node<T> node) {
        int count = 0;
        if (node.left != null) {
            count++;
        }
        if (node.right != null) {
            count++;
        }
        return count;
    }

    public int getNumLeafNodes() {
        return getNumLeafNodesRecursive(root);
    }

    private int getNumLeafNodesRecursive(Node<T> current) {
        if (current == null) {
            return 0;
        }
        if (current.left == null && current.right == null) {
            return 1;
        }
        return getNumLeafNodesRecursive(current.left) + getNumLeafNodesRecursive(current.right);
    }

    public void getCousins(Node<T> targetNode) {
        if (root == null || targetNode == null) {
            return;
        }
        int targetLevel = getLevel(root, targetNode, 1);
        findCousins(root, targetNode, targetLevel);
    }
    
    private void findCousins(Node<T> current, Node<T> targetNode, int targetLevel) {
        if (current == null) {
            return;
        }
        
        // Check if the current node is at the same level as the target node
        if (getLevel(root, current, 1) == targetLevel) {
            // Check if the current node is not the target node and not a sibling
            if (current != targetNode && !isSibling(root, current, targetNode)) {
                System.out.println(current.key);
            }
        }
        
        // Recursively search for cousins in the left and right subtrees
        findCousins(current.left, targetNode, targetLevel);
        findCousins(current.right, targetNode, targetLevel);
    }
    
    private boolean isSibling(Node<T> root, Node<T> a, Node<T> b) {
        if (root == null) {
            return false;
        }
        return (root.left == a && root.right == b) || (root.left == b && root.right == a) ||
               isSibling(root.left, a, b) || isSibling(root.right, a, b);
    }
    
    private int getLevel(Node<T> root, Node<T> node, int level) {
        if (root == null) {
            return 0;
        }
        if (root == node) {
            return level;
        }
        int downlevel = getLevel(root.left, node, level + 1);
        if (downlevel != 0) {
            return downlevel;
        }
        return getLevel(root.right, node, level + 1);
    }    
}
