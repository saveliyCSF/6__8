package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import com.company.util.ArrayUtils;
import com.company.util.JTableUtils;
import com.company.util.SwingUtils;;
public class GUI extends JFrame{
    private JTable tableInput;
    private JButton buttonSave;
    private JButton buttonExecute;
    private JButton buttonLoad;
    private JTextArea textAreaResult;
    private JPanel panelMain;
    private JButton buttonRandom;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    GUI(){
        setTitle("Window");
        setSize(800, 500);
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTableUtils.initJTableForArray(tableInput,40,false,true,false,true);


        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        buttonExecute.addActionListener(e -> {
            try {
                textAreaResult.setText(null);
                int[] arr = JTableUtils.readIntArrayFromJTable(tableInput);
                ArrayList<Integer> list = new Solution().solution(arr);
                for (Integer integer : list) {
                    textAreaResult.append(integer + " ");
                }

            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        buttonLoad.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr = ArrayUtils.createRandomIntArray(tableInput.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = JTableUtils.readIntArrayFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });


        setVisible(true);
    }
}
