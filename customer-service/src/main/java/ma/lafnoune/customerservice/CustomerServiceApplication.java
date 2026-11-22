package ma.lafnoune.customerservice;

import ma.lafnoune.customerservice.config.CustomerConfigParams;
import ma.lafnoune.customerservice.entities.Customer;
import ma.lafnoune.customerservice.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("ayman")
                    .email("ayman@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("ahmed")
                    .email("ahmed@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("mohamed")
                    .email("mohamed@gmail.com")
                    .build());
            customerRepository.findAll().forEach(c -> {
                System.out.println("============");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("============");
            });
        };
    }

}
