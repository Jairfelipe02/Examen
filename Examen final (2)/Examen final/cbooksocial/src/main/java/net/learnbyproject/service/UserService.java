package net.learnbyproject.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.learnbyproject.helper.StringHelper;
import net.learnbyproject.model.User;

public class UserService {

    public static boolean isDuplicateEmailOrPhone(String emailOrMobile) throws SQLException {
        String select = "select id from tbl_user where email_mobile = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {
            pStmt.setString(1, emailOrMobile);
            return pStmt.executeQuery().next();
        }
    }


    public static int addNewUser(String firstName, String lastName, String emailOrMobile,
            String password, String birthday, String sex) throws SQLException {
        String insert = "Insert into tbl_user Values(null,?,?,?,?,?,?,null)";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(insert)) {
            pStmt.setString(1, firstName);
            pStmt.setString(2, lastName);
            pStmt.setString(3, emailOrMobile);
            pStmt.setString(4, password);
            pStmt.setString(5, birthday);
            pStmt.setString(6, sex);
            return pStmt.executeUpdate();
        }
    }

    public static User getUser(String emailOrPhone, String password) throws SQLException {
        String select = "select * from tbl_user "
                + "where email_mobile = ? and password = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {

            pStmt.setString(1, emailOrPhone);
            pStmt.setString(2, password);
            return extractUser(pStmt.executeQuery());
        }
    }
    
    public static User getUser(String emailOrPhone) throws SQLException {
        String select = "select * from tbl_user "
                + "where email_mobile = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {

            pStmt.setString(1, emailOrPhone);
            return extractUser(pStmt.executeQuery());
        }
    }
    
    public static User getUser(int id) throws SQLException {
        String select = "select * from tbl_user "
                + "where id = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {

            pStmt.setInt(1, id);
            return extractUser(pStmt.executeQuery());
        }
    }

    public static User checkLogin(String emailOrPhone, String password) throws SQLException {
        return getUser(emailOrPhone, password);
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = null;
        if (rs.next()) {
            String avatar = StringHelper.convertBase64(rs.getBytes("avatar"));
            user = new User(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email_mobile"), rs.getString("password"),
                    rs.getString("birthday"), rs.getString("sex"), avatar);
        }
        return user;
    }

    public static int updateUser(String firstName, String lastName, String newEmailOrMobile,
                            String userPass, String birthDay, String sex, String currentEmailOrMobile) throws SQLException {
        String insert = "Update tbl_user Set first_name = ?, last_name = ?, "
                + "email_mobile = ?, password = ?, birthday = ?, "
                + "sex = ? Where email_mobile = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(insert)) {
            pStmt.setString(1, firstName);
            pStmt.setString(2, lastName);
            pStmt.setString(3, newEmailOrMobile);
            pStmt.setString(4, userPass);
            pStmt.setString(5, birthDay); // day-month-year
            pStmt.setString(6, sex);
            pStmt.setString(7, currentEmailOrMobile);
            return pStmt.executeUpdate();
        } 
    }
    
    public static int updateUserAvatar(InputStream inputStream, 
            String emailOrMobile) throws SQLException {
        String insert = "Update tbl_user Set avatar = ? "
                + "Where email_mobile = ? ";
        
        try (Connection c = DBService.openConnection()) {
            PreparedStatement pStmt = c.prepareStatement(insert);
            pStmt.setBlob(1, inputStream);
            pStmt.setString(2, emailOrMobile);
            return pStmt.executeUpdate();
        } 
    }
    
    public static List<User> getFriendList(int me) throws SQLException {
        List<User> list = new ArrayList<>();
        String select = "select friend_to from tbl_friend "
                + "where me = ?";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {

            pStmt.setInt(1, me);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int friendId = rs.getInt("friend_to");
                User user = getUser(friendId);
                list.add(user);
            }
        } 
        return list;
    }
    
    public static List<User> getSuggestedFriend(int me) throws SQLException {
        List<User> list = new ArrayList<>();
        String select = "Select * From tbl_user "
                + "Where id != ? And id not in "
                + "(Select friend_to from tbl_friend where me = ?)";
        try (Connection c = DBService.openConnection();
             PreparedStatement ps = c.prepareStatement(select)){
            ps.setInt(1, me);
            ps.setInt(2, me);
            ResultSet rs = ps.executeQuery();
            User user;
            while ((user = extractUser(rs)) != null) {
                list.add(user);
            }
        } 
        return list;
    }
    
    public static void addFriend(int me, int friend) throws SQLException {
        String insert = "Insert Into tbl_friend Values(null,?,?)";
        try (Connection c = DBService.openConnection();
             PreparedStatement ps = c.prepareStatement(insert)){
            ps.setInt(1, me);
            ps.setInt(2, friend);
            ps.executeUpdate();
        } 
    }
    
    public static List<User> searchFriend(String keyword) throws SQLException {
        List<User> list = new ArrayList<>();
        String select = "Select * From tbl_user "
                + "Where first_name like ? OR last_name like ?";
        try (Connection c = DBService.openConnection();
             PreparedStatement ps = c.prepareStatement(select)){
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            User user;
            while ((user = extractUser(rs)) != null) {
                list.add(user);
            }
        } 
        return list;
    }
}
