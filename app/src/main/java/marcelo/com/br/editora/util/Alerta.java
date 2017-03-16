package marcelo.com.br.editora.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.SeekBar;

import java.lang.reflect.Method;

/**
 * Created by Marcelo on 14/03/2017.
 */

public  class   Alerta {
    // Metodo que gera um Snackbar.
    public static Snackbar showSnackbar(View view, String mensagem, int duracao) {
        Snackbar snackbar = Snackbar.make(view, mensagem, duracao);
        snackbar.show();
        return snackbar;
    }

    // Metodo que gera um AlertDialog.
    public static AlertDialog showAlertDialog(Context context, String titulo, int mensagem, View view, String textBotaoPositivo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(mensagem);
        alertDialog.setView(view);
        return alertDialog.show();
    }
}
