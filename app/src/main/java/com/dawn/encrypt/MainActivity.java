package com.dawn.encrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_input;
    private TextView tv_encrypy_keystore;
    private TextView tv_decrypy_keystore;
    private TextView tv_encrypy_result;
    private TextView tv_decrypy_result;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv_jni_demo_str = (TextView) findViewById(R.id.tv_jni_demo_str);
        tv_jni_demo_str.setText(stringFromJNI());

        initKeyStoreView();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void initKeyStoreView(){
        et_input = findViewById(R.id.et_input);
        tv_encrypy_keystore = findViewById(R.id.tv_encrypy_keystore);
        tv_decrypy_keystore = findViewById(R.id.tv_decrypy_keystore);
        tv_encrypy_result = findViewById(R.id.tv_encrypy_result);
        tv_decrypy_result = findViewById(R.id.tv_decrypy_result);


        tv_encrypy_keystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = et_input.getText().toString().trim();
                if(!TextUtils.isEmpty(temp)){
                    String result = KeyStoreUtils.getInstance().encrypt(temp);
                    tv_encrypy_result.setText(result);
                }
            }
        });
        tv_decrypy_keystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = tv_encrypy_result.getText().toString().trim();
                if(!TextUtils.isEmpty(temp)) {
                    String result = KeyStoreUtils.getInstance().decrypt(temp);
                    tv_decrypy_result.setText(result);
                }
            }
        });
    }
}
