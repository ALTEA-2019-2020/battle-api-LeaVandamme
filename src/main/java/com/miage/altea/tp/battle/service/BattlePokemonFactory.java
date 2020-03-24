package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;

public interface BattlePokemonFactory {

    BattlePokemon createBattlePokemon(PokemonType pokemonType, int level);
}
