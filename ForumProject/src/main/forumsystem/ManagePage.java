package forumsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ManagePage extends JFrame {
    private JList<String> sectionList;
    private DefaultListModel<String> listModel;
    private JButton createButton, editButton, deleteButton;

    public ManagePage() {
        setTitle("板块管理");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        sectionList = new JList<>(listModel);
        createButton = new JButton("创建");
        editButton = new JButton("编辑");
        deleteButton = new JButton("删除");

        loadSections();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(sectionList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSection();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSection();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSection();
            }
        });
    }

    private void loadSections() {
        try (BufferedReader reader = new BufferedReader(new FileReader("sections.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listModel.addElement(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSection() {
        String newSection = JOptionPane.showInputDialog("输入新板块名称:");
        if (newSection != null && !newSection.isEmpty()) {
            listModel.addElement(newSection);
            saveSections();
        }
    }

    private void editSection() {
        String selectedSection = sectionList.getSelectedValue();
        if (selectedSection != null) {
            String newName = JOptionPane.showInputDialog("编辑板块名称:", selectedSection);
            if (newName != null && !newName.isEmpty()) {
                int index = sectionList.getSelectedIndex();
                listModel.set(index, newName);
                saveSections();
            }
        }
    }

    private void deleteSection() {
        String selectedSection = sectionList.getSelectedValue();
        if (selectedSection != null) {
            listModel.removeElement(selectedSection);
            saveSections();
        }
    }

    private void saveSections() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sections.txt"))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.write(listModel.getElementAt(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}