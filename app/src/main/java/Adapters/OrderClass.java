package Adapters;

public class OrderClass {
    private String orderId;
    private String cupcakeId;
    private String userId;
    private int quantity;
    private int total;

    public OrderClass() {
    }

    public OrderClass(String orderId, String cupcakeId, String userId, int quantity, int total) {
        this.orderId = orderId;
        this.cupcakeId = cupcakeId;
        this.userId = userId;
        this.quantity = quantity;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCupcakeId() {
        return cupcakeId;
    }

    public void setCupcakeId(String cupcakeId) {
        this.cupcakeId = cupcakeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
