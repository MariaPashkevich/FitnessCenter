package entity;

public class Review {

    private int reviewId;
    private int customerId;
    private int trainerId;
    private String comment;

    public Review() {
    }

    public Review(int customerId, int trainerId, String comment) {
        this.customerId = customerId;
        this.trainerId = trainerId;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reviewId != review.reviewId) return false;
        if (customerId != review.customerId) return false;
        if (trainerId != review.trainerId) return false;
        return comment != null ? comment.equals(review.comment) : review.comment == null;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + customerId;
        result = 31 * result + trainerId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
