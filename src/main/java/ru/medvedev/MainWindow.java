package ru.medvedev;

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
        buttonCOS.addActionListener(e -> {setTrigonometricFunction(1);});
        JButton buttonSIN = new JButton("sin");
        buttonSIN.addActionListener(e -> {setTrigonometricFunction(2);});
        JButton buttonTG = new JButton("tg");
        buttonTG.addActionListener(e -> {setTrigonometricFunction(3);});
        JButton buttonCTG = new JButton("ctg");
        buttonCTG.addActionListener(e -> {setTrigonometricFunction(4);});
        JButton buttonLN = new JButton("ln");
        buttonLN.addActionListener(e -> {setTrigonometricFunction(5);});
        JButton buttonSQR = new JButton("x^2");
        buttonSQR.addActionListener(e -> {setTrigonometricFunction(6);});
        JButton buttonPM = new JButton("±");
        buttonPM.addActionListener(e -> {setTrigonometricFunction(7);});
        JButton buttonBackspace = new JButton("<-");
        buttonBackspace.addActionListener(e -> {backSpace();});
        JButton buttonC = new JButton("C");
        buttonC.addActionListener(e -> {C();});
        JButton buttonCE = new JButton("CE");
        buttonCE.addActionListener(e -> {CE();});


        JButton buttonPLUS = new JButton("+");
        buttonPLUS.addActionListener(e -> {setAction(1);});
        JButton buttonMINUS = new JButton("-");
        buttonMINUS.addActionListener(e -> {setAction(2);});
        JButton buttonDIVIDE = new JButton("/");
        buttonDIVIDE.addActionListener(e -> {setAction(3);});
        JButton buttonX = new JButton("*");
        buttonX.addActionListener(e -> {setAction(4);});


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
        JButton buttonDOT = new JButton(".");
        buttonDOT.addActionListener(e -> {setDOT();});
        JButton buttonEQUALLY = new JButton("=");
        buttonEQUALLY.addActionListener(e -> {setResult(firstN,action, secondN);});

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

    public void setDisplay(Double first, String action2, Double second) {
        String r = "";

        if(first == null && action2 == null && second == null) {
            r = "0";
        }
        if (first != null) {
            if (Objects.equals(first.toString().substring(first.toString().length() - 2), ".0")) {
                r = first.toString().substring(0, first.toString().length()-2);
            } else {
                r = first.toString();
            }
        }
        if (action2 != null) {
            r = r+" "+action2;
        }
        if (second != null) {
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

    public void setAction(int selaction) {
        if(Objects.equals(action, null)) {
            hasDOT = false;
            if (selaction == 1) {
                action = "+";
            } else if (selaction == 2){
                action = "-";
            } else if (selaction == 3) {
                action = "/";
            } else if (selaction == 4) {
                action = "*";
            }
            System.out.println(firstN +" "+ action +" "+ secondN);
            setDisplay(firstN, action, secondN);
        } else {
            setResult(firstN, action, secondN);
            setAction(selaction);
        }
    }

    public void setResult(Double first, String action2, Double second) {
        Double result;

        if (first != null & action2 != null && second != null){
            hasDOT = false;
            firstN = null;
            secondN = null;
            action = null;
        }

        if (action2.equals("+")) {
            result = first + second;
            firstN = result;
            System.out.println(first.toString()+" + "+second.toString()+" = "+ result);
            setDisplay(result, null, null);
        } else if (action2.equals("-")) {
            result = first - second;
            firstN = result;
            System.out.println(first.toString()+" - "+second.toString()+" = "+ result);
            setDisplay(result, null, null);
        } else if (action2.equals("/")) {
            result = first / second;
            firstN = result;
            System.out.println(first.toString()+" / "+second.toString()+" = "+ result);
            setDisplay(result, null, null);
        } else if (action2.equals("*")) {
            result = first * second;
            firstN = result;
            System.out.println(first.toString()+" * "+second.toString()+" = "+ result);
            setDisplay(result, null, null);
        } else {
            //дурак чтоле?
        }
    }

    public void setDOT() {
        if (hasDOT == false) {
            hasDOT = true;
            firstN = firstN;
        } else {
            hasDOT = false;
            secondN = secondN;
        }
    }

    public void setTrigonometricFunction(int fun) {
        if (fun == 1) {
            //cos
            if (firstN != null && secondN == null){
                firstN = Math.cos(firstN);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 2) {
            //sin
            if (firstN != null && secondN == null){
                firstN = Math.sin(firstN);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 3) {
            //tg
            if (firstN != null && secondN == null){
                firstN = Math.tan(firstN);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 4 ) {
            //ctg
            if (firstN != null && secondN == null){
                firstN = 1.0 / Math.tan(firstN);//z=1.0 / Math.tan(x);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 5) {
            //ln
            if (firstN != null && secondN == null){
                firstN = Math.log(firstN);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 6) {
            //sqr
            if (firstN != null && secondN == null){
                firstN = Math.pow(firstN, 2);
                setDisplay(firstN, action, secondN);
            }
        } else if (fun == 7) {
            //inv
            if (firstN != null && secondN == null){
                if(firstN > 0 ){
                    firstN = firstN - firstN - firstN;
                } else {
                    firstN = firstN * (-1);
                }
                setDisplay(firstN, action, secondN);
            } else {
                if(secondN > 0 ){
                    secondN = secondN - secondN - secondN;
                } else {
                    secondN = secondN * (-1);
                }
                setDisplay(firstN, action, secondN);
            }
        }
    }

    public void CE() {
        if (secondN == null) {
            action = null;
        }

        if (secondN != null) {
            secondN = null;
        }

        setDisplay(firstN, action, secondN);
    }

    public void C() {
        hasDOT = false;
        firstN = null;
        secondN = null;
        action = null;

        setDisplay(firstN, action, secondN);
    }

    public void backSpace() {
        int f = 0;
        if (firstN != null && action==null && secondN == null) {
            if (hasDOT == false) {
                firstN = Double.parseDouble(firstN.toString().substring(0, firstN.toString().length()-3));
            } else {
                firstN = Double.parseDouble(firstN.toString().substring(0, firstN.toString().length()-1));
            }
        } else if (firstN != null && action !=null && secondN == null) {
            action = null;
        } else if (firstN != null && action !=null && secondN != null) {
            if (hasDOT == false) {
                secondN = Double.parseDouble(secondN.toString().substring(0, secondN.toString().length()-3));
            } else {
                secondN = Double.parseDouble(secondN.toString().substring(0, secondN.toString().length()-1));
            }
        }

        setDisplay(firstN, action, secondN);
    }
}
