package InsertionSort;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class InsertionSortBeta {
    public static void main(String [] args){
        InsertionSortBeta ui = new InsertionSortBeta();
        File f = new File(args[0]);
        try{
            LinkedList <Integer> list = getNumbers(f);
            ui.sort(list);
            print(list);
        }catch(IOException e){e.printStackTrace();System.exit(1);}
    }

    private static LinkedList<Integer> getNumbers(File f)throws IOException{
        LinkedList<Integer> l = new LinkedList<Integer>();
        Scanner fwriter = new Scanner(f);
        while(fwriter.hasNext()){
            String line = fwriter.nextLine();
            StringBuilder k = new StringBuilder();
            for(int c = 0; c<line.length(); c++){
                if(line.charAt(c)==' ') {
                    l.add(Integer.parseInt(k.toString()));
                    k.delete(0,k.length());
                }
                else
                    k.append(line.charAt(c));
            }
            l.add(Integer.parseInt(k.toString()));
        }
        fwriter.close();
        return l;
    }

    private void sort(LinkedList<Integer> list){
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            int key = list.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1,list.get(j));
                j = j - 1;
            }
            list.set(j + 1,key);
        }
    }

    private static void print(LinkedList<Integer> list){
        StringBuilder k = new StringBuilder();
        for(int i: list){
            k.append(i);
            k.append(" ");
        }
        k.delete(k.lastIndexOf(" "),k.lastIndexOf(" ")+1);
        System.out.print(k.toString());
    }
}
