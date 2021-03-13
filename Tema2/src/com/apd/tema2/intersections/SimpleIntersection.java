package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleIntersection {
    public BlockingQueue<String> q10;
    public List<BlockingQueue<BlockingQueue<String>>> n9;
    public List<BlockingQueue<String>> v9;
    public int M;
    public int N;
    public int x9;
    public int x8;//task8
    public Semaphore sem80;
    public Semaphore sem81;
    public CyclicBarrier bar80;
    public CyclicBarrier bar81;
    public boolean maiMulteTreceri=true;
    public final Object lock=new Object();
    public final Object lock2=new Object();
    public int masiniInIntersectie;
    public Queue<String> q;
    public int remainingP;
    public int remainingNP;
    public int n;
    public int t;
    public int masiniPeBanda;
    public Semaphore sem;
    public CyclicBarrier bar;
    public CyclicBarrier bar4;
    public static Semaphore [] dir = new Semaphore [] {new Semaphore(1),
            new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    public void startSem()
    {
        sem=new Semaphore(n);
    }

    public void startBar()
    {
        bar = new CyclicBarrier(n);
    }

    public void startDir(int masiniPeBanda)
    {
        dir=new Semaphore[n];
        for(int i=0;i<n;i++)
        {
            dir[i]=new Semaphore(masiniPeBanda);
        }

    }

    public void start3()
    {
        //startBar();//initializeaza o bariera care asteapta toate masinile
        startDir(1);//initializez un vector de semafoare care lasa sa intre doar cate o masina
    }

    public void start4()
    {
        bar4 = new CyclicBarrier(Main.carsNo);//initializam bariera care asteapta toate masinile la inceput
        //startBar();
        bar = new CyclicBarrier(masiniPeBanda*n);//initializam bariera care asteapta cele nr benzi*masini pe banda masini
        startDir(this.masiniPeBanda);//initializam vectorul cu cate un semafoe pt fiecare banda
    }

    public void start5()
    {
        startDir(this.masiniPeBanda);//initializam vectorul cu cate un semafor pt fiecare banda

    }

    public void start6()
    {
        masiniInIntersectie=0;//tinem minte cate masini cu prioritate sunt in intersectie
        q = new LinkedList<>();//coada ca sa tinem minte ordinea venirii masinilor fara prioritate
    }

    public void start7()
    {
        sem=new Semaphore(0);//initializam un semafor prin intermediul caruia numarul de pietoni determina culoarea rosie sau verde a semaforului


    }

    public void start8()
    {
        sem80=new Semaphore(x8);
        sem81=new Semaphore(0);
        bar=new CyclicBarrier(Main.carsNo);
        bar80=new CyclicBarrier(x8);
        bar81=new CyclicBarrier(x8);

    }

    public void start9()
    {
        n9=Collections.synchronizedList(new ArrayList<BlockingQueue<BlockingQueue<String>>>());
        for(int i=0;i<M;i++)
        {
            n9.add(new ArrayBlockingQueue<BlockingQueue<String>>(Math.min((i+1)*N/M,N)-i*N/M));
            System.out.println(Math.min((i+1)*N/M,N)-i*N/M);
        }
        v9=Collections.synchronizedList(new ArrayList<BlockingQueue<String>>());
        for(int i=0;i<N;i++)
        {
            v9.add(new ArrayBlockingQueue<String>(Main.carsNo));

        }
        bar = new CyclicBarrier(Main.carsNo);

    }

    public void start10()
    {
        bar = new CyclicBarrier(Main.carsNo);
        q = new LinkedList<>();
        sem80=new Semaphore(1);
        sem81=new Semaphore(1);

    }


    // Define your variables here.
}
