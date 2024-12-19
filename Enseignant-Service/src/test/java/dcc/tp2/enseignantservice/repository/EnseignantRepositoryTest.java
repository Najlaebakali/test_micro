package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnseignantRepositoryTest {


    @Autowired
    private EnseignantRepository enseignantRepository;

    @BeforeEach
    void setUp(){
        enseignantRepository.save(new Enseignant(null,"test","test","LA1928","test@gmail.com","123","info","Enseignant"));
        enseignantRepository.save(new Enseignant(null,"test2","test2","LA19283","test2@gmail.com","123","info","Enseignant"));

    }

    /*@Test
    void findEnseignantByEmail() {
        String email = "test@gmail.com";
        Enseignant enseignant = new Enseignant(null,"test","test","LA1928","test@gmail.com","123","info","Enseignant");
        Enseignant result= enseignantRepository.findEnseignantByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(enseignant);
    }*/
    @Test
    void Not_findEnseignantByEmail() {
        String email = "test@gmail.com";
        Enseignant result= enseignantRepository.findEnseignantByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
         }
}