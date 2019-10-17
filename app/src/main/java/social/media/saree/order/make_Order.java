package social.media.saree.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import social.media.saree.R;
import social.media.saree.cart.cartAdapter_list_basic;
import social.media.saree.util.Global;

public class make_Order extends AppCompatActivity {
    private Calendar calendar;
    private int year, month, day;
    String saree_id;


    String CLIENT_NAME = "/Client_Name";
    String CLIENT_EMAIL = "/Client_Email";
    String CLIENT_ADDRESS = "/Client_Address";
    String CLIENT_LOCATION = "/Client_Location";
    String RECEIVER_NAME = "/Receiver_Name";
    String RECEIVER_EMAIL = "/Receiver_Email";
    String RECEIVER_ADDRESS = "/Receiver_Address";
    String RECEIVER_LOCATION = "/Receiver_Location";
    String TOTAL_PRICE = "/Total_Rrice";
    String SHIPPINGPRICE = "/Shipping_Price";
    String CREATED_DATE = "/Created_Date";
    String ORDER_ID = "/Order_Id";
    String SERVICE_EAMIL = "/Service_Email";
    String PRODUCT_NAME = "/Product_Name";
    String RPODUCT_AMOUNT = "/Product_Amount";
    String PRODUCT_PRICE = "/Product_Price";

    String TAG = "make_order";

    TextView client_name;
    TextView client_address;
    TextView client_email;
    TextView client_location;
    TextView receiver_name;
    TextView receiver_address;
    TextView receiver_email;
    TextView receiver_location;
    TextView created_date;
    TextView order_id;
    TextView order_total_price;
    TextView support_email;
    cartAdapter_list_basic adapter;
    LinearLayout manager;
    LinearLayout client;
    String csv_order_id;
    order current_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);
        ListView order_list = (ListView)findViewById(R.id.order_list);
        client_name = (TextView)findViewById(R.id.order_from_name);
        client_address = (TextView)findViewById(R.id.order_from_address);
        client_email = (TextView)findViewById(R.id.order_from_email);
        client_location = (TextView)findViewById(R.id.order_from_location);
        receiver_name  = (TextView)findViewById(R.id.receiver_name);
        receiver_address = (TextView)findViewById(R.id.receiver_address);
        receiver_email = (TextView)findViewById(R.id.receiver_email);
        receiver_location = (TextView)findViewById(R.id.receiver_location);
        created_date = (TextView)findViewById(R.id.order_created_date);
        order_id = (TextView)findViewById(R.id.order_id);
        order_total_price = (TextView)findViewById(R.id.order_total_price);
        support_email =(TextView)findViewById(R.id.support_email);
        manager = (LinearLayout)findViewById(R.id.manager);
        client = (LinearLayout)findViewById(R.id.client);
        Button btn_order_complete = (Button)findViewById(R.id.btn_complete_order);
        Button export_csv = (Button)findViewById(R.id.btn_export);
        btn_order_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_order();
            }
        });
        export_csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                export();
            }
        });
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent base = getIntent();
        Integer index = base.getIntExtra("Index",0);
        if (Global.is_admin){
            client.setVisibility(View.GONE);
            manager.setVisibility(View.VISIBLE);
            current_order = Global.array_all_order.get(index);
            adapter = new cartAdapter_list_basic(this,R.layout.item_cart_list_basic,current_order.getCart());
            client_name.setText(current_order.getClient_name());
            client_address.setText(current_order.getClient_address());
            client_email.setText(current_order.getClient_email());
            client_location.setText(current_order.getClient_location());
            created_date.setText(current_order.getCreated_date());
            order_id.setText(current_order.getOrder_id());
            csv_order_id = current_order.getOrder_id();
            order_total_price.setText(current_order.getOrder_total_price());
        }
        else {
            manager.setVisibility(View.GONE);
            client.setVisibility(View.VISIBLE);
            adapter = new cartAdapter_list_basic(this,R.layout.item_cart_list_basic,Global.user_carts);
            client_name.setText(Global.current_user_name);
            client_address.setText(Global.array_all_members.get(Global.current_user_index).getAddress());
            client_email.setText(Global.current_user_email);
            client_location.setText(Global.array_all_members.get(Global.current_user_index).getLocation());
            created_date.setText(String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year));
            final int min = 10000000;
            final int max = 99999999;
            final int random = new Random().nextInt((max - min) + 1) + min;
            saree_id = String.valueOf(random);
            order_id.setText(saree_id);


            Integer total_price = 0;
            for (int i = 0; i < Global.user_carts.size(); i ++){
                total_price += Integer.parseInt(Global.user_carts.get(i).getProduct_price());
            }
            order_total_price.setText(String.valueOf(total_price));
        }



        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(order_list.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, order_list);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = order_list.getLayoutParams();
        params.height = totalHeight;
        order_list.setLayoutParams(params);


        order_list.setAdapter(adapter);





    }

    private void export() {
        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/order" + csv_order_id + ".csv");

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"Client_name", "Client_address", "Client_location", "Client_email", "Product_id", "Product_name", "Product_amount","Total_Price" });
            if (current_order.getCart().size()>0){
                data.add(new String[]{client_name.getText().toString(), client_address.getText().toString(), client_location.getText().toString(), client_email.getText().toString(),current_order.getCart().get(0).getProduct_id(),current_order.getCart().get(0).getProduct_name(), current_order.getCart().get(0).getProduct_amount(), current_order.getOrder_total_price()});
                for(int i = 0; i < current_order.getCart().size(); i ++){
                    data.add(new String[]{"", "", "", "",current_order.getCart().get(i).getProduct_id(),current_order.getCart().get(i).getProduct_name(), current_order.getCart().get(i).getProduct_amount(), String.valueOf(Integer.parseInt(current_order.getCart().get(i).getProduct_price()) * Integer.parseInt(current_order.getCart().get(i).getProduct_amount()))});
                }
            }
            writer.writeAll(data);

            writer.close();
            Toast.makeText(make_Order.this,"Export Finished", Toast.LENGTH_LONG).show();
            finish();
//            callRead();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(make_Order.this,"Exporting Failed", Toast.LENGTH_LONG).show();
        }

    }

    private void upload_order() {
        FirebaseApp.initializeApp(this);
        String id = "Order/Saree/" + saree_id;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(id+CLIENT_NAME);
        myRef.setValue(client_name.getText().toString());
        myRef = database.getReference(id+CLIENT_EMAIL);
        myRef.setValue(client_email.getText().toString());
        myRef = database.getReference(id+CLIENT_ADDRESS);
        myRef.setValue(client_address.getText().toString());
        myRef = database.getReference(id+CLIENT_LOCATION);
        myRef.setValue(client_location.getText().toString());
        myRef = database.getReference(id+RECEIVER_NAME);
        myRef.setValue(receiver_name.getText().toString());
        myRef = database.getReference(id+RECEIVER_EMAIL);
        myRef.setValue(receiver_email.getText().toString());
        myRef = database.getReference(id+RECEIVER_ADDRESS);
        myRef.setValue(receiver_address.getText().toString());
        myRef = database.getReference(id+RECEIVER_LOCATION);
        myRef.setValue(receiver_location.getText().toString());
        myRef = database.getReference(id+TOTAL_PRICE);
        myRef.setValue(order_total_price.getText().toString());
//        myRef = database.getReference(id+SHIPPINGPRICE);
//        myRef.setValue(saree_Occations.getText().toString());
        myRef = database.getReference(id+CREATED_DATE);
        myRef.setValue(created_date.getText().toString());
        myRef = database.getReference(id+ORDER_ID);
        myRef.setValue(order_id.getText().toString());
        myRef = database.getReference(id+SERVICE_EAMIL);
        myRef.setValue(support_email.getText().toString());
        myRef = database.getReference(id+"/Status");
        myRef.setValue("On Going");
        String subid;
        for (int i = 0; i < Global.user_carts.size(); i ++){
            subid = id + "/Cart/" + Global.user_carts.get(i).getProduct_id();
            myRef = database.getReference(subid + "/Product_ID");
            myRef.setValue(Global.user_carts.get(i).getProduct_id());
            myRef = database.getReference(subid + "/Product_Name");
            myRef.setValue(Global.user_carts.get(i).getProduct_name());
            myRef = database.getReference(subid + "/Product_Amount");
            myRef.setValue(Global.user_carts.get(i).getProduct_amount());
            myRef = database.getReference(subid + "/Product_Price");
            myRef.setValue(Global.user_carts.get(i).getProduct_price());
        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is:" + value);
                Toast.makeText(make_Order.this,"Making Order Completed",Toast.LENGTH_LONG).show();
                Global.user_carts.clear();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG,"Making order Failed ", databaseError.toException());
            }
        });
    }
}
