package com.hwancom.study_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    public static final int REQUEST_CODE_SIGN_UP = 1000;
    private EditText mIdEditText;
    private EditText mPwdEditText;
    private EditText mRePwdEditText;
    private EditText mEmailEditText;
    private RadioGroup mGenderRadioGroup;

    private ArrayList<EditText> mEditTextList;
    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 초기화 버튼을 위해 EditText가 담기는 ArrayList를 만들어 준다 */
        mEditTextList = new ArrayList<>();

        mIdEditText = findViewById(R.id.id_edit);
        mPwdEditText = findViewById(R.id.pwd_edit);
        mRePwdEditText = findViewById(R.id.re_pwd_edit);
        mEmailEditText = findViewById(R.id.email_edit);

        mEditTextList.add(mIdEditText);
        mEditTextList.add(mPwdEditText);
        mEditTextList.add(mRePwdEditText);
        mEditTextList.add(mEmailEditText);

        mGenderRadioGroup = findViewById(R.id.gender_radio_group);
        mGenderRadioGroup.setOnCheckedChangeListener(this);

        findViewById(R.id.reset_button).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // if (checkedId == R.id.male_radio_button) {
            /**
             * // 남자
             * checkedId를 찾아서 거기있는 글자를 mGender에 세팅해가지고 넘긴다.
             *
             * mGender = ((RadioButton) findViewById(R.id.male_radio_button)).getText().toString();
             *
             * 위 코드에서 R.id.male_redio_button은 checkedId 이다.
             *
             * 즉, mGender = ((RadioButton) findViewById(checkedId)).getText().toString();
             * */
        // } else {
            /**
             * // 여자
             * mGender = ((RadioButton) findViewById(R.id.female_radio_button)).getText().toString();
             *
             * 이 코드도 mGender = ((RadioButton) findViewById(checkedId)).getText().toString();
             * 위와 같이 바뀌면
             *
             * IF문을 지울 수 있다.
             * */
        // }
        mGender = ((RadioButton) findViewById(checkedId)).getText().toString();
        /** 결국 위 코드로 대체 할 수 있다. */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                reset();
                break;
            case R.id.sign_up_button:
                signUp();
                break;
        }
    }

    /** 초기화 */
    private void reset() {
        /** for문을 사용하여 editText를 비운다 */
        for (EditText editText : mEditTextList) {
            editText.setText("");
        }

        /** RadioGroup을 비운다 */
        mGenderRadioGroup.clearCheck();
    }

    /** 가입 */
    private void signUp() {
        if (!isValid()) {
            Toast.makeText(this, "모두 입력 해 주셔야 합니다.", Toast.LENGTH_SHORT).show();
        } else if (!isEqualsPassword()) {
            Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
        } else {
            /** 가입 */
            Intent intent = new Intent(this, MemberActivity.class);
            intent.putExtra("id", mIdEditText.getText().toString());
            intent.putExtra("pwd", mPwdEditText.getText().toString());
            intent.putExtra("email", mEmailEditText.getText().toString());
            intent.putExtra("gender", mGender);

            startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
        }
    }

    private boolean isValid() {
        for (EditText editText : mEditTextList) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                /** editText가 비었으면 return false */
                return false;
            }
        }

        if (mGenderRadioGroup.getCheckedRadioButtonId() == -1) {
            /** getCheckedRadioButtonId은 RadioButton이 선택된것이 없으면 -1을 반환한다. */
            return false;
        }

        /** 다 통과했으면 */
        return true;
    }

    private boolean isEqualsPassword() {
        return mPwdEditText.getText().toString().equals(mRePwdEditText.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == RESULT_OK) {
            Toast.makeText(this, "확인 버튼을 누르셨습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
