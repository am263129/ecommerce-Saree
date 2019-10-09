package social.media.saree.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import social.media.saree.R;
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

        Intent intent = getIntent();
        Integer index = intent.getIntExtra("index",0);
        String style = intent.getStringExtra("Style");
        ArrayList<saree> arrayList = new ArrayList<saree>();

        switch (style){
            case "shaandar":
                arrayList = Global.array_shaandar_sarees;
                break;
            case "khoobsurat":
                arrayList = Global.array_BestSeller_sarees;
                break;
            case "New":
                arrayList = Global.array_New_sarees;
                break;
            default:
                arrayList = Global.array_all_sarees;
                break;
        }

        saree_name.setText(arrayList.get(index).getSaree_Name());
        saree_description.setText(arrayList.get(index).getSaree_Description());
        saree_price.setText(arrayList.get(index).getSaree_Price());
        saree_label.setText(arrayList.get(index).getSaree_Label());
        saree_length.setText(arrayList.get(index).getSaree_Length());
        saree_material.setText(arrayList.get(index).getSaree_Material());
        saree_rating.setProgress(Integer.parseInt(arrayList.get(index).getSaree_rating()));
        String base64photo = arrayList.get(index).getSaree_photo();
        String imageDataBytes = base64photo.substring(base64photo.indexOf(",") + 1);
        InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        saree_photo.setImageBitmap(bitmap);


    }
}
