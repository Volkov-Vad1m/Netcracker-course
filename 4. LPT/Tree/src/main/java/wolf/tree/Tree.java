package wolf.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;

    public Tree() { }


    // *******************************************************
    // *******************************************************
    private Node<T> find(T value) {
        if(root == null) {
            return null;
        }

        Node<T> curNode = root;

        while (curNode.getValue().equals(value)) {
            int compare  = value.compareTo(curNode.getValue());

            if(compare < 0) {  //  value < curNode.value
                curNode = curNode.getLeftChild();
            } else {
                curNode = curNode.getRightChild();
            }

            if(curNode == null) {
                return null; // not found
            }
        }

        return curNode;
    }

    // *******************************************************
    public boolean contains(T value) {
        return find(value) != null;
    }

    // *******************************************************
    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        addRecursively(value, root);

    }

    private void addRecursively(T value, Node<T> curNode) {

        int compare = value.compareTo(curNode.getValue());

        if(compare > 0) { // value > curNode.value

            if(curNode.getRightChild() == null) {
                curNode.setRightChild(new Node<>(value));
            } else {
                addRecursively(value, curNode.getRightChild());
            }

        }
        else if(compare < 0) { // value < curNode.value

            if(curNode.getLeftChild() == null) {
                curNode.setLeftChild(new Node<>(value));
            } else {
                addRecursively(value, curNode.getLeftChild());
            }

        } else { } // already exists

    }

    // *******************************************************
    public void delete(T value) {
        deleteRecursively(value, root);

    }

    private Node<T> deleteRecursively(T value, Node<T> curNode) {
        if (curNode == null) {
            return null;
        }

        int compare = value.compareTo(curNode.getValue());

        if (compare < 0) {
            curNode.setLeftChild(deleteRecursively(value, curNode.getLeftChild()));
        }
        else if (compare > 0) {
            curNode.setRightChild(deleteRecursively(value, curNode.getRightChild()));
        }
        else { //equals

            if (isLast(curNode)) {
                return null;
            }
            else if (curNode.getLeftChild() == null) {
                return curNode.getRightChild();
            }
            else if (curNode.getRightChild() == null) {
                return curNode.getLeftChild();
            }
            else {
                // ищем самый маленький элемент в правом поддереве и ставим его вместо удалённого узла.
                Node<T> smallestNode = findSmallestNode(curNode.getRightChild());
                curNode.setValue(smallestNode.getValue());
                curNode.setRightChild(deleteRecursively(smallestNode.getValue(), curNode.getRightChild()));
                return curNode;
            }
        }
        return curNode;
    }

    /**
     * method finds the smallest node in subtree of node
     * @param node
     * @return the smallest node
     */
    private Node<T> findSmallestNode(Node<T> node) {
        return node.getLeftChild() == null ? node : findSmallestNode(node);
    }

    // *******************************************************
    public List<T> preorder() {
        if(root == null) {
            return new LinkedList<>();
        }

        return preorderRecursively(root, new LinkedList<T>());
    }

    private List<T> preorderRecursively(Node<T> curNode, List<T> list) {
        list.add(curNode.getValue());

        if(curNode.getLeftChild() != null) {
            preorderRecursively(curNode.getLeftChild(), list);
        }
        if(curNode.getRightChild() != null) {
            preorderRecursively(curNode.getRightChild(), list);
        }

        return list;
    }
    // *******************************************************

    public List<T> postorder() {
        if(root == null) {
            return new LinkedList<T>();
        }

        return postorderRecursively(root, new LinkedList<T>());
    }

    private List<T> postorderRecursively(Node<T> curNode, List<T> list) {
        if(curNode.getLeftChild() != null) {
            postorderRecursively(curNode.getLeftChild(), list);
        }
        if(curNode.getRightChild() != null) {
            postorderRecursively(curNode.getRightChild(), list);
        }
        list.add(curNode.getValue());
        return list;
    }
    // *******************************************************

    public List<T> inorder() {
        if(root == null) {
            return new LinkedList<T>();
        }

        return inorderRecursively(root, new LinkedList<T>());
    }

    private List<T> inorderRecursively(Node<T> curNode, List<T> list) {
        if(curNode.getLeftChild() != null) {
            inorderRecursively(curNode.getLeftChild(), list);
        }

        list.add(curNode.getValue());

        if(curNode.getRightChild() != null) {
            inorderRecursively(curNode.getRightChild(), list);
        }
        return list;
    }
    // *******************************************************

    public int size() {
        return sizeRecursively(root);
    }

    private int sizeRecursively(Node<T> node) {
        if (node == null || isLast(node)) {
            return 0;
        }
        
        return 1 + Math.max(sizeRecursively(node.getLeftChild()), sizeRecursively(node.getRightChild()));
    }

    // *******************************************************
    private boolean isLast(Node<T> node) {
        return node.getLeftChild() == null && node.getRightChild() == null;
    }

}
