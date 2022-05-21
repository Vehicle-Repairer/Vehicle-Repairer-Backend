package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinalPrice implements Serializable {
    BigDecimal repairmanPrice;
    BigDecimal partsPrice;
    double discountRate;
    BigDecimal totalPrice;

    public BigDecimal getRepairmanPrice() {
        return repairmanPrice;
    }

    public void setRepairmanPrice(BigDecimal repairmanPrice) {
        this.repairmanPrice = repairmanPrice;
    }

    public BigDecimal getPartsPrice() {
        return partsPrice;
    }

    public void setPartsPrice(BigDecimal partsPrice) {
        this.partsPrice = partsPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public FinalPrice(BigDecimal repairmanPrice, BigDecimal partsPrice, double discountRate) {
        this.repairmanPrice = repairmanPrice;
        this.partsPrice = partsPrice;
        this.discountRate = discountRate;
    }
}
