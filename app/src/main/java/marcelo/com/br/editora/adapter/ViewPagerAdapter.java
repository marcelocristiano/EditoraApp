package marcelo.com.br.editora.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import marcelo.com.br.editora.fragment.CadastroFragment;
import marcelo.com.br.editora.fragment.ListaLivro;

/**
 * Created by Marcelo on 14/03/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titulo;

    public ViewPagerAdapter(FragmentManager fm, String[] titulo) {
        super(fm);
        this.titulo = titulo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListaLivro();
            case 1:
                return new CadastroFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return titulo.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulo[position];
    }
}
