package social.media.saree.order;

public class order {
    public String Client_Address;
    public String Client_Email;
    public String Client_Name;
    public String Delieve_Fee;
    public String ID;
    public String Order_created_date;
    public String Order_status;
    public String Product_Id;
    public String Product_Name;
    public String Product_Price;
    public String product_amount;

    public order(){

    }

    public order(String client_Address,
                 String client_Email,
                 String client_Name,
                 String delieve_Fee,
                 String id,
                 String order_created_date,
                 String order_status,
                 String product_Id,
                 String product_Name,
                 String product_Price,
                 String product_Amount){
        this.Client_Address = client_Address;
        this.Client_Email = client_Email;
        this.Client_Name = client_Name;
        this.Delieve_Fee = delieve_Fee;
        this.ID = id;
        this.Order_created_date = order_created_date;
        this.Order_status = order_status;
        this.Product_Id =  product_Id;
        this.Product_Name = product_Name;
        this.Product_Price = product_Price;
        this.product_amount = product_Amount;
    }

    public String getClient_Address() {
        return Client_Address;
    }

    public String getClient_Email() {
        return Client_Email;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public String getDelieve_Fee() {
        return Delieve_Fee;
    }

    public String getID() {
        return ID;
    }

    public String getOrder_created_date() {
        return Order_created_date;
    }

    public String getOrder_status() {
        return Order_status;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public String getProduct_amount() {
        return product_amount;
    }
}
