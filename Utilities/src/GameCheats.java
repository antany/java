

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Date;

/**
 *
 * @author antany
 */
public class GameCheats {

    /**
     * @param args
     *            the command line arguments
     */
    enum CubeColors {

        RED, GREEN, BLUE, YELLO, PURPLE
    }
    public static int x = 0;
    public static int y = 0;
    public static int sqsize = 40;
    public static Robot robot;

    public static void main(String[] args) throws Exception {
        robot = new Robot();
        System.out.println("Bot Started");
        Thread.sleep(5000);

      //  Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.);            
        
        x = MouseInfo.getPointerInfo().getLocation().x;
        y = MouseInfo.getPointerInfo().getLocation().y;

        colorSelectionTest();

        System.exit(0);
        
        Date dt = new Date();
        long startTime = dt.getTime();
        
        TimerThread th = new TimerThread();
        th.setStartTime(dt);
        th.start();
        
        while (true) {
            dt = new Date();
            long currentTime = dt.getTime();
            solveProblemBot();
            
            if ((currentTime - startTime) / 1000 > 45) {
                //System.out.println((currentTime - startTime) / 1000);
                break;
            }
        }
    }

    public static void solveProblemBot() throws Exception {


        

        for (int j = (y + (sqsize * 8)); j >= y; j -= sqsize) {
            for (int i = x; i < (x + (sqsize * 10)); i += sqsize) {
                //System.out.println(i + ":" + j);

                boolean ignoreUpOne = false;
                boolean ignoreUpTwo = false;
                boolean ignoreRightOne = false;
                boolean ignoreRightTwo = false;
                boolean ignoreCrossPlus = false;
                boolean ignoreCrossMinus = false;
                
                CubeColors currColor = getColor(i, j);

                CubeColors upOne = getColor(i, j - sqsize);
                CubeColors upTwo = getColor(i, j - (sqsize * 2));
                CubeColors rightOne = getColor(i + sqsize, j);
                CubeColors rightTwo = getColor(i + (sqsize * 2), j);
                CubeColors crossPlus = getColor(i + sqsize, j - sqsize);
                CubeColors crossMinus = getColor(i - sqsize, j - sqsize);
                
                
                
                
                
                int testCount = 0;
                
                if(j==y){
                    ignoreUpOne = true;
                    ignoreUpTwo = true;
                }
                if(j==(y+sqsize)){
                    ignoreUpTwo = true;
                }
                if(i==x){
                    ignoreCrossMinus = true;
                }
                if(i==x+(sqsize*9)){
                    ignoreRightOne = true;
                    ignoreRightTwo = true;
                    ignoreCrossPlus = true;
                }
                if(i==x+(sqsize*9)){
                    ignoreRightTwo = true;
                }
                
                if(currColor==upOne && !ignoreUpOne && currColor ==crossPlus && !ignoreCrossPlus){
                    testCount++;
                }else if(currColor==upOne && !ignoreUpOne && currColor ==upTwo && !ignoreUpTwo){
                    testCount++;
                }else if(currColor==upOne && !ignoreUpOne && currColor ==rightOne && !ignoreRightOne){
                    testCount++;
                }else if(currColor==upOne && !ignoreUpOne && currColor ==crossMinus && !ignoreCrossMinus){
                    testCount++;
                }else if(currColor==rightOne && !ignoreRightOne && currColor ==rightTwo && !ignoreRightTwo){
                    testCount++;
                }else if(currColor==rightOne && !ignoreRightOne && currColor ==crossPlus && !ignoreCrossPlus){
                    testCount++;
                }


                if (testCount > 0) {
                    robot.mouseMove(i, j);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    
                    Thread.sleep(350);
                    
//                    System.out.println(upOne);
//                    System.out.println(upTwo);
//                    System.out.println(rightOne);
//                    System.out.println(rightTwo);
//                    System.out.println(crossPlus);
//                    System.out.println(crossMinus);
//                    System.out.println("----");
                    
                    
//                    if(j<(y + (sqsize * 8))){
//                        j= y + (sqsize * 8);
//                    }else{
//                       Thread.sleep(10);
//                    }    
                }
            }
        }
    }

    public static CubeColors getColor(int x, int y) throws Exception {
        return getColor(x, y, false);
    }

    public static CubeColors getColor(int x, int y, boolean printColor) throws Exception {

        Color color = robot.getPixelColor(x, y);


        int blue = color.getBlue();
        int green = color.getGreen();
        int red = color.getRed();

        CubeColors colorCode;

        if (red > 200 && green < 150 && blue < 150) {
            colorCode = CubeColors.RED;
        } else if (blue > 225 && red < 50) {
            colorCode = CubeColors.BLUE;
        } else if (green > 110 && red < 120 && blue < 100) {
            colorCode = CubeColors.GREEN;
        } else if (red > 220 && green > 160 && blue < 20) {
            colorCode = CubeColors.YELLO;
        } else {
            colorCode = CubeColors.PURPLE;
        }
        if (printColor) {
            System.out.println(x + ":" + ":" + y + ":" + color + ":" + colorCode);
        }
        return colorCode;
    }

    public static void colorSelectionTest() throws Exception {
        for (int j = (y + (sqsize * 8)); j >= y; j -= sqsize) {
            for (int i = x; i < (x + (sqsize * 10)); i += sqsize) {
                getColor(i, j, true);
            }
        }
    }
    
    
}

class TimerThread extends Thread{
    
    Date startTime,dt = null;

    public void run(){
      
        long st = startTime.getTime();
        
        
        
        while(true){
         dt = new Date();
         long currentTime = dt.getTime();
         if ((currentTime - st) / 1000 > 60) {
                System.out.println("test"+(currentTime - st) / 1000);
                System.exit(0);
         }
        }
    }
    
    
    public void setStartTime(Date startTime){
        this.startTime = startTime;
        
    }
    
    
}
