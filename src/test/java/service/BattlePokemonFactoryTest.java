package service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import com.miage.altea.tp.battle.bo.pokemonType.Stats;
import com.miage.altea.tp.battle.service.BattlePokemonFactory;
import com.miage.altea.tp.battle.service.BattlePokemonFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattlePokemonFactoryTest {

    @Test
    void createBattle_shouldReturnBattlePokemon(){
        PokemonType pokemonType = new PokemonType();
        Stats stats = new Stats();
        stats.setDefense(40);
        stats.setAttack(55);
        stats.setSpeed(90);
        stats.setHp(35);
        pokemonType.setStats(stats);

        BattlePokemonFactory battlePokemonFactory = new BattlePokemonFactoryImpl();

        BattlePokemon res = battlePokemonFactory.createBattlePokemon(pokemonType, 18);
        assertEquals(40, res.getHp());
        assertEquals(24, res.getAttack());
        assertEquals(19, res.getDefense());

    }
}
