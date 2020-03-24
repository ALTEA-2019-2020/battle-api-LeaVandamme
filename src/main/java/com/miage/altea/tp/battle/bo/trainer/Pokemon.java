package com.miage.altea.tp.battle.bo.trainer;

import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;

public class Pokemon {

    private int id;

    private int pokemonTypeId;

    private PokemonType pt;

    private int level;

    public Pokemon() {
    }

    public Pokemon(int pokemonType, int level) {
        this.pokemonTypeId = pokemonTypeId;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonType getPt() {
        return pt;
    }

    public void setPt(PokemonType pt) {
        this.pt = pt;
    }
}
