package patterns.MVC_gui;

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

    /** Bind model to this view and bind  controller to model.
     * Register this view as observer to model.
     */
    public View() {
        this.model = new Model();
        this.controller = new Controller(model);
        model.registerObserver(this);

        initDisplayedComponents();
        viewFrame.setVisible(true);
    }

    @Override
    public void update() {
        viewLabel.setText(String.format("%d", model.getDisplayedNumber()));
        viewFrame.pack();
    }

    private void initDisplayedComponents() {
        viewFrame = new JFrame();
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewPanel = new JPanel();
        viewLabel = new JLabel();
        viewLabel.setText(String.format("%d",model.getDisplayedNumber()));

        /* When this button is pressed, controller increments number */
        viewPanel.add(viewLabel);
        JButton pressMe = new myButton("Plus one");
        viewPanel.add(pressMe);


        viewFrame.add(viewPanel);
        viewFrame.pack();
    }

    /**
     * Button with {@link java.awt.event.ActionListener} bounded to it.
     * Thit button increments number when pressed.
     */
    private class myButton extends JButton {
        public myButton(String str) {
            super(str);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.incrementDisplayedNumber();
                }
            });
        }
    }
}
