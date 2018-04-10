package com.example.quxing.quxing.Auxiliary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;

public class Item_DetailsActivity extends AppCompatActivity {

//    int []images = new int[]
//            {
//                    R.mipmap.ic_wode_collection,
//                    R.mipmap.ic_wode_collection1,
//            };

//    int currImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ImageView pic = (ImageView)findViewById(R.id.item_collection);
//        final ImageView image = new ImageView(this);
//
//        image.setImageResource(images[0]);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image.setImageResource(images[++currImg % images.length]);
//            }
//        });

    }






    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collection:

                Toast.makeText(Item_DetailsActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
//                TextView run_action;
//
//                run_action = (TextView) findViewById(R.id.collection_text);//
//
//                run_action.setOnClickListener((View.OnClickListener) this);
//                if (isActive) {
//                    isActive = false;
//                    run_action.setText("收藏");
//                }else{
//                    isActive = true;
//                    run_action.setText("已收藏");
//                }
//                break;
            case R.id.talking:
//                Intent intent_2 = new Intent(this,Main_HotActivity.class);
//                startActivity(intent_2);
//                break;
//            case R.id.main_new:
//                Intent intent_3 = new Intent(this,Main_NewActivity.class);
//                startActivity(intent_3);
//                break;
            default:
                break;
        }
    }




    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Item_DetailsActivity.this, MainActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
