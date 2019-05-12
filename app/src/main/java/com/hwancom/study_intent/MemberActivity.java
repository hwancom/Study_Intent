package com.hwancom.study_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        if (getIntent() != null) {
            String id = getIntent().getStringExtra("id");
            String password = getIntent().getStringExtra("pwd");

            Toast.makeText(this, "ID : " + id + ", Password : " + password, Toast.LENGTH_SHORT).show();

            findViewById(R.id.customerButton).setOnClickListener(this);
            findViewById(R.id.salesButton).setOnClickListener(this);
            findViewById(R.id.goodsButton).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.customerButton :
                intent.putExtra("result", "고객 관리");
                break;
            case R.id.salesButton :
                intent.putExtra("result", "매출 관리");
                break;
            case R.id.goodsButton :
                intent.putExtra("result", "상품 관리");
        }

        setResult(RESULT_OK, intent);
    finish();
    }
}
