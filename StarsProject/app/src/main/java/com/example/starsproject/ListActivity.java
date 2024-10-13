package com.example.starsproject;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

// **Update the import here**
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starsproject.beans.Star;
import com.example.starsproject.service.StarService;
import com.example.starsproject.adapter.StarAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycler_view);

        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {
        service.create(new Star("bryan cranston", "https://m.media-amazon.com/images/M/MV5BMTA2NjEyMTY4MTVeQTJeQWpwZ15BbWU3MDQ5NDAzNDc@._V1_.jpg", 5f));
        service.create(new Star("kit harington", "https://upload.wikimedia.org/wikipedia/commons/3/32/Kit_harrington_by_sachyn_mital_%28cropped_2%29.jpg", 3.0f));
        service.create(new Star("andrew lincoln", "https://fr.web.img5.acsta.net/pictures/16/10/25/11/49/315640.jpg", 3.5f));
        service.create(new Star("willem dafoe", "https://media.themoviedb.org/t/p/w500/ui8e4sgZAwMPi3hzEO53jyBJF9B.jpg", 3.0f));
        service.create(new Star("jk simmons", "https://static.tvtropes.org/pmwiki/pub/images/k_simmons.png", 4.0f));
        service.create(new Star("javier bardem", "https://fr.web.img6.acsta.net/pictures/17/05/19/11/35/232032.jpg", 4.0f));
        service.create(new Star("denzel washington", "https://image.tmdb.org/t/p/original/jj2Gcobpopokal0YstuCQW0ldJ4.jpg", 3.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Inflate the menu
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("ListActivity", newText);
                starAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
