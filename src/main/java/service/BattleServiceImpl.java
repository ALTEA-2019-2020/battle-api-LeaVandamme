package service;

import bo.battle.Battle;
import bo.battle.BattlePokemon;
import bo.battle.BattleTrainer;
import bo.pokemonType.PokemonType;
import bo.trainer.Pokemon;
import bo.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {

    private TrainerService trainerService;
    private BattlePokemonFactory battlePokemonFactory;

    @Override
    public Battle createBattle(String attackerName, String opponentName) {
        Battle battle = new Battle();
        Trainer attacker = trainerService.getTrainerByName(attackerName);
        Trainer opponent= trainerService.getTrainerByName(opponentName);

        List<BattlePokemon> teamAttacker = new ArrayList<BattlePokemon>();
        List<BattlePokemon> teamOpponent = new ArrayList<BattlePokemon>();

        List<Pokemon> pokemonsAttacker = attacker.getTeam();
        for(Pokemon p : pokemonsAttacker){
            teamAttacker.add(battlePokemonFactory.createBattlePokemon(p.getType(), p.getLevel()));
        }

        List<Pokemon> pokemonsOpponent = opponent.getTeam();
        for(Pokemon p : pokemonsOpponent){
            teamOpponent.add(battlePokemonFactory.createBattlePokemon(p.getType(), p.getLevel()));
        }

        battle.setTrainer(new BattleTrainer(attacker.getName(),true,teamAttacker));
        battle.setOpponent(new BattleTrainer(opponent.getName(),false,teamOpponent));
        battle.setUuid(UUID.randomUUID());
        return battle;
    }

    @Override
    public Battle attackAction(String trainer, Battle battle) throws Exception {
        /*BattleTrainer theAttacker;
        BattleTrainer theOpponent;
        if (battle.getTrainer().getName().equals(trainer)){
            theAttacker = battle.getTrainer();
            theOpponent = battle.getOpponent();
        }
        else {
            theAttacker = battle.getOpponent();
            theOpponent = battle.getTrainer();
        }
        if(!theAttacker.isNextTurn()) throw new Exception("Not your turn!");

        var attackerPokemonOpt = attacker.getTeam().stream().filter(BattlePokemon::isAlive).findFirst();
        var opponentPokemonOpt = opponent.getTeam().stream().filter(BattlePokemon::isAlive).findFirst();
        if(!attackerPokemonOpt.isPresent()  || !opponentPokemonOpt.isPresent()) throw new Exception("One trainer hasn't pokemon");
        var attackPokemon = attackerPokemonOpt.get();
        var opponentPokemon = opponentPokemonOpt.get();
        var newHpOpponent = opponentPokemon.getHp() - statsCalculator.calculateDamage(attackPokemon,opponentPokemon);
        newHpOpponent = (newHpOpponent <= 0) ? 0 : newHpOpponent;
        opponentPokemon.setHp(newHpOpponent);
        opponentPokemon.setKo(newHpOpponent == 0);
        opponentPokemon.setAlive(newHpOpponent > 0);
        opponent.setNextTurn(true);
        attacker.setNextTurn(false);
        return battle;*/
        return null;
    }
}
