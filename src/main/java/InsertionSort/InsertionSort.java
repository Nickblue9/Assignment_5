package InsertionSort;

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

public class InsertionSort {

    private static Node head;

    public static void main(String [] args) {
        File f = new File(args[0]);
        try {
            head = getNumbers(f);
            head = sort(head,0);
            print(head);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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

    private static Node sort(Node head,int sorted){
        Node current = head;
        Node key = head;
        for(int i =0; i<=sorted; i++)
            key=key.next;


        if(key!=null){
            remove(head,key);
            key.next=null;
            for(int i =0; i<=sorted; i++) {
                if (current == head && key.val > current.val) {
                    //if largest put at start
                    key.next = current;
                    head = sort(key, ++sorted);
                    return head;
                }else if(current.val>=key.val && current.next.val<=key.val) {
                    //if the number falls between 2 points insert it between them.
                    insert(current,key);
                    head = sort(head,++sorted);
                    return head;
                } else if(i==sorted){
                    //if at the end of what has been sorted, put it back
                    insert(current,key);
                    head = sort(head, ++sorted);
                    return head;
                }else
                    current=current.next;
            }

        }
        return head;
    }

    private static void remove(Node head, Node rm){
        Node current = head;
        while(true){
            if(current.next == rm){
                current.next = current.next.next;
                break;
            }
            else
                current = current.next;
        }
    }

    private static void insert(Node lead, Node inserted){
        Node tmp = lead.next;
        lead.next = inserted;
        inserted.next = tmp;
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
