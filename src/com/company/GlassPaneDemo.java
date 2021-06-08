package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlassPaneDemo {
    static private MyGlassPane myGlassPane;
    static int value = 0;
    public GlassPaneDemo() {
        createAndShowGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */


    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("GlassPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 600));

        //Start creating and adding components.
        JCheckBox changeButton =
                new JCheckBox("Glass pane \"visible\"");
        changeButton.setSelected(false);


        //Set up the content pane, where the "main GUI" lives.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(changeButton);
        JButton incButton = new JButton("+1");
        contentPane.add(new JButton("OK"));
        contentPane.add(incButton);
        JLabel aLabel = new JLabel("0");
        contentPane.add(aLabel);
        incButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value++;
                aLabel.setText(String.valueOf(value));
            }
        });

        //Set up the menu bar, which appears above the content pane.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.add(new JMenuItem("Do nothing"));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitMenuItem);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //Set up the glass pane, which appears over both menu bar
        //and content pane and is an item listener on the change
        //button.
        myGlassPane = new MyGlassPane(
                changeButton,
                menuBar,
                frame.getContentPane());
        changeButton.addItemListener(myGlassPane);
        frame.setGlassPane(myGlassPane);

        //Show the window.
        frame.pack();
        frame.setVisible(true);
    }
}
