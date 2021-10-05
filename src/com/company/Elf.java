package com.company;

import java.util.Random;

public class Elf extends Thread {

    public String name;
    private SantaClaus santaClaus;
    private Random random;
    public boolean wait;

    public Elf(String name, SantaClaus santaClaus) {
        this.name = name;
        this.santaClaus = santaClaus;
        this.random = new Random();
        this.wait = false;
    }

    public void work() {
        int chanceToDiscuss = new Random().nextInt(100 - 0) + 1;

        if (chanceToDiscuss <= 25) {
            talkToSantaClaus();
        } else {
            makeToys();
        }
    }

    public void makeToys() {
        try {
            int time = random.nextInt(5000) + 10000; // 10s - 15s
//            int time = 10000;
            System.out.println(this.name + " irá fabricar brinquedos por: " + time + "ms");
            Thread.sleep(time);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void talkToSantaClaus() {
        System.out.println(this.name + " precisa de ajuda");
        // Se um elfo precisa de ajuda, ele pede ao Papai Noel
        // -> "wait ()" é chamado neste tópico
        santaClaus.addElfToWaitingList(this);
        wait = true;

        synchronized(this) {
            while(wait) {
                try {
                    wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            this.work();
        }
    }
}