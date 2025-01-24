import java.sql.*;

public class StudentService {
    private final String DB_URL = "jdbc:sqlite:students.db";

    public StudentService() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                      "name TEXT NOT NULL, " +
                                      "age INTEGER NOT NULL, " +
                                      "grade TEXT NOT NULL)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String name, int age, String grade) {
        String insertQuery = "INSERT INTO student (name, age, grade) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, grade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllStudents() {
        StringBuilder students = new StringBuilder("ID\tName\tAge\tGrade\n");
        String selectQuery = "SELECT * FROM student";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                students.append(rs.getInt("id")).append("\t")
                        .append(rs.getString("name")).append("\t")
                        .append(rs.getInt("age")).append("\t")
                        .append(rs.getString("grade")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.toString();
    }
}
