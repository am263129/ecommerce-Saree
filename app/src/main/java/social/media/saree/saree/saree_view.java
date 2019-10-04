package social.media.saree.saree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import social.media.saree.R;
import social.media.saree.util.Global;

public class saree_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saree_view);

        Intent intent = getIntent();
        String style = intent.getStringExtra("Style");

        ListView saree_list = (ListView)findViewById(R.id.saree_list);
        TextView saree_style = (TextView)findViewById(R.id.saree_style);


        ArrayList<saree> array_saree = new ArrayList<saree>();


        switch (style){
            case "Fashion":
                array_saree = Global.array_Fashion_sarees;
                break;
            case "Best Seller":
                array_saree = Global.array_BestSeller_sarees;
                break;
            case "New":
                array_saree = Global.array_New_sarees;
                break;
            default:
                array_saree = Global.array_all_sarees;
                break;
        }
        sareeAdapter_list adapter = new sareeAdapter_list(this,R.layout.item_saree_list,array_saree);
        saree_list.setAdapter(adapter);
    }
}
