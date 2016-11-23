package io.deschene.lightsout.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Custom view that fits the font size to the size of the view.
 */
public class FontFitTextView extends TextView {

    private Paint testPaint;

    public FontFitTextView(Context context) {
        super(context);
        initialise();
    }

    public FontFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        testPaint = new Paint();
        testPaint.set(getPaint());
        // max size defaults to the initially specified text size unless it is
        // too small
    }

    /*
     * Re size the font so the specified text fits in the text box assuming the
     * text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth <= 0) { return; }
        int targetWidth = textWidth - getPaddingLeft() - getPaddingRight();
        float high = 100;
        float low = 2;
        float threshold = 0.5f; // How close we have to be

        testPaint.set(getPaint());

        while ((high - low) > threshold) {
            float size = (high + low) / 2;
            testPaint.setTextSize(size);
            if (testPaint.measureText(text) >= targetWidth) {
                high = size; // too big
            } else {
                low = size; // too small
            }
        }

        // Use lo so that we undershoot rather than overshoot
        setTextSize(TypedValue.COMPLEX_UNIT_PX, low);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int height = getMeasuredHeight();
        refitText(getText().toString(), parentWidth);
        setMeasuredDimension(parentWidth, height);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        refitText(text.toString(), getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(getText().toString(), w);
        }
    }
}