package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AthleteQBERepository extends MongoRepository<Athlete, Long>,
        QueryByExampleExecutor<Athlete> {
}
