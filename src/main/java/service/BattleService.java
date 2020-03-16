package service;

import bo.battle.Battle;

public interface BattleService {
    Battle createBattle(String attackerName, String opponentName);
    Battle attackAction(String attacker, Battle battle) throws Exception;
}
