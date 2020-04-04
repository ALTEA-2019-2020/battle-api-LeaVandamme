package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.trainer.Trainer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface TrainerService {
    List<Trainer> listTrainer();
    Trainer getTrainerByName(String name);
    void setRestTemplate(RestTemplate restTemplate);
    void setTrainerServiceUrl(String pokemonServiceUrl);
}
