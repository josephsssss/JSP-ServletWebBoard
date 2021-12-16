package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
  // 1. Connection
  public static Connection getConnection() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    Properties pro = new Properties();
    pro.put("user", "SCOTT");
    pro.put("password","TIGER");
    
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", pro);
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
  
  public static boolean isConnected(Connection conn) {
    boolean validConnection = true;
    try {
      if(conn == null || conn.isClosed()) {
        validConnection = false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return validConnection;
  }
  
  public static void close(Connection conn) {
      if (isConnected(conn)) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
  }
  
  public static void close(Statement stmt) {
    if(stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
  }
  
  
  // 4. close resultSet
  public static void close(ResultSet rs) {
    if(rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  
  // 5. RollBack()
  public static void rollBack(Connection conn) {
    if(isConnected(conn)) {
      try {
        conn.rollback();
        System.out.println("[ JDBCTemplate.RollBack ] : DB Successfully Rollbacked");
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  
  // 6. Commit()
  public static void commit(Connection conn) {
    if(isConnected(conn)) {
      try {
        conn.commit();
        System.out.println("[ JDBCTemplate.Commit ] : DB Successfully Committed");
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
