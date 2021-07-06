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
    Program title:         Rain Risk 2
    Programmed by:         Anish Racharla
    Date:                  Tuesday, 2021/7/6
 */
public class RainRisk2 {

    /**
     * @param args the command line arguments
     */  
    static int[] arnBoat = {0, 0}; 
    static int[] arnWay = {10, 1};

    public static void main(String[] args) {
        /*
        *                     ↑ N
        *                     | y+
        *                     |
        *                     |
        *         W           |
        *         ←-----------+-----------→ E
        *                     |              x+
        *                     |
        *                     |
        *                     |
        *                     ↓ S
        */

        int nVal;
        int nManDist;
        
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
                    rotateWaypoint(cAct, nVal);
                    break;
                case 'R':
                    rotateWaypoint(cAct, nVal);
                    break;
                case 'F':
                    moveBoat(nVal);
                    break;
                default:
                    moveWaypoint(cAct, nVal);
                    break;
            }

//            System.out.println("NS: " + nY + " EW: " + nX + "");      //TEST
//            System.out.println("Heading: " + facingDirection() + "\n");      //TEST
        }

        nManDist = Math.abs(arnBoat[0]) + Math.abs(arnBoat[1]);
        
        //Puzzle output
        System.out.println(nManDist);
    }
    
    //This method rotates the waypoint according to the R and L commands
    public static void rotateWaypoint(char cRotation, int nAmt) {
        nAmt = nAmt / 90;
        int nTempX, nTempY;
        
        //Rotating the waypoint as a coordinate vector
        for (int i = 0; i < nAmt; i++) {
            if (cRotation == 'R') {
                nTempY = arnWay[1];
                arnWay[1] = -arnWay[0];
                arnWay[0] = nTempY;
            } else {
                nTempX = arnWay[0];
                arnWay[0] = -arnWay[1];
                arnWay[1] = nTempX;
            }
        }
    }

    //This method moves the waypoint coords according to the N/E/S/W commands
    public static void moveWaypoint(char cDir, int nAmt) {
        switch (cDir) {
            case 'N':
                arnWay[1] += nAmt;
                break;
            case 'E':
                arnWay[0] += nAmt;
                break;
            case 'S':
                arnWay[1] -= nAmt;
                break;
            case 'W':
                arnWay[0] -= nAmt;
                break;
        }
    }
    
    //This method moves the boat to the waypoint nAmt times & updates the boat's coordinates
    public static void moveBoat(int nAmt) {
        arnBoat[0] += arnWay[0] * nAmt;       
        arnBoat[1] += arnWay[1] * nAmt;       
    }
}
