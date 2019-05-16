package com.hwancom.study_intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MemberActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        if (getIntent() != null) {
            String id = getIntent().getStringExtra("id");
            String pwd = getIntent().getStringExtra("pwd");
            String email = getIntent().getStringExtra("email");
            String gender = getIntent().getStringExtra("gender");

            String result = String.format("아이디 : %s\n비밀번호 : %s\nE-mail : %s\n성별 : %s",
                    id,
                    pwd,
                    email,
                    gender);

            resultTextView = findViewById(R.id.result_text_view);
            resultTextView.setText(result);
        }

        findViewById(R.id.ok_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Intent intent = new Intent();
        // setResult(RESULT_OK, intent);

        setResult(RESULT_OK);
        finish();
    }
}
