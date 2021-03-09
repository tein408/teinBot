package teinBot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    Connection conn = null;
    private String server = "localhost:3306";
    private String database = "pro01";
    private String user = "pro01";
    private String pwd = "1234";
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public Connection getConnection() {
        conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database +"?useSSL=false", user, pwd);
            //System.out.println("conn success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }//getConnection

    public void closeDB(){
        try {
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(conn!=null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//closeDB

    public void insertDB(LogVO logVO){
        try {
            conn = getConnection();
            sql = "insert into teinbotlog (author, name, channel, channelid, messageId, message)" +
                    " values (?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, logVO.getAuthor());
            pstmt.setString(2, logVO.getName());
            pstmt.setString(3, logVO.getChannel());
            pstmt.setString(4, logVO.getChannelid());
            pstmt.setString(5, logVO.getMessageId());
            pstmt.setString(6, logVO.getMessage());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }//insertDB


}//class
