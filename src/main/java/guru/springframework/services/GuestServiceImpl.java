package guru.springframework.services;

import guru.springframework.domain.Guest;
import guru.springframework.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
    private GuestRepository guestRepository;

    @Autowired
    public void setGuestRepository(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    @Override
    public Iterable<Guest> listAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Guest getGuestById(Integer id) {
        return guestRepository.findOne(id);
    }

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public void deleteGuest(Guest guest) { guestRepository.delete(guest);    }
}