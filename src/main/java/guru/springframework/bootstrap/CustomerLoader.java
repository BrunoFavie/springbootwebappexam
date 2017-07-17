package guru.springframework.bootstrap;


import guru.springframework.domain.Guest;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoader implements ApplicationListener<ContextRefreshedEvent> {

    private CustomerRepository customerRepository;

    private Logger log = Logger.getLogger(CustomerLoader.class);

    @Autowired
    public void setProductRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Guest alpha = new Guest();
        alpha.setNaam("alpha");
        alpha.setLastname("AAA");
        alpha.setAdress("Streetwise 1a");
        alpha.setZipcode("1234AA");
        alpha.setCity("Alphatown");
        alpha.setId(101);
        customerRepository.save(alpha);
        log.info("Saved alpha - id: " + alpha.getId());


        Guest beta = new Guest();
        beta.setNaam("beta");
        beta.setLastname("AAA");
        beta.setAdress("Streetwise 1a");
        beta.setZipcode("1234AA");
        beta.setCity("betatown");
        beta.setId(101);
        customerRepository.save(beta);
        log.info("Saved beta - id: " + beta.getId());
    }
}