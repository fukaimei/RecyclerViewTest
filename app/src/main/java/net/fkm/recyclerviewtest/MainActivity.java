package net.fkm.recyclerviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.fkm.recyclerviewtest.activity.GridLayoutRVActivity;
import net.fkm.recyclerviewtest.activity.NestRVActivity;
import net.fkm.recyclerviewtest.activity.RecyclerViewActivity;
import net.fkm.recyclerviewtest.activity.StaggeredGridRVActivity;
import net.fkm.recyclerviewtest.utils.CheckNetwork;
import net.fkm.recyclerviewtest.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_rv01, R.id.btn_rv02, R.id.btn_rv03, R.id.btn_rv04, R.id.btn_rv05})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rv01:
                // 判断网络是否可用
                if (!CheckNetwork.isNetworkConnected(this)) {
                    ToastUtil.showToastLong("当前网络不可用，请检查您的网络设置");
                    return;
                }
                Intent intent01 = new Intent(this, RecyclerViewActivity.class);
                intent01.putExtra("ViewType", "NoDividingLine");
                startActivity(intent01);
                break;
            case R.id.btn_rv02:
                if (!CheckNetwork.isNetworkConnected(this)) {
                    ToastUtil.showToastLong("当前网络不可用，请检查您的网络设置");
                    return;
                }
                Intent intent02 = new Intent(this, RecyclerViewActivity.class);
                intent02.putExtra("ViewType", "DividingLine");
                startActivity(intent02);
                break;
            case R.id.btn_rv03:
                if (!CheckNetwork.isNetworkConnected(this)) {
                    ToastUtil.showToastLong("当前网络不可用，请检查您的网络设置");
                    return;
                }
                startActivity(new Intent(this, GridLayoutRVActivity.class));
                break;
            case R.id.btn_rv04:
                if (!CheckNetwork.isNetworkConnected(this)) {
                    ToastUtil.showToastLong("当前网络不可用，请检查您的网络设置");
                    return;
                }
                startActivity(new Intent(this, StaggeredGridRVActivity.class));
                break;
            case R.id.btn_rv05:
                if (!CheckNetwork.isNetworkConnected(this)) {
                    ToastUtil.showToastLong("当前网络不可用，请检查您的网络设置");
                    return;
                }
                startActivity(new Intent(this, NestRVActivity.class));
                break;
            default:
                break;
        }
    }

}
