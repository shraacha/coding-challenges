/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day05;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Binary Boarding 1
    Programmed by:         Anish Racharla
    Date:                  Saturday, 2020/12/5
 */
public class BinaryBoarding2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strRowInstr, strColInstr;
        int nListLength, nRow, nCol, nIDTemp, nMissing = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day05_Input.txt"));
            String word;
            while ((word = br.readLine()) != null) {
                arListEntries.add(word);
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

        nListLength = arListEntries.size();
        int[] arnIDs = new int[nListLength];
        
        //Getting the row & column number for each input line and calculating the seat ID
        for (int i = 0; i < nListLength; i++) {
            strRowInstr = arListEntries.get(i).substring(0, 7);
            strColInstr = arListEntries.get(i).substring(7, arListEntries.get(i).length());

            nRow = findRowCol(strRowInstr, 'F', 'B', 0, 127, 0, 7);
            nCol = findRowCol(strColInstr, 'L', 'R', 0, 7, 0, 3);

            nIDTemp = (nRow * 8) + nCol;
            arnIDs[i] = nIDTemp;
        }
        
        //Sorting the seat IDs
        bubbleSort(arnIDs);

        //Going through the seat IDs and seeing if the current ID is 1 more than the last. If not, then the target ID is the missing seat in between.
        for (int i = 1; i < nListLength; i++) {
            if (arnIDs[i] != (arnIDs[i - 1] + 1)) {
                nMissing = arnIDs[i - 1] + 1;
                i = nListLength;
            }
        }
        
        //Original, slightly overcomplicated code:
        //Going through the seat IDs and searching for a gap of 2 between IDs (meaning the target seat is the one in between).
//        for (int i = 0; i < nListLength; i++) {
//            if (i != 0) {
//                nIDTemp = arnIDs[i - 1];
//                if ((arnIDs[i] - nIDTemp) == 2) {
//                    nMissing = nIDTemp + 1;
//                    i = nListLength;
//                }
//            }
//        }
        
        //Challenge answer
        System.out.println("Your seat ID is: " + nMissing);

    }
    
    //Basically a recursive binary search method without any comparing since the input gives the instrctions). Finds & returns the row or column number.
    public static int findRowCol(String strInstr, char cLower, char cUpper, int nLeft, int nRight, int nCount, int nMax) {
        int nMiddle;
        char cTemp;

        cTemp = strInstr.charAt(nCount);

        nMiddle = (nLeft + nRight) / 2;

        if (nCount == nMax - 1) {
            if (cTemp == cLower) {
                return nLeft;
            } else {
                return nRight;
            }
        } else if (cTemp == cLower) {
            nCount++;
            return findRowCol(strInstr, cLower, cUpper, nLeft, nMiddle, nCount, nMax);
        } else {
            nCount++;
            return findRowCol(strInstr, cLower, cUpper, nMiddle + 1, nRight, nCount, nMax);
        }
    }

    //Bubble sort
    public static int[] bubbleSort(int[] arnList) {
        int nTemp;

        int i, j;
        for (i = 0; i < arnList.length - 1; i++) {
            for (j = 0; j < arnList.length - i - 1; j++) {
                if (arnList[j] > arnList[j + 1]) {
                    nTemp = arnList[j];
                    arnList[j] = arnList[j + 1];
                    arnList[j + 1] = nTemp;
                }
            }
        }

        return arnList;
    }
}
