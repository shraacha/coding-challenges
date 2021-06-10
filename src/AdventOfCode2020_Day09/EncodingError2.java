/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day09;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Encoding Error 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/6/9
 */
public class EncodingError2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nIndex, nPreamble;
        nIndex = 0;
        nPreamble = 25;

        long lNum, lSum;

        String strWord;

        boolean bValid;

        ArrayList<Long> arListSumSet = new ArrayList<Long>();

        //Getting the input
        ArrayList<Long> arListInput = new ArrayList<Long>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day09_Input.txt"));
            while ((strWord = br.readLine()) != null) {
                arListInput.add(Long.parseLong(strWord));
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

        //checking each entry (after the preamble) to see if it is valid or not.
        //If the entry is invalid, then the program will exit the loop and output the entry.
        for (int i = nPreamble; i < arListInput.size(); i++) {
            bValid = isValid(arListInput, nPreamble, i);

            if (!bValid) {
                nIndex = i;

                i = arListInput.size();
            }
        }

        lNum = arListInput.get(nIndex);
        
        findSumSet(arListInput, arListSumSet, lNum, nIndex);
        
        lSum = findExtremity(arListSumSet, true) + findExtremity(arListSumSet, false);
        
        System.out.println("The first number that does not have this property is: " + lNum + " at index: " + nIndex);
        System.out.println("The ecryption weakness is: " + lSum);
    }

    //This method simply goes through the nPreamble (in this case 25) entries before the passed entry and checks whether the passed entry 
    //can be formed by a sum of two numbers in the preceding nPreamble entries. 
    //True/false will be returned accoringly.
    public static boolean isValid(ArrayList<Long> arListLong, int nPreamble, int nIndex) {
        boolean bValid;
        bValid = false;

        long lNum, lSum;
        lNum = arListLong.get(nIndex);

        long[] aLPreamble = new long[nPreamble];
        
        //Creating the array of the nPreamble entries before the passed entry.
        for (int i = nIndex - nPreamble, j = 0; i < nIndex; i++, j++) {
            aLPreamble[j] = arListLong.get(i);
        }

        //Checking whether the sum of 2 numbers within the array = the passed entry.
        //Exits right after finding the sum, or after exhausting the list.
        for (int i = 0; i < nPreamble; i++) {
            for (int j = 0; j < nPreamble; j++) {
                if (j == i && j != nPreamble - 1) {
                    j++;
                }

                lSum = aLPreamble[i] + aLPreamble[j];

                if (lSum == lNum) {
                    bValid = true;

                    i = nPreamble;
                    j = nPreamble;
                }
            }
        }

        return bValid;
    }
    
    //This method "crawls" along the inputs, modifying a set. It compares the sum of the set against the sum we are trying to find.
    //If the set's sum is < the target, it will add the next number on the input list.
    //If the set's sum is > the targer, it will remove the first number from the set. 
    //Eventually, it will find the consecutive set of numbers that adds up to the target, and at that time, the set's arraylist should house
    //all of the correct numbers.
    public static void findSumSet(ArrayList<Long> arListInput, ArrayList<Long> arListSet, long lSum, int nIndex) {
        boolean bFound;
        bFound = false;

        long lTempSum, lTemp;

        int nMin, nMax;
        nMin = 0;
        nMax = 1;

        arListSet.clear();
        arListSet.add(arListInput.get(nMin));
        arListSet.add(arListInput.get(nMax));

        lTempSum = arListSet.get(nMin) + arListSet.get(nMax);
        
        //This loop "crawls" the arraylist down the input list and checks if the sum of the consecutive numbers in the set = the target sum.
        while (!bFound && nMax < arListInput.size()) {
            if (lTempSum < lSum) {
                nMax++;
                lTemp = arListInput.get(nMax);

                arListSet.add(lTemp);
                lTempSum += lTemp;
            } else if (lTempSum > lSum) {
                nMin++;
                lTemp = arListSet.get(0);

                arListSet.remove(0);
                lTempSum -= lTemp;
            } else {
                bFound = true;
            }
        }
        
        System.out.println(lSum);
    }
    
    //A simple method that sequentially searches for the smallest/largest value in the arraylist of longs.
    public static long findExtremity(ArrayList<Long> arListSet, boolean bSmallOrLarge) {
        long lNum, lTemp;
        lNum = arListSet.get(0);
        
        if (bSmallOrLarge) {
            for (int i = 0; i < arListSet.size(); i++) {
                lTemp = arListSet.get(i);
                
                if (lTemp < lNum) {
                    lNum = lTemp;
                }
            }
        } else {
            for (int i = 0; i < arListSet.size(); i++) {
                lTemp = arListSet.get(i);
                
                if (lTemp > lNum) {
                    lNum = lTemp;
                }
            }
        }
        
        return lNum;
    }
}
