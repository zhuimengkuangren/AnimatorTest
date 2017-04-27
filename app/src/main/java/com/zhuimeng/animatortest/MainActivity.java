package com.zhuimeng.animatortest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView namei;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namei = (ImageView) findViewById(R.id.iv_namei);
        msg = (TextView) findViewById(R.id.tv_msg);
    }

    /**
     * 传统动画Animation
     *
     * @param view
     */
    public void Move(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 400, 0, 0);//X平移400
        animation.setDuration(1000);
        animation.setFillAfter(true);
        ImageView xiaoMai = (ImageView) findViewById(R.id.iv_xiao_mai);
        xiaoMai.startAnimation(animation);
        msg.setText("传统Animation多个动画只能通过AnimationSet设置");
    }

    /**
     * 属性动画演示Animator
     *
     * @param view
     */
    public void Action1(View view) {
        //方法一
        ObjectAnimator.ofFloat(namei, "translationX", 0f, 400f).setDuration(1000).start();
        ObjectAnimator.ofFloat(namei, "translationY", 0f, 400f).setDuration(1000).start();
        ObjectAnimator.ofFloat(namei, "rotation", 0, 360f).setDuration(1000).start();//旋转360度
        msg.setText("effect of ACTION 1（异步处理）");
    }

    public void Action2(View view) {
        //方法二
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0f, 400f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 0f, 400f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0, 360f);
        ObjectAnimator.ofPropertyValuesHolder(namei, p1, p2, p3).setDuration(1000).start();
        msg.setText("effect of ACTION 2：ObjectAnimator." +
                "ofPropertyValuesHolder(namei, p1, p2, p3).setDuration(1000).start();");
    }

    public void Action3(View view) {
        //方法三（playTogether）
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(namei, "translationX", 0f, 400f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(namei, "translationY", 0f, 400f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(namei, "rotation", 0, 360f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2, animator3);
        set.setDuration(1000);
        set.start();
        msg.setText("effect of ACTION 3：set.playTogether(animator1, animator2, animator3)");
    }

    public void Action4(View view) {
        //方法四（playSequentially）按顺序
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(namei, "translationX", 0f, 400f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(namei, "translationY", 0f, 400f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(namei, "rotation", 0, 360f);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator1, animator2, animator3);
        set.setDuration(1000);
        set.start();
        msg.setText("effect of ACTION 4：set.playSequentially(animator1, animator2, animator3) 按顺序");
    }
    public void Action5(View view) {
        //方法五（先X平移和旋转一起，再Y轴平移）
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(namei, "translationX", 0f, 400f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(namei, "translationY", 0f, 400f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(namei, "rotation", 0, 360f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator3);//X平移和旋转一起
        set.play(animator2).after(animator1);//之后Y轴平移
        set.setDuration(1000);
        set.start();
        msg.setText("effect of ACTION 5：set.play(animator1).with(animator3);//X平移和旋转一起\n" +
                "        set.play(animator2).after(animator1);//之后Y轴平移");
    }
}
