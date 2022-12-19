package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class CalculateDiscountPriceTag extends SimpleTagSupport {

    private float basePrice;
    private float discount;

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public void doTag() throws JspException, IOException {
        float discountedPrice = calcDiscountedPrice();
        JspWriter out = getJspContext().getOut();
        out.println(discountedPrice);
    }

    private float calcDiscountedPrice() {
        return basePrice - basePrice * (discount / 100);
    }
}
