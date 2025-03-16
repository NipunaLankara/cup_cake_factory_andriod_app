package Adapters;

public class CupCakeClass {
    private String cupcakeId;
    private String cupcakeName;
    private String categoryId;
    private int price;
    private int quantity;

    public CupCakeClass () {

    }

    public CupCakeClass(String cupcakeId, String cupcakeName, String categoryId, int price, int quantity) {
        this.cupcakeId = cupcakeId;
        this.cupcakeName = cupcakeName;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;

    }

    public void setCupcakeId(String cupcakeId) {
        this.cupcakeId = cupcakeId;
    }

    public void setCupcakeName(String cupcakeName) {
        this.cupcakeName = cupcakeName;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCupcakeId() {
        return cupcakeId;
    }

    public String getCupcakeName() {
        return cupcakeName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
