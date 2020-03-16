package service;

public interface StatsCalculator {
    int calculateHp(int base,int level);
    int calculateStat(int base,int level);
    int calculateDegat(int level,  int attack, int defense);
}
