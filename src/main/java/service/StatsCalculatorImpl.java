package service;

import org.springframework.stereotype.Service;

@Service
public class StatsCalculatorImpl implements StatsCalculator {

    @Override
    public int calculateHp(int base, int level) {

        int hp = 10 + level + (Math.round(base * (level/50)));
        return hp;
    }

    @Override
    public int calculateStat(int base, int level) {

        int stat = 5 + (Math.round(base * (level / 50)));
        return stat;
    }

    @Override
    public int calculateDegat(int level, int attack, int defense) {
        int degat = (Math.round((2*level)/5) + 2 * Math.round(attack / defense) + 2);
        return degat;
    }


}
