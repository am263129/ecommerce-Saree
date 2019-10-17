package social.media.saree.cart;

public class cart_item {
    public String product_id;
    public String product_name;
    public String product_amount;
    public String cart_price;

    public cart_item(){

    }
    public cart_item(String product_id, String product_name, String product_amount, String price){
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_amount = product_amount;
        this.cart_price = price;
    }

    public String getProduct_amount() {
        return product_amount;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return cart_price;
    }
}
