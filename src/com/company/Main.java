package com.company;

import java.util.Random;

public class Main {
    public static void createElves(int qty, SantaClaus santaClaus) {
        for (int i = 1; i <= qty; i++) {
            Elf elf = new Elf("Elf #" + i, santaClaus);
            elf.start();
        }
    }

    public static void main(String[] args) {
        int maxElves = 10;
        int maxReindeer = 9;

        SantaClaus santaClaus = new SantaClaus();
        santaClaus.start();
        createElves(maxElves, santaClaus);
    }
}
