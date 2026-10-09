package org.example.model;

import java.util.List;
import java.util.random.RandomGenerator;

public class Bomj extends Unit {
    private int findsLeft = 2;

    private static final String[] FINDS = {
            "нашёл ржавый лом",
            "выкопал булыжник",
            "сходил под себя",
            "достал помои"
    };

    public Bomj(String name, RandomGenerator rnd) {
        super(name, "Бомж", 90, 150, 8, 14, rnd);
    }

    @Override
    public void takeTurn(Squad own, Squad enemy) {
        if (findsLeft > 0) {
            findsLeft--;
            String find = FINDS[rnd.nextInt(FINDS.length)];
            System.out.printf("  %s %s и швыряет в двоих! (осталось находок: %d)%n",
                    name, find, findsLeft);
            List<Unit> targets = enemy.getAliveUnits();
            int hits = Math.min(2, targets.size());
            for (int i = 0; i < hits; i++) {
                Unit t = targets.get(i);
                t.takeDamage(25);
                System.out.printf("    %s получает 25 урона -> %d HP%n",
                        t.getName(), t.getHp());
            }
            return;
        }
        Unit target = enemy.getFirstAlive();
        if (target == null) return;
        int dmg = rollDamage();
        target.takeDamage(dmg);
        System.out.printf("  %s бьёт %s палкой на %d урона. %s -> %d HP%n",
                name, target.getName(), dmg, target.getName(), target.getHp());
    }
}
