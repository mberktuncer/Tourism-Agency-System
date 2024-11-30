package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.User;
import main.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_user_list;
    private JPanel pnl_user_list;
    private JPanel pnl_filter_role;
    private JComboBox cmb_search_user_role;
    private JButton btn_listbyrole_user;
    private JTextField fld_name;
    private JTextField fld_surname;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_add_user;
    private JTextField fld_delete_id;
    private JButton btn_delete;
    private JComboBox cmb_role;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    public AdminGUI() {

        GUIHelper.setLookAndFeel();
        initializeGUI();
        initializeEvents();
        setVisible(true);

        mdl_user_list = GUIHelper.createTableModel();
        Object[] columnUserList = {"Id", "Name", "Surname", "User Name", "Role"};
        mdl_user_list.setColumnIdentifiers(columnUserList);
        row_user_list = new Object[columnUserList.length];
        loadUserModel(Constants.ROLES_ALL);

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

    }

    private void loadUserModel(String roleFilter){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        List<User> users;

        if (roleFilter == null || roleFilter.equals("All Roles")){
            users = UserService.listAll();
        }
        else {
            users = UserService.getUsersByRole(roleFilter);
        }

        for (User user : users){
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
        populateRoles();
    }

    private void populateRoles() {
        cmb_search_user_role.addItem(Constants.ROLES_ALL);
        List<String> roles = UserService.getUserRoles();
        for (String role : roles) {
            cmb_role.addItem(role);
            cmb_search_user_role.addItem(role);
        }
    }

    private void initializeEvents() {
        btn_logout.addActionListener(e -> dispose());

        btn_add_user.addActionListener(e -> {
            if (GUIHelper.isFieldEmpty(fld_name) || GUIHelper.isFieldEmpty(fld_surname) ||GUIHelper.isFieldEmpty(fld_username) ||
                GUIHelper.isFieldEmpty(fld_password)){
                GUIHelper.showMessage(Constants.MSG_FILL);
            }
            else{
                String name = fld_name.getText();
                String surname = fld_surname.getText();
                String userName = fld_username.getText();
                char[] password = fld_password.getPassword();
                String passwordString = new String(password);
                String role = cmb_role.getSelectedItem().toString();
                java.util.Arrays.fill(password, '\0');
                if (UserService.add(name, surname, userName, passwordString, role)){
                    GUIHelper.showMessage(Constants.MSG_DONE);
                    loadUserModel(Constants.ROLES_ALL);
                    fld_name.setText(null);
                    fld_surname.setText(null);
                    fld_username.setText(null);
                    fld_password.setText(null);
                }
            }
        });

        btn_listbyrole_user.addActionListener(e -> {
            String role = cmb_search_user_role.getSelectedItem().toString();
            loadUserModel(role);
        });

    }

    public static void main(String[] args) {

        GUIHelper.setLookAndFeel();
        AdminGUI adminGUI = new AdminGUI();

    }
}
