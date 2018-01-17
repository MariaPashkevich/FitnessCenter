package entity;

public class Trainer {

    private int trainerId;
    private int userId;
    private String firstName;
    private String lastName;
    private String specialization;

    public Trainer() {
    }

    public Trainer(int userId, String firstName, String lastName, String specialization) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFisrtName() {
        return firstName;
    }

    public void setFisrtName(String fisrtName) {
        this.firstName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainer trainer = (Trainer) o;

        if (trainerId != trainer.trainerId) return false;
        if (userId != trainer.userId) return false;
        if (firstName != null ? !firstName.equals(trainer.firstName) : trainer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(trainer.lastName) : trainer.lastName != null) return false;
        return specialization != null ? specialization.equals(trainer.specialization) : trainer.specialization == null;
    }

    @Override
    public int hashCode() {
        int result = trainerId;
        result = 31 * result + userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
}
