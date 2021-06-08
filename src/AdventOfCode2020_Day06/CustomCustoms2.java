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
    Program title:         Custom Customs 2
    Programmed by:         Anish Racharla
    Date:                  Saturday, 2020/12/6
 */
public class CustomCustoms2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strLine, strGrpAns;
        strGrpAns = "";

        int nListLength, nSum, nCount;
        nCount = 0;
        nSum = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day06_Input.txt"));

            while ((strLine = br.readLine()) != null) {
                if (!strLine.isEmpty()) {
                    nCount++;
                    strGrpAns += strLine;
                } else {
                    strGrpAns = Integer.toString(nCount) + strGrpAns;
                    arListEntries.add(strGrpAns.trim());
                    strGrpAns = "";
                    nCount = 0;
                }
            }

            //For the last line
            strGrpAns = Integer.toString(nCount) + strGrpAns;
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

        System.out.println("The sum of the counts from each group is: " + nSum);
    }

    public static int countAns(String strGroupAns) {
        int nSum, nCount, nIndex;
        nSum = 0;

        nCount = Character.getNumericValue(strGroupAns.charAt(0));

        strGroupAns = strGroupAns.substring(1, strGroupAns.length());

        for (int i = 97; i < 123; i++) {
            nIndex = 0;

            for (int j = 0; j < nCount; j++) {
                nIndex = strGroupAns.indexOf(i, nIndex);

                if (nIndex == -1) {
                    j = nCount;
                } else {
                    nIndex += 1;
                }
            }

            if (nIndex != -1) {
                nSum++;
            }
        }

        return nSum;
    }
}
