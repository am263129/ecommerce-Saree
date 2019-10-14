package social.media.saree.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import social.media.saree.R;
import social.media.saree.util.Global;

public class Order_Manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__manager);
        ListView order_list = (ListView)findViewById(R.id.order_list);

        orderAdapter_list_basic adapter = new orderAdapter_list_basic(Order_Manager.this, R.layout.item_order_list_basic, Global.array_all_order);
        order_list.setAdapter(adapter);

        order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Order_Manager.this, make_Order.class);
                intent.putExtra("Index", i);
                startActivity(intent);
            }
        });
    }
}
