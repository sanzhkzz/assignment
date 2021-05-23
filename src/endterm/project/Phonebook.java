package endterm.project;

import java.util.Iterator;
import java.util.Stack;

public class Phonebook<K extends Comparable<K>, V> implements Iterable<K> {
    private Node<K, V> root;


    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            return;
        }
        put(new Node<>(key, value), root);
    }


    private void put(Node<K, V> value, Node<K, V> node) {
        if (node.key.compareTo(value.key) < 0) {
            if (node.rightChild == null)
                node.rightChild = value;
            else
                put(value, node.rightChild);
        }
        if (node.key.compareTo(value.key) > 0) {
            if (node.leftChild == null)
                node.leftChild = value;
            else
                put(value, node.leftChild);
        }
        if (node.key.compareTo(value.key) == 0)
            throw new IllegalArgumentException("Binary search tree does not allow to input duplicates");
    }


    public V get(K key) {
        return get(key, root);
    }


    private V get(K key, Node<K, V> node) {
        if (node.key.compareTo(key) < 0) {
            if (node.rightChild == null) return null;
            return get(key, node.rightChild);
        }
        if (node.key.compareTo(key) > 0) {
            if (node.leftChild == null) return null;
            return get(key, node.leftChild);
        }
        return node.value;
    }


    private void deleteRoot() {
        if (root == null) return;
        deleteNode(root);
    }


    private void deleteNode(Node<K, V> node) {

        if (node.rightChild != null) {
            Node<K, V> left = node.leftChild;
            node.value = node.rightChild.value;
            node.key = node.rightChild.key;
            node.leftChild = node.rightChild.leftChild;
            node.rightChild = node.rightChild.rightChild;
            if (left == null) return;
            put(left, node);
        } else {
            node.value = node.leftChild.value;
            node.key = node.leftChild.key;
            node.rightChild = node.leftChild.rightChild;

            node.leftChild = node.leftChild.leftChild;
        }
    }


    public void delete(K key) {
        if (root.key == key) {
            deleteRoot();
            return;
        }
        Node<K, V> currentNode = root;
        Node<K, V> parent = root;
        while (true) {
            if (currentNode == null) return;
            if (currentNode.key.compareTo(key) < 0) {
                parent = currentNode;
                currentNode = currentNode.rightChild;
            } else if (currentNode.key.compareTo(key) > 0) {
                parent = currentNode;
                currentNode = currentNode.leftChild;
            } else break;
        }
        if (currentNode.rightChild == null && currentNode.leftChild == null) {
            if (currentNode == parent.rightChild)
                parent.rightChild = null;
            else parent.leftChild = null;
            return;
        }
        deleteNode(currentNode);
    }


    @Override
    public Iterator<K> iterator() {
        return new MyIterator<>((Node<K, Object>) root);
    }

    private static class MyIterator<K extends Comparable<K>> implements Iterator<K> {
        private final Stack<K> stack = new Stack<>();
        private K first;
        private boolean isFirst = true;

        public MyIterator(Node<K, Object> root) {
            inorderTraversal(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (isFirst) {
                isFirst = false;
                return first;
            }
            return stack.pop();
        }

        private K inorderTraversal(Node<K, Object> node) {
            if (node.rightChild != null)
                stack.push(inorderTraversal(node.rightChild));
            stack.push(node.key);
            first = node.key;
            if (node.leftChild != null)
                stack.push(inorderTraversal(node.leftChild));
            return stack.pop();
        }
    }

    static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> rightChild;
        private Node<K, V> leftChild;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Phonebook<String, String> phonebook = new Phonebook<>();
        phonebook.put("Uncle", "81231231122");
        phonebook.put("Aunt", "82231231122");
        phonebook.put("Mom", "83231231122");
        phonebook.put("Dad", "81131231122");
        for (String name : phonebook)
            System.out.println(name);
        phonebook.delete("Aunt");
        System.out.println(phonebook.get("Mom"));
    }
}