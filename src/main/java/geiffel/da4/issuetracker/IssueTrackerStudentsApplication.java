package geiffel.da4.issuetracker;

import geiffel.da4.issuetracker.commentaire.Commentaire;
import geiffel.da4.issuetracker.commentaire.CommentaireRepository;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerStudentsApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerStudentsApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpBDD() {
        return (args) -> {

            User u1 = new User (1L, "Machin", Fonction.USER);
            User u2 = new User(2L, "Chose", Fonction.USER);
            List<User> users = new ArrayList<>(){{
            add(u1);
            add(u2);
            add(new User(3L, "Truc", Fonction.DEVELOPPER));


        }};
        userRepository.saveAll(users);

        Issue i1=new Issue(1L, "blah", "some content1", u1, Timestamp.from(Instant.now()), null);
        Issue i2=new Issue(2L, "bleuh", "some content2", u2, Timestamp.from(Instant.now()), null);
        List<Issue> issues = new ArrayList<>(){
            {
                add(i1);
                add(i2);
                add(new Issue(3L, "blih", "some content3", u1, Timestamp.from(Instant.now()), null));
        }};
        issueRepository.saveAll(issues);

        List<Commentaire> commentaires= new ArrayList<>(){
            {
            add(new Commentaire(1L, u1, i1, "Contenu 1"));
            add(new Commentaire(2L, u1, i2, "Contenu 2"));
            add(new Commentaire(3L, u2, i2, "Contenu 3"));
        }};
        commentaireRepository.saveAll(commentaires);

    };
    }



}
