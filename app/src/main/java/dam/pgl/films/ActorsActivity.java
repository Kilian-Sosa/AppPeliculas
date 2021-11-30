package dam.pgl.films;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ActorsActivity extends AppCompatActivity {


    public static String apiKeyActores;
    private static final String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    private ArrayList<Actor> listActors = new ArrayList();
    private ListView list;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = findViewById(R.id.listView2);
        Bundle o = getIntent().getExtras();
        id = o.getString("idPeli");
        apiKeyActores="https://api.themoviedb.org/3/movie/"+id+"/credits?api_key=1865f43a0549ca50d341dd9ab8b29f49&language=en-ES&credit_id="+id;
        new ActorsActivity.ObtenerPeliculasAsync().execute(apiKeyActores);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    class ObtenerPeliculasAsync extends AsyncTask<String, Integer, String> {
        ProgressDialog progress;

        protected void onPreExecute() {
            super.onPreExecute();
            // Mostrar progress bar.
            progress = new ProgressDialog(ActorsActivity.this);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Obteniendo actores...");
            progress.setCancelable(false);
            progress.setMax(100);
            progress.setProgress(0);
            progress.show();
        }

        protected String doInBackground(String... params) {
            StringBuilder result = new StringBuilder();

            Log.d("test", "entrando");
            try {
                URL urlObj = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = reader.readLine()) != null) result.append(line);

                Log.d("test", "respuesta: " + result.toString());

            } catch (Exception e) {
                Log.d("test", "error2: " + e.toString());
            }
            return result.toString();
        }

        protected void onProgressUpdate(Integer... a) {
            super.onProgressUpdate(a);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject resp = new JSONObject(result);
                JSONArray actors= resp.getJSONArray("cast");
                for (int i = 0; i < actors.length(); i++) {
                    JSONObject movie = actors.getJSONObject(i);
                    listActors.add(new Actor(
                            movie.getString("profile_path"),
                            movie.getString("name"),
                            movie.getString("original_name")));
                }
                Thread.sleep(2000);
            } catch (InterruptedException i) {
                i.printStackTrace();
            } catch (JSONException j) {
                j.printStackTrace();
            }
            progress.dismiss();
            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), listActors);
            list.setAdapter(adapter);
        }
    }

    class MovieAdapter extends BaseAdapter {
        Context context;
        ArrayList<Actor> arrayList;

        public MovieAdapter(Context context, ArrayList<Actor> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        public int getCount() {
            return arrayList.size();
        }

        public Actor getItem(int position) {
            return arrayList.get(position);
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.list_actors, parent, false);
            TextView name = convertView.findViewById(R.id.tvTitle2);
            name.setText(arrayList.get(position).getNombre());
            TextView description = convertView.findViewById(R.id.tvDescripcion2);
            description.setText(arrayList.get(position).getNombreSecundario());
            ImageView imagen = convertView.findViewById(R.id.list_image2);
            Picasso.get().load(MOVIE_BASE_URL + arrayList.get(position).getImagen()).into(imagen);
            imagen.setScaleType(ImageView.ScaleType.FIT_XY);
            return convertView;
        }
    }
}

