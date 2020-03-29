package service;

import com.miage.altea.tp.battle.bo.battle.Battle;
import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.battle.BattleTrainer;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import com.miage.altea.tp.battle.bo.pokemonType.Stats;
import com.miage.altea.tp.battle.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BattleServiceTest {

    @Test
    void createBattle_shouldReturnABattle(){

        BattleService battleService = new BattleServiceImpl();

        Battle res = battleService.createBattle("Ash", "Misty");

        assertEquals(res.getTrainer(), "Ash");
        assertEquals(res.getOpponent(), "Misty");
    }

    @Test
    void attackAction_shouldReturnAnExceptionNotYourTurn() throws Exception {
        BattleService battleService = new BattleServiceImpl();

        BattleTrainer ash = new BattleTrainer("ash", true,  new ArrayList<BattlePokemon>());
        BattleTrainer misty = new BattleTrainer("misty", false, new ArrayList<BattlePokemon>());

        Battle battle = new Battle();
        battle.setTrainer(ash);
        battle.setOpponent(misty);

        Assertions.assertThrows(Exception.class, () -> {
            battleService.attackAction("misty", battle);
        });
    }

    @Test
    void attackAction_shouldReturnABattle() throws Exception {
        BattleService battleService = new BattleServiceImpl();

        BattleTrainer ash = new BattleTrainer("ash", true, new ArrayList<BattlePokemon>());
        BattleTrainer misty = new BattleTrainer("misty", false,  new ArrayList<BattlePokemon>());

        PokemonType ptPikachu = new PokemonType();
        ptPikachu.setName("pikachu");
        Stats statsPikachu = new Stats();
        statsPikachu.setAttack(55);
        statsPikachu.setDefense(40);
        statsPikachu.setSpeed(90);
        statsPikachu.setHp(35);
        ptPikachu.setStats(statsPikachu);
        BattlePokemon pikachu = new BattlePokemon(ptPikachu, 18);
        pikachu.setKo(true);
        ash.getTeam().add(pikachu);

        PokemonType ptStari = new PokemonType();
        ptPikachu.setName("stari");
        Stats statsStari = new Stats();
        statsStari.setAttack(45);
        statsStari.setDefense(55);
        statsStari.setSpeed(85);
        statsStari.setHp(30);
        ptStari.setStats(statsStari);
        BattlePokemon stari = new BattlePokemon(ptStari, 18);
        misty.getTeam().add(stari);

        Battle battle = new Battle();
        battle.setTrainer(ash);
        battle.setOpponent(misty);

        Assertions.assertThrows(Exception.class, () -> {
            battleService.attackAction("ash", battle);
        });
    }

    @Test
    void attackAction_shouldReturnAnExceptionNotAnyAlivePokemon() throws Exception {
        BattleService battleService = new BattleServiceImpl();

        BattleTrainer ash = new BattleTrainer("ash", true, new ArrayList<BattlePokemon>());
        BattleTrainer misty = new BattleTrainer("misty", false,  new ArrayList<BattlePokemon>());

        PokemonType ptPikachu = new PokemonType();
        ptPikachu.setName("pikachu");
        Stats statsPikachu = new Stats();
        statsPikachu.setAttack(55);
        statsPikachu.setDefense(40);
        statsPikachu.setSpeed(90);
        statsPikachu.setHp(35);
        ptPikachu.setStats(statsPikachu);
        BattlePokemon pikachu = new BattlePokemon(ptPikachu, 18);
        ash.getTeam().add(pikachu);

        PokemonType ptStari = new PokemonType();
        ptPikachu.setName("stari");
        Stats statsStari = new Stats();
        statsStari.setAttack(45);
        statsStari.setDefense(55);
        statsStari.setSpeed(85);
        statsStari.setHp(30);
        ptStari.setStats(statsStari);
        BattlePokemon stari = new BattlePokemon(ptStari, 18);
        misty.getTeam().add(stari);

        Battle battle = new Battle();
        battle.setTrainer(ash);
        battle.setOpponent(misty);

        battleService.attackAction("ash", battle);
        assertEquals(27, battle.getOpponent().getTeam().get(0).getHp());
    }
}
