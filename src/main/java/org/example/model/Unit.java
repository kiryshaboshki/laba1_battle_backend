package org.example.model;

import java.util.random.RandomGenerator;

public abstract class Unit {
    protected final String name;
    protected final String type;
    protected final int maxHp;
    protected final int minDmg;
    protected final int maxDmg;
    protected final RandomGenerator rnd;
    protected int hp;

    protected Unit(String name, String type, int hp, int maxHp, int minDmg, int maxDmg, RandomGenerator rnd) {

        this.name = name;
        this.hp = hp;
        this.maxDmg = maxDmg;
        this.type = type;
        this.maxHp = maxHp;
        this.minDmg = minDmg;
        this.rnd = rnd;



    }


    public boolean isAlive() {return hp > 0;}
    public String getName() {return name;}
    public int getHp(){return hp;}
    public int getMaxHp(){return maxHp;}

    public void takeDamage(int dmg) {hp = Math.max(0,hp - dmg);}
    protected void heal(int amount) {hp = Math.min(maxHp, hp + amount);}
    protected int rollDamage() {return rnd.nextInt(minDmg, maxDmg + 1);}

    public abstract void takeTurn(Squad own, Squad enemy);

    @Override

    public String toString() {
        return "%s [%s] %d/%d HP".formatted(name, type, hp, maxHp);
    }




}
