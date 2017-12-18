package com.tian.wechatandqq.chart;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tian.wechatandqq.chart.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tian on 2017/3/23.
 */
public class MPChartHelper {
	public static final int[] PIE_COLORS = {
			Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
			Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
			Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
	};
	public static void setCombineChart(CombinedChart combineChart, final List<String> xAxisValues,
	                                   List<Integer> lineValues, List<Float> barValues,
	                                   String lineTitle, String barTitle) {
		combineChart.getDescription().setEnabled(false);//设置描述
		combineChart.setPinchZoom(true);//设置按比例放缩柱状图

		

		//设置绘制顺序，让线在柱的上层
		combineChart.setDrawOrder(new CombinedChart.DrawOrder[]{
				CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
		});

		//x坐标轴设置
		XAxis xAxis = combineChart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setGranularity(1f);
		xAxis.setLabelRotationAngle(90);
		xAxis.setLabelCount(xAxisValues.size() + 2);
		xAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float v, AxisBase axisBase) {
				if (v < 0 || v > (xAxisValues.size() - 1))//使得两侧柱子完全显示
					return "";
				return xAxisValues.get((int) v);
			}

			@Override
			public int getDecimalDigits() {
				return 0;
			}
		});

		//y轴设置
		YAxis leftAxis = combineChart.getAxisLeft();
		leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setDrawGridLines(false);
        /*leftAxis.setAxisMinimum(0f);*/

		Float yMin = Double.valueOf(Collections.min(barValues) * 0.9).floatValue();
		Float yMax = Double.valueOf(Collections.max(barValues) * 1.1).floatValue();
		leftAxis.setAxisMaximum(yMax);
		leftAxis.setAxisMinimum(yMin);


		YAxis rightAxis = combineChart.getAxisRight();
		rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
		rightAxis.setDrawGridLines(false);

		//图例设置
		Legend legend = combineChart.getLegend();
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		legend.setDrawInside(false);
		legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
		legend.setForm(Legend.LegendForm.SQUARE);
		legend.setTextSize(12f);

		//设置组合图数据
		CombinedData data = new CombinedData();
		data.setData(generateLineData(lineValues, lineTitle));
		data.setData(generateBarData(barValues, barTitle));

		combineChart.setData(data);//设置组合图数据源


		//使得两侧柱子完全显示
		xAxis.setAxisMinimum(combineChart.getCombinedData().getXMin() - 1f);
		xAxis.setAxisMaximum(combineChart.getCombinedData().getXMax() + 1f);

		combineChart.setExtraTopOffset(30);
		combineChart.setExtraBottomOffset(10);
		combineChart.animateX(1500);//数据显示动画，从左往右依次显示
		combineChart.invalidate();
	}
	private static BarData generateBarData(List<Float> barValues, String barTitle) {

		ArrayList<BarEntry> barEntries = new ArrayList<>();

		for (int i = 0, n = barValues.size(); i < n; ++i) {
			barEntries.add(new BarEntry(i, barValues.get(i)));
		}

		BarDataSet barDataSet = new BarDataSet(barEntries, barTitle);
		barDataSet.setColor(Color.rgb(255, 0, 255));
		barDataSet.setValueTextColor(Color.rgb(159, 143, 186));
		barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

		BarData barData = new BarData(barDataSet);
		barData.setValueTextSize(10f);
		barData.setBarWidth(0.9f);
		barData.setValueFormatter(new IValueFormatter() {
			@Override
			public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
				return StringUtils.double2String(value, 2);
			}
		});

		return barData;
	}

	private static LineData generateLineData(List<Integer> lineValues, String lineTitle) {
		ArrayList<Entry> lineEntries = new ArrayList<>();

		for (int i = 0, n = lineValues.size(); i < n; ++i) {
			lineEntries.add(new Entry(i, lineValues.get(i)));
		}

		LineDataSet lineDataSet = new LineDataSet(lineEntries, lineTitle);
		lineDataSet.setColor(Color.rgb(233, 196, 21));
		lineDataSet.setLineWidth(2.5f);//设置线的宽度
		lineDataSet.setCircleColor(Color.rgb(244, 219, 100));//设置圆圈的颜色
		lineDataSet.setCircleColorHole(Color.WHITE);//设置圆圈内部洞的颜色
		//lineDataSet.setValueTextColor(Color.rgb(254,116,139));
		lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
		lineDataSet.setDrawValues(false);//不绘制线的数据

		LineData lineData = new LineData(lineDataSet);
		lineData.setValueTextSize(10f);
		lineData.setValueFormatter(new IValueFormatter() {
			@Override
			public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
				return StringUtils.double2String(value, 2);
			}
		});

		return lineData;
	}

	public static void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
		pieChart.setUsePercentValues(true);//设置使用百分比
		pieChart.getDescription().setEnabled(false);//设置描述
		pieChart.setExtraOffsets(20, 15, 20, 15);
		pieChart.setCenterText(title);//设置环中的文字
		pieChart.setCenterTextSize(22f);//设置环中文字的大小
		pieChart.setDrawCenterText(true);//设置绘制环中文字
		pieChart.setRotationAngle(120f);//设置旋转角度


		//图例设置
		Legend legend = pieChart.getLegend();
		if (showLegend) {
			legend.setEnabled(true);
			legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
			legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
			legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
			legend.setDrawInside(false);
			legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
		} else {
			legend.setEnabled(false);
		}

		//设置饼图数据
		setPieChartData(pieChart, pieValues);

		pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);//数据显示动画
	}
	private static void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
		ArrayList<PieEntry> entries = new ArrayList<>();

		Set set = pieValues.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
		}

		PieDataSet dataSet = new PieDataSet(entries, "");
		dataSet.setSliceSpace(3f);//设置饼块之间的间隔
		dataSet.setSelectionShift(5f);//设置饼块选中时偏离饼图中心的距离

		dataSet.setColors(PIE_COLORS);//设置饼块的颜色
		dataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
		dataSet.setValueLinePart1Length(0.3f);
		dataSet.setValueLinePart2Length(0.4f);
		dataSet.setValueLineColor(Color.BLUE);//设置连接线的颜色
		dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

		PieData pieData = new PieData(dataSet);
		pieData.setValueFormatter(new PercentFormatter());
		pieData.setValueTextSize(11f);
		pieData.setValueTextColor(Color.DKGRAY);

		pieChart.setData(pieData);
		pieChart.highlightValues(null);
		pieChart.invalidate();
	}



}
