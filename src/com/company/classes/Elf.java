package com.company.classes;

import java.util.Random;

public class Elf extends Thread {
    private final SantaClaus santaClaus;
    public boolean needToTalkWithSantaClaus;

    public Elf(String name, SantaClaus santaClaus) {
        super(name);
        this.santaClaus = santaClaus;
        this.needToTalkWithSantaClaus = false;

        start();
    }

    public void work() {
        Integer chanceToDiscuss = new Random().nextInt(100 - 0) + 1;

        if (chanceToDiscuss <= 25) {
            talkToSantaClaus();
        } else {
            makeToys();
        }
    }

    public void makeToys() {
        try {
            Integer time = new Random().nextInt(5000) + 10000;
            Logger.log("[" + this.getName() + "]" + " está fabricando brinquedos");
            Thread.sleep(time);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void talkWithSantaClaus() {
        synchronized(this) {
            while(needToTalkWithSantaClaus) {
                try {
                    wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void talkToSantaClaus() {
        Logger.log("[" + this.getName() + "]" + " precisa de ajuda");

        santaClaus.addElfToWaitingList(this);
        needToTalkWithSantaClaus = true;

        this.talkWithSantaClaus();
    }

    @Override
    public void run() {
        while (true) {
            this.work();
        }
    }
}