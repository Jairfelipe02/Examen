package net.learnbyproject.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.learnbyproject.model.Comment;
import net.learnbyproject.model.Post;
import net.learnbyproject.model.User;

public class PostService {

    public static List<Comment> getCommentList(int postId) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        String select = "select * from tbl_comment where post_id = ? order by creation_date desc";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {
            pStmt.setInt(1, postId);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String formattedDate = DBService.getFormattedDate(rs.getDate("creation_date"));
                User author = UserService.getUser(rs.getInt("user_id"));
                Comment comment = new Comment(rs.getInt("id"),
                        rs.getString("content"),
                        formattedDate, author);
                commentList.add(comment);
            }
        }
        return commentList;
    }

    // Load author's posts and his/her friends' posts
    public static List<Post> getPostList(int authorId) throws SQLException {
        List<Post> postList = new ArrayList<>();
        String select = "select * from tbl_post where user_id = ? "
                + " or user_id in (select friend_to from tbl_friend where me = ?) order by creation_date desc";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(select);) {
            pStmt.setInt(1, authorId);
            pStmt.setInt(2, authorId);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int postId = rs.getInt("id");
                String formattedDate = DBService.getFormattedDate(rs.getDate("creation_date"));
                List<Comment> comments = getCommentList(postId);
                User author = UserService.getUser(rs.getInt("user_id"));
                Post post = new Post(postId, rs.getString("content"),
                        formattedDate, author, comments);
                postList.add(post);
            }
        }
        return postList;
    }

    public static int addNewPost(int authorId, String content) throws SQLException {
        String insert = "Insert into tbl_post Values(null,?,?,?)";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(insert)) {
            pStmt.setString(1, content);
            pStmt.setDate(2, new Date(System.currentTimeMillis()));
            pStmt.setInt(3, authorId);
            return pStmt.executeUpdate();
        }
    }

    public static int addNewComment(int authorId, int postId, String content) throws SQLException {
        String insert = "Insert into tbl_comment Values(null,?,?,?,?)";
        try (Connection c = DBService.openConnection();
                PreparedStatement pStmt = c.prepareStatement(insert)) {
            pStmt.setString(1, content);
            pStmt.setDate(2, new Date(System.currentTimeMillis()));
            pStmt.setInt(3, postId);
            pStmt.setInt(4, authorId);
            return pStmt.executeUpdate();
        }
    }

    public static int countUserPosts(int userId) throws SQLException {
        String select = "select count(*) as total from tbl_post where user_id=?";
        try (Connection c = DBService.openConnection();
                PreparedStatement ps = c.prepareStatement(select)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

    public static int countUserComments(int userId) throws SQLException {
        String select = "select count(*) as total from tbl_comment where user_id=?";
        try (Connection c = DBService.openConnection();
                Statement st = c.createStatement();PreparedStatement ps = c.prepareStatement(select)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
}
