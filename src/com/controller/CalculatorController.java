package com.controller;

import com.model.CalculatorModel;
import com.view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private CalculatorView calculatorView;
    private CalculatorModel calculatorModel;

    /**
     * Конструктор, добавляет ActionListens в view и создаёт экземпляры классов CalculatorView и CalculatorModel
     */
    public CalculatorController() {
        calculatorView = new CalculatorView();
        calculatorModel = new CalculatorModel();

        calculatorView.addActionListenerButtonOperations(new ActionListenerButtonOperations());
        calculatorView.addActionListenerButtonNumber(new ActionListenerButtonNumber());
        calculatorView.addActionListenerButtonSignChange(new ActionListenButtonSign());
        calculatorView.addActionListenerButtonClear(new ActionListenerButtonClear());
        calculatorView.addActionListenerButtonComma(new ActionListenerButtonComma());
        calculatorView.setVisible(true);
    }

    /**
     * Класс обработки нажатие на кнопку операции ('*', '/', '+', '-', '^(1/2)')
     */
    private class ActionListenerButtonOperations implements ActionListener {

        /**
         * Метод обработки нажатия на кнопку.
         * Вначале метод добавляет новое число в model и затем проверяет нужно ли выводить результат операции.
         * И если результат нужно выводить - выводит
         * @param e, Событие, генерируемое при нажатии кнопки
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (calculatorView.getStringLabel().equals("Error")){
                return;
            }
            double number = Double.parseDouble(calculatorView.getStringLabel().replace(" ", ""));
            calculatorModel.addNumber(number, e.getActionCommand());

            if (calculatorModel.checkPrintResult()) {
                    calculatorView.setStringLabel(calculatorModel.getResult());
            }

        }

    }

    /**
     * Класс обработки нажатия на кнопку цифры ('0', '1', '2' и т.д.)
     * */
    private class ActionListenerButtonNumber implements ActionListener {

        /**
         * Метод обработки нажатия на кнопку цифры.
         * При нажатии на кнопку, цифра просто приписывается к концу строки.
         * @param e Событие, генерируемое при нажатии кнопки
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            String text = calculatorView.getStringLabel();

            if ((text.equals("0") || calculatorModel.getPressAction())) {
                calculatorModel.setPressAction(false);
                calculatorView.setStringLabel(e.getActionCommand());
            } else {
                if (text.length() < 12) {
                    calculatorView.setStringLabel(text + e.getActionCommand());
                }
            }
        }

    }

    /**
     * Класс обработки нажатия на кнопку точка ('.')
     */
    private class ActionListenerButtonComma implements ActionListener{

        /**
         * Метод для обработки нажатия кнопки точка.
         * При нажатие на кнопку, к концу числа добавляется точка (дробная часть)
         * @param e Событие, генерируемое при нажатии кнопки
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = calculatorView.getStringLabel();
            if (text.indexOf('.') == -1 ){
                if (!text.equals("Error")) {
                    calculatorView.setStringLabel(text + e.getActionCommand());
                }else{
                    calculatorView.setStringLabel("0" + e.getActionCommand());
                }
            }
            calculatorModel.setPressAction(false);
        }

    }

    /**
     * Класс обработки нажатия на кнопку удалить цифру ('С')
     */
    private class ActionListenerButtonClear implements ActionListener{

        /**
         * Метод для обработки нажатия кнопки удалить цифру
         * При нажатие на кнопку, удаляется последняя цифра.
         * @param e Событие, генерируемое при нажатии кнопки
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = calculatorView.getStringLabel();
            StringBuilder buffer = new StringBuilder(text);

            if (buffer.length() == 1 || buffer.toString().equals("0") || buffer.toString().equals("-")
                    || buffer.toString().equals("Error") || calculatorModel.getPressAction()){
                text = "0";
            }else{
                buffer.deleteCharAt(buffer.length() - 1);
                text = buffer.toString();
            }

            calculatorView.setStringLabel(text);
        }

    }

    /**
     * Класс обработки нажатия на кнопку смены знака ('+/-')
     */
    private class ActionListenButtonSign implements ActionListener{

        /**
         * Метод для обработки нажатия кнопки смены знака.
         * При нажатие на кнопку, анализируется текущее введённое число и, либо добавляется '-', либо удаляется.
         * @param e Событие, генерируемое при нажатии кнопки
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = calculatorView.getStringLabel();
            if (text.equals("0") || text.equals("Error")){
                return;
            }

            if (text.charAt(0) == '-'){
                StringBuilder buffer = new StringBuilder(text);
                buffer.deleteCharAt(0);
                text = buffer.toString();
            }else{
                text = '-' + text;
            }

            calculatorView.setStringLabel(text);
        }

    }


}
