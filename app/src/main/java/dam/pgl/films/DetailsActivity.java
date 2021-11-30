package dam.pgl.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private static final String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    private String title;
    private String img;
    private String synopsis;
    private String id;
    private Button btnActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Poner el boton de vuelta atrás.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnActors = findViewById(R.id.btnActors);
        // Leer el parametro de la pelicula.
        Intent intent = getIntent();
        this.title = intent.getStringExtra("titulo");
        this.img = intent.getStringExtra("imagen");
        this.synopsis = intent.getStringExtra("sinopsis");
        this.id = intent.getStringExtra("id");
        Log.d("test", "Creditos: Leyendo id:" + id);
        Log.d("test", "Creditos: Leyendo titulo:" + title);
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtText = findViewById(R.id.txtText);
        ImageView imgView = findViewById(R.id.imgView);
        txtTitle.setText(title);
        txtText.setText(synopsis);
        Picasso.get().load(MOVIE_BASE_URL + img).into(imgView);
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        btnActors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(DetailsActivity.this, ActorsActivity.class);
                Bundle b = new Bundle();
                b.putString("idPeli", id);
                p.putExtras(b);
                startActivity(p);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}