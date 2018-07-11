package com.immo.libcomm.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.immo.libcomm.R;

/**
 * @author Administrator
 * @content 自定义加减控件
 * @date 2017/11/16
 */

public class AmountView01 extends LinearLayout implements View.OnClickListener, TextWatcher {
    //购买数量
    private int amount = 1;
    //商品库存
    private int goods_storage = 1;
    private EditText etAmount;
    private TextView btnDecrease;
    private TextView btnIncrease;

    public AmountView01(Context context) {
        this(context, null);
    }

    public AmountView01(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount_01, this);
        etAmount =findViewById(R.id.etAmount);
        btnDecrease =findViewById(R.id.btnDecrease);
        btnIncrease =findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }
    public  void setDefaultNum(int num){
        etAmount.setText(""+num);
    }
    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }
    public int getNum(){
        return Integer.parseInt(etAmount.getText().toString());
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }
        etAmount.clearFocus();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()){
            return;
        }
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }
    }
}