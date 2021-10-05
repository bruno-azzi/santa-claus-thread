package com.company;

import java.util.Random;
import java.util.Vector;

public class SantaClaus extends Thread {

  private static final int minElvesAmount = 3;
  private static final int minReindeerAmount = 9;

  private Random random;
  // Vector because Thread safe!
  private Vector<Elf> elfList; // Lista de elfos que precisam da ajuda do Papai Noel
  private Vector<Reindeer> reindeerList; // Lista de renas prontas para entregar presentes com o Papai Noel

  public SantaClaus() {
    random = new Random();
    elfList = new Vector<Elf>();
    reindeerList = new Vector<Reindeer>();
  }

  @Override
  public void run() {
    while (true) {
      synchronized (this) {
        while (elfList.size() < minElvesAmount && reindeerList.size() < minReindeerAmount) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

      System.out.println("Papai Noel foi acordado");

      if (reindeerList.size() >= minReindeerAmount) { // Dar presentes tem maior prioridade
        System.out.println("Papai Noel irá distribuir os presentes com as renas");
        distributeGifts();
        System.out.println("Papai Noel voltou de distribuir presentes e irá dormir");
      } else if (elfList.size() >= minElvesAmount) {
        System.out.println("Papai Noel irá ajudar os elfos");
        talkToElves();
        System.out.println("Papai Noel ajudou os 3 elfos e agora irá dormir");
        System.out.println("Lista de elfos: " + this.elfList.size() + " " + this.elfList);
      } else {
        System.out.println("Não há elfos ou renas suficientes esperando o Papai Noel então ele voltou a dormir");
      }
    }
  }

  public void addElfToWaitingList(Elf elf) {
    this.elfList.add(elf); // already synchronized (Vector)
    System.out.println("Lista de elfos: " + this.elfList.size() + " " + this.elfList);
    synchronized (this) {
      this.notify();
    }
  }

  public void addReindeerToSleigh(Reindeer reindeer) {
    this.reindeerList.add(reindeer); // already synchronized (Vector)
    System.out.println("Lista de renas: " + this.reindeerList.size() + " " + this.reindeerList);
    synchronized (this) {
      this.notify();
    }
  }

  private void talkToElves() {
    for (int i = 1; i <= minElvesAmount; i++) {
      Elf elf = elfList.get(0);

      synchronized (elf) {
        int time = random.nextInt(2001) + 1000; // 1s - 3s
        try {
          sleep(time); // Papai Noel aconselha um elfo por um tempo
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(elf.name + " recebeu conselho do Papai Noel por: " + time + "ms");
        elf.wait = false; // A discussão élfica pode continuar
        elf.notify();
      }

      elfList.remove(0); // O elfo foi aconselhado. Será removido da lista
    }
  }

  private void distributeGifts() {
    // Distribua presentes com a rena
    try {
      int pause = random.nextInt(10000) + 15000; // 15 - 25s
      sleep(pause);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // Continue os processos da rena (Dê alta às renas em suas merecidas férias)
    for (int i = 0; i < minReindeerAmount; i++) {
      Reindeer reindeer = reindeerList.get(0);

      synchronized (reindeer) {
        reindeer.wait = false;
        reindeer.notify();
      }

      reindeerList.remove(0);
    }
  }
}