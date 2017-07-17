package guru.springframework.repositories;

import guru.springframework.domain.Guest;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Guest, Integer> {
}
