package com.lq.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;




/** TODO
 * 在描述中写入需要自行切换 androidx 与 com.android.support
 * 写明 imageView 与 textView 分别怎么处理
 */

/**
 * @author LQ
 * 2020/1/21
 *
 * BaseAdapter 是一个简单的RecycleView.Adapter 对于使用recycleView简单显示一些数据  可以考虑使用BaseAdapter
 * 此文件中使用com.android.support下的recycleView 若有使用 androidx的recycleView 自行修改源文件  import
 *
 *
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public static final int BIND_BY_RESID = 0;
    public static final int BIND_BY_FILE_PATH = 1;
    public static final int BIND_BY_URL = 2;

    private Context context;

    private int imageBindStyle = -1;
    private int itemLayoutId;
    private Map<View.OnClickListener,Integer[]> clickMapVids;

    //数据显示
    /**
     * form显示的数据  to显示的位置
     */
    private String[] form;
    private int[] to;
    private List<? extends Map<String,?>> data;


    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] objs = (Object[]) msg.obj;
            ((ImageView) objs[1]).setImageBitmap((Bitmap) objs[0]);
        }
    };

    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, itemLayoutId, null));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {
        try {
            bindClickEvent(holder,position);
            bindData(holder,position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * 设置绑定事件
     * 注：此时并未真正的绑定  只做了记录工作
     * @param onClickListener 所要绑定的事件
     * @param viewResIds 绑定这个事件的 view ids
     */
    public void setOnClickEvent(View.OnClickListener onClickListener, Integer... viewResIds){
        if (clickMapVids==null)  clickMapVids = new HashMap<>();
        if (!clickMapVids.containsKey(onClickListener)) {
            clickMapVids.put(onClickListener,viewResIds);
        }else {
            Integer[] integers = clickMapVids.get(onClickListener);
            List<Integer> list = Arrays.asList(integers);
            list.addAll(Arrays.asList(viewResIds));
            clickMapVids.put(onClickListener, (Integer[]) list.toArray());
        }
    }

    /**
     * 为view holder 绑定数据
     * 根据view 的类型 提供不同的绑定方式
     * textView 仅限绑定字符串类型
     * imageView [1] res id [2] image file path [3] url str
     * @param holder
     * @param position
     */
    private void bindData(ViewHolder holder,int position) throws Exception {
        //绑定数据
        final Map<String, ?> map = data.get(position);

        for (int i = 0; i < to.length; i++) {
            View view = holder.getView(to[i]);
            if (view instanceof ImageView) {
                switch (imageBindStyle){
                    case -1: throw new Exception("image binding method is not specified");
                    case BIND_BY_RESID: {
                        ((ImageView) view).setImageResource(((Integer) map.get(form[i])));
                    }break;
                    case BIND_BY_FILE_PATH: {
                        ((ImageView) view).setImageURI(Uri.fromFile(new File(((String) map.get(form[i])))));
                    }break;
                    case BIND_BY_URL:{
                        final String url = (String) map.get(form[i]);
                        final View _view = view;
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                String _url = url;
                                try {
                                    URL url = new URL(_url);
                                    HttpURLConnection conn = null;
                                    conn = (HttpURLConnection) url.openConnection();
                                    conn.setConnectTimeout(5000);
                                    conn.setRequestMethod("GET");
                                    if (conn.getResponseCode()==200) {
                                        Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                                        Message message = uiHandler.obtainMessage();
                                        message.obj = new Object[]{bitmap,_view};
                                        uiHandler.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }break;
                }
            }else if (view instanceof TextView){
                String text = map.get(form[i]).toString();
                ((TextView) view).setText(text);
            }
        }
    }


    /**
     * 循环绑定点击事件
     * @param holder
     */
    private void bindClickEvent(ViewHolder holder,int position){
        Set<View.OnClickListener> listeners = clickMapVids.keySet();
        for (View.OnClickListener listener : listeners) {
            Integer[] ids = clickMapVids.get(listener);
            for (Integer id : ids) {
                holder.getView(id).setOnClickListener(listener);
            }
        }
    }


    /**
     *
     * @param context  传入一个上下文对象
     * @param itemLayoutId 每个item的布局文件
     * @param data 需要显示的数据 （可以使用DataConverter 将数据对象list  转化为list<Map>）
     * @param form 与 to 一一对应 数据对象的key
     * @param to 与 form 一一对应 需要显示到的view的id
     */
    public BaseAdapter(Context context, int itemLayoutId, List<? extends Map<String, ?>> data, String[] form, int[] to) {
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.form = form;
        this.to = to;
        this.data = data;
    }


    /**
     * @param imageBindStyle image的绑定方式
     */
    public BaseAdapter(Context context, int itemLayoutId, List<? extends Map<String, ?>> data, String[] form, int[] to,int imageBindStyle) {
        this(context,itemLayoutId,data,form,to);
        this.imageBindStyle = imageBindStyle;
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public View getView(int viewId){
            return itemView.findViewById(viewId);
        }

    }



}
