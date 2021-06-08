/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day07;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Handy Haversacks 1
    Programmed by:         Anish Racharla
    Date:                  Monday, 2020/12/7
 */
public class HandyHaversacks1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nCount = 0;
        boolean bContains;
        String strBagContain, word;

        ArrayList<String> arListEntries = new ArrayList<String>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day07_Input.txt"));
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

        String[] arstrParents = new String[nListLength];

        for (int i = 0; i < nListLength; i++) {
            arstrParents[i] = arListEntries.get(i);
        }

        ArrayList<ArrayList> arlistarlistChildren = new ArrayList<ArrayList>();
        
        //Adding the string arraylists to and arraylist arraylist, and modifying the original list entries array to only hold the parent bag names.
        //The idea is that the array of strings holds the parent bag names, and they map to arraylists of the bags they contain.
        for (int i = 0; i < nListLength; i++) {
            arlistarlistChildren.add(i, processLine(arstrParents, i));
//            arListEntries.set(i, word);
//            System.out.println(arstrParents[i] + " ");                          //TEST
//            for(int j = 0; j < arlistarlistChildren.get(i).size(); j ++) {      //TEST
//                System.out.println(arlistarlistChildren.get(i).get(j));         //TEST
//            }
//            System.out.println("");                                             //TEST
        }
        
        //Going through each parent bag and seeing if they (directly or eventually within) contain the "shiny gold: bag.
        for (int i = 0; i < nListLength; i++) {
            bContains = doesContain(arstrParents, arlistarlistChildren, i);
            if (bContains) {
//                System.out.println(arListEntries.get(i) + "\n");                //TEST
                nCount++;
            }
        }

        System.out.println("The number of bags able to hold a shiny gold bag is: " + nCount);
    }

    public static ArrayList<String> processLine(String[] arstrParents, int nIndex) {
        /*
            Purpose: Processes the input information.
            Pre-condition: A string array, and an index must be passed.
            Post-condition: Modifies the string array item to be just the parent bag name, and returns an arraylist of the children bag names.
         */
        ArrayList<String> arListChildren = new ArrayList<String>();
        int nTemp1 = 0, nTemp2;
        String strTemp, strLine;

        strLine = arstrParents[nIndex];
        
        //Manually finding the pertinent information.
        nTemp2 = strLine.indexOf(" ");
        nTemp2 = strLine.indexOf(" ", nTemp2 + 1);

        arstrParents[nIndex] = strLine.substring(0, nTemp2);

        if (!strLine.contains("no other bags")) {
            strTemp = strLine.substring(strLine.indexOf("contain") + 7);
            
            //Finding the number of separating commas between the children.
            for (int i = 0; i < strTemp.length(); i++) {
                if (strTemp.charAt(i) == ',') {
                    nTemp1++;
                }
            }
            
            //Splitting the sections containing the children bags into separate strings.
            String[] arStrBagsContained = strTemp.split(",", nTemp1 + 1);
            
            //Again, manually finding the pertinent information and adding each child bag name to an arraylist.
            //This arraylist is added to another arraylist; the parent location and their respective children (in this arraylist)
            //thus have the same index number.
            for (int i = 0; i < arStrBagsContained.length; i++) {
                strTemp = arStrBagsContained[i].substring(3, arStrBagsContained[i].indexOf("bag") - 1);

                arListChildren.add(strTemp);
            }
        }
        return arListChildren;
    }

    public static boolean doesContain(String[] arstrParents, ArrayList<ArrayList> arlistarlistChildren, int nIndex) {
        /*
            Purpose: Checks if the given bag contains a "shiny gold" bag.
            Pre-condition: A string array, an array list containing an arraylist of strings, and an index must be passed.
            Post-condition: Returns a boolean value for whether the bag exists somewhere within the bag or not.
         */
        
        ArrayList<String> arListChildren = new ArrayList<String>();
        boolean bFound = false;
        String strTemp;

        arListChildren = arlistarlistChildren.get(nIndex);
        
        //If the parent doesn't hold any children, the program will return false.
        if (arListChildren.isEmpty()) {
            return false;
        }
        
        //If the prent holds a shiny gold bag it will immediately return true (after checking the above case).
        for (int i = 0; i < arListChildren.size(); i++) {
            if (arListChildren.get(i).equals("shiny gold")) {
                bFound = true;
            }
        }
        
        //If none of the above cases are met, the program recursively searches through each child, and their respective children
        //until one of the above cases are met.
        if (!bFound) {
            for (int i = 0; i < arListChildren.size(); i++) {
                strTemp = arListChildren.get(i);
                
                bFound = doesContain(arstrParents, arlistarlistChildren, linearSearch(arstrParents, strTemp));
                
                if (bFound) {
                    i = arListChildren.size();
                }
            }
        }
        
        return bFound;
    }

    static public int linearSearch(String[] A, String B) {
        /*
            Purpose: Does a simple linear search of a given string array for a given string.
            Pre-condition: A string array and a string to find must be passed.
            Post-condition: Returns the string's index **the string should be in the array, thus the program does not account for a missing string**
         */
        
        String strTemp;

        for (int k = 0; k < A.length; k++) {
            strTemp = A[k];

            if (strTemp.equals(B)) {
                return k;
            }
        }

        return -1;
    }
}
