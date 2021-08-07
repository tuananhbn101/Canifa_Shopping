package com.example.canifa_shop.Category.Object;

public class Category{
    private String idCategory;
    private String nameCategory;
    private String amountCategory;
    private String describe;

    public Category(String idCategory, String nameCategory, String amountCategory, String describe) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.amountCategory = amountCategory;
        this.describe = describe;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getAmountCategory() {
        return amountCategory;
    }

    public void setAmountCategory(String amountCategory) {
        this.amountCategory = amountCategory;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
