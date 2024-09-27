package views;

import dao.MemberDao;
import dto.DtoValidator;
import dto.MemberDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static views.MainView.*;

public class SignUpView {
    MemberDto MD = null;
    DtoValidator DV = new DtoValidator();
    JLabel idLabel = new JLabel("아이디");
    JLabel passLabel = new JLabel("비밀번호");
    JLabel passCheckLabel = new JLabel("비밀번호확인");
    JLabel nameLabel = new JLabel("이름");
    JLabel emailLabel = new JLabel("이메일");
    JLabel genderLabel = new JLabel("성별");
    JButton checkIdBnt = new JButton("중복 확인");
    JButton checkPassBnt = new JButton("비밀번호 확인");
    JButton checkBnt = new JButton("회원가입");
    JTextField idField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JPasswordField passwordCheckField = new JPasswordField(20);
    JTextField nameField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JRadioButton choice1 = new JRadioButton("남자");
    JRadioButton choice2 = new JRadioButton("여자");
    ButtonGroup radioGroup = new ButtonGroup();

    public void setSignUpView(){
        ViewFunctions VF = new ViewFunctions();
        SignUpPanel.setBackground(yellow);
        SignUpPanel.setPreferredSize(new Dimension(330, 600));
        VF.setTextFeilds(idField);
        VF.setTextFeilds(passwordField);
        VF.setTextFeilds(passwordCheckField);
        VF.setTextFeilds(nameField);
        VF.setTextFeilds(emailField);
        checkPassBnt.setBackground(Color.white);
        checkIdBnt.setBackground(Color.white);
        checkPassBnt.setPreferredSize(new Dimension(110, 30));
        checkIdBnt.setPreferredSize(new Dimension(110, 30));


        choice1.setBackground(yellow);
        choice2.setBackground(yellow);
        choice1.setBorder(new EmptyBorder(5,25,5,5));
        choice2.setBorder(new EmptyBorder(5,25,5,15));
        radioGroup.add(choice1);
        radioGroup.add(choice2);

        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(yellow);
        genderPanel.add(genderLabel);
        genderPanel.add(choice1);
        genderPanel.add(choice2);

        checkBnt.setPreferredSize(new Dimension(100, 30));
        JLabel la1= new JLabel();
        la1.setPreferredSize(new Dimension(110, 10));
        JLabel la2= new JLabel();
        la1.setPreferredSize(new Dimension(110, 10));
        JLabel la3= new JLabel();
        la1.setPreferredSize(new Dimension(110, 10));

        SignUpPanel.setBorder(new EmptyBorder(0,15,0,0));
        SignUpPanel.add(la1);
        SignUpPanel.add(la2);
        SignUpPanel.add(la3, "wrap");
        SignUpPanel.add(idLabel, "wrap, span 3");
        SignUpPanel.add(idField, "span 2");
        SignUpPanel.add(checkIdBnt, "wrap");
        SignUpPanel.add(passLabel, "wrap, span 2");
        SignUpPanel.add(passwordField, "wrap, span 2");
        SignUpPanel.add(passCheckLabel, "wrap, span 2");
        SignUpPanel.add(passwordCheckField, "span 2");
        SignUpPanel.add(checkPassBnt, "wrap");
        SignUpPanel.add(nameLabel, "wrap, span 2");
        SignUpPanel.add(nameField, "wrap, span 2");
        SignUpPanel.add(emailLabel, "wrap, span 2");
        SignUpPanel.add(emailField, "wrap, span 2");
        SignUpPanel.add(genderPanel,"wrap, span 2");
        JPanel temPanel = new JPanel();
        temPanel.setBackground(yellow);
        temPanel.setPreferredSize(new Dimension(50,30));
        SignUpPanel.add(temPanel);
        SignUpPanel.add(checkBnt, "span 2");
        checkBntAction();

    }

    public void checkBntAction(){
        checkBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = insertUserDto();
                ViewFunctions VF = new ViewFunctions();
                VF.setMessageDialog(message);
                idField.setText("");
                passwordField.setText("");
                passwordCheckField.setText("");
                nameField.setText("");
                emailField.setText("");

            }
        });
    }

    public String insertUserDto(){
        String message = "";
        MD = new MemberDto();
        if(!DV.findId(idField.getText()))return message= "중복된 아이디 입니다.";
        if(!DV.checkIdAndName(idField.getText()))return message="아이디 값이 잘 못되었습니다.";
        if(!DV.checkIdAndName(nameField.getText()))return message="비밀번호 값이 잘 못되었습니다";
        String password = new String(passwordField.getPassword());
        String passwordCheck = new String(passwordCheckField.getPassword());
        if(!DV.checkPass(password, passwordCheck))return message = "비밀번호가 올바르지 않습니다.";

        MD = setMemberDto();
        if(MD.getName()==null||MD.geteMail()==null ||MD.getId()==null||MD.getPassword()==null){
            return message="입력값이 비었습니다.";
        }
        MemberDao MDao = new MemberDao();
        if(MDao.insertUser(MD)==0){return message = "회원가입에 실패하였습니다. 다시 시도해주세요.";}

        message = "회원가입에 성공했습니다.";
        return message;
    }

    public MemberDto setMemberDto(){
        MD = new MemberDto();
        MD.setId(idField.getText());
        String password = new String(passwordField.getPassword());
        MD.setPassword(password);
        MD.setName(nameField.getText());
        MD.seteMail(emailField.getText());
        if(choice1.isSelected()){
            MD.setGender("남자");
        }else if(choice2.isSelected()){
            MD.setGender("여자");
        }
        return MD;
    }


}
