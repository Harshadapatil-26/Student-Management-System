import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementUI ui = new StudentManagementUI();
            ui.createAndShowGUI();
        });
    }
}