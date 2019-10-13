package social.media.saree.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import social.media.saree.R;

public class upload_product extends AppCompatActivity {
    String NAME = "/Name";
    String LABEL = "/Label";
    String PRICE = "/Price";
    String OLDPRICE = "/Old Price";
    String SHIPPINGPRICE = "/Shipping Price";
    String OLDSHIPPINGPRICE = "/Old Shipping Price";
    String EXTRAINFO = "/Extra InFo";
    String FABRIC = "/Fabric";
    String LENGTH = "/Length";
    String OCCATION = "/Occations";
    String COLOR = "/Color";
    String ID = "/Id";
    String STYLE = "/Style";
    String RATING = "/Rating";
    String PHOTO_A = "/Photo_a";
    String PHOTO_B = "/Photo_b";
    String PHOTO_C = "/Photo_c";

    ImageView saree_photo, saree_photo_b, saree_photo_c;
    EditText saree_Name;
    EditText saree_Label;
    EditText saree_Price;
    EditText saree_oldPrice;
    TextView priceoff_pecentage;
    EditText saree_Length;
    EditText saree_Occations;
    EditText saree_Color;
    EditText Extra_info;
    EditText shipping_price;
    EditText oldshipping_price;
    Spinner saree_fabric;
    Spinner saree_style;
    Button upload_product;
    String saree_id;
    private Calendar calendar;
    private int year, month, day;

    public static final int PICK_IMAGE_a = 1;
    public static final int PICK_IMAGE_b = 2;
    public static final int PICK_IMAGE_c = 3;
    public Bitmap bitmap_a = null;
    public Bitmap bitmap_b = null;
    public Bitmap bitmap_c = null;

    public Uri filePath ;

    private String TAG = "Upload_product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        saree_photo = (ImageView)findViewById(R.id.image_saree);
        saree_photo_b = (ImageView)findViewById(R.id.image_saree_b);
        saree_photo_c = (ImageView)findViewById(R.id.image_saree_c);
        saree_Name = (EditText)findViewById(R.id.saree_name);
        saree_Label = (EditText)findViewById(R.id.saree_label);
        saree_Price = (EditText)findViewById(R.id.saree_price);
        saree_Length = (EditText)findViewById(R.id.saree_length);
        saree_Occations = (EditText)findViewById(R.id.saree_occasions);
        saree_Color = (EditText)findViewById(R.id.saree_color);
        upload_product = (Button)findViewById(R.id.btn_upload);
        saree_style = (Spinner)findViewById(R.id.saree_style);
        saree_oldPrice = (EditText)findViewById(R.id.saree_old_price);
        priceoff_pecentage = (TextView)findViewById(R.id.price_pecentage);
        Extra_info = (EditText)findViewById(R.id.saree_extra_info);
        shipping_price = (EditText)findViewById(R.id.saree_shipping_price);
        oldshipping_price = (EditText)findViewById(R.id.saree_shipping_old_price);
        saree_fabric = (Spinner)findViewById(R.id.saree_fabric);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        saree_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_a);
            }
        });
        saree_photo_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_b);
            }
        });
        saree_photo_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_c);
            }
        });


        upload_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
                    String str = sdf.format(currentTime);
                    String sub_id = String.valueOf(currentTime.getHours())+String.valueOf(currentTime.getMinutes());
                    saree_id =  String.valueOf(year)  + String.valueOf(month + 1)  +String.valueOf(day)  + str;
                    upload_product();
                }
            }
        });

        saree_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cal_off();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        saree_oldPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cal_off();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    private void cal_off() {
        if(!saree_Price.getText().toString().equals("") && !saree_oldPrice.getText().toString().equals("")){
            Float price = Float.parseFloat(saree_Price.getText().toString());
            Float OldPrice = Float.parseFloat(saree_oldPrice.getText().toString());
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.UP);
            Float pecentage = ((( OldPrice - price ) / Float.parseFloat(saree_oldPrice.getText().toString())) * 100);
            if(Integer.parseInt(saree_Price.getText().toString()) < Integer.parseInt(saree_oldPrice.getText().toString()))
                priceoff_pecentage.setText("(" +  df.format(pecentage) + "%)"+"Off");
            else
                priceoff_pecentage.setText("(" + df.format(pecentage).substring(1) + "%)"+"Up");
        }
        if (saree_Price.getText().toString().equals("") || saree_oldPrice.getText().toString().equals(""))
        {
            priceoff_pecentage.setText("");
        }
    }

    private boolean validate() {
        if (checkColor() & checkName() & checkLabel() & checkOccations() & checkPrice())
            return true;
        else
        return false;
    }

    public boolean checkName() {

        if ( !saree_Name.getText().equals("")){
            return true;
        } else {
            saree_Name.setError("Input Saree Name");
            return false;
        }
    }
    public boolean checkLabel() {

        if ( !saree_Label.getText().equals("")){
            return true;
        } else {
            saree_Label.setError("Input Saree Name");
            return false;
        }
    }
    public boolean checkPrice() {

        if ( !saree_Price.getText().equals("")){
            return true;
        } else {
            saree_Price.setError("Input Saree Name");
            return false;
        }
    }
    public boolean checkOccations() {

        if ( !saree_Occations.getText().equals("")){
            return true;
        } else {
            saree_Occations.setError("Input Saree Name");
            return false;
        }
    }
    public boolean checkColor() {

        if ( !saree_Color.getText().equals("")){
            return true;
        } else {
            saree_Color.setError("Input Saree Name");
            return false;
        }
    }


    private void upload_product(){
        FirebaseApp.initializeApp(this);
        String id = "Product/Saree/" + saree_id;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(id+NAME);
        myRef.setValue(saree_Name.getText().toString());
        myRef = database.getReference(id+LABEL);
        myRef.setValue(saree_Label.getText().toString());
        myRef = database.getReference(id+PRICE);
        myRef.setValue(saree_Price.getText().toString());
        myRef = database.getReference(id+OLDPRICE);
        myRef.setValue(saree_oldPrice.getText().toString());
        myRef = database.getReference(id+SHIPPINGPRICE);
        myRef.setValue(shipping_price.getText().toString());
        myRef = database.getReference(id+OLDSHIPPINGPRICE);
        myRef.setValue(oldshipping_price.getText().toString());
        myRef = database.getReference(id+EXTRAINFO);
        myRef.setValue(oldshipping_price.getText().toString());
        myRef = database.getReference(id+FABRIC);
        myRef.setValue(saree_fabric.getSelectedItem().toString());
        myRef = database.getReference(id+LENGTH);
        myRef.setValue(saree_Length.getText().toString());
        myRef = database.getReference(id+OCCATION);
        myRef.setValue(saree_Occations.getText().toString());
        myRef = database.getReference(id+COLOR);
        myRef.setValue(saree_Color.getText().toString());
        myRef = database.getReference(id+ID);
        myRef.setValue(saree_id);
        myRef = database.getReference(id+STYLE);
        myRef.setValue(saree_style.getSelectedItem().toString());
        myRef = database.getReference(id+RATING);
        myRef.setValue("0");
        if (bitmap_a !=null) {
            myRef = database.getReference(id + PHOTO_A);
            myRef.setValue(getBase64String(bitmap_a));
        }
        if (bitmap_a !=null) {
            myRef = database.getReference(id + PHOTO_B);
            myRef.setValue(getBase64String(bitmap_b));
        }
        if (bitmap_a !=null) {
            myRef = database.getReference(id + PHOTO_C);
            myRef.setValue(getBase64String(bitmap_c));
        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is:" + value);
                Toast.makeText(upload_product.this,"New Saree uploaded successully",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG,"Failed to rad value ", databaseError.toException());
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_a) {
            //TODO: action

            try {
                bitmap_a = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                saree_photo.setImageBitmap(bitmap_a);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (requestCode == PICK_IMAGE_b) {
            //TODO: action

            try {
                bitmap_b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                saree_photo_b.setImageBitmap(bitmap_b);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (requestCode == PICK_IMAGE_c) {
            //TODO: action

            try {
                bitmap_c = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                saree_photo_c.setImageBitmap(bitmap_c);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * encode
     * @param bitmap
     * @return String
     */
    private String getBase64String(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

}
