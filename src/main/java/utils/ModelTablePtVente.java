package utils;

public class ModelTablePtVente {
    int id;
    String loc;

    public ModelTablePtVente(int id, String loc) {
        this.id = id;
        this.loc = loc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
