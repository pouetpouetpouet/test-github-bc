package com.dbStudio.appelgoogle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class AppelGoogle extends Activity {
	
	final Activity activity = this;
	
	/**
	 * Nom : monEcouteur
	 * Création d'un écouteur pour le bouton
	 * Ecoute l'action du bouton
	 */
	
	private android.view.View.OnClickListener monEcouteur=new View.OnClickListener() {
		
		/* 
		 * Action sur le onClick
		 */
		
		public void onClick(View v) {		
			//execute la recherche sur la webview
			executeSearch();
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appel_google);
        //instancie le bouton "Google"
        Button b = (Button)findViewById(R.id.search);
        //attribue au bouton l'ecouteur "monEcouteur"
        b.setOnClickListener(monEcouteur);
        //instancie la webview
        WebView webview = (WebView)findViewById(R.id.webViewSearch);
        //String du message dans la webview par defaut
        String summary = "<html><body><center>Votre recherche apparaitra ici.</center></body></html>";
        // execution du message dans la webview
        webview.loadData(summary, "text/html", null);
        
        //on instancie l'editText
        EditText mEditText = (EditText)findViewById(R.id.searchText);
        // on Attribue l'écouteur sur la touche "ENTREE"
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) { //au moment ou la touche est appuyée
                    if (keyCode == KeyEvent.KEYCODE_ENTER) { //on vérifie si c'est la touche ENTREE
                        executeSearch(); // si "oui" on lance la recherche
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_appel_google, menu);
        return true;
    }
    
    /* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * Nom : onOptionsItemSelected
	 * Permet d'attribuer une action aux items du menu
	 */
    public boolean onOptionsItemSelected(MenuItem item) {
    	Boolean result = false;
		switch (item.getItemId()) {
		case R.id.quitter: // si on appuie sur "Quitter"
			finish(); // on ferme l'application
			result = true;
		default:
			break;
		}
		return result;
	}
	
    

    /**
     * @param view
     * @param url
     * forcer l'url a rester dans la webview
     * @return
     */
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
            Log.v("CALLED SHOULD OVERRIDE URL LOADING", url.toString());

            if (url.contains("http://null")) {
            return true;
        }

        else {
                view.loadUrl(url);
            return false;
          }
    }
    public void executeSearch(){
    	// on récupére le texte de la editText
    	
    	//on instancie "mEditText"
    	EditText mEditText = (EditText)findViewById(R.id.searchText);
    	// on créé une variable "requete" qui contient l'url de google + le texte de la editText. 
		String requete = "Http://google.fr/search?q=" + mEditText.getText();
		
		//permet de fermer le clavier android
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
		
		// on execute la recherche dans la webview
		
		// on instancie la webview
		WebView webview = (WebView) findViewById(R.id.webViewSearch);
		//on instancie la progressBar
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        //on attribue la valeur de la progressBar par rapport au chargement
        webview.setWebChromeClient(new WebChromeClient() {
          public void onProgressChanged(WebView view, int progress) {
        	pb.setProgress(progress); 
          }
        });
        
       //forcer les url a rester dans la webview
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return false;
           }
        });
        // lance la recherche en forcant l'affichage dans la webview
        shouldOverrideUrlLoading(webview, requete);
    }
}

