package com.flightmanager.abslistview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flightmanager.helper.ViewHelper;

public class EasyLVHolder implements ViewHelper.AbsListView<EasyLVHolder> {

    /**
     * findViewById后保存view集合
     */
    private SparseArray<View>mViews=new SparseArray<>();
    private SparseArray<View>mConvertViews=new SparseArray<>();

    private View mConvertView;
    protected int mPosition;
    protected int mLayoutId;
    protected Context mContext;

    public EasyLVHolder(Context mContext, int mPosition, ViewGroup parent, int mLayoutId) {
        this.mConvertView=mConvertViews.get(mLayoutId);
        this.mPosition = mPosition;
        this.mLayoutId = mLayoutId;
        this.mContext = mContext;
        if(mConvertView==null){
            mConvertView= LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
            mConvertViews.put(mLayoutId,mConvertView);
            mConvertView.setTag(this);
        }
    }

    protected EasyLVHolder(){

    }

    public <BVH extends EasyLVHolder>BVH get(Context context,int position,View convertView,ViewGroup parent,int layoutId){
        if(convertView==null){
            return (BVH)new EasyLVHolder(context,position,parent,layoutId);
        }else{
            EasyLVHolder holder=(EasyLVHolder)convertView.getTag();
            if(holder.mLayoutId!=layoutId){
                return (BVH) new EasyLVHolder(context,position,parent,layoutId);
            }
            holder.setPosition(position);
            return (BVH)holder;
        }
    }

    public View getConvertView(){
        return mConvertViews.valueAt(0);
    }

    public View getConvertView(int layoutId){
        return mConvertViews.get(layoutId);
    }

    public <V extends View> V getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (V)view;
    }

    public void setPosition(int mPosition){
        this.mPosition=mPosition;
    }

    public int getLayoutId(){
        return mLayoutId;
    }

    @Override
    public EasyLVHolder setText(int viewId, String value) {
        TextView view=getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public EasyLVHolder setTextColor(int viewId, int color) {
        TextView view=getView(viewId);
        view.setTextColor(color);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public EasyLVHolder setTextColorRes(int viewId, int colorRes) {
        TextView view=getView(viewId);
        view.setTextColor(mContext.getResources().getColor(colorRes,null));
        return this;
    }

    @Override
    public EasyLVHolder setImageResource(int viewId, int imgResId) {
        ImageView view=getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public EasyLVHolder setBackgroundColor(int viewId, int color) {
        View view=getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    @Override
    public EasyLVHolder setBackgroundColorRes(int viewId, int colorRes) {
        return null;
    }

    @Override
    public EasyLVHolder setImageDrawable(int viewId, Drawable drawable) {
        return null;
    }

    @Override
    public EasyLVHolder setImageDrawableRes(int viewId, int drawableRes) {
        return null;
    }

    @Override
    public EasyLVHolder setImageUrl(int viewId, String imgUrl) {
        return null;
    }

    @Override
    public EasyLVHolder setImageBitmap(int viewId, Bitmap imgBitmap) {
        return null;
    }

    @Override
    public EasyLVHolder setVisible(int viewId, boolean visible) {
        return null;
    }

    @Override
    public EasyLVHolder setVisible(int viewId, int visible) {
        return null;
    }

    @Override
    public EasyLVHolder setTag(int viewId, Object tag) {
        return null;
    }

    @Override
    public EasyLVHolder setTag(int viewId, int key, Object tag) {
        return null;
    }

    @Override
    public EasyLVHolder setChecked(int viewId, boolean checked) {
        return null;
    }

    @Override
    public EasyLVHolder setAlpha(int viewId, float value) {
        return null;
    }

    @Override
    public EasyLVHolder setTypeface(int viewId, Typeface typeface) {
        return null;
    }

    @Override
    public EasyLVHolder setTypeface(Typeface typeface, int... viewIds) {
        return null;
    }

    @Override
    public EasyLVHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        return null;
    }
}
