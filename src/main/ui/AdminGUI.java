package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.User;
import main.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_user_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    public AdminGUI() {

        GUIHelper.setLookAndFeel();
        initializeGUI();
        initializeEvents();
        setVisible(true);

        mdl_user_list = GUIHelper.createTableModel(new String[]{"ID", "Name", "Surname", "User Name",  "Role"});
        row_user_list = new Object[5];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

    }

    private void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User user : UserService.listAll()){
            int i = 0;
            row_user_list[i++] = user.getId();
            row_user_list[i++] = user.getName();
            row_user_list[i++] = user.getSurname();
            row_user_list[i++] = user.getUserName();
            row_user_list[i++] = user.getRole();
            mdl_user_list.addRow(row_user_list);
        }
    }

    private void initializeGUI() {
        add(wrapper);
        setSize(1000, 500);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
    }

    private void initializeEvents() {
        btn_logout.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        AdminGUI adminGUI = new AdminGUI();


    }
}
