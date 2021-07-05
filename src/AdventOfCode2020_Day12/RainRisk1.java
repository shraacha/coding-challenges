/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day12;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Rain Risk 1
    Programmed by:         Anish Racharla
    Date:                  Monday, 2021/7/5
 */
public class RainRisk1 {

    /**
     * @param args the command line arguments
     */
    static int nFacing, nX, nY;

    public static void main(String[] args) {
        nFacing = 1;

        int nVal;
        int nManDist;

        nX = 0;
        nY = 0;
        nFacing = 1;                       //nFacing stores the direction the boat is facing: N/E/S/W are 0/1/2/3 respectively

        char cAct;
        String strInstr;

        //Getting the input
        ArrayList<String> arListInput = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day12_Input.txt"));
            String strIn;
            while ((strIn = br.readLine()) != null) {
                arListInput.add(strIn);
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

        for (int i = 0; i < arListInput.size(); i++) {
            strInstr = arListInput.get(i);

            cAct = strInstr.charAt(0);

            nVal = Integer.parseInt(strInstr.substring(1));

//            System.out.println("Action: " + cAct + "\nValue: " + nVal);         //TEST
            //Switch statement to carry out different commands based on the action
            switch (cAct) {
                case 'L':
                    setFacing(cAct, nVal);
                    break;
                case 'R':
                    setFacing(cAct, nVal);
                    break;
                case 'F':
                    move(facingDirection(), nVal);
                    break;
                default:
                    move(cAct, nVal);
                    break;
            }

//            System.out.println("NS: " + nY + " EW: " + nX + "");      //TEST
//            System.out.println("Heading: " + facingDirection() + "\n");      //TEST
        }

        nManDist = Math.abs(nY) + Math.abs(nX);
        
        //Puzzle output
        System.out.println(nManDist);
    }

    //This method sets the nFacing value based on the rotation direction and amount
    public static void setFacing(char cRotation, int nAmt) {
        nAmt = nAmt / 90;
        
        //If the dir of rotation is right, the amt is added to nFacing, if the dir is left, it is subtracted from nFacing
        if (cRotation == 'R') {
            nFacing += nAmt;
        } else {
            nFacing -= nAmt;
        }
        
        //Correcting the rotation if it goes over 3 or below 0
        if (nFacing > 3) {
            nFacing -= 4;
        } else if (nFacing < 0) {
            nFacing += 4;
        }
    }

    //This method converts the facing value into a cardinal direction
    public static char facingDirection() {
        switch (nFacing) {
            case 0:
                return 'N';
            case 1:
                return 'E';
            case 2:
                return 'S';
            case 3:
                return 'W';
            default:
                return '0';
        }
    }

    //This method changes the coodinates of the boat
    public static void move(char cDir, int nAmt) {
        switch (cDir) {
            case 'N':
                nY += nAmt;
                break;
            case 'E':
                nX += nAmt;
                break;
            case 'S':
                nY -= nAmt;
                break;
            case 'W':
                nX -= nAmt;
                break;
        }
    }
}
