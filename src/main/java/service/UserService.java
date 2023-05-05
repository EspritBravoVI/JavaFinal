package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DataSource;

public class UserService {
  Connection conn = DataSource.getInstance().getCnx();
  Statement sst;



  public boolean userLogin(String username, String pass) throws SQLException {
    sst = conn.createStatement();
    ResultSet rss=sst.executeQuery("select * from user where username = '" + username + "' and (password ='" + pass + "')");

    if(rss.next()){
      rss.close();
      return true;

    }
    sst.close();
    rss.close();
    return false;
  }

  public void updateUsername(Integer oldId, String newUsername) throws SQLException{
    sst = conn.createStatement();
    String sql = "update user set username = '" + newUsername + "' where id = '" + oldId + "'";
    sst.executeUpdate(sql);
    sst.close();
  }

  public void updatePassword(String newPassword, Integer id) throws SQLException{
    Statement st = conn.createStatement();
    String sql = "update user set password = '" + newPassword + "' where id = '" + id + "'";
    st.executeUpdate(sql);
    st.close();
  }

  public void deleteUser(Integer id) throws SQLException{
    String sql = "delete from user where id = '" + id + "'";
    Statement st = conn.createStatement();
    ResultSet r = st.executeQuery("select COUNT(*) from user where id = '"+id+"'");
    r.next();
    if (r.getInt(1)==0) {
      throw new SQLException("User Not Found!!");
    }
    st.executeUpdate(sql);
    st.close();
    r.close();
  }

  public void addUser(String username, String password) throws SQLException{

    PreparedStatement add = conn.prepareStatement("INSERT INTO user VALUES(NULL,?,?)");
    add.setString(1, password);
    add.setString(2, username);
    add.executeUpdate();
    add.close();
  }
  public static void main(String[] args) throws SQLException {
    UserService userService = new UserService();
    //userService.addUser("anas","anas");
    //userService.deleteUser(Integer.valueOf(1));
    //userService.updatePassword("hahahhaha",Integer.valueOf(2));
    //userService.updateUsername(Integer.valueOf(4),"mou7a");
  }

}
