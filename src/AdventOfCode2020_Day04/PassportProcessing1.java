/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day04;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Passport Processing 1
    Programmed by:         Anish Racharla
    Date:                  Friday, 2020/12/4
 */
public class PassportProcessing1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nValidCount;
        nValidCount = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day04_Input.txt"));
            String strLine, strPassportInfo;
            strPassportInfo = "";
            while ((strLine = br.readLine()) != null) {
                if (!strLine.isEmpty()) {
                    strPassportInfo += strLine + " ";
                } else {
                    arListEntries.add(strPassportInfo.trim());
                    strPassportInfo = "";
                }
            }
            
            
            //For the last line
            arListEntries.add(strPassportInfo.trim());
            strPassportInfo = "";
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

        for (int i = 0; i < nListLength; i++) {
            if (isValid(arListEntries.get(i))) {
                nValidCount++;
            }
        }

        System.out.println("Example input line: " + arListEntries.get(0) + "\n\n ");
        
        System.out.println("");
        
        System.out.println("The number of valid passports is: " + nValidCount);
    }

    public static boolean isValid(String strLine) {
        String[] strInfo = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; //cid does not need to be included

        boolean[] bContainInfo = new boolean[strInfo.length];
        boolean bValid = true;

        //
        for (int i = 0; i < strInfo.length; i++) {
            if (strLine.contains(strInfo[i])) {
                bContainInfo[i] = true;
            } else {
                bContainInfo[i] = false;
            }
        }
        
        for (int i = 0; i < bContainInfo.length; i++) {
            if (!bContainInfo[i]) {
                bValid = false;
                i = bContainInfo.length - 1;
            }
        }

        return bValid;
    }
}
