package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.Projet.Projet;
import geiffel.da4.issuetracker.Projet.ProjetLocaleService;
import geiffel.da4.issuetracker.Projet.ProjetService;
import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserLocalService;
import geiffel.da4.issuetracker.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetServiceTest {
    private ProjetService projetService;
    private List<Projet> projets;


@BeforeEach
void setUp(){
    projets = new ArrayList<>(){{
        add(new Projet(1L,"projet1"));
        add(new Projet(2L,"projet2"));
        add(new Projet(3L,"projet3"));
        add(new Projet(6L,"projet6"));
    }};
    projetService= new ProjetLocaleService(projets);
}

    @Test
    void whenGettingAll_shouldGet4(){
        List<Projet>projets =projetService.getAll();
        assertEquals(4,projets.size());
    }

    @Test
    void whenGettingById_shouldReturnIfExists_andBeTheSame() {
        assertAll(
                () -> assertEquals(projets.get(0), projetService.getById(1L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(12L))

        );
    }

    @Test
    void whenCreating_ShouldReturnSame() {
        Projet projet4 =new Projet(4L,"projet4");
        projets.add(projet4);
        assertTrue(projetService.getAll().contains(projet4));

    }

    @Test
    void whenCreatingTheSameObject_ShouldReturnException() {
        Projet projet5 =new Projet(1L,"projet1");
        assertThrows(ResourceAlreadyExistsException.class,()->projetService.create(projet5));

    }

    @Test
    void whenUpdatingById_shouldReturnUpdatingObject(){
        Projet projet6=projets.get(3);

        Projet newProjet= new Projet(projet6.getId(),"projetUpdate");

        projetService.update(newProjet.getId(),newProjet);
        Projet projetUpdate = projetService.getById(projet6.getId());
        assertEquals(newProjet,projetUpdate);
        assertTrue(projetService.getAll().contains(newProjet));

    }

    @Test
    void whenUpdatingObjectNoExisting_shouldReturnException(){
        Projet projet7=new Projet(7L,"projet7");

        assertThrows(ResourceNotFoundException.class, ()->projetService.update(78L,projet7));
    }



}
