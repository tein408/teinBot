package teinBot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void updateDB(LogVO logVO){
        try {
            conn = getConnection();
            sql = "update teinbotlog set changed = ? where messageId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, logVO.getChanged());
            pstmt.setString(2, logVO.getMessageId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }//updateDB

    public String selectBeforeMessage(String messageId) {
        String beforeMessage = "";
        try {
            conn = getConnection();
            sql = "select message from teinbotlog where messageId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, messageId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                beforeMessage = rs.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return beforeMessage;
    }//selectBeforeMessage

    public void messageDeleted(String messageId) {
        try {
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String date = format.format(time);

            conn = getConnection();
            sql = "update teinbotlog set deleteMsg = ? where messageId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, messageId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }//messageDeleted


}//class
