package cn.edu.jlu.a4g_profibus;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomAdapter extends PagerAdapter {
    private List<View> pageList;
    public CustomAdapter(List<View> pageList){
        this.pageList=pageList;
    }
    @Override
    public int getCount(){
        return pageList.size();
    }
    @Override
    public boolean isViewFromObject(View arg0,Object arg1){
        return arg0==arg1;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        //移除当前位置的view
        container.removeView(pageList.get(position));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(pageList.get(position));
        return pageList.get(position);
    }
}
