package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;

import java.util.List;

public interface PokemonTypeService {

    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonType(int id);


}
