package com.thedevhorse.querybyexample;

import com.thedevhorse.querybyexample.repository.Address;
import com.thedevhorse.querybyexample.repository.Athlete;
import com.thedevhorse.querybyexample.repository.AthleteGraphqlQBERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.HttpGraphQlTester;


class AthleteGraphqlQBERepositoryTests extends AbstractBaseIntegrationTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;

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

    @Test
    void whenFindAll_thenListOfAthletesIsReturned() {
        String query = """
                    query {
                        athletes {
                            age
                            name
                        }
                    }
                """;

        graphQlTester.document(query)
                .execute()
                .path("data.athletes")
                .entityList(Athlete.class)
                .hasSize(1);
    }

    @Test
    void givenValidAthleteId_whenFindById_thenReturnsAthlete() {
        Athlete savedAthlete = athleteGraphqlQBERepository.findAll().getFirst();

        String query = """
                    query($id: ID!) {
                        athlete(id: $id) {
                            age
                            name
                        }
                    }
                """;

        graphQlTester.document(query)
                .variable("id", savedAthlete.getId())
                .execute()
                .path("data.athlete")
                .entity(Athlete.class)
                .satisfies(athlete -> {
                    assert athlete.getId().equals(savedAthlete.getId());
                    assert athlete.getName().equals(savedAthlete.getName());
                });
    }
}
