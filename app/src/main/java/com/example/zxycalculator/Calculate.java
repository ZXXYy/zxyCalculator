package com.example.zxycalculator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculate {
    private HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

    public Calculate(){
        precedence.put('+',1);
        precedence.put('-',1);
        precedence.put('*',2);
        precedence.put('/',2);
    }

    public String calExp(String exp){
        Queue<String> postfix = new LinkedList<String>();
        in2post(exp, postfix);
        return Double.toString(calPost(postfix));
    }


    public void in2post(String exp, Queue<String> postfix){
        Stack<Character> operator = new Stack<Character>();
        String num = "";
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
        postfix.add(String.valueOf(operator.peek()));
        operator.pop();
    }

    public double calPost(Queue<String> postfix){
        Stack<Double> resStk = new Stack<Double>();
        while(!postfix.isEmpty()){
            String temp = postfix.poll();
            if(isDigit(temp)){
                if(isFraction(temp)){
                    if(temp.contains("%")){
                        resStk.push(Integer.valueOf(temp.substring(0,temp.length()-1))*1.0/100);
                    }
                    else if(temp.contains(".")){
                        int index = temp.indexOf('.');
                        int num1 = Integer.valueOf(temp.substring(0,index-1));
                        int num2 = Integer.valueOf(temp.substring(index+1));
                        resStk.push(num1+num2*1.0/Math.pow(0.1,temp.substring(index+1).length()));
                    }
                }
                else
                    resStk.push(Double.valueOf(temp));
            }
            else{
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
                else if(temp.equals("*")){
                    resStk.push(num1*num2);
                }
                else if(temp.equals("/")){
                    resStk.push(num2/num1);
                }
            }
        }
        return resStk.peek();
    }

    public boolean isDigit(String s){
        return !(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*"));
    }
    public boolean isFraction(String s){
        return s.contains(".") || s.contains("%");
    }

}
