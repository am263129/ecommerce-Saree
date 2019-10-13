package social.media.saree.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import social.media.saree.R;
import social.media.saree.order.make_Order;
import social.media.saree.saree.saree;
import social.media.saree.util.Global;

public class product_view extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        TextView saree_name = (TextView)findViewById(R.id.detail_saree_name);
        TextView saree_label = (TextView)findViewById(R.id.detail_saree_label);
        RatingBar saree_rating = (RatingBar)findViewById(R.id.detail_saree_rating);
        TextView saree_price = (TextView)findViewById(R.id.detail_saree_price);
        TextView saree_length = (TextView)findViewById(R.id.detail_saree_length);
        TextView saree_material = (TextView)findViewById(R.id.detail_saree_material);
        TextView saree_description = (TextView)findViewById(R.id.detail_saree_description);
        ImageView saree_photo = (ImageView)findViewById(R.id.detail_saree_photo);

        Button add_to_cart = (Button)findViewById(R.id.btn_add_to_cart);
        final Button make_order = (Button)findViewById(R.id.btn_make_order);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        make_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(product_view.this, make_Order.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        saree selected_saree = Global.selected_saree;


        saree_name.setText(selected_saree.getSaree_Name());
        saree_price.setText(selected_saree.getSaree_Price());
        saree_label.setText(selected_saree.getSaree_Label());
        saree_length.setText(selected_saree.getSaree_Length());
        saree_rating.setProgress(Integer.parseInt(selected_saree.getSaree_rating()));
        String base64photo = selected_saree.getSaree_photo_a();
        String imageDataBytes = base64photo.substring(base64photo.indexOf(",") + 1);
        InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        saree_photo.setImageBitmap(bitmap);


    }
}
