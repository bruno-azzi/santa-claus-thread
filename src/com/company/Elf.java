package com.company;

import java.util.Random;

public class Elf extends Thread {
    String name;
    SantaClaus santaClaus;

    public Elf(String name, SantaClaus santaClaus) {
        this.name = name;
        this.santaClaus = santaClaus;
    }

    public void work() {
        int random = new Random().nextInt(100 - 0) + 1;

        if (random <= 25) {
            talkToSantaClaus();
        } else {
            makeToys();
        }
    }

    public void makeToys() {
        try {
            //int time = (int)(10000 + Math.random() * 30000);
            int time = 10000;
            System.out.println("Elfo " + this.name + " irá fabricar brinquedos por: " + time + "ms");
            Thread.sleep(time);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void talkToSantaClaus() {
        santaClaus.groupUpElves(this);
    }

    public void run() {
        while(true) {
            this.work();
        }
    }
}
