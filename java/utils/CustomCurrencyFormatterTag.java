/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

/**
 *
 * @author Admin
 */
public class CustomCurrencyFormatterTag extends SimpleTagSupport {

    private float amount;
    private String currencyFormat;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setCurrencyFormat(String currencyFormat) {
        this.currencyFormat = currencyFormat;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String formatted = addCurrencyUnit(format());
        JspWriter out = getJspContext().getOut();
        out.println(formatted);
    }

    public StringBuilder format() {
        StringBuilder formatted = new StringBuilder();
        long currentAmount = (long) amount;

        if (currencyFormat.equalsIgnoreCase("vi") || currencyFormat == null) {
            System.out.println(amount % 1000);

            long baseDivider = 1000;
            long divider = baseDivider;

            while (currentAmount / divider > 0) {
                long mod = currentAmount % divider;
                if (mod == 0) {
                    formatted = formatted.append(mod).append(mod).append(mod).append(".");
                } else {
                    formatted = formatted.append(mod);
                    formatted.reverse().append(".");
                }
                currentAmount /= divider;
                divider *= baseDivider;
            }
            formatted.reverse();
            formatted.insert(0, currentAmount);
        }

        return formatted;
    }

    public String addCurrencyUnit(StringBuilder formatted) {
        StringBuilder formattedAndAddedCurrencyUnit = formatted;

        if (currencyFormat.equalsIgnoreCase("vi") || currencyFormat == null) {
            formattedAndAddedCurrencyUnit.append("<sup>đ</sup>").toString();
        }

        return formattedAndAddedCurrencyUnit.toString();
    }
}
