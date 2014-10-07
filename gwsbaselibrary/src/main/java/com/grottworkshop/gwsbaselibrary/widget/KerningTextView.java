package com.grottworkshop.gwsbaselibrary.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * KerningTextView, to use:
 * <code>
 *     KerningTextView textview = new KerningTextView(context)_;
 *     textView.setLetterSpacing(10);
 *     textView.setText("My Text");
 *     ((LinearLayout))findViewBy(R.id.myLinearLayout)).addView(textView);
 * </code>
 *
 * Remember to use
 * <code>
 *     builder.append("\u00A0")
 * </code>
 *
 * rather than
 *
 * <code>
 *     builder.append("")
 * </code>
 *
 * otherwise you  get broken text wrapping.
 * Created by fgrott on 10/7/2014.
 */
public class KerningTextView extends TextView {

    private float letterSpacing = LetterSpacing.NORMAL;
    private CharSequence originalText = "";


    /**
     * Instantiates a new Kerning text view.
     *
     * @param context the context
     */
    public KerningTextView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Kerning text view.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public KerningTextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * Instantiates a new Kerning text view.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public KerningTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    /**
     * Gets letter spacing.
     *
     * @return the letter spacing
     */
    public float getLetterSpacing() {
        return letterSpacing;
    }

    /**
     * Sets letter spacing.
     *
     * @param letterSpacing the letter spacing
     */
    public void setLetterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        applyLetterSpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyLetterSpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyLetterSpacing() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if(i+1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if(builder.toString().length() > 1) {
            for(int i = 1; i < builder.toString().length(); i+=2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing+1)/10), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    /**
     * The type Letter spacing.
     */
    public class LetterSpacing {
        /**
         * The constant NORMAL.
         */
        public final static float NORMAL = 0;
    }
}
