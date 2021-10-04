package com.company;

import java.util.ArrayList;

public class SantaClaus extends Thread {
    int minQtyOfElves = 3;
    ArrayList<Elf> elfGroup = new ArrayList<Elf>();

    public synchronized void talkToElves() {
        try {
            if (elfGroup.size() >= minQtyOfElves) {
                removeGroupOfElves();

                System.out.println("Papai Noel começou a falar com os elfos");

                Thread.sleep(5000);

                System.out.println("Papai Noel terminou de falar com os elfos");
            } else {
                System.out.println("Não há elfos suficiente");
            }
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public synchronized void groupUpElves(Elf elf) {
        try {
            elfGroup.add(elf);

            while(this.elfGroup.contains(elf)) {
                System.out.println(elf.name + " na fila");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeGroupOfElves() {
        elfGroup.remove(0);
        elfGroup.remove(0);
        elfGroup.remove(0);
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Papai Noel acordou");
                System.out.println("Elfos na fila: " + elfGroup.size());

                talkToElves();

                System.out.println("Papai Noel foi dormir");
                Thread.sleep(10000);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }
}
