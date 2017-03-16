package marcelo.com.br.editora;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;

import marcelo.com.br.editora.adapter.ViewPagerAdapter;
import marcelo.com.br.editora.fragment.CadastroFragment;
import marcelo.com.br.editora.fragment.ListaLivro;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Recupera objetos da View.
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        // Cria um array de Strings de tirulos.
        String[] titulo = {"Lista de Livros", "Cadastro de Livro"};
        // Seta o Adapeter a ViewPage.
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titulo));
        // Seta a view pager do tablyout.
        tabLayout.setupWithViewPager(viewPager);

        // Seta icones das Tabs.
        tabLayout.getTabAt(0).setIcon(R.drawable.list);
        tabLayout.getTabAt(1).setIcon(R.drawable.book_branco_2);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
