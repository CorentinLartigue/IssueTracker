package geiffel.da4.issuetracker.Projet;

public class Projet {
    private Long id;
    private String nom;

    public Projet(Long unId, String unNom) {
        this.id=unId;
        this.nom=unNom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
