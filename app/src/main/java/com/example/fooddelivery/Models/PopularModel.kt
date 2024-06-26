package com.example.fooddelivery.Models

class PopularModel {

    private var foodimage: Int? = null
    private var foodName: String = ""
    private var foodPrice: Int = 0
    private var foodPriceConstant: Int = 0
    private var foodCount: Int = 1

    constructor()
    constructor(foodimage: Int?, foodName: String, foodPrice: Int, foodPriceConstant: Int, foodCount: Int) {
        this.foodimage = foodimage
        this.foodName = foodName
        this.foodPrice = foodPrice
        this.foodPriceConstant = foodPriceConstant
        this.foodCount = foodCount
    }

    fun getFoodPriceConstant() :Int {
        return foodPriceConstant
    }

    fun setFoodPriceConstant(foodPriceConstant: Int) {
        this.foodPriceConstant = foodPriceConstant
    }

    fun getFoodImage(): Int? {
        return foodimage
    }

    fun getFoodName(): String {
        return foodName
    }

    fun getFoodPrice(): Int {
        return foodPrice
    }
    fun setFoodImage(foodImage: Int?) {
        this.foodimage = foodImage
    }

    fun setFoodName (foodName: String) {
        this.foodName = foodName
    }

    fun setFoodPrice (foodPrice: Int) {
        this.foodPrice = foodPrice
    }

    fun getFoodCount(): Int {
        return foodCount
    }

    fun setFoodCount(foodCount: Int) {
        this.foodCount = foodCount
    }
}