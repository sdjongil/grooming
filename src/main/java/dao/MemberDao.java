package dao;

import driver.DBconnect;
import dto.CurrentUserIfor;
import dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao extends DBconnect {

    public int insertUser(MemberDto MDto){
        PreparedStatement ps=null;
        int resultInt = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="insert into f_members values (? , ? , ? , ?, ?, default, '' )";
                ps=connection.prepareStatement(sql);
                ps.setString(1, MDto.getId());
                ps.setString(2, MDto.getPassword());
                ps.setString(3, MDto.getName());
                ps.setString(4, MDto.geteMail());
                ps.setString(5, MDto.getGender());

                resultInt = ps.executeUpdate();
                if(resultInt!=0){
                    createFriendTable(MDto.getId());
                }
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return resultInt;
    }
    private void createFriendTable(String id){
        PreparedStatement ps=null;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="create table "+ id + "_friends_user(" +
                        "friends_id varchar2(20) primary key, " +
                        "my_id varchar2(20)," +
                        "foreign key (my_id) references f_members(f_id)" +
                        ")";
                ps=connection.prepareStatement(sql);
                int resultInt = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
    }
    public int findId(String id){
        PreparedStatement ps=null;
        int result = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from f_members where f_id = ?";
                ps=connection.prepareStatement(sql);
                ps.setString(1, id);
                result = ps.executeUpdate();
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
                return result;
            }
        }
        return result;
    }
    public int updateHistory(){
        PreparedStatement ps=null;
        int resultInt = 0;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="update f_members set f_history = ? where f_id = ?";
                ps=connection.prepareStatement(sql);
                ps.setString(1, CurrentUserIfor.getHistory() );
                ps.setString(2, CurrentUserIfor.currentId);
                resultInt = ps.executeUpdate();

            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return resultInt;
    }

    public MemberDto logIn(String id, String password){
        PreparedStatement ps=null;
        ResultSet RS = null;
        MemberDto memberDto = null;
        if(getConnection()) {//커넥션 성공 시 DB에 쿼리문 전송
            try {
                String sql="select * from f_members where f_id = ?";
                ps=connection.prepareStatement(sql);
                ps.setString(1, id);
                RS = ps.executeQuery();
                while (RS.next()){
                    memberDto = new MemberDto();
                    memberDto.setId(RS.getString(1));
                    memberDto.setPassword(RS.getString(2));
                    memberDto.setName(RS.getString(3));
                    memberDto.seteMail(RS.getString(4));
                    memberDto.setGender(RS.getString(5));
                    memberDto.setPoint(RS.getInt(6));
                    memberDto.setHistory(RS.getString(7));
                }
                if(password.equals(memberDto.getPassword())){
                    return memberDto;
                }else {
                    memberDto = null;
                }
            }catch(Exception e) {
                e.getMessage();
            }finally {//자원반납
                close();
            }
        }
        return memberDto;
    }

}
