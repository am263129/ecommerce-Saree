package social.media.saree.order;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import social.media.interfaces.ItemClickListener;
import social.media.saree.R;


public class orderAdapter_list_basic extends ArrayAdapter <cart_item> implements Filterable {


    ArrayList<cart_item> array_cart_item = new ArrayList<cart_item>();



    cart_item cart_item;
    CheckBox hire;
    public orderAdapter_list_basic(Context context, int textViewResourceId, ArrayList<cart_item> objects) {
        super(context, textViewResourceId, objects);
        array_cart_item = objects;
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
        TextView item_name = (TextView) v.findViewById(R.id.item_name);
        TextView item_amount = (TextView)v.findViewById(R.id.item_amount);

        item_name.setText(array_cart_item.get(position).getProduct_name());
        item_amount.setText(array_cart_item.get(position).getProduct_amount());

        return v;
    }

}
