package forumsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForumPage extends JFrame {
    private String username;
    private JButton postButton, browseButton, searchButton, manageButton;

    public ForumPage(String username) {
        this.username = username;
        setTitle("论坛主页");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);

        postButton = new JButton("发帖");
        browseButton = new JButton("浏览帖子");
        searchButton = new JButton("搜索帖子");
        manageButton = new JButton("管理板块");

        add(postButton);
        add(browseButton);
        add(searchButton);
        add(manageButton);

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostPage(username).setVisible(true);
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostListPage(username).setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchPage().setVisible(true);
            }
        });

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagePage().setVisible(true);
            }
        });
    }
}