package com.baobao.butterknife;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time:2019/6/8
 * <p>
 * Author:baobao
 * <p>
 * Description:
 */
public class MyFragment extends Fragment {

    //绑定控件
    @BindView(R.id.tv_fr)
    TextView mTv;
    @BindView(R.id.btn_fr)
    Button mBtn;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_main, null);
        //绑定fragment的view，并得到解绑对象
        unbinder = ButterKnife.bind(this, view);
        mTv.setText("baobao fragment");
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //销毁视图时解绑
        unbinder.unbind();
    }

    //绑定点击事件
    @OnClick(R.id.btn_fr)
    public void onClick() {
        Toast.makeText(getActivity(), "button fragment", Toast.LENGTH_SHORT).show();
    }
}
