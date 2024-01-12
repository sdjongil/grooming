package dao;

import driver.DBconnect;
import dto.CurrentUserIfor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserEpisodeDao extends DBconnect {
    public void getUserEpisodes(String id){
        PreparedStatement ps=null;
        ResultSet RS = null;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from " + id + "_episodes";
                ps=connection.prepareStatement(sql);
                RS = ps.executeQuery();
                while (RS.next()){
                    CurrentUserIfor.userEpisodes.put(RS.getString(1), RS.getInt(2));
                }
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
    }

    public int insertUserEpisodes(String userFileName, String id){
        PreparedStatement ps=null;
        int RS = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="insert into " + id + "_episodes values ("+id+"_episodes_seq.nextval , ? )";
                ps=connection.prepareStatement(sql);
                ps.setString(1, userFileName);
                RS = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return RS;
    }
}
