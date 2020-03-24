package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactoryImpl implements BattlePokemonFactory {

    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }

    @Autowired
    private StatsCalculator statsCalculator;

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level) {
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
