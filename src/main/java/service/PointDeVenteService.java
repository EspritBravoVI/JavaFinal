package service;

import entite.Fournisseur;
import entite.PointDeVente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import utils.DataSource;

public class PointDeVenteService {
  Connection conn = DataSource.getInstance().getCnx();

  public void addPointDeVente(String localisation) throws SQLException {


    PreparedStatement add = conn.prepareStatement("INSERT INTO point_de_vente VALUES(NULL,?)");

    add.setString(1, localisation);

    add.executeUpdate();
    add.close();
  }
  public void updatePointDeVente(String newLocalisation, String oldLoc) throws SQLException{
    PreparedStatement add = conn.prepareStatement("UPDATE point_de_vente SET localisation=? WHERE localisation=?");
    add.setString(1, newLocalisation);
    add.setString(2, oldLoc);
    add.executeUpdate();

    add.close();
  }
  public ArrayList<PointDeVente> getAllPointDeVente() throws SQLException {

    PreparedStatement ps = conn.prepareStatement("select * from point_de_vente");
    ResultSet rs= ps.executeQuery();
    ArrayList<PointDeVente> pointDeVenteList = new ArrayList<>();

    while (rs.next()) {
      PointDeVente pointDeVente = new PointDeVente(rs.getInt("id"),rs.getString("localisation"));
      pointDeVenteList.add(pointDeVente);
    }
    rs.close();
    ps.close();

    return pointDeVenteList;
  }

  public List<PointDeVente> getPointDeVenteSorted() throws SQLException {

    PreparedStatement ps = conn.prepareStatement("select * from point_de_vente");
    ResultSet rs= ps.executeQuery();
    ArrayList<PointDeVente> pointDeVenteList = new ArrayList<>();

    while (rs.next()) {
      PointDeVente pointDeVente = new PointDeVente(rs.getInt("id"),rs.getString("localisation"));
      pointDeVenteList.add(pointDeVente);
    }
    rs.close();
    ps.close();

    List<PointDeVente> filtred;
    filtred = pointDeVenteList.stream().sorted(Comparator.comparing(PointDeVente::getLocalisation)).collect(Collectors.toList());

    return filtred;
  }

  public void deletePt(String des) throws SQLException{
    PreparedStatement add = conn.prepareStatement("DELETE FROM point_de_vente WHERE localisation LIKE ?");

    add.setString(1, des);
    add.executeUpdate();

    add.close();
  }
  public static void main(String[] args) throws SQLException {
    PointDeVenteService pointDeVenteService = new PointDeVenteService();
    //pointDeVenteService.addPointDeVente("Ariana");
    //pointDeVenteService.addPointDeVente("Tunis");
    //pointDeVenteService.addPointDeVente("Mannouba");
    //pointDeVenteService.addPointDeVente("Sfax");
    System.out.println(pointDeVenteService.getAllPointDeVente());
    System.out.println(pointDeVenteService.getPointDeVenteSorted());
  }
}
