package ru.medvedev;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main implements SwingConstants {
    public static void main(final String[] args) throws IOException, URISyntaxException {
        final MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}