/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day03;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Toboggan Trajectory 1
    Programmed by:         Anish Racharla
    Date:                  Thursday, 2020/12/3
 */
public class TobogganTrajectory2 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        int nListLength;
        
        long lProduct = 1;
        
        int [] arnTreeCount = new int[5];

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day03_Input.txt"));
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

        //Transferring the arraylist items to an array.
        String[] arstrLines = arListEntries.toArray(new String[nListLength]);

        arnTreeCount[0] = treeCounter(arstrLines, 1, 1);
        arnTreeCount[1] = treeCounter(arstrLines, 3, 1);
        arnTreeCount[2] = treeCounter(arstrLines, 5, 1);
        arnTreeCount[3] = treeCounter(arstrLines, 7, 1);
        arnTreeCount[4] = treeCounter(arstrLines, 1, 2);
        
        
        for (int i = 0 ; i < arnTreeCount.length; i++){
            lProduct *= arnTreeCount[i];
            
            System.out.println("The number of trees encountered in path" + (i+1) + " is : " + arnTreeCount[i]);
        }
        
        System.out.println("The product of these numbers is : " + lProduct);
    }

    public static int treeCounter(String[] arstrLines, int nRight, int nDown) {
        int nTreeCount, i, j, nIndexLineLength;
        nTreeCount = 0;
        nIndexLineLength = arstrLines[0].length() - 1;
        j = 0;

        for (i = 0; i < arstrLines.length - 1;) {
            if ((j + nRight) > nIndexLineLength) {
                j = (j + nRight) - nIndexLineLength - 1;
                i += nDown;
            } else {
                j += nRight;
                i += nDown;
            }

//            System.out.println("i= " + i);                                  //TEST

            switch (arstrLines[i].charAt(j)) {
                case '#':
                    nTreeCount++;
                    break;
                case '.':
                    //No tree
                    break;
                default:
                    System.out.println("An error has occured in the tree count");
                    break;
            }
        }

        return nTreeCount;
    }

}