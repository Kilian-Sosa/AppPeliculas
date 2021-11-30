package dam.pgl.films;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        img=findViewById(R.id.imageView);
        int imgID=R.drawable.img_about;
        img.setImageResource(imgID);
    }
}
