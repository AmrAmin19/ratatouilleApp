package com.example.ratatouilleapp.View.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.AuthPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;


public class AuthFragment extends Fragment implements Iauth
{

    //TextView authText;
    ImageView faceBtn;
    ImageView googleBtn;
    Button signUpNtn;
    TextView login;
    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 20;

    AuthPresenter presenter;
    public AuthFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_auth, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter=new AuthPresenter(Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);

        faceBtn=view.findViewById(R.id.facebookImage);
        googleBtn=view.findViewById(R.id.googleImage);
        signUpNtn=view.findViewById(R.id.button);
        login=view.findViewById(R.id.textView2);



        signUpNtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_authFragment_to_signUpFragment);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_authFragment_to_logInFragment);
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUsingGoogle();


            }
        });


    }


//    private void signInUsingGoogle() {
//        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this.getContext(), signInOptions);
//        mGoogleSignInClient.signOut();  // Optional: Sign out any existing users
//
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
//                if (account != null) {
//                    presenter.linkGoogle(account);
//                }
//            } catch (ApiException e) {
//                Toast.makeText(this.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this.getContext(), "Sucsses Sign in" , Toast.LENGTH_SHORT).show();

//        GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(this.getContext());
//
//        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isSignedIn", true);
//        editor.putString("userEmail",googleSignInAccount.getEmail());
//        editor.apply();

        Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

    }

    @Override
    public void onSignInFailure(String error) {
        Toast.makeText(this.getContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }


    private void signInUsingGoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this.getContext(), signInOptions);
        mGoogleSignInClient.signOut();  // Optional: Sign out any existing users

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                if (account != null) {
                    presenter.signInWithGoogle(account.getIdToken());
                  //  loginPresenter.signInUsingGmailAccount(account.getIdToken(), this);
                  //  signInUsingGmailAccount(account.getIdToken());
                }
            } catch (ApiException e) {
                Toast.makeText(this.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

//    public void signInUsingGmailAccount(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//
//                Toast.makeText(this.getContext(), "Sucsses google", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this.getContext(), "fail google", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}