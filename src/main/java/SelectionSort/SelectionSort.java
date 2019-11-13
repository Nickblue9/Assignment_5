package SelectionSort;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Node {
    int val;
    Node next;

    Node (int x, Node n) {
        val = x;
        next = n;
    }

    Node (int x) {
        val = x;
        next = null;
    }
}

public class SelectionSort {
    private static Node head;
    public static void main(String [] args) {
        File f = new File(args[0]);
        try {
            head = getNumbers(f);
            head = sort(head);
            print(head);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Node sort(Node head){
        int n = getLength(head);
        int high = Integer.MIN_VALUE;
        Node current = head;

        for(int j = 0; j<n; j++) {
            while (current != null) {
                if (current.val > high)
                    high = current.val;
                else
                    current = current.next;
            }
            current = head;
            swap(head,j,high);
        }

        return head;
    }

    private static void swap(Node head, int j, int high){
        Node n1 = head;
        for(int i = 0; i<j; i++)
            n1 = n1.next;

        Node n2 = head;
        while(n2.val != high)
            n2 = n2.next;

        swap(n1,n2);
    }

    private static void swap(Node i, Node j){
        Node tmp = i;
        i = j;
        j = tmp;
    }

    private static int getLength(Node head){
        int count = 0;
        Node current = head;
        while(current!=null){
            ++count;current=current.next;
        }
        return count;
    }

    private static Node getNumbers(File f)throws IOException{
        Scanner fScanner = new Scanner(f);
        Node head = new Node(fScanner.nextInt());
        Node current = head;
        while(fScanner.hasNextInt()){
            current.next = new Node(fScanner.nextInt());
            current = current.next;
        }
        return head;
    }

    private static void print(Node head){
        Node current = head;
        while(current.next!=null){
            System.out.print(current.val+" ");
            current = current.next;
        }
        System.out.print(current.val);
    }
}
