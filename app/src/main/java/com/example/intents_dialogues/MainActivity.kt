package com.example.intents_dialogues

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var firstName: EditText;
    private lateinit var lastName: EditText;
    private lateinit var email: EditText;
    private lateinit var webViewWindow: WebView;
    private lateinit var progressDialog: ProgressDialog;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        webViewWindow = findViewById(R.id.webViewWindow);
        progressDialog =  ProgressDialog(this);


        webViewWindow.webViewClient = this.WebViewClient();
        webViewWindow.loadUrl("https://github.com/AhmedTrabelsy")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()
        webViewWindow.settings.javaScriptEnabled = true;
        webViewWindow.settings.setSupportZoom(true)
    }

        inner class WebViewClient : android.webkit.WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean{
                view.loadUrl(url);
                return false;
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressDialog.cancel();
            }
        }

    fun valider(view: View) {
        val inputLength = firstName.text.length * lastName.text.length * email.text.length;
        if(inputLength < 3){
            val ad: AlertDialog.Builder = AlertDialog.Builder(this);
            ad.setMessage("Les champs ne doivent pas être vide !");
            ad.setTitle("Erreur !");
            ad.setIcon(android.R.drawable.btn_dialog)
            ad.setPositiveButton("Try again"){
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            val a = ad.create();
            a.show();
        }else{
            launchProgressDialog();
        }
    }

    private fun launchProgressDialog() {
        progressDialog.setTitle("Creating your account..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Don't close this window");
        progressDialog.setIndeterminate(true)
        progressDialog.setProgress(0);
        progressDialog.show()
        val snackBar = Snackbar.make(findViewById(R.id.rootElement),"Connexion en cours..", Snackbar.LENGTH_SHORT);
        snackBar.show();
    }


}