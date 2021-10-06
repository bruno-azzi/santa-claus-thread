package com.company.classes;

import java.util.Random;

public class Reindeer extends Thread {
	private final SantaClaus santaClaus;
	public boolean vacationEnded;

	public Reindeer(String name, SantaClaus santaClaus) {
		super(name);
		this.santaClaus = santaClaus;
		this.vacationEnded = false;

		start();
	}

	public void workWithSantaClaus() {
		synchronized(this){
			while(vacationEnded) {
				try {
					wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			this.takeVacation();

			santaClaus.addReindeerToSleigh(this);
			vacationEnded = true;

			this.workWithSantaClaus();
		}
	}

	public void takeVacation() {
		Integer time = new Random().nextInt(10000) + 20000;

		try {
			Thread.sleep(time);
			Logger.log("[" + this.getName() + "]" + " acabou suas férias e está voltando");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}