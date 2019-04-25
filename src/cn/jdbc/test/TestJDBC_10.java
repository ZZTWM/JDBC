package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC_10 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "insert into hero values(null,?,?,?)";
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin"); PreparedStatement ps = c.prepareStatement(sql); Statement st = c.createStatement();) {
 
            ps.setString(1, "盖伦");
            ps.setFloat(2, 616);
            ps.setInt(3, 100);
 
            ps.execute();
 
            ResultSet rs1 = ps.getGeneratedKeys();
            int id = -1;
            if (rs1.next()) {
                id = rs1.getInt(1);
            }
 
            System.out.println("刚插入的数据的id是:" + id);
 
            for (int i = id - 1; i > 0; i--) {
                int targetId = i;
                ResultSet rs2 = st.executeQuery("select id from Hero where id = " + targetId);
                if (rs2.next()) {
                    System.out.println("id=" + targetId + " 的数据存在，删除该数据");
 
                    String deleteSQL = "delete from hero where id = " + targetId;
                    st.execute(deleteSQL);
                    break;
                }
            }
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
}
