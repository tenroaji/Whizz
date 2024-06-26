package id.magau.whizz.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by Anwar on 08/11/2017.
 */

public class PicassoCircleTransform implements Transformation {

    private final int BORDER_COLOR;
    private final int BORDER_RADIUS;

    public PicassoCircleTransform(int borderColor, int borderWidth){
        BORDER_COLOR = borderColor;
        BORDER_RADIUS = borderWidth;
    }

    public PicassoCircleTransform(){
        BORDER_COLOR = 0xFFFFFF;
        BORDER_RADIUS = 0;
    }

    @Override
    public Bitmap transform(Bitmap source) {

        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        float r2 = r - BORDER_RADIUS;

        // Prepare the background
        Paint paintBg = new Paint();
        paintBg.setColor(BORDER_COLOR);
        paintBg.setAntiAlias(true);

        canvas.drawCircle(r, r, r, paintBg);
        canvas.drawCircle(r, r, r2,paint);
        // Draw the background circle

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle+border=" +BORDER_RADIUS ;
    }
}
