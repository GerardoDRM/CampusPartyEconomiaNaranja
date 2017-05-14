package com.craftcode.android.clicksocial.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by gerardo on 6/07/16.
 */
public class DynamicHeightTransformation implements Transformation {

    int mSize;
    boolean isHeightScale;

    public DynamicHeightTransformation(int size, boolean isHeightScale) {
        mSize = size;
        this.isHeightScale = isHeightScale;
    }

    @Override
    public Bitmap transform(Bitmap source) {

        float scale;
        int newSize;
        Bitmap scaledBitmap;
        if (isHeightScale) {
            scale = (float) mSize / source.getHeight();
            newSize = Math.round(source.getWidth() * scale);
            scaledBitmap = Bitmap.createScaledBitmap(source, newSize, mSize, true);
        } else {
            scale = (float) mSize / source.getWidth();
            newSize = Math.round(source.getHeight() * scale);
            scaledBitmap = Bitmap.createScaledBitmap(source, mSize, newSize, true);
        }
        if (scaledBitmap != source) {
            source.recycle();
        }

        return scaledBitmap;
    }

    @Override
    public String key() {
        return "scaleRespectRatio" +mSize+isHeightScale;
    }


}
