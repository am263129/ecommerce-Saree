package social.media.saree.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import social.media.interfaces.ItemClickListener;
import social.media.saree.R;
import social.media.saree.saree.saree;
import social.media.saree.saree.sareeAdapter_list;
import social.media.saree.util.Global;

public class product_view_all extends AppCompatActivity {


    ArrayList<saree> array_all_sarees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view_all);

        TextView saree_style = (TextView)findViewById(R.id.saree_style);
        ListView saree_list = (ListView) findViewById(R.id.saree_list);

        array_all_sarees = new ArrayList<saree>();
        final Intent intent = getIntent();
        String style = intent.getStringExtra("Style");
        String fabric = intent.getStringExtra("Fabric");
        for (int i = 0; i < Global.array_all_sarees.size(); i++){
            if(style != null)
                if (Global.array_all_sarees.get(i).getSaree_style().equals(style))
                    array_all_sarees.add(Global.array_all_sarees.get(i));
            if (fabric != null)
                if (Global.array_all_sarees.get(i).getSaree_Fabric().equals(fabric))
                    array_all_sarees.add(Global.array_all_sarees.get(i));
        }

        if (style == null)
            saree_style.setText(fabric);
        else
            saree_style.setText(style);


        sareeAdapter_list adapter = new sareeAdapter_list(this,R.layout.item_saree_list,array_all_sarees);
        saree_list.setAdapter(adapter);
        adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent detail = new Intent(product_view_all.this, product_view.class);
                Global.selected_saree = array_all_sarees.get(position);
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
