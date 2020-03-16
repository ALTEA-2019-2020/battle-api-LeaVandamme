package service;

import bo.trainer.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> listTrainer();

    Trainer getTrainerByName(String name);

}
