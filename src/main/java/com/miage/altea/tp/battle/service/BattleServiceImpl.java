package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.Battle;
import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.battle.BattleTrainer;
import com.miage.altea.tp.battle.bo.trainer.Pokemon;
import com.miage.altea.tp.battle.bo.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleServiceImpl implements BattleService{

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private BattlePokemonFactory battlePokemonFactory;

    @Autowired
    private StatsCalculator statsCalculator;

    @Override
    public Battle createBattle(String attackerName, String opponentName) {
        Battle battle = new Battle();
        Trainer attacker = trainerService.getTrainerByName(attackerName);
        Trainer opponent= trainerService.getTrainerByName(opponentName);

        List<BattlePokemon> teamAttacker = new ArrayList<BattlePokemon>();
        List<BattlePokemon> teamOpponent = new ArrayList<BattlePokemon>();

        List<Pokemon> pokemonsAttacker = attacker.getTeam();

        for(Pokemon p : pokemonsAttacker){
            teamAttacker.add(battlePokemonFactory.createBattlePokemon(p.getPt(), p.getLevel()));
        }

        List<Pokemon> pokemonsOpponent = opponent.getTeam();
        for(Pokemon p : pokemonsOpponent){
            teamOpponent.add(battlePokemonFactory.createBattlePokemon(p.getPt(), p.getLevel()));
        }

        boolean attackerFirst = false;
        boolean opponentFirst = false;
        if(attacker.getTeam().get(0).getPt().getStats().getSpeed() >= opponent.getTeam().get(0).getPt().getStats().getSpeed()){
            attackerFirst = true;
        }
        else{
            opponentFirst = true;
        }

        battle.setTrainer(new BattleTrainer(attacker.getName(),attackerFirst,teamAttacker));
        battle.setOpponent(new BattleTrainer(opponent.getName(),opponentFirst,teamOpponent));
        battle.setUuid(UUID.randomUUID());
        return battle;
    }

    @Override
    public Battle attackAction(String trainer, Battle battle) throws Exception {
        BattleTrainer theAttacker;
        BattleTrainer theOpponent;

        if (battle.getTrainer().getName().equals(trainer)){
            theAttacker = battle.getTrainer();
            theOpponent = battle.getOpponent();
        }
        else {
            theAttacker = battle.getOpponent();
            theOpponent = battle.getTrainer();
        }
        if(!theAttacker.isNextTurn()) throw new Exception("It is not your turn");

        boolean allPokemonsKOAtt = true;
        int firstPokAttAlive = 0;
        int firstPokOppAlive = 0;
        for(int i=0; i<theAttacker.getTeam().size(); i++){
            if(!theAttacker.getTeam().get(i).isKo()){
                allPokemonsKOAtt = false;
                firstPokAttAlive = i;
                break;
            }
        }

        boolean allPokemonsKOOpp = true;
        for(int i=0; i<theOpponent.getTeam().size(); i++){
            if(!theOpponent.getTeam().get(i).isKo()){
                allPokemonsKOOpp = false;
                firstPokOppAlive = i;
                break;
            }
        }

        if(allPokemonsKOAtt || allPokemonsKOOpp){
            throw new Exception("All pokemons of a player are KO !");
        }

        BattlePokemon attackerPokemon = theAttacker.getTeam().get(firstPokAttAlive);
        BattlePokemon opponentPokemon = theOpponent.getTeam().get(firstPokOppAlive);

        int newHpOpponent = opponentPokemon.getHp() - statsCalculator.calculateDegat(attackerPokemon,opponentPokemon);
        if(newHpOpponent <= 0){
            newHpOpponent = 0;
        }

        opponentPokemon.setHp(newHpOpponent);

        if(newHpOpponent == 0){
            opponentPokemon.setKo(true);
        }

        theOpponent.setNextTurn(true);
        theAttacker.setNextTurn(false);
        return battle;
    }

    public void setBattlePokemonFactory(BattlePokemonFactory battlePokemonFactory) {
        this.battlePokemonFactory = battlePokemonFactory;
    }

    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }

    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
