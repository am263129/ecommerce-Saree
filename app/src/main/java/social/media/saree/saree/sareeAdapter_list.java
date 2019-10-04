package social.media.saree.saree;

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

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import social.media.saree.R;


public class sareeAdapter_list extends ArrayAdapter <saree> implements Filterable {


    ArrayList<saree> array_all_saree = new ArrayList<>();
    saree saree;
    CheckBox hire;
    public sareeAdapter_list(Context context, int textViewResourceId, ArrayList<saree> objects) {
        super(context, textViewResourceId, objects);
        array_all_saree = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_saree_list, null);
        TextView saree_name = (TextView) v.findViewById(R.id.item_saree_name);
        ImageView saree_photo = (ImageView)v.findViewById(R.id.item_saree_photo);
        RatingBar saree_rating = (RatingBar)v.findViewById(R.id.item_saree_rating);
        TextView saree_price = (TextView)v.findViewById(R.id.item_saree_price);
        String Name = array_all_saree.get(position).getSaree_Name();
        String price = array_all_saree.get(position).getSaree_Price();
        String rating = array_all_saree.get(position).getSaree_rating();
        saree_name.setText(Name);
        saree_price.setText(price);
        Integer in_rating = Integer.parseInt(rating);
        saree_rating.setNumStars(in_rating);


        String base64photo = array_all_saree.get(position).getSaree_photo();
        if (base64photo.equals("")) {

            saree_photo.setImageResource(R.drawable.saree_dummy);

        } else {
            String imageDataBytes = base64photo.substring(base64photo.indexOf(",") + 1);
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            saree_photo.setImageBitmap(bitmap);
        }
        return v;
    }

}
