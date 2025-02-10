package com.thedevhorse.querybyexample;

import com.thedevhorse.querybyexample.repository.AthleteQBERepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

class AthleteQBERepositoryTests extends AbstractBaseIntegrationTest {


    @Autowired
    private AthleteQBERepository athleteQBERepository;

    @BeforeEach
    void setUp() {

    }
}
