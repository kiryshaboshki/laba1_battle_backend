package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Squad {
    private final String name;
    private final List<Unit> units = new ArrayList<>();

    public Squad(String name) { this.name = name; }

    public void add(Unit u)   { units.add(u); }
    public String getName()   { return name; }

    public boolean isAlive() {
        for (Unit u : units) if (u.isAlive()) return true;
        return false;
    }

    public List<Unit> getAliveUnits() {
        List<Unit> res = new ArrayList<>();
        for (Unit u : units) if (u.isAlive()) res.add(u);
        return res;
    }

    public Unit getFirstAlive() {
        for (Unit u : units) if (u.isAlive()) return u;
        return null;
    }

    public Unit getMostHurtAlive() {
        Unit best = null;
        int minHp = Integer.MAX_VALUE;
        for (Unit u : units) {
            if (u.isAlive() && u.getHp() < minHp) {
                minHp = u.getHp();
                best = u;
            }
        }
        return best;
    }

    public int totalHp() {
        int sum = 0;
        for (Unit u : units) sum += u.getHp();
        return sum;
    }

    public void printState() {
        System.out.printf("Отряд \"%s\":%n", name);
        for (Unit u : units) {
            System.out.printf("  - %s%s%n", u, u.isAlive() ? "" : "  (отмучился)");
        }
    }
}
