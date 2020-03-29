package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactoryImpl implements BattlePokemonFactory {

    /*StatsCalculator statsCalculator;
    @Autowired
    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }*/

    StatsCalculator statsCalculator = new StatsCalculatorImpl();

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level) {
        BattlePokemon battlePokemon = new BattlePokemon(pokemonType,level);
        int hp = statsCalculator.calculateHp(pokemonType.getStats().getHp(),level);
        int attack = statsCalculator.calculateStat(pokemonType.getStats().getAttack(),level);
        int speed = statsCalculator.calculateStat(pokemonType.getStats().getSpeed(),level);
        int defense = statsCalculator.calculateStat(pokemonType.getStats().getDefense(),level);
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
