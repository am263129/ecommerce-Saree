package social.media.saree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import social.media.saree.product.upload_product;

public class MainActivity extends AppCompatActivity {
    public static MainActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button upload = (Button) findViewById(R.id.btn_upload_product);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, upload_product.class);
                startActivity(intent);
            }
        });
        myself = this;
    }

    public static MainActivity getInstance(){

        return myself;
    }
}
