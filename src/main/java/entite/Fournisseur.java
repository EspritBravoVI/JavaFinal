package entite;

public class Fournisseur {
    private String contact ;
    private String nom;
    private String categorie ;


    public Fournisseur(String contact, String nom, String categorie) {
        this.contact = contact;
        this.nom = nom;
        this.categorie = categorie;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
