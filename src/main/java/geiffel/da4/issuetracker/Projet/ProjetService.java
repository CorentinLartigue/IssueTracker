package geiffel.da4.issuetracker.Projet;

import java.util.List;

public interface ProjetService {

    List<Projet> getAll();

    Projet getById(long id);

    Object create(Projet unProjet);

    void update(Long id, Projet newProjet);
}
