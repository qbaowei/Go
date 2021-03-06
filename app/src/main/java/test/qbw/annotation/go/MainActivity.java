package test.qbw.annotation.go;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.qbw.annotation.Go;
import com.qbw.annotation.go.BundleValue;
import com.qbw.log.XLog;

import java.util.ArrayList;

import test.qbw.annotation.go.databinding.ActivityMainBinding;


public class MainActivity extends FragmentActivity {

    @BundleValue
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(false);
            getWindow().setAllowReturnTransitionOverlap(false);
            /*Fade fade = new Fade();
            fade.setDuration(5000);
            getWindow().setSharedElementExitTransition(fade);*/
        }
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setAct(this);
        XLog.setEnabled(true);

        XLog.d(String[].class.getName());
        XLog.d(String[].class.getCanonicalName());
        XLog.d(TestFragment.class.getName());
        XLog.d(TestFragment.class.getCanonicalName());
        XLog.d(TestFragment.Test.class.getName());
        XLog.d(TestFragment.Test.class.getCanonicalName());
        ArrayList<Parcelable> parcelables = new ArrayList<>();
        XLog.d(parcelables.getClass().getCanonicalName());
        XLog.d(parcelables.getClass().getTypeParameters().toString());
        Bundle bundle = new Bundle();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        XLog.d("in");
        id = System.currentTimeMillis();
        XLog.d("id = %d", id);
        Go.MainActivity().pack(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        XLog.d("in");
        Go.MainActivity().unpack(this, savedInstanceState);
        XLog.d("restore id = %d", id);
    }

    public static class TestFragment extends Fragment {

        @BundleValue
        Long id;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getArguments();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        public void onViewStateRestored(Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
        }

        public static class Test {}
    }

    public View.OnClickListener test1GoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TestGoActivityGO goActivityGO = Go.TestGoActivity()
                                              .from(MainActivity.this)
                                              .tb((byte) 1)
                                              .tb1((byte) 2)
                                              .tc('q')
                                              .tc1('b')
                                              .ts((short) 3)
                                              .ts1((short) 4)
                                              .ti(5)
                                              .ti1(6)
                                              .tl(7)
                                              .tl1(8l)
                                              .tf(0.1f)
                                              .tf1(0.2f)
                                              .td(0.3)
                                              .td1(0.4)
                                              .tstr("haha")
                                              .tbn(true)
                                              .tbn1(false)
                                              .tsarr(new String[]{
                                                      "hello,", "a", "n", "d", "r", "o", "i", "d"
                                              })
                                              .tiarr(new int[]{1, 2, 3})
                                              .tlarr(new long[]{7, 8, 9})
                                              .tfarr(new float[]{0.1f, 0.001f, 0.036f})
                                              .tdarr(new double[]{1.6, 9.6, 0.39})
                                              .tsl(new ModelS())
                                              .tpl(new ModelP())
                                              .tplarr(new Parcelable[]{
                                                      new ModelP(), new ModelP(), new ModelP()
                                              });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                v.setTransitionName("test");
                goActivityGO.go(ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                                                             v,
                                                                             "test").toBundle());
            } else {
                goActivityGO.go(9);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XLog.d("here-----------requestCode=%d, resultCode=%d", requestCode, resultCode);
    }
}
