package com.hwancom.study_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1000;
    private EditText mId;
    private EditText mPassword;
    private EditText mRePassword;
    private EditText mEmail;
    private RadioButton mMale;
    private RadioButton mFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mId = findViewById(R.id.id_edit);
        mPassword = findViewById(R.id.pwd_edit);
        mRePassword = findViewById(R.id.re_pwd_edit);
        mEmail = findViewById(R.id.email_edit);
        mMale = findViewById(R.id.male_radio_button);
        mFemale = findViewById(R.id.female_radio_button);


        findViewById(R.id.reset_button).setOnClickListener(this);
        findViewById(R.id.sing_up_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MemberActivity.class);
        intent.putExtra("id", mId.getText().toString());
        intent.putExtra("pwd", mPassword.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
        /** REQUEST_CODE는 Find Action을 통해 상수로 선언한다 */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra("result");
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
