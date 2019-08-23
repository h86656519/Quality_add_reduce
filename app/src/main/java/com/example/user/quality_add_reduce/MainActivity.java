package com.example.user.quality_add_reduce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btAdd, btReduce;
    private EditText edtNumber;
    int num = 1; //數量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd = (Button) findViewById(R.id.cart_pro_reduce);
        btReduce = (Button) findViewById(R.id.cart_pro_add);
        edtNumber = (EditText) findViewById(R.id.cart_pro_count);
        btAdd.setTag("+");
        btReduce.setTag("-");
        //設置輸入類型為數字
        edtNumber.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        edtNumber.setText(String.valueOf(num));
        SetViewListener();
    }

    /**
     * 設置文本變化相關監聽事件
     */
    private void SetViewListener() {
        btAdd.setOnClickListener(new OnButtonClickListener());
        btReduce.setOnClickListener(new OnButtonClickListener());
        edtNumber.addTextChangedListener(new OnTextChangeListener());
    }

    /**
     * 加減按鈕事件監聽器
     */
    class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String numString = edtNumber.getText().toString();
            if (numString == null || numString.equals("")) {
                num = 0;
                edtNumber.setText("0");
            } else {
                if (v.getTag().equals("-")) {
                    if (++num < 0) //先加，再判斷
                    {
                        num--;
                        Toast.makeText(MainActivity.this, "請輸入一個大於0的數字",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        edtNumber.setText(String.valueOf(num));
                    }
                } else if (v.getTag().equals("+")) {
                    if (--num < 0) //先減，再判斷
                    {
                        num++;
                        Toast.makeText(MainActivity.this, "請輸入一個大於0的數字",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        edtNumber.setText(String.valueOf(num));
                    }
                }
            }
        }
    }

    /**
     * EditText輸入變化事件監聽器
     */
    class OnTextChangeListener implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            String numString = s.toString();
            if (numString == null || numString.equals("")) {
                num = 0;
            } else {
                int numInt = Integer.parseInt(numString);
                if (numInt < 0) {
                    Toast.makeText(MainActivity.this, "請輸入一個大於0的數字",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //設置EditText光標位置 為文本末端
                    edtNumber.setSelection(edtNumber.getText().toString().length());
                    num = numInt;
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }
    }
}
