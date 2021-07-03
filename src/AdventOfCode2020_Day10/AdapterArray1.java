/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day10;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Adapter Array 1
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/6/9
 */
public class AdapterArray1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] arnDiff;
        
        int nProd;
        
        String strWord;
        
        //Getting the input.
        ArrayList<Integer> arListEntries = new ArrayList<Integer>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day10_Input.txt"));
            while ((strWord = br.readLine()) != null) {
                arListEntries.add(Integer.parseInt(strWord));
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
        
        //Sorting the list of inputs.
        insertionSort(arListEntries);
        
        //Adding the start and end values.
        arListEntries.add(0, 0);
        arListEntries.add(arListEntries.get(arListEntries.size() - 1) + 3);
        
        arnDiff = countDiff(arListEntries);
        
        //Getting the product of the # of differences of 1 and 3
        nProd = arnDiff[0] * arnDiff[1];
        
        //Output with extra info
//        System.out.println("The number of 1-jolt differences is: " + arnDiff[0] + "\nThe number of 3-jolt differences is: " + arnDiff[1] + "\nThe product is: " + nProd);
        
        //Puzzle output
        System.out.println(nProd);
    }
    
    //Simple insertion sort method.
    public static void insertionSort(ArrayList<Integer> arListInt){
        int nTempI, nTempJ, i, j;
        
        for (i = 1; i < arListInt.size(); i++) {
            nTempI = arListInt.get(i);
            j = i - 1;
            while ((j >= 0) && nTempI < arListInt.get(j)) {
                nTempJ = arListInt.get(j);
                
                arListInt.set(j, arListInt.get(j + 1));
                arListInt.set((j + 1), nTempJ);
                
                j--;
            }
        }
    }
    
    //This method takes the sorted arraylist and counts the number of differences of 1 and 3 between the numbers in the list.
    //The method returns the numbers in an array.
    public static int[] countDiff(ArrayList<Integer> arListInt){
        int nTemp1, nTemp2, nDiff, nOne, nThree;
        nOne = 0;
        nThree = 0;
        
        for (int i = 0, j = 1; j < arListInt.size(); i++, j++) {
            nTemp1 = arListInt.get(i);
            nTemp2 = arListInt.get(j);
            
            nDiff = nTemp2 - nTemp1;
            
            switch (nDiff){
                case 1:
                    nOne++;
                    break;
                case 3:
                    nThree++;
                    break;
                default:
            }
        }
        
        int[] arnDiff = {nOne, nThree};
        
        return arnDiff;
    }
}
