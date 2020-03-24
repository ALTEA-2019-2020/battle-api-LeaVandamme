package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.Battle;
import com.miage.altea.tp.battle.service.BattleService;
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

        battle.setTrainer(new BattleTrainer(attacker.getName(),true,teamAttacker));
        battle.setOpponent(new BattleTrainer(opponent.getName(),false,teamOpponent));
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
        if(!theAttacker.isNextTurn()) throw new Exception("It is not your turn !");

        for(int i=0; i<theAttacker.getTeam().size(); i++){
            if(theAttacker.getTeam().get(i).isKo()){
                throw new Exception("Attacker does not have any alive Pokemon ! ");
            }
        }

        for(int i=0; i<theOpponent.getTeam().size(); i++){
            if(theOpponent.getTeam().get(i).isKo()){
                throw new Exception("Opponent does not have any alive Pokemon ! ");
            }
        }

        BattlePokemon attackerPokemon = theAttacker.getTeam().get(0);
        BattlePokemon opponentPokemon = theOpponent.getTeam().get(0);

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
}
