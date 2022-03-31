/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day08;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Handheld Halting 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/6/9
 */
public class HandheldHalting2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strWord, strOpr, strArg;
        String[] arSplit;

        int[] arnIndexAcc = {-1, 0};

        int nTempVisitedIndex;
        nTempVisitedIndex = -1;

        //Getting the input
        ArrayList<String> arListEntries = new ArrayList<String>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day08_Input.txt"));
            while ((strWord = br.readLine()) != null) {
                arListEntries.add(strWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //swapping "nop" and "jmp" one at a time and checking whether the program reaches the end of the input lines
        while (arnIndexAcc[0] != arListEntries.size() - 1) {
            //"Resetting" the modified operation
            if (nTempVisitedIndex != -1) {
                arSplit = arListEntries.get(nTempVisitedIndex).split(" ");
                strOpr = arSplit[0];
                strArg = arSplit[1];

                if (strOpr.equals("nop")) {
                    arListEntries.set(nTempVisitedIndex, "jmp " + strArg);
                } else {
                    arListEntries.set(nTempVisitedIndex, "nop " + strArg);
                }
            }

            //Going through the original operations and swapping the first found instance of "nop" or "jmp" with the other
            for (int i = nTempVisitedIndex + 1; i < arListEntries.size(); i++) {
                arSplit = arListEntries.get(i).split(" ");
                strOpr = arSplit[0];
                strArg = arSplit[1];

                if (strOpr.equals("nop")) {
//                    System.out.println(arListEntries.get(i));                       //TEST

                    arListEntries.set(i, "jmp " + strArg);

                    nTempVisitedIndex = i;
                    i = arListEntries.size();
                } else if (strOpr.equals("jmp")) {

                    arListEntries.set(i, "nop " + strArg);

                    nTempVisitedIndex = i;
                    i = arListEntries.size();
                }
            }

//            System.out.println(nTempVisitedIndex);                              //TEST
//            System.out.println("Last Index test: " + arnIndexAcc[0]);           //TEST

            arnIndexAcc = executeBoot(arListEntries);
        }
        
        //Output with extra info
//        System.out.println("The accumulator value is: " + arnIndexAcc[1]);
        
        //Puzzle output
        System.out.println(arnIndexAcc[1]);
    }

    //Simple insertion sort method
    public static void insertionSort(ArrayList<Integer> arListInt) {
        int nTemp1, nTemp2, i, j;

        for (i = 1; i < arListInt.size(); i++) {
            nTemp1 = arListInt.get(i);
            j = i - 1;
            while ((j >= 0) && nTemp1 < arListInt.get(j)) {
                nTemp2 = arListInt.get(j);
                arListInt.set(j, arListInt.get(j + 1));
                arListInt.set((j + 1), nTemp2);
                j--;
            }
        }
    }

    //Simple iterative binary search method
    public static boolean binarySearch(ArrayList<Integer> arListInt, int nLeft, int nRight, int nSearch) {
        int nMiddle;
        boolean found = false;

        while (!found && nLeft <= nRight) {
            nMiddle = nLeft + (nRight - nLeft) / 2;

            if (nSearch == arListInt.get(nMiddle)) {
                found = true;
            } else {
                if (nSearch < arListInt.get(nMiddle)) {
                    nRight = nMiddle - 1;
                } else {
                    nLeft = nMiddle + 1;
                }
            }
        }

        return found;
    }

    //Goes through the boot operations like in part 1, and returns the last index in the input list the program reaches as well as the accumulated value
    public static int[] executeBoot(ArrayList<String> arListEntries) {
        int nAcc, nLastIndex;

        nAcc = 0;
        nLastIndex = 0;

        String[] arSplit;
        String strOpr, strArg;

        ArrayList<Integer> arListVisitedEntries = new ArrayList<Integer>();

        for (int i = 0; i < arListEntries.size();) {
            nLastIndex = i;

            //Splitting the line into the operation and the argument
            arSplit = arListEntries.get(i).split(" ");

            strOpr = arSplit[0];
            strArg = arSplit[1];

            //If the list of instructions gone through currently is > 1 then the list is sorted, and the current index is searched for (to check for a loop)
            if (arListVisitedEntries.size() > 1) {
                insertionSort(arListVisitedEntries);

                if (binarySearch(arListVisitedEntries, 0, arListVisitedEntries.size() - 1, i)) {
                    i = arListEntries.size();
                }
            }

            //Carrying out each command if a loop is not detected
            if (i < arListEntries.size()) {
                if (strOpr.equals("acc")) {
                    arListVisitedEntries.add(i);
                    nAcc += Integer.parseInt(strArg);
                    i++;
                } else if (strOpr.equals("jmp")) {
                    arListVisitedEntries.add(i);
                    i += Integer.parseInt(strArg);
                } else if (strOpr.equals("nop")) {
                    arListVisitedEntries.add(i);
                    i++;
                } else {
                    i = arListEntries.size();
                    System.out.println("Error");
                }
            }
        }

        int[] arInt = {nLastIndex, nAcc};

        return arInt;
    }
}
