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
public class PassportProcessing2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strLine, strPassportInfo;
        int nListLength, nValidCount;
        nValidCount = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day04_Input.txt"));
            
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
            if (fieldsPresent(arListEntries.get(i))) {
                if (fieldsValid(arListEntries.get(i))) {
                    nValidCount++;
                }
            }
        }

        System.out.println("The number of valid passports is: " + nValidCount);
    }

    public static boolean fieldsPresent(String strLine) {
        String[] strInfo = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

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

    public static boolean fieldsValid(String strLine) {
        String[] strInfoType = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        String[] arstrTemp;
        String strTemp, strAltTemp = "";
        char cTemp;
        boolean bValid = true;

        int nSpaces = 0, nTemp;

        for (int i = 0; i < strLine.length(); i++) {
            if (strLine.charAt(i) == ' ') {
                nSpaces++;
            }
        }

        String[] strInfo = strLine.split(" ", nSpaces + 1);

        for (int i = 0; i < strInfo.length; i++) {
            arstrTemp = strInfo[i].split(":", 2);
            strTemp = arstrTemp[1];

            if (arstrTemp[0].equals(strInfoType[0])) {
                if (strTemp.length() != 4) {
                    bValid = false;
                } else {
                    nTemp = Integer.parseInt(strTemp);
                    if (nTemp < 1920 || 2002 < nTemp) {
                        bValid = false;
                    }
                }
                
//                System.out.println("byr : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[1])) {
                if (strTemp.length() != 4) {
                    bValid = false;
                } else {
                    nTemp = Integer.parseInt(strTemp);
                    if (nTemp < 2010 || 2020 < nTemp) {
                        bValid = false;
                    }
                }
                
//                System.out.println("iyr : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[2])) {
                if (strTemp.length() != 4) {
                    bValid = false;
                } else {
                    nTemp = Integer.parseInt(strTemp);
                    if (nTemp < 2020 || 2030 < nTemp) {
                        bValid = false;
                    }
                }
                
//                System.out.println("eyr : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[3])) {
                if (strTemp.contains("cm")) {
                    nTemp = Integer.parseInt(strTemp.substring(0, strTemp.indexOf("cm")));
                    if (nTemp < 150 || 193 < nTemp) {
                        bValid = false;
                    }
                } else if (strTemp.contains("in")) {
                    nTemp = Integer.parseInt(strTemp.substring(0, strTemp.indexOf("in")));
                    if (nTemp < 59 || 76 < nTemp) {
                        bValid = false;
                    }
                } else {
                    bValid = false;
                }
                
//                System.out.println("hgt : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[4])) {
                if (strTemp.charAt(0) != '#') {
                    bValid = false;
                } else if ((strTemp.substring(1, strTemp.length())).length() != 6) {
                    bValid = false;
                } else {
                    strAltTemp = strTemp.substring(1, strTemp.length());
                    
                    for (int j = 0; j < strAltTemp.length(); j++) {
                        cTemp = strAltTemp.charAt(j);
                        
                        if (cTemp < '0' || ('9' < cTemp && cTemp < 'a') || 'f' < cTemp) {
//                            System.out.println("Tripped letter: " + cTemp + "\n");                            //Test
                            bValid = false;
                            j = strAltTemp.length();
                        }
                    }
                }
                
//                System.out.println("hcl : " + strAltTemp+ "\n");
//                System.out.println("hcl : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[5])) {
                if (!strTemp.equals("amb") && !strTemp.equals("blu") && !strTemp.equals("brn") && !strTemp.equals("gry") && !strTemp.equals("grn") && !strTemp.equals("hzl") && !strTemp.equals("oth")) {
                    bValid = false;
                }
                
//                System.out.println("ecl : " + bValid + "\n");                          //Test
            } else if (arstrTemp[0].equals(strInfoType[6])) {
                if (strTemp.length() != 9) {
                    bValid = false;
                } else {
                    try {
                       nTemp = Integer.parseInt(strTemp); 
                    } catch (NumberFormatException ignore) {
                       bValid = false;
                    }
                }
                
//                System.out.println("pid : " + bValid + "\n");                          //Test
            }
            
            if (!bValid) {
                i = strInfo.length;
            }
        }

        return bValid;
    }
}
