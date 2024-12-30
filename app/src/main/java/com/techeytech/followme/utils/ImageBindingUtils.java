package com.techeytech.followme.utils;

import android.graphics.drawable.Drawable;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageBindingUtils {

    @BindingAdapter(value = {"simpleResourse"})
    public static void setStepGroupIcon(final ImageView imageView, int simpleResourse) {

        if (simpleResourse != -1) {
            imageView.setImageResource(simpleResourse);

        }
    }

    @BindingAdapter(value = {"image_url","]"}, requireAll = false)
    public static void setImageUrl(final ImageView imageView, String imageUrl, Drawable placeholder) {
        Picasso.get().load(imageUrl).placeholder(placeholder).into(imageView);
    }

    @BindingAdapter(value = {"image_path"}, requireAll = false)
    public static void setBitmpaOnImage(final ImageView imageView, String imagePath) {
        Picasso.get().load(new File(imagePath))
                .transform(new CircleImageTransform())
                .centerCrop()
                .resize(100, 100)
                .into(imageView);
    }

    @BindingAdapter({"android:src_circle"})
    public static void setCircleImageViewResource(ImageView imageView, int resource) {
        Picasso.get().load(resource).transform(new CircleImageTransform()).into(imageView);
    }

    @BindingAdapter(value = {"circle_image"}, requireAll = false)
    public static void setCircleImage(final ImageView imageView, String imagePath) {
        Picasso.get().load(imagePath)
                .transform(new CircleImageTransform())
                .centerCrop()
                .resize(100, 100)
                .into(imageView);
    }

    @BindingAdapter(value = {"dummy_value"}, requireAll = false)
    public static void setCirclpleImage(final ImageView imageView,String file){

    }

    @BindingAdapter(value = {"round_pic","placeholder"}, requireAll = false)
    public static void round(final ImageView imageView, String imageUrl, Drawable placeholder) {
        Picasso.get()
                .load(imageUrl)
                .centerCrop()
                .resize(100, 100)
                .transform(new CircleImageTransform())
                .placeholder(placeholder)
                .into(imageView);
    }

    /*@BindingAdapter({"like"})
    public static void like(CheckBox checkBox, GetBuyerSellerDetailsBean.DataBeanX.DataBean bean) {
        if (bean != null) {
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(bean.getIs_like() == 1);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> bean.setIs_like(isChecked ? 1 : 0));
        } else checkBox.setChecked(false);
    }*/
}
