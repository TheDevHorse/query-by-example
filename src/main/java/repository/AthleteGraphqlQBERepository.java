package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface AthleteGraphqlQBERepository extends MongoRepository<Athlete, String>,
        QueryByExampleExecutor<Athlete> {
}
