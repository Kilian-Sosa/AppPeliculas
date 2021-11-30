package dam.pgl.films;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {
    public SharedPreferences preferences;
    private EditText editTextAPI;
    private EditText editTextEndPointF;
    private EditText editTextEndPointC;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        editTextAPI=findViewById(R.id.editTextAPI);
        editTextEndPointF=findViewById(R.id.editTextEndPointF);
        editTextEndPointC=findViewById(R.id.editTextEndPointC);
        btnSave=findViewById(R.id.btnSave);

        // Storing data into SharedPreferences
        preferences = getSharedPreferences("datos", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)

        editTextAPI.setText(preferences.getString("api_key", "1865f43a0549ca50d341dd9ab8b29f49"));
        editTextEndPointF.setText(preferences.getString("endPointF", "http://api.themoviedb.org/3/discover/movie?api_key=1865f43a0549ca50d341dd9ab8b29f49&language=es"));
        editTextEndPointC.setText(preferences.getString("endPointC", "https://api.themoviedb.org/3/movie/580489/credits?api_key=1865f43a0549ca50d341dd9ab8b29f49&language=en-ES&credit_id=580489"));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("api_key", editTextAPI.getText().toString());
                editor.putString("endPointF", editTextEndPointF.getText().toString());
                editor.putString("endPointC", editTextEndPointC.getText().toString());
                editor.commit();
                Toast t = Toast.makeText(getApplicationContext(), "Configuración guardada correctamente.", Toast.LENGTH_SHORT);
                t.show();
                finish();
            }
        });
    }
}
