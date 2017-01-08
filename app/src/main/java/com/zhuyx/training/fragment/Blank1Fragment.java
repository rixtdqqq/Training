package com.zhuyx.training.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuyx.training.R;
import com.zhuyx.training.activity.TrainingEventActivity;
import com.zhuyx.training.databinding.TrainingFBlank1Binding;
import com.zhuyx.training.entity.Employee;
import com.zhuyx.training.entity.TrainingEventEntity;
import com.zhuyx.training.util.TrainingConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Blank1Fragment extends Fragment {
    TrainingFBlank1Binding binding;

    public Blank1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        binding = TrainingFBlank1Binding.bind(inflater.inflate(R.layout.training_f_blank1, null));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Employee employee = new Employee("362429198906060619", "zhu", "30", "165", "119", "o", "q", "男", "江西吉安", "坂田山海", "80后IT男");
        if (null != binding) {
            binding.setEmployee(employee);
            binding.setPresenter(new Presenter());
        }
    }

    public class Presenter {
        //方法引用，严格引用onClickListener接口中的方法
        public void onClick(View view) {
            Toast.makeText(getActivity(), "点到了", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), TrainingEventActivity.class);
            intent.putExtra(TrainingConstants.FRAGMENT_FLAG, "Blank7Fragment");
            startActivity(intent);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Employee employee = new Employee(charSequence.toString());
            binding.setEmployee(employee);
        }

        //自定义的接口方法没那么严格
        public void setOnClick(Employee employee) {
            Toast.makeText(getActivity(), employee.getGender(), Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TrainingEventEntity entity){
        Toast.makeText(getActivity(), entity.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
