package service;

import bo.battle.BattlePokemon;
import bo.pokemonType.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattlePokemonFactoryImpl implements BattlePokemonFactory {

    @Autowired
    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }

    private StatsCalculator statsCalculator;

    public BattlePokemon  createBattlePokemon(PokemonType pokemonType, int level) {
        var battlePokemon = new BattlePokemon(pokemonType,level);
        var hp = statsCalculator.calculateHp(pokemonType.getStats().getHp(),level);
        var attack = statsCalculator.calculateStat(pokemonType.getStats().getAttack(),level);
        var speed = statsCalculator.calculateStat(pokemonType.getStats().getSpeed(),level);
        var defense = statsCalculator.calculateStat(pokemonType.getStats().getDefense(),level);
        battlePokemon.setHp(hp);
        battlePokemon.setMaxHp(hp);
        battlePokemon.setAttack(attack);
        battlePokemon.setDefense(defense);
        battlePokemon.setSpeed(speed);
        battlePokemon.setKo(false);
        battlePokemon.setId(pokemonType.getId());
        return battlePokemon;
    }
}
