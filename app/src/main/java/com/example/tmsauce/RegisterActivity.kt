package com.example.tmsauce

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import java.util.concurrent.TimeUnit

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

        // Initialize Firebase Auth
        // auth = Firebase.auth

        // Initialize phone auth callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
            }
        }

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
                startPhoneNumberVerification(phone)

//                val intent = Intent(this, VerificationActivity::class.java).apply{
//                }
//                startActivity(intent)
            }
        }

    }


    // [START on_start_check_user]
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
//                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
        val intent = Intent(this, VerificationActivity::class.java).apply{
        }
        startActivity(intent)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
    }

    // [START resend_verification]
    private fun resendVerificationCode(
            phoneNumber: String,
            token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")

                        val user = task.result?.user
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                        // Update UI
                    }
                }
    }

    private fun updateUI(user: FirebaseUser? = auth.currentUser) {

    }

    companion object {
        private const val TAG = "RegisterActivity"
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
