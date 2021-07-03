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
public class BinaryBoarding1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strRowInstr, strColInstr;
        int nListLength, nRow, nCol, nHighID, nIDTemp;
        nHighID = 0;
        

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

        for (int i = 0; i < nListLength;i ++) {
            strRowInstr = arListEntries.get(i).substring(0, 7);
            strColInstr = arListEntries.get(i).substring(7, arListEntries.get(i).length());
            
            nRow = findRowCol(strRowInstr, 'F', 'B', 0, 127, 0, 7);
            nCol = findRowCol(strColInstr, 'L', 'R', 0, 7, 0, 3);
            
            nIDTemp = (nRow * 8) + nCol;
            
            if (nIDTemp > nHighID) {
                nHighID = nIDTemp;
            }
        }
        
        //Output with extra info
//        System.out.println("The highest seat ID is: " + nHighID);
        
        //Puzzle output
        System.out.println(nHighID);
    }
    
    public static int findRowCol(String strInstr, char cLower, char cUpper, int nLeft, int nRight, int nCount, int nMax) {
        int nMiddle;
        char cTemp;
        
        cTemp = strInstr.charAt(nCount);
        
        nMiddle = (nLeft + nRight)/2;
        
         if (nCount == nMax - 1) {
             if (cTemp == cLower) {
                 return nLeft;
             } else {
                 return nRight;
             }
         } else if (cTemp == cLower) {
             nCount ++;
             return findRowCol(strInstr, cLower, cUpper, nLeft, nMiddle, nCount, nMax);
         } else {
             nCount ++;
             return findRowCol(strInstr, cLower, cUpper, nMiddle + 1, nRight, nCount, nMax);
         }
    }
}
