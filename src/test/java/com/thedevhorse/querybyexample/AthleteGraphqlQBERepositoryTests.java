package com.thedevhorse.querybyexample;

import com.thedevhorse.querybyexample.repository.Address;
import com.thedevhorse.querybyexample.repository.Athlete;
import com.thedevhorse.querybyexample.repository.AthleteGraphqlQBERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.Map;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
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
    void givenName_whenFindByName_thenAthleteIsFound() {
        String document = """
                    query($athleteRequest: AthleteRequest!) {
                        athletes(athlete: $athleteRequest) {
                            id
                            age
                            name
                        }
                    }
                """;

        graphQlTester.document(document)
                .variable("athleteRequest", Map.of("name", "Jake Thompson"))
                .execute()
                .path("data.athletes")
                .entityList(Athlete.class)
                .hasSize(1)
                .satisfies(athletes -> {
                            assert athletes.getFirst().getName().equals("Jake Thompson");
                        }
                );
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
                    assert athlete.getAge().equals(savedAthlete.getAge());
                    assert athlete.getName().equals(savedAthlete.getName());
                });
    }
}
