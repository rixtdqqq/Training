package com.zhuyx.training.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;

/**
 * 直播：摄像头和麦克风采集到的图像和声音推流到流媒体服务器，观众访问服务器观看直播。
 * 推流：音频采样数据和视频像素数据（编码：压缩），观众看的时候是解码（解压缩）
 */
public class Blank6Fragment extends TrainingBaseFragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;

    private VideoView training_video_view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_blank6;
    }

    @Override
    public void initView(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        training_video_view = (VideoView) view. findViewById(R.id.training_video_view);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData() {
        setRadioButton(new String[]{"1月/次", "2月/次", "3月/次", "4月/次"});
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        Toast.makeText(getActivity(), rb.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private void setRadioButton(String[] select) {
        for (int i = 0, length = select.length; i < length; i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(select[i]);
            radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setPadding(0,15,0,15);
            radioButton.setTextSize(14);
            radioButton.setTextColor(mResources.getColor(R.color.color_4d4c59));
            mRadioGroup.addView(radioButton);
            if (i < length - 1) {
                View view = new View(getActivity());
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                view.setLayoutParams(params);
                mRadioGroup.addView(view);
            }
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    buttonView.setTextColor(isChecked ? mResources.getColor(R.color.colorPrimary) : mResources.getColor(R.color.color_4d4c59));
                }
            });
        }
    }
}
