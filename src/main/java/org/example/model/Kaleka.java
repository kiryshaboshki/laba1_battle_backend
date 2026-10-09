package org.example.model;

import java.util.random.RandomGenerator;

public class Kaleka extends Unit {
    private int healsLeft = 3;

    public Kaleka(String name, RandomGenerator rnd) {
        super(name, "Калека", 50, 70, 5, 9, rnd);
    }

    @Override
    public void takeTurn(Squad own, Squad enemy) {
        Unit mostHurt = own.getMostHurtAlive();
        if (healsLeft > 0 && mostHurt != null && mostHurt.getHp() < mostHurt.getMaxHp()) {
            healsLeft--;
            mostHurt.heal(25);
            System.out.printf("  %s ковыляет к %s и просит денег (+25 HP, осталось: %d). %s -> %d HP%n",
                    name, mostHurt.getName(), healsLeft,
                    mostHurt.getName(), mostHurt.getHp());
            return;
        }
        Unit target = enemy.getFirstAlive();
        if (target == null) return;
        int dmg = rollDamage();
        target.takeDamage(dmg);
        System.out.printf("  %s бьёт костылём и давит на мораль %s на %d урона. %s -> %d HP%n",
                name, target.getName(), dmg, target.getName(), target.getHp());
    }
}
