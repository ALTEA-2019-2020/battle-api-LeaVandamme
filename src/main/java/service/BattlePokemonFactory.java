package service;

import bo.battle.BattlePokemon;
import bo.pokemonType.PokemonType;

public interface BattlePokemonFactory {

    BattlePokemon createBattlePokemon(PokemonType pokemonType, int level);
}
