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
    Program title:         Encoding Error 1
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/6/9
 */
public class EncodingError1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nIndex, nPreamble;
        nIndex = 0;
        nPreamble = 25;
        
        String strWord;
        
        boolean bValid;
        
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
        
        //Output with extra info
//        System.out.println("The first number that does not have this property is: " + arListInput.get(nIndex) + " at index: " + nIndex);
        
        //Puzzle output
        System.out.println(arListInput.get(nIndex));
    }
    
    //This method simply goes through the nPreamble (in this case 25) entries before the passed entry and checks whether the passed entry 
    //can be formed by a sum of two numbers in the preceding nPreamble entries.
    //True/false will be returned accoringly.
    public static boolean isValid(ArrayList<Long> arListLong, int nPreamble, int nIndex){
        boolean bValid;
        bValid = false;
        
        long lNum, lSum;
        lNum = arListLong.get(nIndex);
        
        long[] aLPreamble = new long[nPreamble];
        
        //Creating the array of the nPreamble entries before the passed entry.
        for (int i = nIndex - nPreamble, j = 0; i < nIndex; i ++, j ++) {
            aLPreamble[j] = arListLong.get(i);
        }
        
        //Checking whether the sum of 2 numbers within the array = the passed entry.
        //Exits right after finding the sum, or after exhausting the list.
        for (int i = 0; i < nPreamble; i ++) {
            for (int j = 0; j < nPreamble; j ++) {
                if (j == i && j != nPreamble - 1) j++;
                
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
}
