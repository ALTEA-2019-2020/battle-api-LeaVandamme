package service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import com.miage.altea.tp.battle.bo.pokemonType.Stats;
import com.miage.altea.tp.battle.bo.trainer.Pokemon;
import com.miage.altea.tp.battle.service.PokemonTypeService;
import com.miage.altea.tp.battle.service.PokemonTypeServiceImpl;
import com.miage.altea.tp.battle.service.StatsCalculatorImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StatsCalculatorTest {

    @Test
    void calculateHpPikachuLevel18_shouldReturn40(){

        StatsCalculatorImpl statsCalculator = new StatsCalculatorImpl();
        int result = statsCalculator.calculateHp(35,18);
        assertEquals(40, result);
    }

    @Test
    void calculateHpStarossLevel21_shouldReturn56(){

        StatsCalculatorImpl statsCalculator = new StatsCalculatorImpl();
        int result = statsCalculator.calculateHp(60,21);
        assertEquals(56, result);
    }

    @Test
    void calculateStatsAttackPikachuLevel18_shouldReturn40(){

        StatsCalculatorImpl statsCalculator = new StatsCalculatorImpl();
        int result = statsCalculator.calculateStat(55,18);
        assertEquals(24, result);
    }

    @Test
    void calculateStatsDefenseStarossLevel21_shouldReturn40(){

        StatsCalculatorImpl statsCalculator = new StatsCalculatorImpl();
        int result = statsCalculator.calculateStat(85,21);
        assertEquals(40, result);
    }

    @Test
    void calculateDegatsPikachuStari_shouldReturn11(){

        StatsCalculatorImpl statsCalculator = new StatsCalculatorImpl();
        PokemonTypeService pokemonTypeService = new PokemonTypeServiceImpl();
        PokemonType ptPikachu = pokemonTypeService.getPokemonType(25);
        PokemonType ptMisty = pokemonTypeService.getPokemonType(120);

        BattlePokemon pikachu = new BattlePokemon(ptPikachu, 18);
        BattlePokemon misty = new BattlePokemon(ptMisty, 18);

        int result = statsCalculator.calculateDegat(pikachu,misty);
        assertEquals(11, result);
    }
}
