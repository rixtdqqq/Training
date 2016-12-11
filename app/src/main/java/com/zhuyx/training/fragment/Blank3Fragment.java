package com.zhuyx.training.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuyx.training.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Observable：发射源，英文释义“可观察的”，在观察者模式中称为“被观察者”或“可观察对象”；
 * Observer：接收源，英文释义“观察者”，没错！就是观察者模式中的“观察者”，可接收Observable、Subject发射的数据；
 * Subject：Subject是一个比较特殊的对象，既可充当发射源，也可充当接收源，为避免初学者被混淆，本章将不对Subject做过多的解释和使用，重点放在Observable和Observer上，先把最基本方法的使用学会，后面再学其他的都不是什么问题；
 * Subscriber：“订阅者”，也是接收源，那它跟Observer有什么区别呢？Subscriber实现了Observer接口，比Observer多了一个最重要的方法unsubscribe( )，用来取消订阅，当你不再想接收数据了，可以调用unsubscribe( )方法停止接收，Observer 在 subscribe() 过程中,最终也会被转换成 Subscriber 对象，一般情况下，建议使用Subscriber作为接收源；
 * Subscription ：Observable调用subscribe( )方法返回的对象，同样有unsubscribe( )方法，可以用来取消订阅事件；
 * Action0：RxJava中的一个接口，它只有一个无参call（）方法，且无返回值，同样还有Action1，Action2...Action9等，Action1封装了含有 1 个参的call（）方法，即call（T t），Action2封装了含有 2 个参数的call方法，即call（T1 t1，T2 t2），以此类推；
 * Func0：与Action0非常相似，也有call（）方法，但是它是有返回值的，同样也有Func0、Func1...Func9;
 * 文／Weavey（简书作者）
 * 原文链接：http://www.jianshu.com/p/5e93c9101dc5
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 */

public class Blank3Fragment extends Fragment {

    private TextView mTextView;

    public Blank3Fragment() {
        // Required empty public constructor
    }

    public static Blank3Fragment newInstance() {

        Bundle args = new Bundle();

        Blank3Fragment fragment = new Blank3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.training_f_blank3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mTextView = (TextView) view.findViewById(R.id.tv_rx_java);
    }

    private void initData() {
        final StringBuilder stringBuilder = new StringBuilder();
        //数据发射源 1.使用create( ),最基本的创建方式
        Observable<String> normalObservable  = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello RxJava");
                subscriber.onNext("I'm coming.");
                subscriber.onCompleted();
            }
        });
        //数据发射源 2.使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据
        Observable justObservable = Observable.just("A", "B");
        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");
        //数据发射源 3.使用from( )，遍历集合，发送每个item
        Observable fromObservable = Observable.from(list);
        //数据发射源 4.使用defer( )，有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
//        Observable deferObservable = Observable.defer(new Func0<Observable>() {
//            //注意此处的call方法没有Subscriber参数
//            @Override
//            public Observable call() {
//                return Observable.just("deferObservable");
//            }
//        });
        //数据发射源 5.使用interval( ),创建一个按固定时间间隔发射整数序列的Observable，可用作定时器
        Observable intervalObservable = Observable.interval(1, TimeUnit.SECONDS);//每隔一秒发送一次
        //数据发射源 6.使用range( ),创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常
        Observable rangeObservable = Observable.range(10, 5);//将发送整数10，11，12，13，14
        //数据发射源 7.使用timer( ),创建一个Observable，它在一个给定的延迟后发射一个特殊的值，等同于Android中Handler的postDelay( )方法
        Observable timeObservable = Observable.timer(3,TimeUnit.SECONDS);
        //数据发射源 8.使用repeat( ),创建一个重复发射特定数据的Observable
        Observable repeatObservable = Observable.just("repeatObservable").repeat(3);//重复发射3次
        //数据接收源
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                stringBuilder.append("onCompleted").append("\n");
                mTextView.setText(stringBuilder.toString());
            }

            @Override
            public void onError(Throwable e) {
                stringBuilder.append(e.toString()).append("\n");
            }

            @Override
            public void onNext(String s) {
                stringBuilder.append(s).append("\n");
            }
        };
//        normalObservable.subscribe(subscriber);
        repeatObservable.subscribe(subscriber);
    }
}
