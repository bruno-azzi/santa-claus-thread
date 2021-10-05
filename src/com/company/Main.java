package com.company;

public class Main{
	public static void main(String[] args){
		// SantaClaus
		SantaClaus santaClaus = new SantaClaus();
		santaClaus.start();
		
		// Elf
		for(int i = 1; i <= 10; i++){
			Elf elf = new Elf("Elfo #" + i, santaClaus);
			elf.start();
		}
		
		// Reindeer
		for(int i = 1; i <= 9; i++){
			Reindeer reindeer = new Reindeer("Rena #" + i, santaClaus);
			reindeer.start();
		}
	}
}