package com.company;

import com.company.classes.Elf;
import com.company.classes.Reindeer;
import com.company.classes.SantaClaus;

import java.util.concurrent.atomic.AtomicInteger;

public class SantaClausApplication {
	public static void main(String[] args) {
		AtomicInteger idElf = new AtomicInteger();
		AtomicInteger idReindeer = new AtomicInteger();

		SantaClaus santaClaus = new SantaClaus();

		for(int i = 1; i <= 10; i++) {
			new Elf("Elfo #" + idElf.getAndIncrement(), santaClaus);
		}

		for(int i = 1; i <= 9; i++) {
			new Reindeer("Rena #" + idReindeer.getAndIncrement(), santaClaus);
		}
	}
}