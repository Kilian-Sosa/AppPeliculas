package dam.pgl.films;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        //Toast.makeText(getApplicationContext(),"Configuración guardada",Toast.LENGTH_SHORT).show();
        // Storing data into SharedPreferences
        /*SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("api", API.getText().toString());
        myEdit.putString("api_key", APIKEY.getText().toString());
        myEdit.putString("url_images", name.getText().toString());

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.commit();*/
    }
}
