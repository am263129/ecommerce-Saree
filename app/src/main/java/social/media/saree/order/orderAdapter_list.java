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
import social.media.saree.saree.saree;


public class orderAdapter_list extends ArrayAdapter <order> implements Filterable {


    ArrayList<order> array_order = new ArrayList<order>();

    public void setItemListener(ItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    ItemClickListener itemListener;

    order order;
    CheckBox hire;
    public orderAdapter_list(Context context, int textViewResourceId, ArrayList<order> objects) {
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
        v = inflater.inflate(R.layout.item_order_list, null);
        TextView saree_name = (TextView) v.findViewById(R.id.item_saree_name);
        ImageView saree_photo = (ImageView)v.findViewById(R.id.item_saree_photo);
        RatingBar saree_rating = (RatingBar)v.findViewById(R.id.item_saree_rating);
        TextView saree_price = (TextView)v.findViewById(R.id.item_saree_price);


        String base64photo = array_order.get(position).getClient_Address();
        if (base64photo.equals("")) {

            saree_photo.setImageResource(R.drawable.saree_dummy);

        } else {
            String imageDataBytes = base64photo.substring(base64photo.indexOf(",") + 1);
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            saree_photo.setImageBitmap(bitmap);
        }
        v.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                itemListener.onClick(position);
            }
        });
        return v;
    }

}
