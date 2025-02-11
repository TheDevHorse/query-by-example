package com.thedevhorse.querybyexample;

import com.thedevhorse.querybyexample.repository.Address;
import com.thedevhorse.querybyexample.repository.Athlete;
import com.thedevhorse.querybyexample.repository.AthleteGraphqlQBERepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

class AthleteQBERepositoryTests extends AbstractBaseIntegrationTest {

    @Autowired
    private AthleteGraphqlQBERepository athleteGraphqlQBERepository;

    @BeforeEach
    void setUp() {
        athleteGraphqlQBERepository.deleteAll();

        Address address = new Address(
                "Nova Iorque",
                "5th Avenue",
                "10001"
        );

        Athlete athlete = new Athlete(
                "INT-999",
                28,
                "Jake Thompson",
                address
        );
        athleteGraphqlQBERepository.save(athlete);
    }
}
