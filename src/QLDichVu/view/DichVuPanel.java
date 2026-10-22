
package QLDichVu.view;

import QLDichVu.dao.DichVuDAO;
import QLDichVu.model.DichVu;
import QLDichVu.dao.LoaiDichVuDAO;
import QLDichVu.model.LoaiDichVu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

public class DichVuPanel extends JPanel {
private void taiDanhSachLoai() {

    try {
        cboLoaiDV.removeAllItems();

        for (LoaiDichVu loai : loaiDAO.getAll()) {
            cboLoaiDV.addItem(loai);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Khong the tai danh sach loai dich vu: "
                + e.getMessage(),
                "Loi database",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    private final DichVuDAO dao = new DichVuDAO();

    // Cac o nhap lieu
    private JTextField txtMaDV;
    private JComboBox<LoaiDichVu> cboLoaiDV;

private final LoaiDichVuDAO loaiDAO = new LoaiDichVuDAO();
    private JTextField txtTenDV;
    private JTextField txtDonGia;
    private JTextField txtDonViTinh;
    private JTextField txtMoTa;
    private JTextField txtTimKiem;

    // Trang thai dich vu
    private JCheckBox chkTrangThai;

    // Bang du lieu
    private JTable tblDichVu;
    private DefaultTableModel model;

    // Ma dich vu dang duoc chon
    private int maDVDangChon = -1;

    public DichVuPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        khoiTaoGiaoDien();
        taiDanhSachLoai();
        taiDanhSach();
    }

    private void khoiTaoGiaoDien() {

        // Tieu de
        JLabel lblTieuDe = new JLabel(
                "QUAN LY DICH VU KHACH SAN",
                SwingConstants.CENTER
        );

        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 22));
        lblTieuDe.setForeground(new Color(75, 50, 130));
        lblTieuDe.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 12, 5)
        );

        // Thanh tim kiem
        JPanel pnlTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtTimKiem = new JTextField(25);

        JButton btnTimKiem = new JButton("Tim kiem");
        JButton btnLamMoi = new JButton("Lam moi");

        pnlTimKiem.add(new JLabel("Ten dich vu:"));
        pnlTimKiem.add(txtTimKiem);
        pnlTimKiem.add(btnTimKiem);
        pnlTimKiem.add(btnLamMoi);

        // Form nhap lieu
        txtMaDV = new JTextField();
        txtMaDV.setEditable(false);

        cboLoaiDV = new JComboBox<>();
cboLoaiDV.setPreferredSize(new Dimension(180, 28));
        txtTenDV = new JTextField();
        txtDonGia = new JTextField();
        txtDonViTinh = new JTextField();
        txtMoTa = new JTextField();

        chkTrangThai = new JCheckBox("Dang hoat dong");
        chkTrangThai.setSelected(true);

        JPanel pnlForm = new JPanel(new GridLayout(4, 4, 8, 8));
        pnlForm.setBorder(
                BorderFactory.createTitledBorder("Thong tin dich vu")
        );

        pnlForm.add(new JLabel("Ma dich vu:"));
        pnlForm.add(txtMaDV);
        pnlForm.add(new JLabel("Loai dich vu:"));
        pnlForm.add(cboLoaiDV);

        pnlForm.add(new JLabel("Ten dich vu:"));
        pnlForm.add(txtTenDV);
        pnlForm.add(new JLabel("Don gia:"));
        pnlForm.add(txtDonGia);

        pnlForm.add(new JLabel("Don vi tinh:"));
        pnlForm.add(txtDonViTinh);
        pnlForm.add(new JLabel("Trang thai:"));
        pnlForm.add(chkTrangThai);

        pnlForm.add(new JLabel("Mo ta:"));
        pnlForm.add(txtMoTa);
        pnlForm.add(new JLabel(""));
        pnlForm.add(new JLabel(""));

        // Cac nut chuc nang
        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnThem = new JButton("Them");
        JButton btnCapNhat = new JButton("Cap nhat");
        JButton btnNgung = new JButton("Ngung hoat dong");
        JButton btnKhoiPhuc = new JButton("Khoi phuc");
        JButton btnXoaTrang = new JButton("Xoa trang");

        pnlNut.add(btnThem);
        pnlNut.add(btnCapNhat);
        pnlNut.add(btnNgung);
        pnlNut.add(btnKhoiPhuc);
        pnlNut.add(btnXoaTrang);

        JPanel pnlNhapLieu = new JPanel(new BorderLayout(5, 5));
        pnlNhapLieu.add(pnlForm, BorderLayout.CENTER);
        pnlNhapLieu.add(pnlNut, BorderLayout.SOUTH);

        JPanel pnlPhiaTren = new JPanel(new BorderLayout(5, 5));
        pnlPhiaTren.add(lblTieuDe, BorderLayout.NORTH);
        pnlPhiaTren.add(pnlTimKiem, BorderLayout.CENTER);
        pnlPhiaTren.add(pnlNhapLieu, BorderLayout.SOUTH);

        add(pnlPhiaTren, BorderLayout.NORTH);

        // Bang hien thi dich vu
        String[] cot = {
            "Ma DV",
            "Ma loai DV",
            "Ten dich vu",
            "Don gia",
            "Don vi tinh",
            "Mo ta",
            "Trang thai"
        };

        model = new DefaultTableModel(cot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDichVu = new JTable(model);
        tblDichVu.setRowHeight(25);
        tblDichVu.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        tblDichVu.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(tblDichVu), BorderLayout.CENTER);

        // Su kien cac nut
        btnThem.addActionListener(e -> themDichVu());
        btnCapNhat.addActionListener(e -> capNhatDichVu());
        btnNgung.addActionListener(e -> ngungHoatDong());
        btnXoaTrang.addActionListener(e -> xoaTrangForm());

        btnTimKiem.addActionListener(e -> timKiemDichVu());

        txtTimKiem.addActionListener(e -> timKiemDichVu());

        btnLamMoi.addActionListener(e -> {
            txtTimKiem.setText("");
            xoaTrangForm();
            taiDanhSach();
        });
        btnKhoiPhuc.addActionListener(e -> khoiPhucDichVu());
        // Chon mot dong trong bang
        tblDichVu.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTin();
            }
        });
    }

    // Doc du lieu tu database va hien thi len bang
    private void taiDanhSach() {
        try {
            hienThiDanhSach(dao.getAll());
        } catch (SQLException e) {
            baoLoi("Khong the tai danh sach dich vu.", e);
        }
    }

    private void hienThiDanhSach(List<DichVu> danhSach) {

        model.setRowCount(0);

        for (DichVu dv : danhSach) {
            model.addRow(new Object[]{
                dv.getMaDV(),
                dv.getMaLoaiDV(),
                dv.getTenDV(),
                dv.getDonGia(),
                dv.getDonViTinh(),
                dv.getMoTa(),
                dv.isTrangThai()
                        ? "Dang hoat dong"
                        : "Ngung hoat dong"
            });
        }
    }

    private void khoiPhucDichVu() {

        if (maDVDangChon <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hay chon dich vu can khoi phuc."
            );
            return;
        }

        if (chkTrangThai.isSelected()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Dich vu nay dang hoat dong."
            );
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Ban co muon khoi phuc dich vu nay?",
                "Xac nhan",
                JOptionPane.YES_NO_OPTION
        );

        if (luaChon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (dao.restore(maDVDangChon)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Khoi phuc dich vu thanh cong!"
                );

                xoaTrangForm();
                taiDanhSach();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Khong the khoi phuc dich vu."
                );
            }

        } catch (SQLException e) {
            baoLoi("Khoi phuc dich vu that bai.", e);
        }
    }

    // Dua thong tin dong duoc chon len form
    private void hienThiThongTin() {

        int row = tblDichVu.getSelectedRow();

        if (row < 0) {
            return;
        }

        maDVDangChon = (int) model.getValueAt(row, 0);

        txtMaDV.setText(String.valueOf(maDVDangChon));
       int maLoai = (int) model.getValueAt(row, 1);
for (int i = 0; i < cboLoaiDV.getItemCount(); i++) {
    LoaiDichVu loai = cboLoaiDV.getItemAt(i);

    if (loai.getMaLoaiDV() == maLoai) {
        cboLoaiDV.setSelectedIndex(i);
        break;
    }
}
        txtTenDV.setText(
                giaTriChuoi(model.getValueAt(row, 2))
        );
        txtDonGia.setText(
                giaTriChuoi(model.getValueAt(row, 3))
        );
        txtDonViTinh.setText(
                giaTriChuoi(model.getValueAt(row, 4))
        );
        txtMoTa.setText(
                giaTriChuoi(model.getValueAt(row, 5))
        );

        String trangThai = giaTriChuoi(
                model.getValueAt(row, 6)
        );

        chkTrangThai.setSelected(
                trangThai.equals("Dang hoat dong")
        );
    }

    // Lay thong tin tu form
    
private DichVu layDuLieuForm() {

    LoaiDichVu loai =
            (LoaiDichVu) cboLoaiDV.getSelectedItem();

    String ten = txtTenDV.getText().trim();
    String gia = txtDonGia.getText().trim();
    String donVi = txtDonViTinh.getText().trim();
    String moTa = txtMoTa.getText().trim();

    if (loai == null) {
        throw new IllegalArgumentException(
                "Hay chon loai dich vu."
        );
    }

    if (ten.isEmpty() || gia.isEmpty() || donVi.isEmpty()) {
        throw new IllegalArgumentException(
                "Hay nhap day du ten dich vu, don gia "
                + "va don vi tinh."
        );
    }

    BigDecimal donGia;

    try {
        donGia = new BigDecimal(gia);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
                "Don gia phai la so hop le, vi du: 50000"
        );
    }

    if (donGia.compareTo(BigDecimal.ZERO) < 0) {
        throw new IllegalArgumentException(
                "Don gia khong duoc am."
        );
    }

    donGia = donGia.setScale(2, RoundingMode.HALF_UP);

    return new DichVu(
            loai.getMaLoaiDV(),
            ten,
            donGia,
            donVi,
            moTa,
            chkTrangThai.isSelected()
    );
}


    // Them dich vu
    private void themDichVu() {

        try {
            DichVu dv = layDuLieuForm();

            int luaChon = JOptionPane.showConfirmDialog(
                    this,
                    "Ban co chac muon them dich vu nay?",
                    "Xac nhan",
                    JOptionPane.YES_NO_OPTION
            );

            if (luaChon != JOptionPane.YES_OPTION) {
                return;
            }

            if (dao.insert(dv)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Them dich vu thanh cong!"
                );

                xoaTrangForm();
                taiDanhSach();
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Du lieu khong hop le",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (SQLException e) {
            baoLoi("Them dich vu that bai.", e);
        }
    }

    // Cap nhat dich vu
    private void capNhatDichVu() {

        if (maDVDangChon <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hay chon dich vu can cap nhat trong bang."
            );
            return;
        }

        try {
            DichVu dv = layDuLieuForm();
            dv.setMaDV(maDVDangChon);

            if (dao.update(dv)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cap nhat dich vu thanh cong!"
                );

                xoaTrangForm();
                taiDanhSach();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Khong tim thay dich vu de cap nhat."
                );
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Du lieu khong hop le",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (SQLException e) {
            baoLoi("Cap nhat dich vu that bai.", e);
        }
    }

    // Xoa mem: chuyen TrangThai ve 0
    private void ngungHoatDong() {

        if (maDVDangChon <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hay chon dich vu can ngung hoat dong."
            );
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Dich vu se duoc chuyen sang ngung hoat dong. "
                + "Ban co chac khong?",
                "Xac nhan ngung hoat dong",
                JOptionPane.YES_NO_OPTION
        );

        if (luaChon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (dao.delete(maDVDangChon)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Da ngung hoat dong dich vu."
                );

                xoaTrangForm();
                taiDanhSach();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Dich vu da ngung hoat dong "
                        + "hoac khong ton tai."
                );
            }

        } catch (SQLException e) {
            baoLoi("Ngung hoat dong dich vu that bai.", e);
        }
    }

    // Tim kiem theo ten dich vu
    private void timKiemDichVu() {

        String tuKhoa = txtTimKiem.getText().trim();

        try {
            if (tuKhoa.isEmpty()) {
                taiDanhSach();
            } else {
                hienThiDanhSach(dao.searchByName(tuKhoa));
            }

            xoaTrangForm();

        } catch (SQLException e) {
            baoLoi("Tim kiem dich vu that bai.", e);
        }
    }

    // Xoa cac o nhap lieu
    private void xoaTrangForm() {

        maDVDangChon = -1;

        txtMaDV.setText("");
        if (cboLoaiDV.getItemCount() > 0) {
    cboLoaiDV.setSelectedIndex(0);
}
        txtTenDV.setText("");
        txtDonGia.setText("");
        txtDonViTinh.setText("");
        txtMoTa.setText("");

        chkTrangThai.setSelected(true);

        tblDichVu.clearSelection();
    }

    private String giaTriChuoi(Object value) {
        return value == null ? "" : value.toString();
    }

    private void baoLoi(String thongBao, SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                thongBao + "\nChi tiet: " + e.getMessage(),
                "Loi database",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Chay giao dien doc lap de kiem tra
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quan ly dich vu khach san");

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setContentPane(new DichVuPanel());
            frame.setSize(1100, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
