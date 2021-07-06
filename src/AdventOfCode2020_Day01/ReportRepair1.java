/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day01;

import java.io.*;
import java.util.*;

/*
                Advent of Code
    Program title:         Report Repair 1
    Programmed by:         Anish Racharla
    Date:                  Tuesday, 2020/12/1
    Description:           
 */
public class ReportRepair1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nNum1 = 0, nNum2 = 0, nProduct;

        //Getting the entries from a .txt file
        ArrayList<Integer> arListnEntries = new ArrayList<Integer>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day01_Input.txt"));
            String word;
            while ((word = br.readLine()) != null) {
                arListnEntries.add(Integer.parseInt(word));
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

        nListLength = arListnEntries.size();

        //Transferring the arraylist items to an array.
        int[] arnEntriesUnsorted = new int[nListLength];
        arnEntriesUnsorted = arListnEntries.stream().mapToInt(Integer::intValue).toArray();

//        System.out.println(Arrays.toString(arnEntriesUnsorted));                                //TEST
        //Sorting the array.
        int[] arnEntriesSorted = new int[nListLength];
        arnEntriesSorted = selectionSort(arnEntriesUnsorted);
//        System.out.println(Arrays.toString(arnEntriesSorted));                                //TEST

        //Going through the sorted array, finding the difference between 2020 and the int at index "i", then searching the array for that number.
        //(assuming the pair exists in the list - no code for if the pair does not exist)
        for (int i = 0; i < nListLength; i++) {
            nNum1 = arnEntriesSorted[i];
            nNum2 = 2020 - nNum1;

            if (binarySearch(arnEntriesSorted, 0, nListLength - 1, nNum2)) {
                i = nListLength;
            }
        }
        
        //Getting the product of the two numbers.
        nProduct = nNum1 * nNum2;
        
        //Output with extra info
//        System.out.println("The two entries that sum to 2020 are: " + nNum1 + " + " + nNum2 + " = " + (nNum1 + nNum2));
//        System.out.println("Their product is: " + nProduct);
        
        //Puzzle output
        System.out.println(nProduct);

    }

    public static int[] selectionSort(int[] arnList) {
        int nMinValue, nMinIndex, nTemp = 0;

        for (int i = 0; i < arnList.length; i++) {
            nMinValue = arnList[i];
            nMinIndex = i;
            for (int j = i; j < arnList.length; j++) {
                if (arnList[j] < nMinValue) {
                    nMinValue = arnList[j];
                    nMinIndex = j;
                }
            }
            if (nMinValue < arnList[i]) {
                nTemp = arnList[i];
                arnList[i] = arnList[nMinIndex];
                arnList[nMinIndex] = nTemp;
            }
        }

        return arnList;
    }

    public static Boolean binarySearch(int[] A, int nLeft, int nRight, int nFindRef) {
        int nMiddle, nEntriesRef;
        boolean bFound = false;

        while (!bFound && nLeft <= nRight) {
            nMiddle = nLeft + (nRight - nLeft) / 2;

            nEntriesRef = A[nMiddle];

            if (nFindRef == nEntriesRef) {
                bFound = true;
            } else {
                if (nFindRef < nEntriesRef) {
                    nRight = nMiddle - 1;
                } else {
                    nLeft = nMiddle + 1;
                }
            }
        }

        if (bFound) {
            return true;
        } else {
            return false;
        }
    }
}
