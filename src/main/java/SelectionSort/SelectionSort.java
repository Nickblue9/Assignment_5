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
            head = sort();
            print();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Node sort(){
        int n = getLength();
        int high;
        Node current = head;

        for(int j = 0; j<n; j++) {
            high= Integer.MIN_VALUE;
            for(int i=0;i<j;++i)
                current = current.next;
            while (current != null) {
                if (current.val >= high) {
                    high = current.val;
                    current = current.next;
                }
                else
                    current = current.next;
            }
            current = head;
            for(int i=0;i<j;++i)current=current.next;
            swap(current,high);
            //print();
            //System.out.println("\n---------------------------------------------------");
            current = head;
        }

        return head;
    }


    private static void swap(Node x, int high)
    {

        // Nothing to do if x and y are same
        if (x.val == high) return;

        Node prevX = head;

        if(x==head)
            prevX = null;
        else
            while(prevX.next != x)
                prevX=prevX.next;



        // Search for y (keep track of prevY and currY)
        Node prevY = null, y = x;
        while (y.val != high)
        {
            prevY = y;
            y = y.next;
        }

        // If x is not head of linked list
        if (x == head)
            head = y;
        else //make y the new head
            prevX.next = y;

        // If y is not head of linked list
        if (prevY != null)
            prevY.next = x;
        else // make x the new head
            head = x;

        // Swap next pointers
        Node temp = x.next;
        x.next = y.next;
        y.next = temp;
    }

    private static int getLength(){
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

    private static void print(){
        Node current = head;
        while(current.next!=null){
            System.out.print(current.val+" ");
            current = current.next;
        }
        System.out.print(current.val);
    }
}
