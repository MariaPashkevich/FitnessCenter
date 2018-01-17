package entity;

import java.util.Date;

public class Meal {

    private int mealId;
    private int customerId;
    private int recipeId;
    private char type;
    private Date mealDate;

    public Meal() {
    }

    public Meal(int customerId, int recipeId, char type, Date mealDate) {
        this.customerId = customerId;
        this.recipeId = recipeId;
        this.type = type;
        this.mealDate = mealDate;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (mealId != meal.mealId) return false;
        if (customerId != meal.customerId) return false;
        if (recipeId != meal.recipeId) return false;
        if (type != meal.type) return false;
        return mealDate != null ? mealDate.equals(meal.mealDate) : meal.mealDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mealId ^ (mealId >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (int) (recipeId ^ (recipeId >>> 32));
        result = 31 * result + (int) type;
        result = 31 * result + (mealDate != null ? mealDate.hashCode() : 0);
        return result;
    }
}
