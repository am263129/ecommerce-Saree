package social.media.saree.saree;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
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

import com.google.firebase.auth.TwitterAuthCredential;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import social.media.interfaces.ItemClickListener;
import social.media.saree.R;


public class sareeAdapter_list extends ArrayAdapter <saree> implements Filterable {


    ArrayList<saree> array_all_saree = new ArrayList<>();

    public void setItemListener(ItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    ItemClickListener itemListener;

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
        ImageView saree_photo_a = (ImageView)v.findViewById(R.id.saree_photo_a);
        ImageView saree_photo_b = (ImageView)v.findViewById(R.id.saree_photo_b);
        ImageView saree_photo_c = (ImageView)v.findViewById(R.id.saree_photo_c);
        RatingBar saree_rating = (RatingBar)v.findViewById(R.id.item_saree_rating);
        TextView saree_price = (TextView)v.findViewById(R.id.item_saree_real_price);
        TextView saree_Oldprice = (TextView)v.findViewById(R.id.item_saree_old_price);
        TextView change_pecentage = (TextView)v.findViewById(R.id.item_saree_percentage);
        TextView saree_shipping = (TextView)v.findViewById(R.id.item_saree_shipping_price);
        TextView saree_oldshipping = (TextView)v.findViewById(R.id.item_saree_shipping_price_old);
        TextView saree_extraInfo = (TextView)v.findViewById(R.id.item_saree_extra_info);
        String Name = array_all_saree.get(position).getSaree_Name();
        String price = array_all_saree.get(position).getSaree_Price();
        String rating = array_all_saree.get(position).getSaree_rating();
        saree_name.setText(Name);
        saree_price.setText("₹ " + price);
        saree_Oldprice.setText("₹ " + array_all_saree.get(position).getSaree_Oldprice());
        saree_shipping.setText("₹ " + array_all_saree.get(position).getSaree_shipping());
        saree_oldshipping.setText("₹ " + array_all_saree.get(position).getSaree_Oldshipping());
        saree_extraInfo.setText(array_all_saree.get(position).getSaree_ExtraInfo());
        saree_Oldprice.setPaintFlags(saree_Oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        saree_oldshipping.setPaintFlags(saree_oldshipping.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Float realprice = Float.parseFloat(array_all_saree.get(position).getSaree_Price());
        Float OldPrice = Float.parseFloat(array_all_saree.get(position).getSaree_Oldprice());
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        Float pecentage = (( OldPrice - realprice ) / OldPrice * 100);
        if(realprice < OldPrice)
            change_pecentage.setText("(" +  df.format(pecentage) + "%)"+"Off");
        else
            change_pecentage.setText("(" + df.format(pecentage).substring(1) + "%)"+"Up");




        Integer in_rating = Integer.parseInt(rating);
        saree_rating.setProgress(in_rating);


        String base64photo_a = array_all_saree.get(position).getSaree_photo_a();
        String base64photo_b = array_all_saree.get(position).getSaree_photo_b();
        String base64photo_c = array_all_saree.get(position).getSaree_photo_c();
        Image_view(base64photo_a,saree_photo_a);
        Image_view(base64photo_b,saree_photo_b);
        Image_view(base64photo_c,saree_photo_c);

        v.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                itemListener.onClick(position);
            }
        });
        return v;
    }

    private void Image_view(String base64photo, ImageView Image_view) {

        if (base64photo.equals("")) {

            Image_view.setImageResource(R.drawable.saree_dummy);

        } else {
            String imageDataBytes = base64photo.substring(base64photo.indexOf(",") + 1);
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            Image_view.setImageBitmap(bitmap);
        }
    }

}
