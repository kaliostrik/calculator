package ru.medvedev;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainWindow extends JFrame {
    String action = null;
    Double firstN = null;
    Double secondN = null;
    Boolean hasDOT = false;

    JTextField display = new JTextField();

    public MainWindow() {
        super("Calculator");
        initCompoments();
    }

    public void initCompoments() {
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 200, 300, 200);

        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setText("0");

        JPanel wPanel = new JPanel(); //косинус, синус и т.д.
        JPanel cPanel = new JPanel(); //цифры
        JPanel ePanel = new JPanel(); //операции

        wPanel.setLayout(new GridLayout(5, 2, 2, 2)); //строки, столбцы
        cPanel.setLayout(new GridLayout(4, 2, 2, 2));
        ePanel.setLayout(new GridLayout(4, 2, 2, 2));

        JButton buttonCOS = new JButton("cos");
        JButton buttonSIN = new JButton("sin");
        JButton buttonTG = new JButton("tg");
        JButton buttonCTG = new JButton("ctg");
        JButton buttonLN = new JButton("ln");
        JButton buttonSQR = new JButton("x^y");
        JButton buttonPM = new JButton("±");
        JButton buttonBackspace = new JButton("<-"); //не забыть про hasDOT
        JButton buttonC = new JButton("C"); //не забыть про hasDOT
        JButton buttonCE = new JButton("CE"); //не забыть про hasDOT

        JButton buttonPLUS = new JButton("+");
        buttonPLUS.addActionListener(e -> {setAction("+");});
        JButton buttonMINUS = new JButton("-");
        buttonPLUS.addActionListener(e -> {setAction("-");});
        JButton buttonDIVIDE = new JButton("/");
        buttonPLUS.addActionListener(e -> {setAction("/");});
        JButton buttonX = new JButton("*");
        buttonPLUS.addActionListener(e -> {setAction("*");});


        JButton n1 = new JButton("1");
        n1.addActionListener(e -> {addNumeral(1.0);});
        JButton n2 = new JButton("2");
        n2.addActionListener(e -> {addNumeral(2.0);});
        JButton n3 = new JButton("3");
        n3.addActionListener(e -> {addNumeral(3.0);});
        JButton n4 = new JButton("4");
        n4.addActionListener(e -> {addNumeral(4.0);});
        JButton n5 = new JButton("5");
        n5.addActionListener(e -> {addNumeral(5.0);});
        JButton n6 = new JButton("6");
        n6.addActionListener(e -> {addNumeral(6.0);});
        JButton n7 = new JButton("7");
        n7.addActionListener(e -> {addNumeral(7.0);});
        JButton n8 = new JButton("8");
        n8.addActionListener(e -> {addNumeral(8.0);});
        JButton n9 = new JButton("9");
        n9.addActionListener(e -> {addNumeral(9.0);});
        JButton n0 = new JButton("0");
        n0.addActionListener(e -> {addNumeral(0.0);});
        JButton buttonDOT = new JButton("."); //не забыть про hasDOT
        JButton buttonEQUALLY = new JButton("=");

        cPanel.add(n1);
        cPanel.add(n2);
        cPanel.add(n3);
        cPanel.add(n4);
        cPanel.add(n5);
        cPanel.add(n6);
        cPanel.add(n7);
        cPanel.add(n8);
        cPanel.add(n9);
        cPanel.add(n0);
        cPanel.add(buttonDOT);
        cPanel.add(buttonEQUALLY);

        wPanel.add(buttonCOS);
        wPanel.add(buttonSIN);
        wPanel.add(buttonTG);
        wPanel.add(buttonCTG);
        wPanel.add(buttonLN);
        wPanel.add(buttonSQR);
        wPanel.add(buttonPM);
        wPanel.add(buttonBackspace);
        wPanel.add(buttonC);
        wPanel.add(buttonCE);

        ePanel.add(buttonPLUS);
        ePanel.add(buttonMINUS);
        ePanel.add(buttonDIVIDE);
        ePanel.add(buttonX);

        add(wPanel, BorderLayout.WEST);
        add(cPanel, BorderLayout.CENTER);
        add(ePanel, BorderLayout.EAST);

        add(display, BorderLayout.NORTH);
    }

    public void setDisplay(Double first, String action, Double second) {
        String r = "";

        if(first == null && action == null && second == null) {
            r = "0";
        } else  if (first != null) {
            if (Objects.equals(first.toString().substring(first.toString().length() - 2), ".0")) {
                r = first.toString().substring(0, first.toString().length()-2);
            } else {
                r = first.toString();
            }
        } else if (action != null) {
            r = r+" "+action;
        } else if (second != 0) {
            if (Objects.equals(second.toString().substring(second.toString().length() - 2), ".0")) {
                r = r+" "+second.toString().substring(0, second.toString().length()-2);
            } else {
                r = r+" "+second.toString();
            }
        }

        display.setText(r);
    }

    public void addNumeral(Double numeral) {
        if (firstN == null && action == null && secondN == null) {
            firstN = numeral;
        } else if (firstN != null && action == null) {
            if (hasDOT == false) {
                //удалить .0 и приклеить цифру
                firstN = Double.parseDouble(firstN.toString().substring(0, firstN.toString().length()-2)+numeral);
            } else {
                //приклеить цифру и удалить .0
                firstN = Double.parseDouble(firstN.toString()+numeral.toString().substring(0,numeral.toString().length()-2));
            }
        } else if (firstN != null && action != null && secondN == null) {
            hasDOT = false; //на всякий случай
            secondN = numeral;
        } else if (firstN != null && action != null && secondN != null) {
            if (hasDOT == false) {
                //удалить .0 и приклеить цифру
                secondN = Double.parseDouble(secondN.toString().substring(0, secondN.toString().length()-2)+numeral);
            } else {
                //приклеить цифру и удалить .0
                secondN = Double.parseDouble(secondN.toString()+numeral.toString().substring(0,numeral.toString().length()-2));
            }
        }

        setDisplay(firstN, action, secondN);
    }

    //НЕ РАБОТАЕТ :(
    public void setAction(String selaction) {
        System.out.println(firstN +" "+ action +" "+ secondN);
        if(Objects.equals(action, null)) {
            hasDOT = false;
            if (Objects.equals(selaction, "+")) {
                action = "+";
            } else if (Objects.equals(selaction, "-")){
                action = "-";
            } else if (Objects.equals(selaction, "/")) {
                action = "/";
            } else if (Objects.equals(selaction, "*")) {
                action = "*";
            }
            setDisplay(firstN, action, secondN);
        } else {
            //setResult();
            //setAction(selaction);
        }
    }


}
