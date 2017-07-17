package guru.springframework.services;

import guru.springframework.domain.Guest;

public interface CustomerService {

    Iterable<Guest> listAllGuests();

    Guest getGuestById(Integer id);

    Guest saveGuest(Guest guest);

    void deleteGuest(Guest guest);
}
