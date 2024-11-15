package forumsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SearchPage extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;

    public SearchPage() {
        setTitle("搜索帖子");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // 将窗口定位在屏幕中央

        searchField = new JTextField();
        searchButton = new JButton("搜索");
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("关键词:"), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultList), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                searchPosts(keyword);
            }
        });

        resultList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedPost = resultList.getSelectedValue();
                if (selectedPost != null) {
                    showPostDetails(selectedPost);
                }
            }
        });
    }

    private void searchPosts(String keyword) {
        listModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("posts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && (parts[0].contains(keyword) || parts[1].contains(keyword))) { // 检查数组长度
                    listModel.addElement(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPostDetails(String postTitle) {
        StringBuilder details = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("posts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equals(postTitle)) { // 检查数组长度
                    details.append("标题: ").append(postTitle).append("\n");
                    details.append("作者: ").append(parts[2]).append("\n");
                    details.append("时间: ").append(parts[3]).append("\n");
                    details.append("内容: ").append(parts[1]).append("\n");
                    details.append("回复:\n");
                    loadReplies(postTitle, details);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, details.toString());
    }

    private void loadReplies(String postTitle, StringBuilder details) {
        try (BufferedReader reader = new BufferedReader(new FileReader("replies.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(postTitle)) { // 检查数组长度
                    details.append("回复作者: ").append(parts[2]).append("\n");
                    details.append("回复时间: ").append(parts[3]).append("\n");
                    details.append("回复内容: ").append(parts[1]).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}