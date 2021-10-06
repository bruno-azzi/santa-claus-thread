package com.company.classes;

import java.util.ArrayList;
import java.util.Random;

public class SantaClaus extends Thread {
  private final ArrayList<Elf> elfList;
  private final ArrayList<Reindeer> reindeerList;
  private static final Integer minElvesAmount = 3;
  private static final Integer minReindeerAmount = 9;

  public SantaClaus() {
    elfList = new ArrayList<Elf>();
    reindeerList = new ArrayList<Reindeer>();

    start();
  }

  public boolean isElfListBiggerOrEqualThanMin() {
    return elfList.size() >= minElvesAmount;
  }

  public boolean isReindeerListBiggerOrEqualThanMin() {
    return reindeerList.size() >= minReindeerAmount;
  }

  public void checkIfHaveElvesOrReindeerWaiting() {
    synchronized (this) {
      while (!isElfListBiggerOrEqualThanMin() && !isReindeerListBiggerOrEqualThanMin()) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void santaClausChooseWhatToDo() {
    if (isReindeerListBiggerOrEqualThanMin()) {
      Logger.log("[Papai Noel] irá distribuir os presentes com as renas");
      distributeGifts();
      Logger.log("[Papai Noel] voltou de distribuir presentes e irá dormir");
    } else if (isElfListBiggerOrEqualThanMin()) {
      Logger.log("[Papai Noel] irá ajudar os elfos");
      talkToElves();
      Logger.log("[Papai Noel] ajudou os 3 elfos e agora irá dormir");
    } else {
      Logger.log("Não há elfos ou renas suficientes esperando o [Papai Noel] então ele voltou a dormir");
    }
  }

  public void addElfToWaitingList(Elf elf) {
    this.elfList.add(elf);

    synchronized (this) {
      this.notify();
    }
  }

  public void addReindeerToSleigh(Reindeer reindeer) {
    this.reindeerList.add(reindeer);

    synchronized (this) {
      this.notify();
    }
  }

  private void talkToElves() {
    for (int i = 1; i <= minElvesAmount; i++) {
      Elf elf = elfList.get(0);

      synchronized (elf) {
        Integer time = new Random().nextInt(2001) + 1000;

        try {
          Thread.sleep(time);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        Logger.log("[" + elf.getName() + "]" + " recebeu conselhos do [Papai Noel]");
        elf.needToTalkWithSantaClaus = false;
        elf.notify();
      }

      elfList.remove(0);
    }
  }

  private void distributeGifts() {
    try {
      Integer pause = new Random().nextInt(10000) + 15000;

      Thread.sleep(pause);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < minReindeerAmount; i++) {
      synchronized (reindeerList.get(0)) {
        reindeerList.get(0).vacationEnded = false;
        reindeerList.get(0).notify();
      }

      reindeerList.remove(0);
    }
  }

  @Override
  public void run() {
    while (true) {
      this.checkIfHaveElvesOrReindeerWaiting();
      Logger.log("[Papai Noel] foi acordado");
      this.santaClausChooseWhatToDo();
    }
  }
}