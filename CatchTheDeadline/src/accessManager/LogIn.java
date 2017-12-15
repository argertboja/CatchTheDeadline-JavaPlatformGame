package accessManager;

// Import
import database.DBInterface;
import userinterface.MainMenu;
import window.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogIn extends JFrame implements ActionListener {

    // Properties
    private JTextField username;
    private JPasswordField pasw;
    private JLabel username_txt, pasw_txt, loginImage;
    private JButton login, register, playAsGuest;
    private DBInterface db;
    private GameEngine gameEngine;
    private MainMenu mainMenu;
    public static String usernameValue;

    // Constructor
    public LogIn() {
        setTitle ("Catch The Deadline - Login");
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        getContentPane().setLayout (null);
        setSize(408, 608);
        setLocationRelativeTo(null);
        setResizable(false);
        db= new DBInterface();
        // Set Frame Icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
        this.setIconImage(imageIcon.getImage());

        // Texts
        username_txt = new JLabel("Username");
        username_txt.setBounds(50,280,150,50);
        username_txt.setFont(new Font("Monospaced", Font.BOLD, 18));
        username_txt.setForeground(new Color(10,132,199));
        add(username_txt);

        pasw_txt = new JLabel("Password");
        pasw_txt.setBounds(50,328,150,50);
        pasw_txt.setFont(new Font("Monospaced", Font.BOLD, 18));
        pasw_txt.setForeground(new Color(10,132,199));
        add(pasw_txt);

        // Login Image
        ImageIcon loginImageIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        loginImage = new JLabel(loginImageIcon);
        loginImage.setBounds(110,50,180,220);
        add(loginImage);

        // Username field
        username = new JTextField();
        username.setText("Username");
        username.setBounds(200, 290, 150, 30);
        username.setForeground(Color.GRAY);


        // Place holder hide effect
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                    username.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setForeground(Color.GRAY);
                    username.setText("Username");
                }
            }
        });
        add(username);

        // Password Field
        pasw = new JPasswordField();
        pasw.setText("Password");
        pasw.setBounds(200, 338, 150, 30);
        pasw.setForeground(Color.GRAY);
        // Place holder hide effect
        pasw.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (pasw.getText().equals("Password")) {
                    pasw.setText("");
                    pasw.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (pasw.getText().isEmpty()) {
                    pasw.setForeground(Color.GRAY);
                    pasw.setText("Password");
                }
            }
        });
        add(pasw);

        // Login Button
        login = new JButton( "Log In");
        login.setBounds(80, 400, 100, 30);
        login.addActionListener(this);
        login.setIcon(new ImageIcon(getClass().getResource("/images/login.png")));
        login.setMargin(new Insets(1,1,1,1));
        add(login);

        // Register Button
        register = new JButton( "Register");
        register.setBounds(220, 400, 100, 30);
        register.addActionListener(this);
        register.setMargin(new Insets(1,1,1,1));
        register.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        add(register);

        // PlayAsGuest Button
        playAsGuest = new JButton( "Guest");
        playAsGuest.setBounds(150, 450, 100, 30);
        playAsGuest.addActionListener(this);
        playAsGuest.setMargin(new Insets(1,1,1,1));
        playAsGuest.setIcon(new ImageIcon(getClass().getResource("/images/guest.png")));
        add(playAsGuest);

        // Make the frame visible
        setVisible(true);
    }

    // Password checker
    public boolean checkPasw() throws ClassNotFoundException, SQLException {
        String userValue = username.getText();
        String paswValue = new String (pasw.getPassword());
        ResultSet rs = db.checkPasw(userValue);
        return rs.getString(1).equalsIgnoreCase(userValue) && rs.getString(2).equals(paswValue);
    }

    // Register a new user
    private void addUser() throws SQLException, ClassNotFoundException {
        String userValue = username.getText();
        String paswValue = new String (pasw.getPassword());
        db.createUser(userValue, paswValue);
    }

    // Create data for a guest
    private void guestGame() throws SQLException, ClassNotFoundException {
        db.createUser("guest", "guest");
        db.updateGuestAccount("guest");
    }

    // Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(login)) {
            usernameValue = username.getText();
            try {
                if (checkPasw()){
                    gameEngine = new GameEngine(1, username.getText());
                    gameEngine.startTheGame();
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Username and passwords do not match or account does not exist!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(register)) {
            try {
                usernameValue = username.getText();
                addUser();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            gameEngine = new GameEngine(1, username.getText());
            gameEngine.startTheGame();
            dispose();
        }
        if (e.getSource().equals(playAsGuest)) {
            /*try {
                guestGame();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }*/
            usernameValue = username.getText();
            mainMenu = new MainMenu();
            dispose();
        }
    }
}

