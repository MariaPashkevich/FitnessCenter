package entity;

public class Order {

    private int orderId;
    private int customerId;
    private int trainerId;
    private int trainingNumber;
    private double price;

    public Order() {
    }

    public Order(int customerId, int trainerId, int trainingNumber, double price) {
        this.customerId = customerId;
        this.trainerId = trainerId;
        this.trainingNumber = trainingNumber;
        this.price = price;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTrainingNumber() {
        return trainingNumber;
    }

    public void setTrainingNumber(int trainingNumber) {
        this.trainingNumber = trainingNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (customerId != order.customerId) return false;
        if (trainerId != order.trainerId) return false;
        if (trainingNumber != order.trainingNumber) return false;
        return Double.compare(order.price, price) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (int) (trainerId ^ (trainerId >>> 32));
        result = 31 * result + trainingNumber;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", trainerId=" + trainerId +
                ", trainingNumber=" + trainingNumber +
                ", price=" + price +
                '}';
    }
}
