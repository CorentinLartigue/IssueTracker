package geiffel.da4.issuetracker.commentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
@Primary
public class CommentaireJPAService implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if (commentaire.isPresent()) {
            return commentaire.get();
        } else {
            throw new ResourceNotFoundException("Commentaire", id);
        }
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        return null;
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        return null;
    }

    @Override
    public Commentaire create(Commentaire newCommentaire) {
        try {
            commentaireRepository.findById(newCommentaire.getId());
            throw new ResourceAlreadyExistsException("Commentaire", newCommentaire.getId());
        } catch (ResourceNotFoundException e) {
            return commentaireRepository.save(newCommentaire);
        }
    }

    @Override
    public void update(Long id, Commentaire updatedCommentaire) {
        Optional<Commentaire> found = commentaireRepository.findById(id);
        if(found.isPresent()){
            Commentaire commentaire = found.get();
            commentaireRepository.delete(commentaire);
            commentaireRepository.save(updatedCommentaire);
        }
        else{
            throw new ResourceAlreadyExistsException("Commentaire", updatedCommentaire.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Commentaire> found = commentaireRepository.findById(id);
        if(found.isPresent()){
            commentaireRepository.deleteById(id);
        }
        else {
            throw new ResourceAlreadyExistsException("Commentaire", id);
        }
    }
}
