package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import geiffel.da4.issuetracker.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class IssueJPAService  implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long code) {
        Optional<Issue> issue = issueRepository.findById(code);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new ResourceNotFoundException("Issue", code);
        }
    }

    @Override
    public Issue create(Issue newIssue) {
        try {
            issueRepository.findById(newIssue.getCode());
            throw new ResourceAlreadyExistsException("User", newIssue.getCode());
        } catch (ResourceNotFoundException e) {
            return issueRepository.save(newIssue);
        }
    }

    @Override
    public void update(Long code, Issue updatedIssue) {
        Optional<Issue> found = issueRepository.findById(code);
        if(found.isPresent()){
            Issue issue = found.get();
            issueRepository.delete(issue);
            issueRepository.save(updatedIssue);
        }
        else{
            throw new ResourceAlreadyExistsException("Issue", updatedIssue.getCode());
        }
    }

    @Override
    public void delete(Long code) {
        Optional<Issue> found = issueRepository.findById(code);
        if(found.isPresent()){
            issueRepository.deleteById(code);
        }
        else {
            throw new ResourceAlreadyExistsException("Issue", code);
        }
    }
}
