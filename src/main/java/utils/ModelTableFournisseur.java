package utils;

public class ModelTableFournisseur {

    String nom;
    String cat;
    String contact;

    public ModelTableFournisseur(String nom, String cat, String contact) {
        this.nom = nom;
        this.cat = cat;
        this.contact = contact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
