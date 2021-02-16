import java.io.*;
import java.util.*;

public class main {

    private static Vector<Integer> picture = new Vector<>();
    private static Vector<Character> OneF = new Vector<>();
    private static Vector<Character> TwoF = new Vector<>();    /* declare static variables */
    private static int maxCols = 0;
    private static uandf uf;
    private static String frequency = "";

    public static void main(String[] args) {
        String file;
        if (args.length == 1) {
            file = args[0];      /* takes arg1 as input file or warns user */
        } else {
            System.out.println("Incorrect number of arguments please input one!");
            return;
        }
        try {
            BufferedReader rdr = new BufferedReader(new FileReader(file));
            String line;
            int counter = 0;      /*reads through file w/ BR */
            boolean first = true;
            while ((line = rdr.readLine()) != null) {
                char[] charl = line.toCharArray();
                if (first){
                    for (int i = 0; i < charl.length; i++){
                        picture.add(0);       /*fills picture vector */
                    }
                    first = false;
                }
                for (char c : charl) {
                    if (c == '+') {
                        picture.add(1);    /* scans through file and fill picture vectpr */
                    }
                    if (c == ' '){
                        picture.add(0);
                    }
                    counter++;
                }
                maxCols = counter;
                counter = 0;
            }
        } catch (IOException e) {
            System.out.println("IOERROR!");     /*catch errors */
        }
        System.out.println("1. The input binary image : \n");
        print();                                /* prints input impage */
        uf = new uandf(picture.size());
        for (int i = 1; i <maxCols; i++){       /*traverses picture and adds connected components */
            if (picture.get(i) ==1) {
                if (picture.get(i).equals(picture.get(i - 1))){
                    uf.union_sets(i-1, i);
                }
            }else{
                uf.union_sets(0,i);
            }
        }
        for (int i = maxCols; i< picture.size(); i++) { /*traverses picture and adds connected components */
            if (picture.get(i) == 1){
                if (i%maxCols != 0){
                    if (picture.get(i - maxCols).equals(picture.get(i)) && picture.get(i - 1).equals(picture.get(i))) {
                        uf.union_sets(i - 1, i);
                        uf.union_sets(i - maxCols, i);

                    } else if (picture.get(i).equals(picture.get(i - 1))) {
                        uf.union_sets(i - 1, i);
                    } else if (picture.get(i - maxCols).equals(picture.get(i))) {
                        uf.union_sets(i - maxCols, i);
                    }
                } else {
                    if (picture.get(i - maxCols) == 1) {
                        if (picture.get(i - maxCols).equals(picture.get(i))) {
                            uf.union_sets(i - maxCols, i);
                        }
                    }
                }
            } else {
                uf.union_sets(0, i);
            }
        }
        System.out.println("\n");
        printConnected();        /*prints the connected component by calling helper */
    }

    private static void print(){
        int x = 0;
        StringBuilder out = new StringBuilder();
        for (Integer integer : picture) {    /* prints the original input by looping through vector */
            if (x == maxCols) {
                out.append('\n');
                x = 0;
            }
            if (integer == 1) {
                out.append('+');
            } else {
                out.append(' ');   /* appends original chars to output then prints */
            }
            x++;
        }
        System.out.println(out);
    }

    private static void printConnected(){
        int x =0;
        Vector<Integer> n = new Vector<>();       /*initialize local vars */
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < picture.size(); i++) {
            if (!n.contains(uf.find_set(i).getNum())) {
                n.add(uf.find_set(i).getNum());     /*adds connected components to vector */
            }
            if (x == maxCols) {
                out.append('\n');
                x = 0;
            }
            char d = (char) ((char) 64 + n.indexOf(uf.find_set(i).getNum()));
            if (d == '@') {      /*for each component adds a unique char to output */
                d = ' ';
            }
            out.append(d);
            x++;
        }
        System.out.println("2. The connected component image with unique characters: \n");
        System.out.println(out);
        frequency = out.toString();   /*prints connected component then prints freq */
        System.out.println("\n");
        System.out.println("3. List of components labelled by name and frequency: \n");
        printFrequency();
        System.out.println("\n");
        out = new StringBuilder();
        for (int i=0; i<picture.size(); i++){
            if (!n.contains(uf.find_set(i).getNum())){
                n.add(uf.find_set(i).getNum());  /* prints connected but removes components with frequency of one or two */
            }
            if (x == maxCols){
                out.append("\n");
                x = 0;
            }
            char digit = (char) ((char) 64 + n.indexOf(uf.find_set(i).getNum()));
            if(OneF.contains(digit) || TwoF.contains(digit)){
                digit = ' ';
            }else if (digit == '@'){
                digit = ' ';
            }
            out.append(digit);
            x++;
        }
        System.out.println("4. The connected component image with unique characters, with components of size 1 or 2 removed: \n");
        System.out.println(out);
    }

    private static void printFrequency(){   /* method prints frequency of connected components of imaage */
        char[] chars = frequency.toCharArray();
        Arrays.sort(chars);
        int l = chars.length;
        fNode[] freqarr = new fNode[l];
        char symbol = chars[0];         /* initialize local vars */
        int freq = 1;
        int counter = 0;
        for (char aChar : chars) {
            if (!(aChar == symbol)) {          /* fill freqarr with connected components */
                freqarr[counter++] = new fNode(symbol, freq);
                symbol = aChar;
                freq = 0;
            }
            freq++;
        }
        freqarr[counter++] = new fNode(symbol, freq);
        Arrays.sort(freqarr, 1, counter);
        for (int i = 2; i < counter; i++){      /*loops through and prints frequency of each connected component */
            if(freqarr[i].frequency ==1) {
                OneF.add(freqarr[i].symbol);
            }if(freqarr[i].frequency ==2){
                TwoF.add(freqarr[i].symbol);
            }
                System.out.println(freqarr[i].symbol + " has a frequency of: " + freqarr[i].frequency);
            }
    }
}
