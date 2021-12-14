package com.example.movieapp.utilities;

import android.media.Image;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("android:imageURL")

    public static void setImageURL(ImageView ImageView, String URL){
        try{

            ImageView.setAlpha(0f);
            Picasso.get().load(URL).noFade().into(ImageView, new Callback() {
                @Override
                public void onSuccess() {
                    ImageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }catch(Exception ignored) {


        }

    }
}
