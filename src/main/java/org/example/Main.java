package org.example;

import org.example.battle.battle;
import org.example.model.Alkash;
import org.example.model.Bomj;
import org.example.model.Kaleka;
import org.example.model.Squad;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random(42);   // seed фиксирован — воспроизводимость

        Squad dvornya = new Squad("Team Spirit");
        dvornya.add(new Alkash("жора боров", rnd));
        dvornya.add(new Bomj("олег перфоратор", rnd));
        dvornya.add(new Kaleka("саня армлет", rnd));

        Squad botva = new Squad("Team Stariy_bog");
        botva.add(new Alkash("ЛЁХА", rnd));
        botva.add(new Bomj("василий дезолятор", rnd));
        botva.add(new Kaleka("макс даст", rnd));

        new battle(dvornya, botva).run();
    }
}