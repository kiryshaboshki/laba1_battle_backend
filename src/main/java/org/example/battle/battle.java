package org.example.battle;

import org.example.model.Squad;   // ← было com.example.model
import org.example.model.Unit;   // ← было com.example.model

import java.util.ArrayList;
import java.util.List;

public class battle {
    private static final int MAX_ROUNDS = 30;

    private final Squad s1;
    private final Squad s2;

    public battle(Squad s1, Squad s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void run() {
        System.out.println("В БОЙ");
        s1.printState();
        s2.printState();
        System.out.println("_________________________________________________\n");

        int round = 1;
        while (round <= MAX_ROUNDS && s1.isAlive() && s2.isAlive()) {
            System.out.printf("Раунд %d%n", round);
            performTurn(s1, s2);
            if (s2.isAlive()) performTurn(s2, s1);

            System.out.println("Состояние после раунда:");
            s1.printState();
            s2.printState();
            System.out.println();
            round++;
        }

        declareResult(round - 1);
    }

    private void performTurn(Squad actor, Squad target) {
        if (!actor.isAlive()) return;
        System.out.printf("Ход отряда \"%s\":%n", actor.getName());
        List<Unit> actors = new ArrayList<>(actor.getAliveUnits());
        for (Unit u : actors) {
            if (!u.isAlive()) continue;
            if (!target.isAlive()) break;
            u.takeTurn(actor, target);
        }
    }

    private void declareResult(int roundsPlayed) {
        System.out.println("РЕЗУЛЬТАТ");
        boolean a1 = s1.isAlive();
        boolean a2 = s2.isAlive();

        if (a1 && !a2) {
            System.out.printf("Отряд \"%s\" выстоял! Победа за %d раундов.%n",
                    s1.getName(), roundsPlayed);
        } else if (!a1 && a2) {
            System.out.printf("Отряд \"%s\" выстоял! Победа за %d раундов.%n",
                    s2.getName(), roundsPlayed);
        } else if (!a1 && !a2) {
            System.out.printf("Все полегли за %d раундов — НИЧЬЯ!%n", roundsPlayed);
        } else {
            int hp1 = s1.totalHp();
            int hp2 = s2.totalHp();
            System.out.printf("Лимит раундов (%d) исчерпан. Суммарное HP:%n", MAX_ROUNDS);
            System.out.printf("  \"%s\": %d HP%n", s1.getName(), hp1);
            System.out.printf("  \"%s\": %d HP%n", s2.getName(), hp2);
            if (hp1 > hp2) System.out.printf("По очкам побеждает \"%s\".%n", s1.getName());
            else if (hp2 > hp1) System.out.printf("По очкам побеждает \"%s\".%n", s2.getName());
            else System.out.println("Равенство по HP — НИЧЬЯ (бой без победителя).");
        }
    }
}