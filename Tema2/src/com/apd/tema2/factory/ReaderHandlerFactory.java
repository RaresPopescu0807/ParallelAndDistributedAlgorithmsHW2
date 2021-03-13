package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;
import com.apd.tema2.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
        return switch (handlerType) {
            case "simple_semaphore" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) {
                }
            };
            case "simple_n_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simple_n_roundabout");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.n = parseInt(line[0]);
                    Main.intersection.t = parseInt(line[1]);
                    Main.intersection.startSem();//initializeaza un semafor care lasa maxim n masini sa intre
                    // To parse input line use:
                    // String[] line = br.readLine().split(" ");
                }
            };
            case "simple_strict_1_car_roundabout" -> new ReaderHandler() {
                @Override

                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_1_car_roundabout");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.n = parseInt(line[0]);
                    Main.intersection.t = parseInt(line[1]);
                    Main.intersection.start3();

                }
            };
            case "simple_strict_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_x_car_roundabout");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.n = parseInt(line[0]);
                    Main.intersection.t = parseInt(line[1]);
                    Main.intersection.masiniPeBanda = parseInt(line[2]);
                    Main.intersection.start4();

                }
            };
            case "simple_max_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simple_max_x_car_roundabout");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.n = parseInt(line[0]);
                    Main.intersection.t = parseInt(line[1]);
                    Main.intersection.masiniPeBanda = parseInt(line[2]);
                    Main.intersection.start5();
                }
            };
            case "priority_intersection" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("priority_intersection");
                    String[] line = br.readLine().split(" ");
                    //Main.intersection.n = parseInt(line[0]);
                    //Main.intersection.t = parseInt(line[1]);
                    Main.intersection.remainingP = parseInt(line[0]);
                    Main.intersection.remainingNP = parseInt(line[1]);
                    Main.intersection.start6();
                }
            };
            case "crosswalk" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("crosswalk");
                    String[] line = br.readLine().split(" ");
                    Main.pedestrians=new Pedestrians(parseInt(line[0]),parseInt(line[1]));
                    if(parseInt(line[0])<parseInt(line[1])* Constants.PEDESTRIAN_COUNTER_TIME)
                        Main.intersection.maiMulteTreceri=false;
                    Main.intersection.start7();
                }
            };
            case "simple_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simple_maintenance");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.x8=parseInt(line[0]);
                    Main.intersection.start8();
                    
                }
            };
            case "complex_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    /*Main.intersection = IntersectionFactory.getIntersection("complex_maintenance");
                    String[] line = br.readLine().split(" ");
                    Main.intersection.M=parseInt(line[0]);
                    Main.intersection.N=parseInt(line[1]);
                    Main.intersection.x9=parseInt(line[2]);
                    Main.intersection.start9();*/
                }
            };
            case "railroad" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("railroad");
                    Main.intersection.start10();
                }
            };
            default -> null;
        };
    }

}
