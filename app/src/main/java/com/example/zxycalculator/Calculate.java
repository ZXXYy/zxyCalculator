package com.example.zxycalculator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculate {
    private HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

    public Calculate(){
        precedence.put('+',1);
        precedence.put('-',1);
        precedence.put('×',2);
        precedence.put('÷',2);
    }

    public String calExp(String exp){
        Queue<String> postfix = new LinkedList<String>();
        in2post(exp, postfix);
        DecimalFormat dec = new DecimalFormat("0.00");
        return dec.format(calPost(postfix));
    }


    public void in2post(String exp, Queue<String> postfix){
        Stack<Character> operator = new Stack<Character>();
        String num = "";
        boolean hasNum = false;
        exp = "0"+exp;
        for(int i = 0;i<exp.length();i++){
            if(exp.charAt(i)>='0' && exp.charAt(i)<='9' || exp.charAt(i)=='.' || exp.charAt(i)=='%'){
                num += exp.charAt(i);
            }else{
                postfix.add(num);
                num = "";
                if (!operator.empty()) {
                    while (!operator.empty() && precedence.get(operator.peek()) >= precedence.get(exp.charAt(i))) {
                        postfix.add(String.valueOf(operator.peek()));
                        operator.pop();
                    }
                }
                operator.push(exp.charAt(i));
            }
        }
        postfix.add(num);
        while(!operator.empty())
            postfix.add(String.valueOf(operator.pop()));

    }

    public double calPost(Queue<String> postfix) throws ArithmeticException{
        Stack<Double> resStk = new Stack<Double>();
        while(!postfix.isEmpty()){
            String temp = postfix.poll();
//            System.out.println(temp);
            if(isDigit(temp)){
                if(isFraction(temp)){
                    if(temp.contains("%")){
                        resStk.push(Integer.valueOf(temp.substring(0,temp.length()-1))*1.0/100);
                    }
                    else if(temp.contains(".")){
                        int index = temp.indexOf('.');
                        double num1 = Double.valueOf(temp.substring(0,index));
                        double num2 = Double.valueOf(temp.substring(index+1));
                        resStk.push(num1+num2*1.0*Math.pow(0.1,temp.substring(index+1).length()));
                    }
                }
                else
                    resStk.push(Double.valueOf(temp));
            }
            else{
                if(resStk.empty()){
                    // ---To Finish--- handle error;
                    return -1;
                }
                double num1 = resStk.pop();
                if(resStk.empty()) {
                    // ---To Finish--- handle error;
                    return -1;
                }
                double num2 = resStk.pop();
                if(temp.equals("+")){
                    resStk.push(num1+num2);
                }
                else if(temp.equals("-")){
                    resStk.push(num2-num1);
                }
                else if(temp.equals("×")){
                    resStk.push(num1*num2);
                }
                else if(temp.equals("÷")){
                    if(num1==0) {
                        // ---To Finish--- handle error;
                        throw new ArithmeticException();
                    }
                    resStk.push(num2/num1);
                }
            }
        }

        return resStk.peek();
    }

    public boolean isDigit(String s){
        return !(s.equals("+") || s.equals("-") || s.equals("÷") || s.equals("×"));
    }
    public boolean isFraction(String s){
        return s.contains(".") || s.contains("%");
    }

}
