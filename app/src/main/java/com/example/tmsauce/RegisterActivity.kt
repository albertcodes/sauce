package com.example.tmsauce

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

class RegisterActivity : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // get references
        val backToLoginButton = findViewById<Button>(R.id.back_to_login_button)
        val signUpButton = findViewById<Button>(R.id.signup_button)
        val inputName = findViewById<EditText>(R.id.name_register)
        val inputPhoneNumber = findViewById<EditText>(R.id.phonenumber_register)
        val inputPassword = findViewById<EditText>(R.id.password_register)

        // [START initialize_auth]
        // Initialize Firebase Auth
//        auth = Firebase.auth

        // set on-click listener
        backToLoginButton.setOnClickListener {
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java).apply{
            }
            startActivity(intent)
        }

        // set on-click listener
        signUpButton.setOnClickListener {
            // Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val name: String = inputName.text.toString()
            val phone: String = inputPhoneNumber.text.toString()
            val password: String = inputPassword.text.toString()
            if (TextUtils.isEmpty(name))
            {
                Toast.makeText(this, "Invalid Name", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(phone))
            {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(password))
            {
                Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            }
            else {
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phone)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
//                Toast.makeText(this, " Done", Toast.LENGTH_SHORT).show();
                val intent = Intent(this, VerificationActivity::class.java).apply{
                }
                startActivity(intent)
            }
        }



//        private void CreateAccount()
//        {
//            String name = InputName.getText().toString();
//            String phone = InputPhoneNumber.getText().toString();
//            String password = InputPassword.getText().toString();
//
//            if (TextUtils.isEmpty(name))
//            {
//                Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
//            }
//            else if (TextUtils.isEmpty(phone))
//            {
//                Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
//            }
//            else if (TextUtils.isEmpty(password))
//            {
//                Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                loadingBar.setTitle("Create Account");
//                loadingBar.setMessage("Please wait, while we are checking the credentials.");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
//
//                ValidatephoneNumber(name, phone, password);
//            }
//        }

    }

}



//    private void ValidatephoneNumber(final String name, final String phone, final String password)
//    {
//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference();
//
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                if (!(dataSnapshot.child("Users").child(phone).exists()))
//                {
//                    HashMap<String, Object> userdataMap = new HashMap<>();
//                    userdataMap.put("phone", phone);
//                    userdataMap.put("password", password);
//                    userdataMap.put("name", name);
//
//                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task)
//                                {
//                                    if (task.isSuccessful())
//                                    {
//                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
//                                        loadingBar.dismiss();
//
//                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    }
//                                    else
//                                    {
//                                        loadingBar.dismiss();
//                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
//                else
//                {
//                    Toast.makeText(RegisterActivity.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
