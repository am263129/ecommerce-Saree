package social.media.saree.order;


import java.util.ArrayList;

import social.media.saree.cart.cart_item;

public class order {

    public String client_name;
    public String client_address;
    public String client_email;
    public String client_location;
    public String receiver_name;
    public String receiver_address;
    public String receiver_email;
    public String receiver_location;
    public String created_date;
    public String order_id;
    public String order_total_price;
    public String support_email;
    public String status;
    public String shipping_price;
    public ArrayList<cart_item> cart;
    public order(){

    }

    public order(
            String client_name,
            String client_address,
            String client_email,
            String client_location,
            String receiver_name,
            String receiver_address,
            String receiver_email,
            String receiver_location,
            String status,
            String created_date,
            String order_id,
            String order_total_price,
            String shipping_price,
            String support_email,
            ArrayList<cart_item> cart){

        this.client_name = client_name;
        this.client_address = client_address;
        this.client_email = client_email;
        this.client_location = client_location;
        this.receiver_name = receiver_name;
        this.receiver_address = receiver_address;
        this.receiver_email = receiver_email;
        this.receiver_location = receiver_location;
        this.status = status;
        this.created_date = created_date;
        this.order_id = order_id;
        this.order_total_price = order_total_price;
        this.shipping_price = shipping_price;
        this.support_email = support_email;
        this.cart = cart;
    }


    public ArrayList<cart_item> getCart() {
        return cart;
    }

    public String getClient_address() {
        return client_address;
    }

    public String getClient_email() {
        return client_email;
    }

    public String getClient_location() {
        return client_location;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_total_price() {
        return order_total_price;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public String getReceiver_location() {
        return receiver_location;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getSupport_email() {
        return support_email;
    }

    public String getStatus() {
        return status;
    }

    public String getShipping_price() {
        return shipping_price;
    }

}
