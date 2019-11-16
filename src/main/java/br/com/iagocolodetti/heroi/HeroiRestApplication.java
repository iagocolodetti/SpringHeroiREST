package br.com.iagocolodetti.heroi;

import br.com.iagocolodetti.heroi.repository.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author iagocolodetti
 */
@SpringBootApplication
@EnableJpaRepositories (repositoryBaseClass = CustomRepositoryImpl.class)
public class HeroiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroiRestApplication.class, args);
    }

}
