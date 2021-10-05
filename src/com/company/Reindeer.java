package com.company;

import java.util.Random;

public class Reindeer extends Thread {

	private String name;
	private SantaClaus santaClaus;
	private Random random;
	public boolean wait;

	public Reindeer(String name, SantaClaus santaClaus) {
		this.name = name;
		this.santaClaus = santaClaus;
		random = new Random();
		wait = false;
	}

	@Override
	public void run() {
		while (true) {
			int time = random.nextInt(5000) + 10000; // 10s - 15s
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(this.name + " voltou das suas férias após: " + time + "ms" );
			// Quando uma rena volta de férias, ela espera para ser usada
			// -> "wait ()" é chamado neste tópico
			santaClaus.addReindeerToSleigh(this);
			wait = true;

			synchronized(this){
				while(wait) {
					try {
						wait();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println(this.name + " irá tirar férias até o próximo natal");
			takeVacation();
		}
	}

	public void takeVacation() {
		int time = random.nextInt(10000) + 20000; // 20s - 30s
		try {
			Thread.sleep(time);
			System.out.println(this.name + " acabou suas férias de: " + time + "ms e está voltando");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}