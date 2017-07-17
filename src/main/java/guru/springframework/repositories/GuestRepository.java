package guru.springframework.repositories;

import guru.springframework.domain.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
}
