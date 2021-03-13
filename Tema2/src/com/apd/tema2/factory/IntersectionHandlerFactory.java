package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;
import com.apd.tema2.intersections.*;
import com.apd.tema2.utils.Constants;

import java.util.concurrent.BrokenBarrierException;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of
        // them)
        // road in maintenance - 2 ways 1 lane each, X cars at a time
        // road in maintenance - 1 way, M out of N lanes are blocked, X cars at a time
        // railroad blockage for s seconds for all the cars
        // unmarked intersection
        // cars racing
        return switch (handlerType) {
            case "simple_semaphore" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                    System.out.println("Car "+car.getId()+" has reached the semaphore, now waiting...");
                    /*try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("Car "+car.getId()+" has waited enough, now driving...");
                    //fiecare masina intra, asteapta si iese
                    //am comentat asteptarea ca sa se execute checkerul mai rpd
                }
            };
            case "simple_n_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car "+car.getId()+" has reached the roundabout, now waiting...");
                    try {
                        Main.intersection.sem.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//semafor care lasa sa intre maxim n masini
                    System.out.println("Car "+car.getId()+" has entered the roundabout");
                    /*try {
                        sleep(Main.intersection.t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    //am comentat sleep ca sa mearga mai repede checkerul
                    System.out.println("Car "+car.getId()+" has exited the roundabout after "+Main.intersection.t/1000+" seconds");
                    Main.intersection.sem.release();
                    //dupa ce paraseste intersectia masina lasa loc urmatoarei masini

                }
            };
            case "simple_strict_1_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car "+car.getId()+" has reached the roundabout");
                    /*try {
                        Main.intersection.bar.await();//bariera care asteapta toate masinile sa ajunga la intrare in giratoriu mai intai
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }*/
                    try {
                        Main.intersection.dir[car.getStartDirection()].acquire();//vector de semafoare care lasa sa treaca doar cate o masina din fiecare sens
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car "+car.getId()+" has entered the roundabout from lane "+car.getStartDirection());
                    /*try {
                        sleep(Main.intersection.t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    //am comentat sleepul ca sa mearga mai repede checkerul
                    System.out.println("Car "+car.getId()+" has exited the roundabout after "+Main.intersection.t/1000+" seconds");
                    Main.intersection.dir[car.getStartDirection()].release();//dupa ce o masina iese lasa loc urmatoarei masini


                }
            };
            case "simple_strict_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car "+car.getId()+" has reached the roundabout, now waiting...");
                    try {
                        Main.intersection.bar4.await();//astept toate masini mai intai
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    try {
                        Main.intersection.dir[car.getStartDirection()].acquire();//semafor care lasa sa treaca maxim x masini
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car "+car.getId()+" was selected to enter the roundabout from lane "+car.getStartDirection());

                    try {
                        Main.intersection.bar.await();//ne asiguram ca de semafor au trecut x masini
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //combinatia semafor bariera face sa treaca cate exact x masini
                    System.out.println("Car "+car.getId()+" has entered the roundabout from lane "+car.getStartDirection());
                    /*try {
                        sleep(Main.intersection.t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    //am comentat sleepul ca sa dureze mai putin checkerul
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car "+car.getId()+" has exited the roundabout after "+Main.intersection.t/1000+" seconds");
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }//aceste 2 bariere se asigura ca ies toate cele x masini inainte sa continuie
                    Main.intersection.dir[car.getStartDirection()].release();//masinile fac loc altor masini de pe acelasi sens dupa ce ies

                }
            };
            case "simple_max_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI
                    // Continuati de aici
                    System.out.println("Car "+car.getId()+" has reached the roundabout from lane "+car.getId());
                    try {
                        Main.intersection.dir[car.getStartDirection()].acquire();//semafor care are grija sa intre maxim x masini pe sens
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car "+car.getId()+" has entered the roundabout from lane "+car.getId());


                    Main.intersection.dir[car.getStartDirection()].release();//dupa ce iese o masina face loc urmatoarei
                    System.out.println("Car "+car.getId()+" has exited the roundabout after "+Main.intersection.t/1000+" seconds");


                }
            };
            case "priority_intersection" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                    if(car.getPriority()>1)//daca masina are prioritate intra in intersectie
                    {
                        synchronized (Main.intersection.lock)
                        {Main.intersection.masiniInIntersectie++;}//tinem minte cate masini cu prioritate sunt in intersectie ca sa stim cand pot intra cele fara prioritate
                        System.out.println("Car "+car.getId()+" with high priority has entered the intersection");
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (Main.intersection.lock)//avem grija sa nu umble mai multi la acest contor simultan
                        {Main.intersection.masiniInIntersectie--;}//iese o masina cu prioritate
                        System.out.println("Car "+car.getId()+" with high priority has exited the intersection");
                        if (Main.intersection.masiniInIntersectie == 0) {
                            synchronized (Main.intersection.lock2)
                            {
                                Main.intersection.lock2.notifyAll();//daca in intersectie nu mai sunt masini cu prioritate cele fara prioritate sunt notificate sa treaca
                            }
                        }

                    }
                    else
                    {
                        System.out.println("Car "+car.getId()+" with low priority is trying to enter the intersection...");
                        synchronized (Main.intersection.q)//avem grija sa nu umble mai multi la aceasta coada simultan
                        {//folosim o coada pentru a putea reda iesirea masinilor in ordinea venirii lor
                            Main.intersection.q.add("Car "+car.getId()+" with low priority has entered the intersection");
                        }
                        synchronized (Main.intersection.lock2)
                        {
                            while (Main.intersection.masiniInIntersectie != 0) {


                                try {
                                    Main.intersection.lock2.wait();//masinile fara prioritate care nu pot intra asteapta sa fie notificate
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        synchronized (Main.intersection.q)
                        {
                            System.out.println(Main.intersection.q.remove());//cand masinile sunt notificate ies in ordinea in care au ajuns
                        }
                    }

                }
            };
            case "crosswalk" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car "+car.getId()+" has now green light");
                    if(Main.intersection.maiMulteTreceri==true) {
                        while (Main.pedestrians.isFinished() == false) {
                            try {
                                Main.intersection.sem.acquire();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Car " + car.getId() + " has now red light");
                            try {
                                sleep(Constants.PEDESTRIAN_PASSING_TIME*Main.pedestrians.maxPedestriansNo);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Main.intersection.sem.acquire();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Car " + car.getId() + " has now green light");//dupa primul green light masinile primesc perechi de rosu si verde la semafor


                        }
                    }

                    
                }
            };
            case "simple_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car "+car.getId()+" from side number "+car.getStartDirection()+" has reached the bottleneck");
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }//asteptam toate masinile mai intai printr-o bariera
                    if(car.getStartDirection()==0)
                    {
                        try {
                            Main.intersection.sem80.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Main.intersection.bar80.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Car "+car.getId()+" from side number "+car.getStartDirection()+" has passed the bottleneck");
                        Main.intersection.sem81.release();
                    }
                    else
                    {
                        try {
                            Main.intersection.sem81.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Main.intersection.bar81.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Car "+car.getId()+" from side number "+car.getStartDirection()+" has passed the bottleneck");
                        Main.intersection.sem80.release();
                    }//pentru fiecare sens folosim o combinatie de semafor cu bariere pentru a ne asigura ca de fiecare data trec exact nr de masini dorit
                    //dupa ce o masina de pe un sens trece face release pe semaforul celuilalt sens, facand loc masinilor de pe celalalt sens sa intre


                }
            };
            case "complex_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {//am comentat ce am scris, era incomplet
                    /*
                    System.out.println("Car "+car.getId()+" has come from the lane number "+car.getStartDirection());
                    for(int i =0;i<Main.intersection.M;i++)
                    {
                        if((car.getStartDirection()>=i*Main.intersection.N/Main.intersection.M)&&(car.getStartDirection()<Math.min((i+1)*Main.intersection.N/Main.intersection.M,Main.intersection.N)))
                            Main.intersection.v9.get(car.getStartDirection()).add("Car "+car.getId()+" from the lane "+car.getStartDirection()+" has entered lane number "+i);

                    }
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    if(car.getId()==0)
                    {
                        for(int i =0;i<Main.intersection.M;i++)
                        {
                            for(int j=i*Main.intersection.N/Main.intersection.M;j<Math.min((i+1)*Main.intersection.N/Main.intersection.M,Main.intersection.N);j++)
                            {
                                Main.intersection.n9.get(i).add(Main.intersection.v9.get(j));
                            }
                        }
                    }
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    */



                }
            };
            case "railroad" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    try {
                        Main.intersection.sem80.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car "+car.getId()+" from side number "+car.getStartDirection()+" has stopped by the railroad");

                    synchronized (Main.intersection.q)
                    {
                        Main.intersection.q.add("Car "+car.getId()+" from side number "+car.getStartDirection()+" has started driving");
                    }
                    Main.intersection.sem80.release();

                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    if(car.getId()==0)
                        System.out.println("The train has passed, cars can now proceed");//dupa ce toate masinile ajung si trec de bariera una dintre ele anunta ca trenul a trecut si ca toate pot continua drumul
                    try {
                        Main.intersection.bar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    try {
                        Main.intersection.sem80.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (Main.intersection.q)
                    {
                        System.out.println(Main.intersection.q.remove());

                    }
                    Main.intersection.sem80.release();
                }
                //ca la unul dintre taskurile anterioare folosesc o coada pentru a tine minte in ce ordine au venit masinile
                //trebuie sa am grija si ca scrierile sa se faca in ordinea care trebuie
            };
            default -> null;
        };
    }
}
