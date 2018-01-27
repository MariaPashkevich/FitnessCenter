package entity;

import java.util.Date;

public class Result {

    private int resultId;
    private int customerId;
    private double weight;
    private double waist;
    private double thigh;
    private double chest;
    private double height;
    private Date measurementDate;

    public Result() {
    }

    public Result(int customerId, double weight, double height, Date measurementDate) {
        this.customerId = customerId;
        this.weight = weight;
        this.height = height;
        this.measurementDate = measurementDate;
    }

    public Result(int customerId, double weight, double waist, double thigh, double chest, double height, Date measurementDate) {
        this.customerId = customerId;
        this.weight = weight;
        this.waist = waist;
        this.thigh = thigh;
        this.chest = chest;
        this.height = height;
        this.measurementDate = measurementDate;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (resultId != result.resultId) return false;
        if (customerId != result.customerId) return false;
        if (Double.compare(result.weight, weight) != 0) return false;
        if (Double.compare(result.waist, waist) != 0) return false;
        if (Double.compare(result.thigh, thigh) != 0) return false;
        if (Double.compare(result.chest, chest) != 0) return false;
        if (Double.compare(result.height, height) != 0) return false;
        return measurementDate != null ? measurementDate.equals(result.measurementDate) : result.measurementDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = resultId;
        result = 31 * result + customerId;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(waist);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(thigh);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(chest);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (measurementDate != null ? measurementDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", customerId=" + customerId +
                ", weight=" + weight +
                ", waist=" + waist +
                ", thigh=" + thigh +
                ", chest=" + chest +
                ", height=" + height +
                ", measurementDate=" + measurementDate +
                '}';
    }
}
