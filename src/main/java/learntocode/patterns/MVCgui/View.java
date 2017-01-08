package learntocode.patterns.MVCgui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View is GUI which can be seen by user. It can only display items with it's own methods.
 * Inner state of view is managed by {@link Model}. You can query model parameters directly.
 * To change parameters use {@link Controller} bounded to model upon which view is based on.
 */
public class View implements Observer{
    private Model model;
    private Controller controller;

    JFrame viewFrame;
    JPanel viewPanel;
    JLabel viewLabel;


    public View() {
        this.model = new Model();
        this.controller = new Controller(model);

        model.registerObserver(this);
        viewFrame = new JFrame();
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewPanel = new JPanel();
        viewLabel = new JLabel();
        viewLabel.setText(String.format("%d",model.getDisplayedNumber()));

        viewPanel.add(viewLabel);
        JButton pressMe = new JButton("Plus one");
        pressMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.incrementDisplayedNumber();
            }
        });

        viewPanel.add(pressMe);


        viewFrame.add(viewPanel);
        viewFrame.pack();
        viewFrame.setVisible(true);

    }

    @Override
    public void update() {
        viewLabel.setText(String.format("%d", model.getDisplayedNumber()));
        viewFrame.pack();
    }
}
