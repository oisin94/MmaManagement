package com.mmamanagement.service.impl;

import com.mmamanagement.entity.Trainer;
import com.mmamanagement.repository.TrainerRepository;
import com.mmamanagement.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<Trainer> findAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers;
    }
}
