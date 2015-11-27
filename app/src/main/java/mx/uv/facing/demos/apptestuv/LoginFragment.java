package mx.uv.facing.demos.apptestuv;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.activity.Item;
import com.flickr4java.flickr.activity.ItemList;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginFragment extends Fragment {
    public static final String  CALLBACK_URL= "http://facing.uv.mx";

    Flickr flickr;
    AuthInterface authInterface;
    Token requestToken;
    Permission permission = Permission.WRITE;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String apiKey = "a5a896a1116663f3c60435a526d341c0";
        String sharedSecret = "bbd777e4e16707e0";

        final WebView webView = (WebView) getActivity().findViewById (R.id.webViewLogin);
        webView.setWebViewClient (new GalleryWebViewClient ());
        webView.setWebChromeClient (new WebChromeClient ());
        webView.getSettings().setJavaScriptEnabled (true);

        getAuthorizationURL(apiKey, sharedSecret)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(String s) {
                        webView.loadUrl (s);
                    }
                });
    }


    private Observable<String> getAuthorizationURL (final String apiKey, final String sharedSecret) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                flickr = new Flickr (apiKey, sharedSecret, new REST ());
                authInterface = flickr.getAuthInterface ();
                requestToken = authInterface.getRequestToken (CALLBACK_URL);
                String authorizationURL = authInterface.getAuthorizationUrl (requestToken, permission);

                subscriber.onNext (authorizationURL);
            }
        });
    }

    private class GalleryWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            if (url.startsWith (CALLBACK_URL)) {
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter ("oauth_token") != null) {
                    String oauthVerifier = uri.getQueryParameter ("oauth_verifier");
                    Verifier verifier = new Verifier (oauthVerifier);

                    getAccessToken (requestToken, verifier)
                            .observeOn(AndroidSchedulers.mainThread ())
                            .subscribeOn (Schedulers.io ())
                            .subscribe(new Observer<Token>() {
                                @Override
                                public void onCompleted() {}

                                @Override
                                public void onError(Throwable e) {}

                                @Override
                                public void onNext(Token token) {
                                    Toast.makeText(getContext(), token.getToken (), Toast.LENGTH_SHORT).show();

                                }
                            });

                    return true;
                }
            }

            return super.shouldOverrideUrlLoading (view, url);
        }

        private Observable<Token> getAccessToken (final Token requestToken, final Verifier verifier) {
            return Observable.create (new Observable.OnSubscribe<Token>() {
                @Override
                public void call (Subscriber<? super Token> subscriber) {
                    Token token = authInterface.getAccessToken (requestToken, verifier);
                    subscriber.onNext (token);
                }
            });
        }
    }

}
