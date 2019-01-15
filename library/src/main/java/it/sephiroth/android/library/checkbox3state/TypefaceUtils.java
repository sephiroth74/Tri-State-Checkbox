package it.sephiroth.android.library.checkbox3state;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;

public final class TypefaceUtils {
    private static final LruCache<String, Typeface> fontCache = new LruCache<>(4);

    private TypefaceUtils() {
    }

    public static Typeface createTypefaceWithStyle(boolean bold, boolean italic) {
        if (!bold && italic) {
            return Typeface.defaultFromStyle(Typeface.ITALIC);
        } else if (bold && !italic) {
            return Typeface.defaultFromStyle(Typeface.BOLD);
        } else if (bold && italic) {
            return Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
        }

        return Typeface.defaultFromStyle(Typeface.NORMAL);
    }

    public static Typeface loadFromAsset(@NonNull final AssetManager assets, @Nullable final String assetPath) {

        if (TextUtils.isEmpty(assetPath)) {
            return null;
        }

        Typeface result;
        Typeface cachedFont = getFromCache(assetPath);

        if (null != cachedFont) {
            result = cachedFont;
        } else {
            result = Typeface.createFromAsset(assets, assetPath);
            putIntoCache(assetPath, result);
        }

        return result;
    }

    private static Typeface getFromCache(final String fontname) {
        synchronized (fontCache) {
            return fontCache.get(fontname);
        }
    }

    private static void putIntoCache(final String fontname, final Typeface font) {
        synchronized (fontCache) {
            fontCache.put(fontname, font);
        }
    }
}