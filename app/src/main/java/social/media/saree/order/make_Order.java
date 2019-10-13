package social.media.saree.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import social.media.saree.R;
import social.media.saree.util.Global;

public class make_Order extends AppCompatActivity {
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);
        ListView order_list = (ListView)findViewById(R.id.order_list);
        TextView client_name = (TextView)findViewById(R.id.order_from_name);
        TextView client_address = (TextView)findViewById(R.id.order_from_address);
        TextView client_email = (TextView)findViewById(R.id.order_from_email);
        TextView client_location = (TextView)findViewById(R.id.order_from_location);
        TextView created_date = (TextView)findViewById(R.id.order_created_date);
        TextView order_id = (TextView)findViewById(R.id.order_id);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        client_name.setText(Global.current_user_name);
        client_address.setText(Global.array_all_members.get(Global.current_user_index).getAddress());
        client_email.setText(Global.current_user_email);
        client_location.setText(Global.array_all_members.get(Global.current_user_index).getLocation());
        created_date.setText(String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year));
        final int min = 10000000;
        final int max = 99999999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        order_id.setText(String.valueOf(random));

    }
}
