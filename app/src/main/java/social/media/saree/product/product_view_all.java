package social.media.saree.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import social.media.interfaces.ItemClickListener;
import social.media.saree.R;
import social.media.saree.saree.saree;
import social.media.saree.saree.sareeAdapter_list;
import social.media.saree.util.Global;

public class product_view_all extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view_all);

        TextView saree_style = (TextView)findViewById(R.id.saree_style);
        ListView saree_list = (ListView) findViewById(R.id.saree_list);

        ArrayList<saree> array_all_sarees = new ArrayList<saree>();
        final Intent intent = getIntent();
        String style = intent.getStringExtra("Style");
        switch (style){
            case "Fashion":
                array_all_sarees = Global.array_Fashion_sarees;
                saree_style.setText("Fashion Saree");
                break;
            case "Best Seller":
                array_all_sarees = Global.array_BestSeller_sarees;
                saree_style.setText("Best Sellers");
                break;
            case "New":
                array_all_sarees = Global.array_New_sarees;
                saree_style.setText("New Saree");
                break;
            default:
                array_all_sarees = Global.array_all_sarees;
                break;
        }
        sareeAdapter_list adapter = new sareeAdapter_list(this,R.layout.item_saree_list,array_all_sarees);
        saree_list.setAdapter(adapter);
        adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent detail = new Intent(product_view_all.this, product_view.class);
                detail.putExtra("index", position);
                detail.putExtra("Style",intent.getStringExtra("Style"));
                startActivity(detail);
            }
        });
        /*saree_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(product_view_all.this,"working0",Toast.LENGTH_LONG).show();
                Intent detail = new Intent(product_view_all.this, product_view.class);
                detail.putExtra("index", i);
                detail.putExtra("Style",intent.getStringExtra("Style"));
                startActivity(intent);
            }
        });*/
    }
}
