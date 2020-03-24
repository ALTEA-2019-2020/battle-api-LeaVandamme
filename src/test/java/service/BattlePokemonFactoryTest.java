package service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import com.miage.altea.tp.battle.bo.pokemonType.Stats;
import com.miage.altea.tp.battle.service.BattlePokemonFactory;
import com.miage.altea.tp.battle.service.BattlePokemonFactoryImpl;
import com.miage.altea.tp.battle.service.StatsCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BattlePokemonFactoryTest {

    @Test
    void createBattle_shouldReturnBattlePokemon(){
        PokemonType pokemonType = Mockito.mock(PokemonType.class);
        Stats stats = new Stats();
        stats.setDefense(70);
        stats.setAttack(50);
        stats.setHp(80);
        pokemonType.setStats(stats);

        BattlePokemonFactory battlePokemonFactory = new BattlePokemonFactoryImpl();
        Mockito.when(pokemonType.getStats().getAttack()).thenReturn(50);
        Mockito.when(pokemonType.getStats().getHp()).thenReturn(80);
        Mockito.when(pokemonType.getStats().getDefense()).thenReturn(70);

        BattlePokemon res = battlePokemonFactory.createBattlePokemon(pokemonType, 50);
        assertEquals(80, res.getHp());
        assertEquals(50, res.getAttack());
        assertEquals(70, res.getDefense());

    }
}
