package pl.secrets.secrets.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.secrets.secrets.models.Secret;

@Repository
public interface SecretsRepository extends MongoRepository<Secret, String> {
}
