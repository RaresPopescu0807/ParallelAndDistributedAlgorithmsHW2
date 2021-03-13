package com.apd.tema2.entities;

import com.apd.tema2.Main;
import com.apd.tema2.utils.Constants;

import static java.lang.Thread.sleep;

/**
 * Clasa utilizata pentru gestionarea oamenilor care se strang la trecerea de pietoni.
 */
public class Pedestrians implements Runnable {
    private int pedestriansNo = 0;
    public int maxPedestriansNo;
    private boolean pass = false;
    private boolean finished = false;
    private int executeTime;
    private long startTime;

    public Pedestrians(int executeTime, int maxPedestriansNo) {
        this.startTime = System.currentTimeMillis();
        this.executeTime = executeTime;
        this.maxPedestriansNo = maxPedestriansNo;
    }

    @Override
    public void run() {
        while(System.currentTimeMillis() - startTime < executeTime) {
            try {
                pedestriansNo++;
                sleep(Constants.PEDESTRIAN_COUNTER_TIME);

                if(pedestriansNo == maxPedestriansNo) {
                    Main.intersection.sem.release(Main.carsNo);//pietonii sunt cei care dau culoare rosie sau verde a semaforului cand se strang ca in cerinta

                    pedestriansNo = 0;
                    pass = true;
                    sleep(Constants.PEDESTRIAN_PASSING_TIME);
                    pass = false;
                    Main.intersection.sem.release(Main.carsNo);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        finished = true;
    }

    public boolean isPass() {
        return pass;
    }

    public boolean isFinished() {
        return finished;
    }
}
