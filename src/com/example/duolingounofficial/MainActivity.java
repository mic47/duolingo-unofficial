package com.example.duolingounofficial;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

public class MainActivity extends Activity {

	private WebView myWebView;
	
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
        	((WebView)findViewById(R.id.webview)).restoreState(savedInstanceState);
        	myWebView = (WebView) findViewById(R.id.webview);
        }        
        if (myWebView == null) {
	        myWebView = (WebView) findViewById(R.id.webview);
	        android.webkit.WebSettings webSettings = myWebView.getSettings();
	        webSettings.setJavaScriptEnabled(true);
	        //webSettings.setAppCacheEnabled(true);
	        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
	        myWebView.setWebChromeClient(new WebChromeClient() {
	        	  public void onConsoleMessage(String message, int lineNumber, String sourceID) {
	        	    Log.d("MyApplication", message + " -- From line "
	        	                         + lineNumber + " of "
	        	                         + sourceID);
	        	  }
	         });
	        
	       // WebViewClient a;
	       // myWebView.
	        
	        myWebView.setWebViewClient(new WebViewClient() { 
	            @Override   
	            public void onPageFinished(WebView view, String url)  
	            {  
	            	
	                myWebView.loadUrl("javascript:(function() {   " +
	                		"function loadcssfile(filename){" +
	                        "var fileref=document.createElement(\"link\");" +
	                    	"fileref.setAttribute(\"rel\", \"stylesheet\");" +
	                    	"fileref.setAttribute(\"type\", \"text/css\");" +
	                    	"fileref.setAttribute(\"href\", filename);" +
	                    	"if (typeof fileref!=\"undefined\") " +
	                    	"document.getElementsByTagName(\"head\")[0].appendChild(fileref);" +
	                    	"}" +
	                    	"loadcssfile(\"http://compbio.fmph.uniba.sk/~mic/mystyle.css\");" +
	                    	"console.log(\"done\")"+
	                        "})()");
	                //myWebView.loadData("/android_asset/mystyle.css", "text/html", "utf-8");
	                	                
	            }  
	        });  
	        //myWebView.loadUrl("http://duolingo.com/#/skill/es/Basics-1/2");
	        myWebView.loadUrl("http://duolingo.com");
        }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig){        
        super.onConfigurationChanged(newConfig);
    }
    
    
    protected void onSaveInstanceState(Bundle outState) {
        myWebView.saveState(outState);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navlink_home:
                myWebView.loadUrl("http://duolingo.com/");
                return true;
            case R.id.navlink_help:
            	myWebView.loadUrl("http://duolingo.com/#/help");
                return true;
            case R.id.navlink_discussions:
            	myWebView.loadUrl("http://duolingo.com/#/questions");
                return true;
            case R.id.navlink_logout:
            	myWebView.loadUrl("http://duolingo.com/logout");
                return true;
            case R.id.navlink_settings:
            	myWebView.loadUrl("http://duolingo.com/#/settings/account");
                return true;
            case R.id.navlink_translations:
            	myWebView.loadUrl("http://duolingo.com/#/translations");
                return true;
            case R.id.navlink_vocab:
            	myWebView.loadUrl("http://duolingo.com/#/vocab");
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}
