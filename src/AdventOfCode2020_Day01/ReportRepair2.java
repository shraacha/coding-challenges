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
    Program title:         Report Repair 2
    Programmed by:         Anish Racharla
    Date:                  Tuesday, 2020/12/1
    Description:           
 */
public class ReportRepair2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nNum1 = 0, nNum2 = 0, nNum3 = 0, nDifference, nProduct;

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
            nDifference = 2020 - nNum1;

            for (int j = 0; j < nListLength; j++) {
                if (j != i) {
                    nNum2 = arnEntriesSorted[j];
                    nNum3 = nDifference - nNum2;

                    if (binarySearch(arnEntriesSorted, 0, nListLength - 1, nNum3)) {
                        j = nListLength;
                        i = nListLength;
                    }
                }
            }
        }

        //Getting the product of the two numbers.
        nProduct = nNum1 * nNum2 * nNum3;

        //Output
        System.out.println("The three entries that sum to 2020 are: " + nNum1 + " + " + nNum2 + " + " + nNum3 + " = " + (nNum1 + nNum2 + nNum3));
        System.out.println("Their product is: " + nProduct);

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
