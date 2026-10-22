
package QLDichVu;

import QLDichVu.view.DichVuPanel;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm() {

        setTitle("He thong quan ly khach san");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tieu de cua so chinh
        JLabel lblTieuDe = new JLabel(
                "HE THONG QUAN LY KHACH SAN",
                SwingConstants.CENTER
        );

        lblTieuDe.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        lblTieuDe.setForeground(
                new Color(75, 50, 130)
        );

        lblTieuDe.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        add(lblTieuDe, BorderLayout.NORTH);

        // Cac chuc nang duoc bo tri theo tab
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(
                "Quan ly dich vu",
                new DichVuPanel()
        );

        add(tabbedPane, BorderLayout.CENTER);

        // Thanh trang thai
        JLabel lblTrangThai = new JLabel(
                "Quan ly dich vu khach san"
        );

        lblTrangThai.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );

        add(lblTrangThai, BorderLayout.SOUTH);

        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainForm form = new MainForm();
            form.setVisible(true);
        });
    }
}
