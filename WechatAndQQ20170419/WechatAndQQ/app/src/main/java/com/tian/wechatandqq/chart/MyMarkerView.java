package com.tian.wechatandqq.chart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.tian.wechatandqq.R;

import java.text.DecimalFormat;


/**
 * Created by tian on 2017/3/19.
 */

public class MyMarkerView extends MarkerView {

	private TextView tvContent;
	private PercentFormatter percentFormatter;
	private DecimalFormat format;

	public MyMarkerView(Context context, PercentFormatter percentFormatter) {
		super(context, R.layout.custom_marker_view);

		this.percentFormatter = percentFormatter;
		tvContent = (TextView) findViewById(R.id.tvContent);
		format = new java.text.DecimalFormat("##.0");
	}

	//回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
	@Override
	public void refreshContent(Entry e, Highlight highlight) {
		tvContent.setText( format.format(e.getY()));
		super.refreshContent(e, highlight);
	}

	@Override
	public MPPointF getOffset() {
		return new MPPointF(-(getWidth() / 2), -getHeight());
	}
}

