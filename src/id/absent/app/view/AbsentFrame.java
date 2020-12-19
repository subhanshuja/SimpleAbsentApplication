package id.absent.app.view;

import id.absent.app.common.AbsentService;
import id.absent.app.common.ConfigDatabase;
import id.absent.app.model.AbsentIn;
import id.absent.app.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class AbsentFrame extends JFrame implements AbsentService {

    private JLabel jlName = new JLabel("Enter Your Name");
    private JLabel jlNim = new JLabel("Enter Your Nim");
    private JTextField jtfName = new JTextField(30);
    private JTextField jtfNim = new JTextField(10);;
    private JButton jbAbsent = new JButton("Absent");

    private JLabel jlStatusAbsent = new JLabel("");

    private String name, nim;

    public AbsentFrame() {
        super("Simple Absent Application");
        initView();
        doAbsent();
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
        constraints.gridy = 2;
        base.add(jlStatusAbsent, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        base.add(jbAbsent, constraints);

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
                    User user = new User();
                    user.setName(name);
                    user.setNim(nim);

                    AbsentIn absentIn = new AbsentIn(user);
                    absentIn.setStartDate();

                    try {
                        insertAbsent(absentIn);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    jlStatusAbsent.setText("woke "+user.getName()+" "+user.getNim()+" "+absentIn.getStartDate());
                } else {
                    jlStatusAbsent.setText("empty");
                }
            }
        });
    }

    @Override
    public void insertAbsent(AbsentIn absentIn) throws Exception {
        String qAbsent = " insert into absentin (name, nim, absent)"+"values (?, ?, ?)";
        Connection connection = new ConfigDatabase().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(qAbsent);
        preparedStatement.setString(1, absentIn.getUserByName());
        preparedStatement.setString(2, absentIn.getUserByNim());
        preparedStatement.setInt(3, (int) absentIn.getStartDate());
        preparedStatement.execute();
    }
}
