package social.media.saree.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import social.media.saree.R;

public class upload_product extends AppCompatActivity {
    ImageView saree_photo;
    EditText saree_Name;
    EditText saree_Label;
    EditText saree_Price;
    EditText saree_Length;
    EditText saree_Occations;
    EditText saree_Color;
    EditText saree_custom_a;
    EditText saree_custom_b;
    EditText saree_custom_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        saree_Name = (EditText)findViewById(R.id.saree_name);
        saree_Label = (EditText)findViewById(R.id.saree_label);
        saree_Price = (EditText)findViewById(R.id.saree_price);
        saree_Length = (EditText)findViewById(R.id.saree_length);
        saree_Occations = (EditText)findViewById(R.id.saree_occasions);
        saree_Color = (EditText)findViewById(R.id.saree_color);


    }
}
