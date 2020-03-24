package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;

public interface StatsCalculator {
    int calculateHp(int base,int level);
    int calculateStat(int base,int level);
    int calculateDegat(BattlePokemon attacker, BattlePokemon opponent);
}
