package com.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorView extends JFrame {


    final private JLabel textField  = initLabel();

    private JButton zeroButton = createButton("0", KeyEvent.VK_0);
    private JButton oneButton = createButton("1", KeyEvent.VK_1);
    private JButton twoButton = createButton("2", KeyEvent.VK_2);
    private JButton threeButton = createButton("3", KeyEvent.VK_3);
    private JButton fourButton = createButton("4", KeyEvent.VK_4);
    private JButton fiveButton = createButton("5", KeyEvent.VK_5);
    private JButton sixButton = createButton("6", KeyEvent.VK_6);
    private JButton sevenButton = createButton("7", KeyEvent.VK_7);
    private JButton eightButton = createButton("8", KeyEvent.VK_8);
    private JButton nineButton = createButton("9", KeyEvent.VK_9);

    private JButton additionButton = createButton("+", KeyEvent.VK_PLUS);
    private JButton subtractionButton = createButton("-", KeyEvent.VK_SUBTRACT);
    private JButton multiplicationButton = createButton("*", KeyEvent.VK_MULTIPLY);
    private JButton divisionButton = createButton("/", KeyEvent.VK_DIVIDE);
    private JButton rootsquaringButton = new JButton("^(1/2)");
    private JButton equalButton = createButton("=", KeyEvent.VK_EQUALS);
    private JButton commaButton = createButton(".", KeyEvent.VK_PERIOD);
    private JButton clearButton = createButton("С", KeyEvent.VK_BACK_SPACE);
    private JButton signChangeButton = new JButton("+/-");
    private JPanel panel = new JPanel(new GridBagLayout());


    final private Dimension dimensionFrame = new Dimension(320, 300);

    /**
     * Конструктор, который добавляет кнопки на панель
     */
    public CalculatorView(){

        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(dimensionFrame);
        this.setMaximumSize(dimensionFrame);
        this.setMinimumSize(dimensionFrame);


        panel.setLayout(new GridBagLayout());

        addComponentToPanel(textField, 0, 0, GridBagConstraints.REMAINDER, 1, GridBagConstraints.BOTH);

        addComponentToPanel(clearButton, 1, 0, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(signChangeButton, 1, 1, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(rootsquaringButton, 1, 2, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(divisionButton, 1, 3, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(sevenButton, 2, 0, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(eightButton, 2, 1, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(nineButton, 2, 2, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(multiplicationButton, 2, 3, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(fourButton, 3, 0, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(fiveButton, 3, 1, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(sixButton, 3, 2, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(subtractionButton, 3, 3, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(oneButton, 4, 0, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(twoButton, 4, 1, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(threeButton, 4, 2, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(additionButton, 4, 3, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(zeroButton, 5, 0, 2, 1, GridBagConstraints.BOTH);

        addComponentToPanel(commaButton, 5, 2, 1, 1, GridBagConstraints.BOTH);

        addComponentToPanel(equalButton, 5, 3, 1, 1, GridBagConstraints.BOTH);

        this.setContentPane(panel);
    }

    /**
     * Метод, который инцилизирует label
     * @return объект JLabel
     */
    private JLabel initLabel() {
        JLabel jLabel = new JLabel();

        jLabel.setHorizontalAlignment(JTextField.RIGHT);
        jLabel.setFont(jLabel.getFont().deriveFont(30f));
        jLabel.setText("0");

        return jLabel;
    }

    /**
     * Метод, который создаёт кнопку и привязывает к ней клавишу клавиатруы
     * @param name название кнопки
     * @param key клавишу клавиатуры
     * @return объект JButton
     */
    private JButton createButton(String name, int key){
        JButton button = new JButton(name);

        InputMap im = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = button.getActionMap();
        im.put(KeyStroke.getKeyStroke(key, 0), name);
        am.put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton) e.getSource()).doClick();
            }
        });

        return button;
    }

    /**
     * Метод, для добавления кнопки на панель
     * @param component JComponent
     * @param gridy строка таблицы
     * @param gridx стролбец таблицы
     * @param gridwidth число столбцов таблицы, которое будет занимать компонент
     * @param gridheight число строк таблицы, которое будет занимать компонент
     * @param fill размер компонента
     */
    private void addComponentToPanel(JComponent component, int gridy, int gridx, int gridwidth, int gridheight, int fill){
        GridBagConstraints gridBagConstraints =  new GridBagConstraints();
        gridBagConstraints.fill = fill;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;

        panel.add(component, gridBagConstraints);
    }

    /**
     * Метод для получения строки из JLabel
     * @return Строка JLabel
     */
    public String getStringLabel(){
        return this.textField.getText();
    }

    /**
     * Метод для вывода текста в JLabel
     * @param text тект, который нужно вывести
     */
    public void setStringLabel(String text){
        this.textField.setText(text);
    }

    /**
     * Методы, который добавляют обработку кнопкам
     * @param actionListener реализация интерфейся ActionListener
     */
    public void addActionListenerButtonOperations(ActionListener actionListener){
        this.additionButton.addActionListener(actionListener);
        this.subtractionButton.addActionListener(actionListener);
        this.multiplicationButton.addActionListener(actionListener);
        this.divisionButton.addActionListener(actionListener);
        this.rootsquaringButton.addActionListener(actionListener);
        this.equalButton.addActionListener(actionListener);
    }

    public void addActionListenerButtonNumber(ActionListener actionListener){
        this.zeroButton.addActionListener(actionListener);
        this.oneButton.addActionListener(actionListener);
        this.twoButton.addActionListener(actionListener);
        this.threeButton.addActionListener(actionListener);
        this.fourButton.addActionListener(actionListener);
        this.fiveButton.addActionListener(actionListener);
        this.sixButton.addActionListener(actionListener);
        this.sevenButton.addActionListener(actionListener);
        this.eightButton.addActionListener(actionListener);
        this.nineButton.addActionListener(actionListener);
    }

    public void addActionListenerButtonComma(ActionListener actionListener){
        this.commaButton.addActionListener(actionListener);
    }

    public void addActionListenerButtonClear(ActionListener actionListener){
        this.clearButton.addActionListener(actionListener);
    }

    public void addActionListenerButtonSignChange(ActionListener actionListener){
        this.signChangeButton.addActionListener(actionListener);
    }


}
