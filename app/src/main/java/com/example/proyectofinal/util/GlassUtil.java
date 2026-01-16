package com.example.proyectofinal.ui.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.AttrRes;

import com.google.android.material.card.MaterialCardView;

public final class GlassUtil {

    private GlassUtil() {}

    public static int resolveColor(Context c, @AttrRes int attr) {
        TypedValue tv = new TypedValue();
        c.getTheme().resolveAttribute(attr, tv, true);
        return tv.data;
    }

    public static int withAlpha(int color, int alpha0_255) {
        return (alpha0_255 << 24) | (color & 0x00FFFFFF);
    }

    public static int dp(Context c, float dp) {
        return Math.round(dp * c.getResources().getDisplayMetrics().density);
    }

    public static void applyGlass70Card(MaterialCardView card) {
        Context c = card.getContext();
        int primary = resolveColor(c, com.google.android.material.R.attr.colorPrimary);
        int secondary = resolveColor(c, com.google.android.material.R.attr.colorSecondary);

        card.setCardBackgroundColor(withAlpha(primary, 178));
        card.setStrokeColor(secondary);
        if (card.getStrokeWidth() == 0) card.setStrokeWidth(dp(c, 1));
    }

    public static void applyGlass70RoundedView(View view, float radiusDp) {
        Context c = view.getContext();
        int primary = resolveColor(c, com.google.android.material.R.attr.colorPrimary);
        int secondary = resolveColor(c, com.google.android.material.R.attr.colorSecondary);

        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.RECTANGLE);
        bg.setCornerRadius(dp(c, radiusDp));
        bg.setColor(withAlpha(primary, 178));
        bg.setStroke(dp(c, 1), secondary);

        view.setBackground(bg);
    }
}
