package social.media.saree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL;

import social.media.saree.Member.Member;
import social.media.saree.login.LoginActivity;
import social.media.saree.product.product_view_all;
import social.media.saree.product.upload_product;
import social.media.saree.regiser.register;
import social.media.saree.saree.saree;
import social.media.saree.util.Global;

public class MainActivity extends AppCompatActivity {
    public static MainActivity myself;
    LinearLayout Fashion_saree, BestSeller_saree, New_Saree, Upload_product, Order_manager, adminPanel;
    ImageView Image_area_a, Image_area_b;
    String TAG = "MainActivity";
    private ArrayList<Member> array_all_members = new ArrayList<Member>();
    private ArrayList<saree> array_all_sarees = new ArrayList<saree>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Image_area_a = (ImageView)findViewById(R.id.image_area_a);
        Image_area_b = (ImageView)findViewById(R.id.image_area_b);
        Fashion_saree = (LinearLayout)findViewById(R.id.fashion_sarees);
        BestSeller_saree = (LinearLayout)findViewById(R.id.best_seller);
        New_Saree = (LinearLayout)findViewById(R.id.new_saree);
        Upload_product = (LinearLayout)findViewById(R.id.btn_upload_product);
        Order_manager = (LinearLayout)findViewById(R.id.order_manager);
        adminPanel = (LinearLayout)findViewById(R.id.admin_panel);

        final Intent intent = new Intent(MainActivity.this, product_view_all.class);
        Fashion_saree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_area_a.setBackgroundResource(R.drawable.select_fashion);
                intent.putExtra("Style","Fashion");
                startActivity(intent);
            }
        });
        BestSeller_saree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_area_a.setBackgroundResource(R.drawable.select_best_seller);
                intent.putExtra("Style","Best Seller");
                startActivity(intent);
            }
        });
        New_Saree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_area_a.setBackgroundResource(R.drawable.select_new);
                intent.putExtra("Style","New");
                startActivity(intent);
            }
        });
        Upload_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, upload_product.class);
                startActivity(intent);
            }
        });
        myself = this;

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();

        FirebaseApp.initializeApp(this);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        catch (Exception e){
            Log.e(TAG, e.toString());
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Member");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is:" + value);
                Global.array_all_members.clear();
                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
//                    Member Member = dataSnapshot.getValue(Member.class);
//                    Log.d(TAG, "User name: " + Member.getName() + ", email " + Member.getEmail());
                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;
                            String userName = userData.get("Name").toString();
                            String userEmail = userData.get("Personal Email").toString();
                            String userGender = userData.get("Gender").toString();
                            String password = userData.get("Password").toString();
                            String user_designation = userData.get("Designation").toString();
                            String user_official_email = userData.get("Official Email").toString();
                            String user_official_number = userData.get("Official Number").toString();
                            String user_personal_number = userData.get("Personal Number").toString();
                            String base64photo = "";
                            String birthday = null;
                            String address = null;
                            String location = null;
                            try {
                                base64photo = userData.get("Photo").toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            try {
                                birthday = userData.get("Birthday").toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            try {
                                address = userData.get("Address").toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            try {
                                location = userData.get("Location").toString();
                            }
                            catch (Exception e)
                            {
                                Log.e(TAG, "user profile is not complited");
                                if (userEmail.equals(Global.current_user_email))
                                    Toast.makeText(MainActivity.this,"You didn't complete profile. please complete the profile",Toast.LENGTH_LONG).show();
                            }

                            if(userEmail.equals(Global.current_user_email)) {
                                Global.current_user_name = userName;
                                Global.current_user_index = array_all_members.size();
                                try {
                                    Global.current_user_photo = base64photo;
                                }
                                catch (Exception e){
                                    Log.e(TAG,e.toString());
                                }
                                try {
                                    String permission = userData.get("Permission").toString();
                                    if (permission.equals("admin")) {
                                        Global.is_admin = true;
                                        adminPanel.setVisibility(View.VISIBLE);
                                    }

                                }
                                catch (Exception e){
                                    Log.e(TAG, "No permission");

                                }
                            }
                            array_all_members.add(new Member(userName, userEmail, userGender, base64photo, birthday, address, location, password, user_designation, user_official_email, user_official_number, user_personal_number));
                        }catch (Exception cce){
                            Log.e(TAG, cce.toString());
                        }

                    }
                    Global.array_all_members = array_all_members;
                    progressDialog.dismiss();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG,"Failed to rad value ", databaseError.toException());
                progressDialog.dismiss();
            }
        });

        myRef = database.getReference("Product/Saree");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Global.array_all_sarees.clear();
                Global.array_Fashion_sarees.clear();
                Global.array_BestSeller_sarees.clear();
                Global.array_New_sarees.clear();

                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;
                            String saree_Id = userData.get("Id").toString();
                            String saree_Name = userData.get("Name").toString();
                            String saree_Label = userData.get("Label").toString();
                            String saree_Price = userData.get("Price").toString();
                            String saree_Material = userData.get("Material").toString();
                            String saree_Length = userData.get("Length").toString();
                            String saree_Occations = userData.get("Occations").toString();
                            String saree_Color = userData.get("Color").toString();
                            String saree_Rating = userData.get("Rating").toString();
                            String saree_Description = "";
                            try {
                                saree_Description = userData.get("Description").toString();
                            }catch (Exception e){
                                Log.e(TAG,e.toString());
                            }

                            String saree_Style = userData.get("Style").toString();
                            String base64photo = "";
                            try {
                                base64photo = userData.get("Photo").toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            saree new_saree = new saree(saree_Id, saree_Name, saree_Label, saree_Price, saree_Material, saree_Length, saree_Occations, saree_Color, saree_Rating, saree_Description, saree_Style,base64photo, null, null, null ) ;
                            array_all_sarees.add(new_saree);
                            if (saree_Style.equals("Fashion"))
                                Global.array_Fashion_sarees.add(new_saree);
                            else if (saree_Style.equals("Best Seller"))
                                Global.array_BestSeller_sarees.add(new_saree);
                            else if (saree_Style.equals("New"))
                                Global.array_New_sarees.add(new_saree);
                        }catch (Exception cce){
                            Log.e(TAG, cce.toString());
                        }

                    }
                    Global.array_all_sarees = array_all_sarees;
                    progressDialog.dismiss();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG,"Failed to rad value ", databaseError.toException());
                progressDialog.dismiss();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.miCart:
                Intent shareIntent = new Intent (Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "your body here";
                String shareSub = "Your subject here";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra (Intent.EXTRA_TEXT, shareBody);
                startActivity (Intent.createChooser (shareIntent,"Share App Locker"));
                return true;

            case R.id.miProfile:
                // Some other methods
                Intent intent;
                if (!Global.current_user_name.equals("")) {
                    intent = new Intent(MainActivity.this, register.class);
                    intent.putExtra("mode","edit");
                    startActivity(intent);
                }
                else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static MainActivity getInstance(){

        return myself;
    }
}
