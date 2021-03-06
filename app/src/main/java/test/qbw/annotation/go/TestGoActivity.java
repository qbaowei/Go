package test.qbw.annotation.go;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

import com.qbw.annotation.Go;
import com.qbw.annotation.go.BundleValue;
import com.qbw.log.XLog;

import java.io.Serializable;
import java.util.Arrays;

import test.qbw.annotation.go.databinding.ActivityTestBinding;

/**
 * @author QBW
 * @createtime 2016/09/01 10:32
 * @company 9zhitx.com
 * @description
 */


public class TestGoActivity extends Activity {

    /**
     * BundleValue限制条件如下
     * 1.修饰符不能是‘Private’
     * 2.如果变量是’mX‘类型，生成函数的时候会去掉’m‘并将第一个字符改为小写，比如‘mByte’->‘byte’,然而byte是关键字不能使用，所以你写变量的时候要注意一下
     * 3.没有列出来的类型不支持
     * 4.有时候需要build一下才会生成对应文件
     * 5.会自动生成与Activity名字对应的文件名
     * 6.不要在类名相同的类中使用，这样Go.java里面会生成函数名一样的函数
     */


    @BundleValue
    byte mTb;
    @BundleValue
    Byte tb1;
    @BundleValue
    char mTc;
    @BundleValue
    Character mTc1;
    @BundleValue
    short mTs;
    @BundleValue
    Short mTs1;
    @BundleValue
    int mTi;
    @BundleValue
    Integer mTi1;
    @BundleValue
    long mTl;
    @BundleValue
    Long tl1;
    @BundleValue
    float mTf;
    @BundleValue
    Float mTf1;
    @BundleValue
    double mTd;
    @BundleValue
    Double mTd1;
    @BundleValue
    String mTstr;
    @BundleValue
    boolean mTbn;
    @BundleValue
    Boolean mTbn1;

    @BundleValue
    String[] mTsarr;
    @BundleValue
    int[] mTiarr;
    @BundleValue
    long[] mTlarr;
    @BundleValue
    float[] mTfarr;
    @BundleValue
    double[] mTdarr;


    @BundleValue
    Serializable mTsl;
    @BundleValue
    Parcelable mTpl;
    @BundleValue
    Parcelable[] mTplarr;

    private ObservableField<String> mInfo = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            Slide slide = new Slide();
            slide.setDuration(5000);
            //getWindow().setSharedElementEnterTransition(slide);
            Fade fade = new Fade();
            fade.setDuration(5000);
        }
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setInfo(mInfo);
        Go.TestGoActivity().unpack(this, getIntent().getExtras());
        mInfo.set(strInfo());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.txtInfo.setTransitionName("test");
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        XLog.d("in");
        Go.TestGoActivity().unpack(this, savedInstanceState);
        XLog.d(strInfo());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        XLog.d("in");
        Go.TestGoActivity().pack(this, outState);
    }

    private String strInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("mTb=%d, tb1=%d\n", mTb, tb1));
        stringBuilder.append(String.format("mTc=%c, mTc1=%c\n", mTc, mTc1));
        stringBuilder.append(String.format("mTs=%d, mTs1=%d\n", mTs, mTs1));
        stringBuilder.append(String.format("mTl=%d, tl1=%d\n", mTl, tl1));
        stringBuilder.append(String.format("mTi=%d, mTi1=%d\n", mTi, mTi1));
        stringBuilder.append(String.format("mTf=%f, mTf1=%f\n", mTf, mTf1));
        stringBuilder.append(String.format("mTd=%f, mTd1=%f\n", mTd, mTd1));
        stringBuilder.append(String.format("mTstr=%s\n", mTstr));
        stringBuilder.append(String.format("mTbn=%b, mTbn1=%b\n", mTbn, mTbn1));
        stringBuilder.append(String.format("mTsarr=%s\n", Arrays.toString(mTsarr)));
        stringBuilder.append(String.format("mTiarr=%s\n", Arrays.toString(mTiarr)));
        stringBuilder.append(String.format("mTlarr=%s\n", Arrays.toString(mTlarr)));
        stringBuilder.append(String.format("mTfarr=%s\n", Arrays.toString(mTfarr)));
        stringBuilder.append(String.format("mTdarr=%s\n", Arrays.toString(mTdarr)));
        stringBuilder.append(String.format("mTsl=%s\n", null == mTsl ? "null" : mTsl.toString()));
        stringBuilder.append(String.format("mTpl=%s\n", null == mTpl ? "null" : mTpl.toString()));
        StringBuilder sb = new StringBuilder();
        if (null != mTplarr) {
            for (Parcelable parcelable : mTplarr) {
                sb.append(String.format("{%s}", parcelable.toString()));
            }
        } else {
            sb.append("null");
        }
        stringBuilder.append(String.format("mTplarr=%s", sb.toString()));
        return stringBuilder.toString();
    }

    @Override
    public void onBackPressed() {
        setResult(100);
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
