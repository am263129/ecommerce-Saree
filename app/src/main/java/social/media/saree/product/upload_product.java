package social.media.saree.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import social.media.saree.R;

public class upload_product extends AppCompatActivity {
    ImageView saree_photo;
    EditText saree_Name;
    EditText saree_Label;
    EditText saree_Price;
    EditText saree_Length;
    EditText saree_Occations;
    EditText saree_Color;
    EditText saree_Material;
    EditText saree_custom_a;
    EditText saree_custom_b;
    EditText saree_custom_c;
    Spinner saree_style;
    Button upload_product;
    String saree_id;
    private Calendar calendar;
    private int year, month, day;

    public static final int PICK_IMAGE = 1;
    public Bitmap bitmap = null;
    public Uri filePath ;

    private String TAG = "Upload_product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        saree_photo = (ImageView)findViewById(R.id.image_saree);
        saree_Name = (EditText)findViewById(R.id.saree_name);
        saree_Label = (EditText)findViewById(R.id.saree_label);
        saree_Price = (EditText)findViewById(R.id.saree_price);
        saree_Length = (EditText)findViewById(R.id.saree_length);
        saree_Occations = (EditText)findViewById(R.id.saree_occasions);
        saree_Color = (EditText)findViewById(R.id.saree_color);
        upload_product = (Button)findViewById(R.id.btn_upload);
        saree_Material = (EditText)findViewById(R.id.saree_material);
        saree_style = (Spinner)findViewById(R.id.saree_style);

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
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
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
        DatabaseReference myRef = database.getReference(id+"/Name");
        myRef.setValue(saree_Name.getText().toString());
        myRef = database.getReference(id+"/Label");
        myRef.setValue(saree_Label.getText().toString());
        myRef = database.getReference(id+"/Price");
        myRef.setValue(saree_Price.getText().toString());
        myRef = database.getReference(id+"/Length");
        myRef.setValue(saree_Length.getText().toString());
        myRef = database.getReference(id+"/Occations");
        myRef.setValue(saree_Occations.getText().toString());
        myRef = database.getReference(id+"/Color");
        myRef.setValue(saree_Color.getText().toString());
        myRef = database.getReference(id+"/Id");
        myRef.setValue(saree_id);
        myRef = database.getReference(id+"/Material");
        myRef.setValue(saree_Material.getText().toString());
        myRef = database.getReference(id+"/Style");
        myRef.setValue(saree_style.getSelectedItem().toString());
        myRef = database.getReference(id+"/Rating");
        myRef.setValue("0");
        if (bitmap!=null) {
            myRef = database.getReference(id + "/Photo");
            myRef.setValue(getBase64String(bitmap));
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
        if (requestCode == PICK_IMAGE) {
            //TODO: action

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                saree_photo.setImageBitmap(bitmap);
                filePath = data.getData();
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
