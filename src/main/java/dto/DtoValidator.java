package dto;


import dao.MemberDao;

public class DtoValidator {

    public boolean checkIdAndName(String id){ //영어랑 숫자만, 길이는 4~18
        if(id.length()>18)return false;
        if(id.length()<4)return false;
        if(!id.matches("^[a-zA-Z0-9]+$"))return false;
        return true;
    }
    public boolean checkPass(String pass, String passCheck){
        if(!pass.equals(passCheck)) {
            System.out.println("1");
            return false;
        }
        if(pass==null){
            System.out.println("2");
            return false;
        }
        if(pass.length()>20){
            System.out.println("3");
            return false;
        }
        if(pass.length()<10){
            System.out.println("4");
            return false;
        }
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        if(!pass.matches(regex)){
            System.out.println("5");
            return false;
        }
        return true;
    }
    public boolean findId(String id){
        MemberDao MD = new MemberDao();
        int num = MD.findId(id);
        if(num == 0){
            return true;
        }
        return false;
    }

}
