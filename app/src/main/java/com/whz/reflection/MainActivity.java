package com.whz.reflection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whz.reflection.module.Person;

import static com.whz.reflection.utils.Util.simpel_1;
import static com.whz.reflection.utils.Util.simpel_2;
import static com.whz.reflection.utils.Util.simpel_3;
import static com.whz.reflection.utils.Util.simpel_4;
import static com.whz.reflection.utils.Util.simpel_5;
import static com.whz.reflection.utils.Util.simpel_6;
import static com.whz.reflection.utils.Util.simpel_7;
import static com.whz.reflection.utils.Util.simpel_8;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_person:
                btnPerson();
                break;
            case R.id.btn_hashmap:
                btnHashMap();
                break;
            case R.id.btn_field:
                btnField();
                break;
            case R.id.btn_modifyField:
                btnModifyField();
                break;
            case R.id.btn_method:
                btnMethod();
                break;
            case R.id.btn_methods:
                btnMethods();
                break;
            case R.id.btn_array:
                btnArray();
                break;
            case R.id.btn_finger_count:
                btnFingerCount();
                break;
            default:
                break;
        }
    }


    /**
     * 示例8
     */
    private void btnFingerCount() {
        String result = simpel_8(this);
        mResult.setText(result);
    }

    /**
     * 示例7
     */
    private void btnArray() {
        String result = simpel_7();
        mResult.setText(result);
    }

    /**
     * 示例6
     */
    private void btnMethods() {
        String result = simpel_6();
        mResult.setText(result);
    }

    /**
     * 示例5
     */
    private void btnMethod() {
        simpel_5();
    }

    /**
     * 示例4
     */
    private void btnModifyField() {
        String result = simpel_4();
        mResult.setText(result);
    }

    /**
     * 示例3
     */
    private void btnField() {
        String result = simpel_3();
        mResult.setText(result);
    }

    /**
     * 示例2
     */
    private void btnHashMap() {
        String result = simpel_2();
        mResult.setText(result);
    }

    /**
     * 示例1
     */
    private void btnPerson() {
        Person person = simpel_1();
        mResult.setText(person.toString());
    }

    /**
     * 初始化View
     */
    private void initView() {
        findByID(R.id.btn_field);
        findByID(R.id.btn_array);
        findByID(R.id.btn_person);
        findByID(R.id.btn_method);
        findByID(R.id.btn_methods);
        findByID(R.id.btn_hashmap);
        findByID(R.id.btn_modifyField);
        findByID(R.id.btn_finger_count);

        mResult = findViewById(R.id.tv_result);
    }

    /**
     * 查找资源ID
     */
    private void findByID(int id) {
        findViewById(id).setOnClickListener(this);
    }
}
