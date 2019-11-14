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
            high=Integer.MIN_VALUE;
            for(int i=0;i<j;++i)
                current = current.next;
            while (current != null) {
                if (current.val > high)
                    high = current.val;
                else
                    current = current.next;
            }
            current = head;
            for(int i=0;i<j;++i)current=current.next;
            swap(current.val,high);
            current = head;
        }

        return head;
    }


    private static void swap(int x, int y){
        {

            if (x == y) return;

            Node prevX = null, currX = head;
            while (currX != null && currX.val != x)
            {
                prevX = currX;
                currX = currX.next;
            }

            Node prevY = null, currY = head;
            while (currY != null && currY.val != y)
            {
                prevY = currY;
                currY = currY.next;
            }


            if (currX == null || currY == null)
                return;

            if (prevX != null) prevX.next = currY;
            else head = currY;

            if (prevY != null) prevY.next = currX;
            else head = currX;

            Node temp = currX.next;
            currX.next = currY.next;
            currY.next = temp;
        }
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
