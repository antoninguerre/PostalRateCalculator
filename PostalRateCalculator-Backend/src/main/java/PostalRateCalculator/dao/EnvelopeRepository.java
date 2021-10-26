package PostalRateCalculator.dao;

import PostalRateCalculator.model.Envelope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvelopeRepository extends CrudRepository<Envelope, String> {
    Optional<Envelope> findEnvelopeById(Long id);
}
