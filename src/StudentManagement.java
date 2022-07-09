import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import java.util.Vector;

public class StudentManagement extends JFrame {
    private JButton btnThem, btnXoa, btnImport, btnExport;
    private JTable tblDsSV;

    public StudentManagement(String title) {
        super(title);
        initComponents();
        addEvents();
        showWindow();
    }

    private void initComponents() {
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Student Management");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 201));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        pnTop.add(lblTitle, BorderLayout.CENTER);

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("MSSV");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("Địa chỉ");
        defaultTableModel.addColumn("GPA");
        tblDsSV = new JTable(defaultTableModel);
        tblDsSV.setShowGrid(true);
        tblDsSV.setGridColor(Color.BLACK);
        tblDsSV.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        tblDsSV.setDefaultEditor(Object.class, null);
        JTableHeader header = tblDsSV.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(78, 138, 201));
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        header.setReorderingAllowed(false);
        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tblDsSV.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tblDsSV.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tblDsSV.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tblDsSV.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        JScrollPane scrollPane = new JScrollPane(tblDsSV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnCenter.add(scrollPane, BorderLayout.CENTER);

        JPanel pnBottom = new JPanel();
        pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        Border lineBorder = BorderFactory.createLineBorder(new Color(78, 138, 201), 2);
        TitledBorder titledBorder = new TitledBorder(lineBorder, "Chức năng");
        titledBorder.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 18));
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(new Color(78, 138, 201));
        pnBottom.setBorder(titledBorder);
        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(78, 138, 201));
        btnThem.setForeground(Color.WHITE);
        pnBottom.add(btnThem);
        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(78, 138, 201));
        btnXoa.setForeground(Color.WHITE);
        pnBottom.add(btnXoa);
        btnImport = new JButton("Import");
        btnImport.setBackground(new Color(78, 138, 201));
        btnImport.setForeground(Color.WHITE);
        pnBottom.add(btnImport);
        btnExport = new JButton("Export");
        btnExport.setBackground(new Color(78, 138, 201));
        btnExport.setForeground(Color.WHITE);
        pnBottom.add(btnExport);

        Container con = this.getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnBottom, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    private void addEvents() {
        btnThem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel = (DefaultTableModel) tblDsSV.getModel();
                Vector<Object> vector = new Vector<>();
                vector.add("20207601");
                vector.add("Phạm Ngọc Hải");
                vector.add("Nam Định");
                vector.add(Math.round(100.0 * (new Random().nextDouble() * (4.0 - 2.5) + 2.5)) / 100.0);
                defaultTableModel.addRow(vector);
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblDsSV.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(StudentManagement.this, "Không có gì để xóa!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int rowSelected = tblDsSV.getSelectedRow();
                    if (rowSelected != -1) {
                        ((DefaultTableModel) tblDsSV.getModel()).removeRow(rowSelected);
                    } else {
                        JOptionPane.showMessageDialog(StudentManagement.this, "Chưa chọn hàng cần xóa!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(StudentManagement.this);
                File saveFile = fileChooser.getSelectedFile();
                if (saveFile != null) {
                    String filePath = saveFile.toString();
                    if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
                        filePath = filePath + ".xlsx";
                    }
                    if (ExportExcel.export(tblDsSV, filePath)) {
                        JOptionPane.showMessageDialog(StudentManagement.this, "Xuất file excel thành công!", "Thông " +
                                "báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(StudentManagement.this, "Xuất file excel thất bại!", "Thông " +
                                "báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void showWindow() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> new StudentManagement("Student Management"));
    }
}
