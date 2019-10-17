package social.media.saree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import social.media.saree.Member.Member;
import social.media.saree.login.LoginActivity;
import social.media.saree.cart.Cart_view;
import social.media.saree.order.Order_Manager;
import social.media.saree.cart.cart_item;
import social.media.saree.order.order;
import social.media.saree.product.product_view_all;
import social.media.saree.product.upload_product;
import social.media.saree.regiser.register;
import social.media.saree.saree.saree;
import social.media.saree.util.Global;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String NAME = "Name";
    String LABEL = "Label";
    String PRICE = "Price";
    String OLDPRICE = "Old Price";
    String SHIPPINGPRICE = "Shipping Price";
    String OLDSHIPPINGPRICE = "Old Shipping Price";
    String EXTRAINFO = "Extra InFo";
    String FABRIC = "Fabric";
    String LENGTH = "Length";
    String OCCATION = "Occations";
    String COLOR = "Color";
    String ID = "Id";
    String STYLE = "Style";
    String RATING = "Rating";
    String PHOTO_A = "Photo_a";
    String PHOTO_B = "Photo_b";
    String PHOTO_C = "Photo_c";

    String CLIENT_NAME = "Client_Name";
    String CLIENT_EMAIL = "Client_Email";
    String CLIENT_ADDRESS = "Client_Address";
    String CLIENT_LOCATION = "Client_Location";
    String RECEIVER_NAME = "Receiver_Name";
    String RECEIVER_EMAIL = "Receiver_Email";
    String RECEIVER_ADDRESS = "Receiver_Address";
    String RECEIVER_LOCATION = "Receiver_Location";
    String STATUS = "Status";
    String TOTAL_PRICE = "Total_Rrice";
    String SHIPPING_PRICE = "Shipping_Price";
    String CREATED_DATE = "Created_Date";
    String ORDER_ID = "Order_Id";
    String SERVICE_EAMIL = "Service_Email";
    String PRODUCT_NAME = "Product_Name";
    String RPODUCT_AMOUNT = "Product_Amount";
    String PRODUCT_PRICE = "Product_Price";


    final String SILK = "SILK";
    final String GEORGERRE = "GEORGERRE";
    final String SANA = "SANA";
    final String KANJEEVARAM = "KANJEEVARAM";
    final String ARTSILK = "ART SILK";
    final String COTTONSILK = "COTTON SILK";
    final String CHIFFON= "CHIFFON";
    final String BANARASI = "BANARASI";
    final String JAPANSATIN = "JAPAN SATIN";
    final String COTTON = "COTTON";
    final String NET = "NET";
    final String BHAGALPURI = "BHAGALPURI";

    Integer amount_SILK = 0;
    Integer amount_GEORGETTE = 0;
    Integer amount_SANA = 0;
    Integer amount_KANJEEVARAM = 0;
    Integer amount_ARTSILK = 0;
    Integer amount_COTTONSILK = 0;
    Integer amount_CHIFFON = 0;
    Integer amount_BANARASI = 0;
    Integer amount_JAPANSATIN = 0;
    Integer amount_COTTON = 0;
    Integer amount_NET = 0;
    Integer amount_BHAGALPURI = 0;



    public static MainActivity myself;
    LinearLayout shaandar_saree, khoobsurat_saree, dhamakedar_Saree, Upload_product, Order_manager, adminPanel;
    LinearLayout saree_silk, saree_sana, saree_georgtte, saree_kanjeevaram, saree_artsilk, saree_cottonsilk, saree_chiffon, saree_banarasi, saree_japansatin, saree_cotton, saree_net, saree_bhagalpuri;
    ImageView Image_area_a, Image_area_b, style_best_of_georgette, style_new_design;
    TextView
            amount_saree_silk,
            amount_saree_sana,
            amount_saree_georgtte,
            amount_saree_kanjeevaram,
            amount_saree_artsilk,
            amount_saree_cottonsilk,
            amount_saree_chiffon,
            amount_saree_banarasi,
            amount_saree_japansatin,
            amount_saree_cotton,
            amount_saree_net,
            amount_saree_bhagalpuri ;
    String TAG = "MainActivity";
    private ArrayList<Member> array_all_members = new ArrayList<Member>();
    private ArrayList<saree> array_all_sarees = new ArrayList<saree>();
    private ArrayList<order> array_order = new ArrayList<order>();
    Intent intent;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Image_area_a = (ImageView)findViewById(R.id.image_area_a);
        Image_area_b = (ImageView)findViewById(R.id.image_area_b);
        shaandar_saree = (LinearLayout)findViewById(R.id.shaandar_sarees);
        khoobsurat_saree = (LinearLayout)findViewById(R.id.khoobsurat);
        dhamakedar_Saree = (LinearLayout)findViewById(R.id.dhamakedar);
        Upload_product = (LinearLayout)findViewById(R.id.btn_upload_product);
        Order_manager = (LinearLayout)findViewById(R.id.order_manager);
        adminPanel = (LinearLayout)findViewById(R.id.admin_panel);

        saree_silk = (LinearLayout)findViewById(R.id.fabric_silk);
        saree_artsilk = (LinearLayout)findViewById(R.id.fabric_art_silk);
        saree_banarasi = (LinearLayout)findViewById(R.id.fabric_banarasi);
        saree_bhagalpuri = (LinearLayout)findViewById(R.id.fabric_bhagalpuri);
        saree_chiffon = (LinearLayout)findViewById(R.id.fabric_chiffon);
        saree_cottonsilk = (LinearLayout)findViewById(R.id.fabric_cotton_silk);
        saree_cotton = (LinearLayout)findViewById(R.id.fabric_cotton);
        saree_kanjeevaram = (LinearLayout)findViewById(R.id.fabric_kanjeevaram);
        saree_net = (LinearLayout)findViewById(R.id.fabric_net);
        saree_japansatin = (LinearLayout)findViewById(R.id.fabric_japan_satin);
        saree_georgtte = (LinearLayout)findViewById(R.id.fabric_georgette);
        saree_sana = (LinearLayout)findViewById(R.id.fabric_sana);

        amount_saree_silk = (TextView)findViewById(R.id.amount_saree_silk);
        amount_saree_sana = (TextView)findViewById(R.id.amount_saree_sana);
        amount_saree_georgtte = (TextView)findViewById(R.id.amount_saree_georgtte);
        amount_saree_kanjeevaram = (TextView)findViewById(R.id.amount_saree_kanjeevaram);
        amount_saree_artsilk = (TextView)findViewById(R.id.amount_saree_artsilk);
        amount_saree_cottonsilk = (TextView)findViewById(R.id.amount_saree_cottonsilk);
        amount_saree_chiffon = (TextView)findViewById(R.id.amount_saree_chiffon);
        amount_saree_banarasi = (TextView)findViewById(R.id.amount_saree_banarasi);
        amount_saree_japansatin = (TextView)findViewById(R.id.amount_saree_japansatin);
        amount_saree_cotton = (TextView)findViewById(R.id.amount_saree_cotton);
        amount_saree_net = (TextView)findViewById(R.id.amount_saree_net);
        amount_saree_bhagalpuri = (TextView)findViewById(R.id.amount_saree_bhagalpuri);

        style_best_of_georgette = (ImageView)findViewById(R.id.style_best_of_georgette);
        style_new_design = (ImageView)findViewById(R.id.style_new_design);
//        style_new_design.setClipToOutline(true);
        style_new_design.setOnClickListener(this);
//        style_best_of_georgette.setClipToOutline(true);
        style_best_of_georgette.setOnClickListener(this);


        intent = new Intent(MainActivity.this, product_view_all.class);
        shaandar_saree.setOnClickListener(this);
        khoobsurat_saree.setOnClickListener(this);
        dhamakedar_Saree.setOnClickListener(this);
        Upload_product.setOnClickListener(this);
        Order_manager.setOnClickListener(this);

        saree_artsilk.setOnClickListener(this);
        saree_silk.setOnClickListener(this);
        saree_banarasi.setOnClickListener(this);
        saree_bhagalpuri.setOnClickListener(this);
        saree_chiffon.setOnClickListener(this);
        saree_cottonsilk.setOnClickListener(this);
        saree_cotton.setOnClickListener(this);
        saree_kanjeevaram.setOnClickListener(this);
        saree_net.setOnClickListener(this);
        saree_japansatin.setOnClickListener(this);
        saree_georgtte.setOnClickListener(this);
        saree_sana.setOnClickListener(this);

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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Order/Saree/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is:" + value);
                Global.array_all_order.clear();
                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
//                    Member Member = dataSnapshot.getValue(Member.class);
//                    Log.d(TAG, "User name: " + Member.getName() + ", email " + Member.getEmail());
                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;
                            String client_name = userData.get(CLIENT_NAME).toString();
                            String client_email = userData.get(CLIENT_EMAIL).toString();
                            String client_address = userData.get(CLIENT_ADDRESS).toString();
                            String client_location = userData.get(CLIENT_LOCATION).toString();
                            String receiver_name = userData.get(RECEIVER_NAME).toString();
                            String receiver_email = userData.get(RECEIVER_EMAIL).toString();
                            String receiver_address = userData.get(RECEIVER_ADDRESS).toString();
                            String receiver_location = userData.get(RECEIVER_LOCATION).toString();
                            String order_status = userData.get(STATUS).toString();
                            String order_id = userData.get(ORDER_ID).toString();
                            String created_date = userData.get(CREATED_DATE).toString();
                            String total_price = userData.get(TOTAL_PRICE).toString();
                            String shipping_price = "";
                            try {
                                shipping_price = userData.get(SHIPPING_PRICE).toString();
                            }
                            catch (Exception e){

                            }
                            String service_email = userData.get(SERVICE_EAMIL).toString();

                            ArrayList<cart_item> carts = new ArrayList<>();
                            HashMap<String, Object> cart = (HashMap<String, Object>) userData.get("Cart");
                            for (String sub_subkey : cart.keySet()) {
                                Object sub_subdata = cart.get(sub_subkey);
                                cart_item member = null;
                                try {
                                    HashMap<String, Object> hired_Data = (HashMap<String, Object>) sub_subdata;
                                    String product_id = hired_Data.get("Product_ID").toString();
                                    String product_name = hired_Data.get("Product_Name").toString();
                                    String prodcut_price = hired_Data.get("Product_Price").toString();
                                    String product_amount = hired_Data.get("Product_Amount").toString();
                                    member = new cart_item(product_id, product_name, product_amount, prodcut_price);
                                    carts.add(member);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                            array_order.add(new order(
                                    client_name,
                                    client_address,
                                    client_email,
                                    client_location,
                                    receiver_name,
                                    receiver_address,
                                    receiver_email,
                                    receiver_location,
                                    order_status,
                                    created_date,
                                    order_id,
                                    total_price,
                                    shipping_price,
                                    service_email,
                                    carts

                            ));
                        }catch (Exception cce){
                            Log.e(TAG, cce.toString());
                        }

                    }
                    Global.array_all_order = array_order;
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
                Global.array_shaandar_sarees.clear();
                Global.array_Khoobsurat_sarees.clear();
                Global.array_Dhamakedar_sarees.clear();
                amount_BHAGALPURI = amount_ARTSILK = amount_SILK = amount_ARTSILK = amount_BANARASI = amount_CHIFFON
                        = amount_KANJEEVARAM = amount_COTTON = amount_COTTONSILK = amount_NET = amount_SANA = amount_JAPANSATIN = 0;

                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;
                            String saree_Id = userData.get(ID).toString();
                            String saree_Name = userData.get(NAME).toString();
                            String saree_Label = userData.get(LABEL).toString();
                            String saree_Price = userData.get(PRICE).toString();
                            String saree_Oldprice = userData.get(OLDPRICE).toString();
                            String saree_Shipping = userData.get(SHIPPINGPRICE).toString();
                            String saree_Oldshipping = userData.get(OLDSHIPPINGPRICE).toString();
                            String saree_Fabric = userData.get(FABRIC).toString();
                            switch (saree_Fabric){
                                case SILK:
                                    amount_SILK++;
                                    break;
                                case GEORGERRE:
                                    amount_GEORGETTE++;
                                    break;
                                case SANA:
                                    amount_SANA++;
                                    break;
                                case KANJEEVARAM:
                                    amount_KANJEEVARAM++;
                                    break;
                                case ARTSILK:
                                    amount_ARTSILK++;
                                    break;
                                case COTTONSILK:
                                    amount_COTTONSILK++;
                                    break;
                                case CHIFFON:
                                    amount_CHIFFON++;
                                    break;
                                case BANARASI:
                                    amount_BANARASI++;
                                    break;
                                case JAPANSATIN:
                                    amount_JAPANSATIN++;
                                    break;
                                case COTTON:
                                    amount_COTTON++;
                                    break;
                                case NET:
                                    amount_NET++;
                                    break;
                                case BHAGALPURI:
                                    amount_BHAGALPURI++;
                                    break;
                            }
                            String saree_Length = userData.get(LENGTH).toString();
                            String saree_Occations = userData.get(OCCATION).toString();
                            String saree_Color = userData.get(COLOR).toString();
                            String saree_Rating = userData.get(RATING).toString();

                            String saree_ExtraInfo = "";
                            try {
                                saree_ExtraInfo = userData.get(EXTRAINFO).toString();
                            }catch (Exception e){
                                Log.e(TAG,e.toString());
                            }

                            String saree_Style = userData.get(STYLE).toString();
                            String base64photo_a = "";
                            try {
                                base64photo_a = userData.get(PHOTO_A).toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            String base64photo_b = "";
                            try {
                                base64photo_b = userData.get(PHOTO_B).toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            String base64photo_c = "";
                            try {
                                base64photo_c = userData.get(PHOTO_C).toString();
                            }
                            catch (Exception E){
                                Log.e(TAG,E.toString());
                            }
                            saree new_saree = new saree(
                                    saree_Id,
                                    saree_Name,
                                    saree_Label,
                                    saree_Price,
                                    saree_Oldprice,
                                    saree_Shipping,
                                    saree_Oldshipping,
                                    saree_Length,
                                    saree_Fabric,
                                    saree_Color,
                                    saree_Rating,
                                    saree_ExtraInfo,
                                    saree_Occations,
                                    saree_Style,
                                    base64photo_a,
                                    base64photo_b,
                                    base64photo_c);
                            array_all_sarees.add(new_saree);
                        }catch (Exception cce){
                            Log.e(TAG, cce.toString());
                        }

                    }
                    Global.array_all_sarees = array_all_sarees;
                    init_amount();
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

    private void init_amount() {

        amount_saree_silk.setText(String.valueOf(amount_SILK));
        amount_saree_sana.setText(String.valueOf(amount_SANA));
        amount_saree_georgtte.setText(String.valueOf(amount_GEORGETTE));
        amount_saree_kanjeevaram.setText(String.valueOf(amount_KANJEEVARAM));
        amount_saree_artsilk.setText(String.valueOf(amount_ARTSILK));
        amount_saree_cottonsilk.setText(String.valueOf(amount_COTTONSILK));
        amount_saree_chiffon.setText(String.valueOf(amount_CHIFFON));
        amount_saree_banarasi.setText(String.valueOf(amount_BANARASI));
        amount_saree_japansatin.setText(String.valueOf(amount_JAPANSATIN));
        amount_saree_cotton.setText(String.valueOf(amount_COTTON));
        amount_saree_net.setText(String.valueOf(amount_NET));
        amount_saree_bhagalpuri.setText(String.valueOf(amount_BHAGALPURI));
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
//                if (Global.is_admin) {
//                    Intent qrintent = new Intent(MainActivity.this, QRCreator.class);
//                    startActivity(qrintent);
//                }
//                else {
                    Intent cartIntent = new Intent(MainActivity.this, Cart_view.class);
                    startActivity(cartIntent);
//                }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_upload_product:
                Intent upload_intent = new Intent(MainActivity.this, upload_product.class);
                startActivity(upload_intent);
                break;
            case R.id.order_manager:
                Intent order_manager = new Intent(MainActivity.this, Order_Manager.class);
                startActivity(order_manager);
                break;
            case R.id.style_best_of_georgette:
                intent.removeExtra(FABRIC);
                intent.putExtra(STYLE,"BEST GEORGETTE");
                startActivity(intent);
                break;
            case R.id.style_new_design:
                intent.removeExtra(FABRIC);
                intent.putExtra(STYLE,"NEW DESIGN");
                startActivity(intent);
                break;
            case R.id.shaandar_sarees:
                Image_area_a.setBackgroundResource(R.drawable.select_shaandar);
                intent.removeExtra(FABRIC);
                intent.putExtra(STYLE,"SHAANDAR");
                startActivity(intent);
                break;
            case R.id.khoobsurat:
                Image_area_a.setBackgroundResource(R.drawable.select_best_seller);
                intent.removeExtra(FABRIC);
                intent.putExtra(STYLE,"KHOOBSURAT");
                startActivity(intent);
                break;
            case R.id.dhamakedar:
                Image_area_a.setBackgroundResource(R.drawable.select_new);
                intent.removeExtra(FABRIC);
                intent.putExtra(STYLE,"DHAMAKEDAR");
                startActivity(intent);
                break;
            case R.id.fabric_silk:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, SILK);
                startActivity(intent);
                break;
            case R.id.fabric_georgette:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, GEORGERRE);
                startActivity(intent);
                break;
            case R.id.fabric_sana:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, SANA);
                startActivity(intent);
                break;
            case R.id.fabric_kanjeevaram:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, KANJEEVARAM);
                startActivity(intent);
                break;
            case R.id.fabric_art_silk:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, ARTSILK);
                startActivity(intent);
                break;
            case R.id.fabric_cotton_silk:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, COTTONSILK);
                startActivity(intent);
                break;
            case R.id.fabric_chiffon:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, CHIFFON);
                startActivity(intent);
                break;
            case R.id.fabric_banarasi:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, BANARASI);
                startActivity(intent);
                break;
            case R.id.fabric_japan_satin:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, JAPANSATIN);
                startActivity(intent);
                break;
            case R.id.fabric_cotton:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, COTTON);
                startActivity(intent);
                break;
            case R.id.fabric_net:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, NET);
                startActivity(intent);
                break;
            case R.id.fabric_bhagalpuri:
                intent.removeExtra(STYLE);
                intent.putExtra(FABRIC, BHAGALPURI);
                startActivity(intent);
                break;

        }
    }
}
