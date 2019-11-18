package BubbleSort;

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

public class BubbleSort {

    private static Node head;

    public static void main(String [] args){

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
        Node current;
        for(int i = 0; i<n-1; ++i){
            current = head;
            for(int j=0; j<n-i-1; ++j)
                if(current.val<current.next.val) {
                    swap(current, current.next);
                    //print();
                    //System.out.println("\n------------------------------------");
                }
                else
                    current = current.next;
        }
        return head;
    }

    private static void swap(Node x, Node y){

        Node prevX = head;
        Node prevY = x;

        if(x==head){
            prevX =null;
        }
        else {
            while (prevX.next != x)
                prevX = prevX.next;
        }

        if (prevX != null)
            prevX.next = y;
        else //make y the new head
            head = y;

        prevY.next = x;





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
