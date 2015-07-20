package com.model;


import com.plugins.Plugin;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CalculatorModel {
    /**
     * firstNumber - Первое введённое число
     * secondNumber - Второе введённое число
     * result - Результат операции
     */
    private double firstNumber;
    private double secondNumber;
    private double result;

    /**
     * countOperation - сколько раз нажали на клавиши операций
     * pressAction - была ли нажата клавиша операции
     * action - тип операции, которая была нажата
     */
    private int countOperation = 0;
    private boolean pressAction = false;
    private String action = null;

    /**
     * error - текст ошибки
     * checkError - false(ошибка арифметической операции), true(нет ошибки)
     */
    final private String error = "Error";
    boolean checkError = true;

    /**
     * Метод для подключения плагина к программе
     * @param path путь к плагину
     * @return true(арифметическая операция выполнена успешна), false(ошибка при выполнении арифметической операции)
     */
    private boolean startPlugin(String path){
        try {
            Class pluginClass = ClassLoader.getSystemClassLoader().loadClass(path);
            Plugin plugin = (Plugin)pluginClass.newInstance();
            double result = plugin.run(firstNumber, secondNumber);

            if (!Double.isFinite(result)) {
                return false;
            }
            this.result = result;

        }catch (Exception ex){
            ex.getStackTrace();
        }
        return true;
    }

    /**
     * Методы для запуска плагина.
     * @return true(арифметическая операция выполнена успешна), false(ошибка при выполнении арифметической операции)
     */
    private boolean additionNumber(){
        return startPlugin("com.plugins.impl.AdditionNumberImpl");
    }

    private boolean subtractionNumber(){
        return startPlugin("com.plugins.impl.SubtractionNumberImpl");
    }

    private boolean multiplicationNumber(){
        return startPlugin("com.plugins.impl.MultiplicationNumberImpl");
    }

    private boolean divisionNumber(){
        return startPlugin("com.plugins.impl.DivisionNumberImpl");
    }

    private boolean rootsquaring(){
       return startPlugin("com.plugins.impl.RootsquaringImpl");
    }


    /**
     * Метод для запоминания введённого числа и храния операции
     * В методе запоминается операция, которую нужно выполнить и запоминается введённое число.
     * @param number Введённое пользователем число
     * @param action Выбранная операция
     */
    public void addNumber(double number, String action){
        pressAction = true;
        countOperation++;
        checkError = true;

        if (countOperation == 1){
            firstNumber = number;
            result = number;
            this.action = action;
        }
        if (countOperation == 2){
            secondNumber = number;
        }

        runOperation();

        this.action = action;

    }

    /**
     * Метод, который выполняет операцию в зависимости он нажатой операции
     */
    private void runOperation(){
        if (this.action.equals("+") && countOperation == 2){
            checkError = additionNumber();
        }

        if (this.action.equals("-")  && countOperation == 2){
            checkError =  subtractionNumber();
        }

        if (this.action.equals("*")  && countOperation == 2){
            checkError = multiplicationNumber();
        }

        if (this.action.equals("/") && countOperation == 2){
            checkError = divisionNumber();
        }

        if (this.action.equals("^(1/2)") && countOperation == 1){
            checkError = rootsquaring();
        }

    }

    /**
     * Метод, который проверяет можно ли выводить результат на экран
     * @return Истина - если можно выводить результат на экран, ложь - если нельзя выводить результат на экран
     */
    public boolean checkPrintResult(){
        if (action.equals("=") || action.equals("^(1/2)")){
            countOperation = 0;
            return true;
        }

        if (countOperation == 2){
            countOperation = 1;
            firstNumber = result;
            return true;
        }

        return false;

    }

    /**
     * @return возвращает значение pressAction
     */
    public boolean getPressAction() {
        return pressAction;
    }

    /**
     * @param pressAction принимает значения для this.pressAction
     */
    public void setPressAction(boolean pressAction) {
        this.pressAction = pressAction;
    }

    /**
     * Метод, который возвращает скроку результата или ошибки
     * @return Строка, которую нужно вывести на экран
     */
    public String getResult(){
        if (!checkError){
            countOperation = 0;
            return error;
        }

        return formatResult();
    }

    /**
     * Метод для форматирования результата из double в String
     * @return результат в String
     */
    private String formatResult(){
        result = new BigDecimal(result).setScale(10, RoundingMode.HALF_UP).doubleValue();

        String resultStr;
        String resultStrFormatted = "";

        if (result - (int)result == 0){
            resultStr = Integer.toString((int)result);
        }else {
            resultStr = Double.toString(result);
        }

        if (resultStr.indexOf('.') != -1) {
            resultStrFormatted = resultStr.substring(resultStr.indexOf('.'), resultStr.length());
            resultStr = resultStr.substring(0, resultStr.indexOf('.'));
        }


        for (int i = resultStr.length() - 1; i >= 0; i--){
            if ((resultStr.length() - i - 1) % 3 == 0 && (resultStr.length() - 1) != i){
                resultStrFormatted = " " + resultStrFormatted;
                System.out.println();
            }
            resultStrFormatted = resultStr.charAt(i) + resultStrFormatted;
        }

        return resultStrFormatted;
    }

}
