package service;

import entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import utils.DataSource;

public class ProduitService {
  Connection conn = DataSource.getInstance().getCnx();

  public void addProduit(String libelle, int quantite,String categorie) throws SQLException {

    //oral care, skin care, sun care, hair care, decorative cosmetics, body care and perfumes

    //list deroulante front-end to choose from those 7 categories

    PreparedStatement add = conn.prepareStatement("INSERT INTO produit VALUES(NULL,?,?,?)");

    add.setString(1, libelle);
    add.setInt(2, quantite);
    add.setString(3, categorie);

    add.executeUpdate();
    add.close();
  }
  public void updateProduit(int newQte, String oldLib, String newLibelle,String newCat) throws SQLException{
    PreparedStatement add = conn.prepareStatement("UPDATE produit SET quantite=?,libelle=?,categorie=? WHERE libelle=?");
    add.setInt(1, newQte);
    add.setString(2, newLibelle);
    add.setString(3, newCat);
    add.setString(4, oldLib);
    add.executeUpdate();

    add.close();
  }
   public void deleteProduit(String des, String catg) throws SQLException{
        PreparedStatement add = conn.prepareStatement("DELETE FROM produit WHERE libelle LIKE ? AND categorie LIKE ? ");

        add.setString(1, des);
        add.setString(2, catg);
        add.executeUpdate();

        add.close();
    }

  public ArrayList<Produit> getProducts() throws SQLException {

    PreparedStatement ps = conn.prepareStatement("select * from produit");
    ResultSet rs= ps.executeQuery();
    ArrayList<Produit> stocks = new ArrayList<>();

    while (rs.next()) {
      Produit produit = new Produit(rs.getString("libelle"),rs.getString("categorie"),rs.getInt("quantite"));
      stocks.add(produit);
    }
    rs.close();
    ps.close();

    return stocks;
  }

  public List<Produit> getProductsPerCategory(String cat) throws SQLException {


    PreparedStatement ps = conn.prepareStatement("select * from produit");
    //ps.setString(1,cat);
    ResultSet rs= ps.executeQuery();
    ArrayList<Produit> stocks = new ArrayList<>();

    while (rs.next()) {
      Produit produit = new Produit(rs.getString("libelle"),rs.getString("categorie"),rs.getInt("quantite"));
      stocks.add(produit);
    }

    rs.close();
    ps.close();

    List<Produit> filtred;
    filtred = stocks.stream().filter(p -> p.getCategorie().equals(cat)).collect(Collectors.toList());
    return filtred;
  }

  public List<Produit> getProductsEnRuptureDeStock() throws SQLException {


    PreparedStatement ps = conn.prepareStatement("select * from produit");
    ResultSet rs= ps.executeQuery();
    ArrayList<Produit> stocks = new ArrayList<>();

    while (rs.next()) {
      Produit produit = new Produit(rs.getString("libelle"),rs.getString("categorie"),rs.getInt("quantite"));
      stocks.add(produit);
    }
    rs.close();
    ps.close();

    List<Produit> filtred;
    filtred = stocks.stream().filter(p -> p.getQuantite() == 0).collect(Collectors.toList());

    return filtred;
  }

  public List<Produit> getProductsDisponible() throws SQLException {


    PreparedStatement ps = conn.prepareStatement("select * from produit");
    ResultSet rs= ps.executeQuery();
    ArrayList<Produit> stocks = new ArrayList<>();

    while (rs.next()) {
      Produit produit = new Produit(rs.getString("libelle"),rs.getString("categorie"),rs.getInt("quantite"));
      stocks.add(produit);
    }
    rs.close();
    ps.close();

    List<Produit> filtred;
    filtred = stocks.stream().filter(p -> p.getQuantite() > 0).collect(Collectors.toList());

    return filtred;
  }
  public static void main(String[] args) throws SQLException {
    ProduitService produitService = new ProduitService();
    //produitService.addProduit("body screen 70",Integer.valueOf(5),"face care");
    System.out.println(produitService.getProductsPerCategory("visage"));
    //produitService.updateProduit(0,1,"loubya","bnina");
    System.out.println(produitService.getProductsEnRuptureDeStock());
    //System.out.println(produitService.getProductsDisponible());
  }


}
