package social.media.saree.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import social.media.interfaces.ItemClickListener;
import social.media.saree.R;
import social.media.saree.util.Global;


public class cartAdapter_list extends ArrayAdapter <social.media.saree.cart.cart_item> implements Filterable {


    ArrayList<cart_item> array_order = new ArrayList<cart_item>();

    public void setItemListener(ItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    ItemClickListener itemListener;

    cart_item cart_item;
    CheckBox hire;
    public cartAdapter_list(Context context, int textViewResourceId, ArrayList<cart_item> objects) {
        super(context, textViewResourceId, objects);
        array_order = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_cart_list, null);

        TextView product_name = (TextView)v.findViewById(R.id.item_order_name);
        TextView product_amount = (TextView)v.findViewById(R.id.item_order_amount);
        TextView product_total_price = (TextView)v.findViewById(R.id.item_order_totalprice);
        Button remove_cart = (Button)v.findViewById(R.id.btn_remove);
        remove_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.user_carts.remove(position);
                Cart_view.set_list();
            }
        });
        product_amount.setText(array_order.get(position).getProduct_amount());
        product_name.setText(array_order.get(position).getProduct_name());
        Integer single_price = 0;
        for (int i = 0; i < Global.array_all_sarees.size(); i++){
            if (Global.array_all_sarees.get(i).getSaree_Id().equals(array_order.get(position).getProduct_id()))
                single_price = Integer.parseInt(Global.array_all_sarees.get(i).getSaree_Price());
        }
        product_total_price.setText(String.valueOf(single_price * Integer.parseInt(array_order.get(position).getProduct_price())));



        return v;
    }

}
