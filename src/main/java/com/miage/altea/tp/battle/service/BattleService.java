package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.Battle;

public interface BattleService {
    Battle createBattle(String attackerName, String opponentName);
    Battle attackAction(String attacker, Battle battle) throws Exception;
    void setBattlePokemonFactory(BattlePokemonFactory battlePokemonFactory);
    void setStatsCalculator(StatsCalculator statsCalculator);
    void setTrainerService(TrainerService trainerService);
}
