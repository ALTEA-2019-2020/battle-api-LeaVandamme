package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/", PokemonType[].class));
    }

    @Override
    public PokemonType getPokemonType(int id) {
        var pokemon = restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/{id}",PokemonType.class,id);
        return pokemon;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}

