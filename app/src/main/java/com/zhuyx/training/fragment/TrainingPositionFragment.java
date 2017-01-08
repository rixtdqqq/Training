package com.zhuyx.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.util.TrainingUtils;

/**
 * 地图定位
 * http://blog.csdn.net/lipeng32768/article/details/51685081百度地图用法
 */
public class TrainingPositionFragment extends TrainingBaseFragment {
    private Toolbar toolbar;
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationMode mCurrentMode;
    private BitmapDescriptor mCurrentMarker;
    private LocationClient mLocClient;
    private boolean isFirstLoc = true; // 是否首次定位


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_position;
    }

    @Override
    public void initView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TrainingUtils.setTitlePaddingView(view.findViewById(R.id.padding_view));
        mapView = (MapView) view.findViewById(R.id.bmapView);
    }

    @Override
    public void initData() {
        mCurrentMode = LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.training_position);
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); //设置为普通模式的地图
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        mLocClient = new LocationClient(getActivity());  //定位用到的一个类
        mLocClient.registerLocationListener(new MyLocationListenner()); //注册监听

        ///LocationClientOption类用来设置定位SDK的定位方式，
        LocationClientOption option = new LocationClientOption(); //以下是给定位设置参数
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener((view) -> {
            getActivity().finish();
        });
        toolbar.inflateMenu(R.menu.training_menu_position_type);
        toolbar.setOnMenuItemClickListener((menuItem) -> {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.type_none) { //空白地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
            } else if (itemId == R.id.type_normal) {//普通地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            } else if (itemId == R.id.type_satellite) {//卫星地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            } else if (itemId == R.id.my_position) {//我的位置 改变定位图标的模式
                if (mCurrentMode == LocationMode.NORMAL) {
                    menuItem.setTitle("我的位置-跟随模式");
                    mCurrentMode = LocationMode.FOLLOWING;
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker));
                } else if (mCurrentMode == LocationMode.FOLLOWING) { //跟随模式
                    menuItem.setTitle("我的位置-罗盘模式");
                    mCurrentMode = LocationMode.COMPASS;
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker));
                } else if (mCurrentMode == LocationMode.COMPASS) { //罗盘模式
                    menuItem.setTitle("我的位置-普通模式");
                    mCurrentMode = LocationMode.NORMAL;
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker));

                }
            }
            return true;
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();// 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
