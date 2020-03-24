package com.miage.altea.tp.battle.service;

import com.miage.altea.tp.battle.bo.trainer.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> listTrainer();
    Trainer getTrainerByName(String name);

}
