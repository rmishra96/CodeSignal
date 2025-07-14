package arraysInterview.linkedlist;

public class Node {
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class myLinkedList{
    private Node head;

    public void insert(int data){
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while(current.next != null)
            current = current.next;
        current.next = newNode;
    }
}