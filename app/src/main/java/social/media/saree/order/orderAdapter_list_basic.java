package social.media.saree.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import social.media.saree.R;


public class orderAdapter_list_basic extends ArrayAdapter <social.media.saree.order.order> implements Filterable {


    ArrayList<order> array_order_item = new ArrayList<order>();



    order order_item;
    CheckBox hire;
    public orderAdapter_list_basic(Context context, int textViewResourceId, ArrayList<order> objects) {
        super(context, textViewResourceId, objects);
        array_order_item = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_order_list_basic, null);
        TextView client_name = (TextView) v.findViewById(R.id.client_name);
        TextView created_date = (TextView)v.findViewById(R.id.created_date);
        TextView total_price = (TextView)v.findViewById(R.id.order_totalprice);

        client_name.setText(array_order_item.get(position).getClient_name());
        created_date.setText(array_order_item.get(position).getCreated_date());
        total_price.setText(String.valueOf(Integer.parseInt(array_order_item.get(position).getOrder_total_price())));
        return v;
    }

}
