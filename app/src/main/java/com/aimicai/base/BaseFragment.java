package com.aimicai.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.aimicai.http.RequestCallback;
import io.reactivex.Observable;

/**
 * ProjectName: aimicai
 * PackageName com.winfo.aimicai.base
 * Author: wenjie
 * Date: 2019-05-05 16:30
 * Description:
 */
public class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;
    private OnFragmentInteractionListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dismissLoadDialog();
        mListener = null;
    }

    protected boolean isFinish() {
        return mBaseActivity == null || mBaseActivity.isFinishing();
    }

    protected BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public void showLoadDialog() {
        if (mBaseActivity != null) {
            mBaseActivity.showLoadDialog();
        }
    }

    public void dismissLoadDialog() {
        if (mBaseActivity != null) {
            mBaseActivity.dismissLoadDialog();
        }
    }

    /**
     * 订阅者和被订阅者 建立关系
     * rxManager 再actiity销毁的时候取消请求和订阅
     *
     * @param observable      被订阅者
     * @param requestCallback 回调
     */
    protected <T> void subscribe(Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (mListener != null) {
            mListener.sendRequest(observable , requestCallback);
        }
    }

    /**
     * 接口回调
     */
    public interface OnFragmentInteractionListener {
        <T> void sendRequest(Observable<T> observable, final RequestCallback<T> requestCallback);
    }

}
