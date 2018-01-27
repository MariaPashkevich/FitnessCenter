package entity;

import java.util.Date;

public class Training {

    private int trainingId;
    private int customerId;
    private int exerciseId;
    private int number;
    private int weight;
    private Date trainingDate;
    private boolean status;

    public Training() {
    }

    public Training(int customerId, int exerciseId, Date trainingDate) {
        this.customerId = customerId;
        this.exerciseId = exerciseId;
        this.trainingDate = trainingDate;
    }

    public Training(int customerId, int exerciseId, int number, int weight, Date trainingDate, boolean status) {
        this.customerId = customerId;
        this.exerciseId = exerciseId;
        this.number = number;
        this.weight = weight;
        this.trainingDate = trainingDate;
        this.status = status;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (trainingId != training.trainingId) return false;
        if (customerId != training.customerId) return false;
        if (exerciseId != training.exerciseId) return false;
        if (number != training.number) return false;
        if (weight != training.weight) return false;
        if (status != training.status) return false;
        return trainingDate != null ? trainingDate.equals(training.trainingDate) : training.trainingDate == null;
    }

    @Override
    public int hashCode() {
        int result = trainingId;
        result = 31 * result + customerId;
        result = 31 * result + exerciseId;
        result = 31 * result + number;
        result = 31 * result + weight;
        result = 31 * result + (trainingDate != null ? trainingDate.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", customerId=" + customerId +
                ", exerciseId=" + exerciseId +
                ", number=" + number +
                ", weight=" + weight +
                ", trainingDate=" + trainingDate +
                ", status=" + status +
                '}';
    }
}
