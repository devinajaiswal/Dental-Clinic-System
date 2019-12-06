/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

/**
 *
 * @author devinajaiswal
 */
public class InsurancePlan {

    private int planId;
    private int enterpriseId;
    private String planName;
    private double price;
    private double fillingCoverage;
    private double rootCoverage;
    private double srpCoverage;

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public double getFillingCoverage() {
        return fillingCoverage;
    }

    public void setFillingCoverage(double fillingCoverage) {
        this.fillingCoverage = fillingCoverage;
    }

    public double getRootCoverage() {
        return rootCoverage;
    }

    public void setRootCoverage(double rootCoverage) {
        this.rootCoverage = rootCoverage;
    }

    public double getSrpCoverage() {
        return srpCoverage;
    }

    public void setSrpCoverage(double srpCoverage) {
        this.srpCoverage = srpCoverage;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getPlanName();
    }

}
