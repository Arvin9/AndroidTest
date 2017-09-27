package site.nebulas.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import site.nebulas.demo.R;

/**
 * Created by Nebula on 2017/9/27.
 */

public class HorizontalProgressbarWithProgress extends ProgressBar {
    private static final int DEFAULT_TEXT_SIZE = 10; //sp
    private static final int DEFAULT_TEXT_COLOR = 0xFFC00D1; //
    private static final int DEFAULT_UNREACH_COLOR = 0xFFD3D6DA; //
    private static final int DEFAULT_UNREACH_HEIGHT = 2; //dp
    private static final int DEFAULT_REACH_COLOR = DEFAULT_TEXT_COLOR; //
    private static final int DEFAULT_REACH_HEIGHT = 2; //dp
    private static final int DEFAULT_TEXT_OFFSET = 10; //dp

    private int mTextSize = sp2dp(DEFAULT_TEXT_SIZE);
    private int mTextColor =  DEFAULT_TEXT_COLOR;
    private int mUnReachColor = DEFAULT_UNREACH_COLOR;
    private int mUnReachHeight = dp2px(DEFAULT_UNREACH_HEIGHT);
    private int mReachColor = DEFAULT_REACH_COLOR;
    private int mReachHeight = dp2px(DEFAULT_REACH_HEIGHT);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    private Paint mPaint = new Paint();
    private int mRealWidth;

    public HorizontalProgressbarWithProgress(Context context) {
        this(context,null);
    }

    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        obtainStyleAttrs(attrs);
    }

    /**
     * 获取自定义属性
     * @param attrs
     * */
    private void obtainStyleAttrs(AttributeSet attrs) {
        TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressbarWithProgress);

        mTextSize = (int) typeArray.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_text_size, mTextSize);

        mTextColor = typeArray.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_text_color, mTextColor);

        mTextOffset = (int) typeArray.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_text_offset, mTextOffset);

        mUnReachColor = typeArray.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_unreach_color, mUnReachColor);

        mUnReachHeight  = (int) typeArray.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_unreach_height, mUnReachHeight);

        mReachColor = typeArray.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_reach_color, mReachColor);

        mReachHeight  = (int) typeArray.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_reach_height, mReachHeight);

        typeArray.recycle();

        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthVal = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthVal, height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop()
                    + getPaddingBottom()
                    + Math.max(Math.max(mReachHeight, mUnReachHeight), Math.abs(textHeight));
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight()/2);
        boolean noNeedUnRech = false;

        String text = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(text);
        float radio = getProgress() * 1.0f / getMax();
        float progressX = radio * mRealWidth;
        if (progressX + textWidth > mRealWidth) {
            progressX = mRealWidth - textWidth;
            noNeedUnRech = true;
        }

        float endX = progressX - mTextOffset /2;
        if (endX > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        // draw text
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
        canvas.drawText(text, progressX, y , mPaint);

        if (!noNeedUnRech) {
            float start = progressX + mTextOffset/2 + textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    private int sp2dp(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                getResources().getDisplayMetrics());
    }
}
