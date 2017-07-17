package guru.springframework.services;

import guru.springframework.domain.Guest;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Iterable<Guest> listAllGuests() {
        return customerRepository.findAll();
    }

    @Override
    public Guest getGuestById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Guest saveGuest(Guest guest) {
        return customerRepository.save(guest);
    }

    @Override
    public void deleteGuest(Guest guest) { customerRepository.delete(guest);    }
}