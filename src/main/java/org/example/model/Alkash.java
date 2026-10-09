package org.example.model;

import java.util.random.RandomGenerator;

public class Alkash extends Unit {
    private boolean bingeUsed = false;

    public Alkash(String name, RandomGenerator rnd) {
        super(name, "Алкаш", 110,200, 10, 16, rnd);
    }

    @Override
    public void takeTurn(Squad own, Squad enemy) {
        Unit target = enemy.getFirstAlive();
        if (target == null) return;

        int dmg = rollDamage();
        boolean binge = false;
        if (!bingeUsed && hp < maxHp / 2) {
            dmg *= 2;
            bingeUsed = true;
            binge = true;
        }
        target.takeDamage(dmg);
        System.out.printf("  %s атакует %s на %d урона%s. %s -> %d HP%n",
                name, target.getName(), dmg,
                binge ? " (ЗАПОЙ! Глаза красные, кулаки тяжёлые, СТРАХА НЕТ, БОЛИ ТОЖЕ)" : "",
                target.getName(), target.getHp());
    }
}
