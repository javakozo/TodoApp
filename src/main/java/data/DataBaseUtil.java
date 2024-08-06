package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class DataBaseUtil {

   static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    
    private static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL@2024";

    /*
     * DB接続
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*
     * データ追加
     */
    public static void insertTask(String task) throws SQLException {
        String sql = "INSERT INTO tasks (task) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.executeUpdate();
        }
    }

    /*
     * データ削除
     */
    public static void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * 
     * @param id
     * @param completed
     * @throws SQLException
     */
    public static void updateTask(int id, boolean completed) throws SQLException {
        String sql = "UPDATE tasks SET completed = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, completed);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    /*
     * タスクリスト生成
     * @return
     * @throws SQLException
     */
    public static List<Task> listTasks() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("task"), rs.getBoolean("completed"));
                taskList.add(task);
            }
        }
        return taskList;
    }
}