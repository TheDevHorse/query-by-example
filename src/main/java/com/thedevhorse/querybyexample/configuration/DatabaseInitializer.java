package com.thedevhorse.querybyexample.configuration;

import com.thedevhorse.querybyexample.repository.Address;
import com.thedevhorse.querybyexample.repository.Athlete;
import com.thedevhorse.querybyexample.repository.AthleteQBERepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final AthleteQBERepository athleteQBERepository;

    public DatabaseInitializer(AthleteQBERepository athleteQBERepository) {
        this.athleteQBERepository = athleteQBERepository;
    }

    @Override
    public void run(String... args) {
        Address address = new Address(
                "Lisboa",
                "Rua das Flores",
                "1000-001"
        );

        Athlete athlete = new Athlete(
                "SOC-001",
                25,
                "Cristiano Ronaldo",
                address
        );

        athleteQBERepository.save(athlete);
    }
}
