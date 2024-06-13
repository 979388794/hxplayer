package com.example.hxplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hxplay.R;
import com.example.hxplay.bean.VideoBean;
import com.example.hxplay.glide.GlideApp;

import java.util.List;

/**
 * @author: henry.xue
 * @date: 2024-05-10
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    private List<VideoBean.Movie> movieList;
    private Context context;
    private MovieAdapter.MyItemClickListener mItemClickListener;

    public MovieAdapter(Context context, List<VideoBean.Movie> movies) {
        this.movieList = movies;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建ViewHolder, 返回每一项的布局
        View view = layoutInflater.inflate(R.layout.view_item, parent, false);
        MovieAdapter.MyViewHolder myViewHolder = new MovieAdapter.MyViewHolder(view, mItemClickListener);
        return myViewHolder;
    }

    // 将数据与控件绑定
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        holder.service_name.setText(movieList.get(position).getTitle());
        String url = movieList.get(position).getCover();
        if(context!=null){
            GlideApp.with(context).load(url).into(holder.service_img);
        }
    }

    // 返回Item总条数
    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();

    }

    public void updateData(List<VideoBean.Movie> newMovieList) {
        movieList = newMovieList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView service_name;
        private ImageView service_img;
        private MovieAdapter.MyItemClickListener myListener;

        public MyViewHolder(@NonNull View view, MovieAdapter.MyItemClickListener myItemClickListener) {
            super(view);
            this.myListener = myItemClickListener;
            itemView.setOnClickListener(this);
            service_img = view.findViewById(R.id.view_image);
            service_name = view.findViewById(R.id.view_title);
        }

        @Override
        public void onClick(View view) {
            if (myListener != null) {
                myListener.onItemClick(view, getPosition());
            }
        }
    }

    //创建一个回调接口
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    //在activity中adapter中调用此方法，将点击事件监听传递过去，并赋值给全局监听
    public void setItemClickListener(MovieAdapter.MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }


}
