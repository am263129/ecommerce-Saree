package social.media.saree.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import social.media.saree.R;
import social.media.saree.order.make_Order;
import social.media.saree.util.Global;

public class Cart_view extends AppCompatActivity {

    static ListView cart_list;
    static cartAdapter_list adapter;
    private static Cart_view self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        cart_list = (ListView)findViewById(R.id.cart_list);
        Button make_order = (Button)findViewById(R.id.btn_make_order);
        make_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Global.current_user_name.equals("")) {
                    Intent intent = new Intent(Cart_view.this, make_Order.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Cart_view.this,"Please Reginter to make order", Toast.LENGTH_LONG).show();
                }
            }
        });
        self = this;
        set_list();
    }
    public static  void set_list(){
        adapter = new cartAdapter_list(Cart_view.getInstance(),R.layout.item_cart_list, Global.user_carts);
        cart_list.setAdapter(adapter);
    }
    public static Cart_view getInstance(){
        return  self;
    }
}
