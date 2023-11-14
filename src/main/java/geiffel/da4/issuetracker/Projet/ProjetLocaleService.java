package geiffel.da4.issuetracker.Projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;

import java.util.List;

public class ProjetLocaleService extends LocalService<Projet,Long> implements ProjetService{
    public ProjetLocaleService(List<Projet> projets) {
        super(projets);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return this.allValues;
    }

    @Override
    public Projet getById(long id) {
        return this.getByIdentifier(id);
    }

    @Override
    public Projet create(Projet unProjet)throws ResourceAlreadyExistsException {
        try {
            this.findByProperty(unProjet.getId());
            throw new ResourceAlreadyExistsException("Projet", unProjet.getId());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(unProjet);
            return unProjet;
        }
    }

    @Override
    public void update(Long id, Projet newProjet) throws ResourceAlreadyExistsException{
        IndexAndValue<Projet>projet=this.findByProperty(id);
        this.allValues.remove(projet.index());
        this.allValues.add(projet.index(),newProjet);
    }


}
