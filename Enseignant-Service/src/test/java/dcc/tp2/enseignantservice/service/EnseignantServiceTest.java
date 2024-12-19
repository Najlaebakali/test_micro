package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnseignantServiceTest {

    @Mock
    private EnseignantRepository enseignantRepository;
    @Mock
    private ChercheurRestFeign chercheurRestFeign;
    @Mock
    private ProjetRestFeign projetRestFeign;
    @InjectMocks
    private EnseignantService enseignantService;

    @Test
    void create_Enseignant() {
        Enseignant enseignant = new Enseignant(null,"test","test","LA1928","test@gmail.com","123","info","Enseignant");
        Enseignant enseignantsaved = new Enseignant(1L,"test","test","LA1928","test@gmail.com","123","info","Enseignant");

        // On va definir une action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignantsaved);

        // Tester
        Enseignant result= enseignantService.Create_Enseignant(enseignant);

        // Verification
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantsaved);




    }
    @Test
    void getAll_Enseignant() {
        List<Enseignant> enseignantList = List.of(
                new Enseignant(null,"test","test","LA1928","test@gmail.com","123","info","Enseignant"),
                new Enseignant(null,"test2","test2","LA19283","test2@gmail.com","123","info","Enseignant")
        );
        // Action
        Mockito.when(enseignantRepository.findAll()).thenReturn(enseignantList);
        List<Enseignant> result= enseignantService.GetAll_Enseignant();

        // Verification
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantList);

    }

    @Test
    void get_EnseignantByID() {

        Long id = 1L;
        Enseignant enseignant =  new Enseignant(1L,"test","test","LA1928","test@gmail.com","123","info","Enseignant");

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));

        Enseignant result= enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void Not_get_EnseignantByID() {

        Long id = 1L;

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.empty());

        Enseignant result= enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).isNull();

    }

    @Test
    void findByEmail() {

        String email = "test@gmail.com";
        Enseignant enseignant =  new Enseignant(1L,"test","test","LA1928","test@gmail.com","123","info","Enseignant");

        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(enseignant);

        Enseignant result= enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void Not_findByEmail() {

        String email = "ac@gmail.com";
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(null);
        Enseignant result= enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
        }

    @Test
    void update_Enseignant() {

        Long id = 1L;
        Enseignant enseignant =  new Enseignant(1L,"test","test","LA1928","test@gmail.com","123","info","Enseignant");
        Enseignant enseignant_updated =  new Enseignant(1L,"test","Najlae","LA1928","test@gmail.com","123","info","Enseignant");

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignant_updated);


        Enseignant result= enseignantService.Update_Enseignant(enseignant_updated,id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant_updated);




    }

    @Test
    void delete_Enseignant() {
        Long id = 1L;
        enseignantService.Delete_Enseignant(id);
    }

    @Test
    void statistique() {
        Long id= 1L;

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",2L);
        Statistiques.put("nombre de chercheur",2L);

        //action
        Mockito.when(chercheurRestFeign.nb_chercheur_Enseignant(id)).thenReturn(2L);
        Mockito.when(projetRestFeign.nb_Projet_Enseignant(id)).thenReturn(2L);

        //test
        Map<String, Long> result = enseignantService.statistique(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(Statistiques);

    }
}