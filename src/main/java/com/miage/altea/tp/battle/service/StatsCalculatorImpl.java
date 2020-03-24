package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
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
    public int calculateDegat(BattlePokemon att, BattlePokemon opp) {
        int statsAttackerAttack = calculateStat(att.getType().getStats().getAttack(), att.getLevel());
        int statsOppDefense = calculateStat(opp.getType().getStats().getDefense(), opp.getLevel());

        int degat = (Math.round((2*att.getLevel())/5) + 2 * Math.round(statsAttackerAttack / statsOppDefense) + 2);
        return degat;
    }



}
