package com.miage.altea.tp.battle.service;
import com.miage.altea.tp.battle.bo.trainer.Pokemon;
import com.miage.altea.tp.battle.bo.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;
    private PokemonTypeService pokemonTypeService;


    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    public List<Trainer> listTrainer() {
        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl+"trainers/", Trainer[].class));
    }

    @Override
    public Trainer getTrainerByName(String name) {
        Trainer t = restTemplate.getForObject(pokemonServiceUrl+"/trainers/"+name+"/", Trainer.class);
        for(Pokemon pok : t.getTeam()){
            pok.setPt(pokemonTypeService.getPokemonType(pok.getPokemonTypeId()));
        }
        return t;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}

