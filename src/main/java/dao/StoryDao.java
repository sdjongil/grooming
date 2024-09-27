package dao;

import driver.DBconnect;
import dto.CurrentUserIfor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StoryDao extends DBconnect {
    //파일에서 읽은 내용 DB저장
    public int insertStory(String speaker, String line, String table){
        PreparedStatement ps=null;
        int resultInt = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="insert into " + table + " values ("+table+"_seq.nextval, ? , ? )";
                ps=connection.prepareStatement(sql);
                ps.setString(1, speaker);
                ps.setString(2, line);

                resultInt = ps.executeUpdate();

            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return resultInt;
    }
    public int createEpisodeTable(String talbe){
        PreparedStatement ps=null;
        int resultInt = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="create table "+talbe+"(" +
                        "line_num number(10) primary key," +
                        "speaker varchar2(20) not null," +
                        "speak_line varchar2(1500) not null" +
                        ")";
                ps=connection.prepareStatement(sql);
                resultInt = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return resultInt;
    }
    public int createEpisodeSequence(String talbe){
        PreparedStatement ps=null;
        int resultInt = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="create sequence "+talbe+"_seq";
                ps=connection.prepareStatement(sql);
                resultInt = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return resultInt;
    }
    public String[] selectScene(String table, int lineNum){
        PreparedStatement ps=null;
        ResultSet RS = null;
        String[] ments = new String[2];
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from "+table+" where line_num = "+lineNum;
                ps=connection.prepareStatement(sql);
                RS = ps.executeQuery();
                while (RS.next()){
                    ments[0] = RS.getString(2);
                    ments[1] = RS.getString(3);
                }

            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return ments;
    }
    public int updateHistory(String history){
        PreparedStatement ps=null;
        int result = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="update "+ CurrentUserIfor.currentId+"_episodes set line_num = ? where episode_name = ?";
                ps=connection.prepareStatement(sql);
                String[] historys = history.split(":");
                ps.setInt(1, Integer.parseInt(historys[1]));
                ps.setString(2, historys[0]);
                System.out.println(sql);
                result = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return result;
    }
    public ArrayList<String> getStories(int line){
        String table = CurrentUserIfor.currentEpisode;
        PreparedStatement ps=null;
        ResultSet RS = null;
        ArrayList<String> lines = new ArrayList<>();
        String[] ments = new String[2];
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from "+table+" where line_num between "+ (line-5) +" and "+ line;
                ps=connection.prepareStatement(sql);
                RS = ps.executeQuery();
                while (RS.next()){
                    ments[0] = RS.getString(2);
                    ments[1] = RS.getString(3);
                    lines.add(ments[0]+" : "+ments[1]);
                }

            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return lines;
    }
    public ArrayList<String> getStories(int line, int add){
        String table = CurrentUserIfor.currentEpisode;
        PreparedStatement ps=null;
        ResultSet RS = null;
        ArrayList<String> lines = new ArrayList<>();
        String[] ments = new String[2];
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from "+table+" where line_num between "+ (line-5) +" and "+ (line+add);
                ps=connection.prepareStatement(sql);
                RS = ps.executeQuery();
                while (RS.next()){
                    ments[0] = RS.getString(2);
                    ments[1] = RS.getString(3);
                    lines.add(ments[0]+" : "+ments[1]);
                }

            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return lines;
    }
}
