package com.thedevhorse.querybyexample;

import com.thedevhorse.querybyexample.repository.Address;
import com.thedevhorse.querybyexample.repository.Athlete;
import com.thedevhorse.querybyexample.repository.AthleteQBERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AthleteQBERepositoryTests extends AbstractBaseIntegrationTest {

    @Autowired
    private AthleteQBERepository athleteQBERepository;

    @BeforeEach
    void setUp() {
        athleteQBERepository.deleteAll();

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
        athleteQBERepository.save(athlete);
    }

    @Test
    void givenValidAthleteId_whenFindById_thenReturnsAthlete() {
        // Given
        Example<Athlete> exampleAthlete = Example.of(
                new Athlete(
                        "INT-999",
                        null,
                        null,
                        null
                )
        );

        // When
        Optional<Athlete> actual = athleteQBERepository.findOne(exampleAthlete);

        // Then
        assertTrue(actual.isPresent());
    }
}
