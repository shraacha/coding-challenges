/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day06;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Custom Customs 1
    Programmed by:         Anish Racharla
    Date:                  Saturday, 2020/12/6
 */
public class CustomCustoms1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strLine, strGrpAns;
        strGrpAns = "";

        int nListLength, nSum;
        nSum = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day06_Input.txt"));
            
            while ((strLine = br.readLine()) != null) {
                if (!strLine.isEmpty()) {
                    strGrpAns += strLine;
                } else {
                    arListEntries.add(strGrpAns.trim());
                    strGrpAns = "";
                }
            }

            //For the last line
            arListEntries.add(strGrpAns.trim());
            strGrpAns = "";
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

        nSum = 0;

        for (int i = 0; i < nListLength; i++) {
            nSum += countAns(arListEntries.get(i));
        }
        
        
        //Output with extra info
//        System.out.println("The sum of the counts from each group is: " + nSum);
        
        //Puzzle output
        System.out.println(nSum);
    }

    public static int countAns(String strGroupAns) {
        int nSum;
        nSum = 0;
        
        char cTemp;

        for (int i = 97; i < 123; i++) {
            cTemp = (char) i;
            
            if (strGroupAns.contains("" + cTemp)) {
                nSum++;
            }
        }
        
        return nSum;
    }
}
