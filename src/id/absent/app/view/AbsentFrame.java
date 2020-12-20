package id.absent.app.view;

import id.absent.app.common.AbsentService;
import id.absent.app.common.ConfigDatabase;
import id.absent.app.controller.ValidAbsent;
import id.absent.app.model.Absent;
import id.absent.app.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AbsentFrame extends JFrame implements AbsentService {

    private JLabel jlName = new JLabel("Enter Your Name");
    private JLabel jlNim = new JLabel("Enter Your Nim");
    private JTextField jtfName = new JTextField(30);
    private JTextField jtfNim = new JTextField(10);
    private JButton jbAbsent = new JButton("Absent");
    private JButton jbLeft = new JButton("Left");

    private JLabel jlStatusAbsent = new JLabel("");

    private String name, nim;

    User user;
    Absent absent;
    ValidAbsent validAbsent;

    public AbsentFrame() {
        super("Simple Absent Application");
        initView();
        doAbsent();
        doLeft();
    }

    private void initView() {
        JPanel base = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        base.add(jlName, constraints);

        constraints.gridx = 1;
        base.add(jtfName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        base.add(jlNim, constraints);

        constraints.gridx = 1;
        base.add(jtfNim, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        base.add(jlStatusAbsent, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        base.add(jbAbsent, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        base.add(jbLeft, constraints);

        base.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Absent Panel"));

        add(base);

        pack();
        setLocationRelativeTo(null);
    }

    private void doAbsent() {
        jbAbsent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = jtfName.getText().toString();
                nim = jtfNim.getText().toString();

                if (!name.equals("") && !nim.equals(""))  {
                    user = new User();
                    user.setName(name);
                    user.setNim(nim);

                    absent = new Absent(user);
                    absent.setStartDate();

                    validAbsent = new ValidAbsent(absent);

                    try {
                        insertAbsent(absent);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    jlStatusAbsent.setText("status:"+validAbsent.showAbsent()+
                            "name: "+user.getName()+
                            "nim: "+user.getNim()+
                            "start class "+absent.getStartDate()+
                            "end class"+absent.getEndDate());
                } else {
                    jlStatusAbsent.setText("empty");
                }
            }
        });
    }

    private void doLeft() {
        jbLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = jtfName.getText().toString();
                nim = jtfNim.getText().toString();

                if (!name.equals("") && !nim.equals(""))  {
                    user = new User();
                    user.setName(name);
                    user.setNim(nim);

                    absent = new Absent(user);
                    absent.setEndDate();

                    validAbsent = new ValidAbsent(absent);

                    try {
                        updateAbsent(absent);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    jlStatusAbsent.setText("status:"+validAbsent.showAbsent()+
                            "name: "+user.getName()+
                            "nim: "+user.getNim()+
                            "start class "+absent.getStartDate()+
                            "end class"+absent.getEndDate());

                } else {
                    jlStatusAbsent.setText("empty");
                }
            }
        });
    }

    @Override
    public void insertAbsent(Absent absent) throws Exception {
        String qAbsent = " insert into absent (name, nim, absentin, absentout)"+"values (?, ?, ?, ?)";
        Connection connection = new ConfigDatabase().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(qAbsent);
        preparedStatement.setString(1, absent.getUserByName());
        preparedStatement.setString(2, absent.getUserByNim());
        preparedStatement.setInt(3, (int) absent.getStartDate());
        preparedStatement.setInt(4, 0);
        preparedStatement.execute();

        connection.close();
    }

    @Override
    public void updateAbsent(Absent absent) throws Exception {
        String query = "update absent set absentout = ? where name = ?";
        Connection connection = new ConfigDatabase().getConnection();
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt   (1, (int) absent.getEndDate());
        preparedStmt.setString(2, absent.getUserByName());
        preparedStmt.executeUpdate();

        connection.close();
    }
}
