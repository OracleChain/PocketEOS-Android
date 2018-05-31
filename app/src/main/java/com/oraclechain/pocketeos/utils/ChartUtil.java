package com.oraclechain.pocketeos.utils;

/**
 * Created by pocketEos on 2017/12/6.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.oraclechain.pocketeos.R;

import java.util.List;


public class ChartUtil {
    /**
     * 显示图表
     *
     * @param context    上下文
     * @param lineChart  图表对象
     * @param yDataList  数据
     * @param title      图表标题（如：XXX趋势图）
     * @param curveLable 曲线图例名称（如：--用电量/时间）
     * @param unitName   坐标点击弹出提示框中数字单位（如：KWH）
     */
    public static void showChart(Context context, LineChart lineChart,
                                 List<Entry> yDataList, String title, String curveLable, String unitName) {
        // 设置数据
        lineChart.setData(setLineData(context, yDataList, curveLable));
        // 描述的显示和隐藏
        lineChart.getDescription().setEnabled(false);
        // 是否在折线图上添加边框
        lineChart.setDrawBorders(true);
        // 如果没有数据的时候，会显示这个，类似文本框的placeholder
        lineChart.setNoDataText(context.getString(R.string.no_data_chart));
        // 是否显示表格颜色
        lineChart.setDrawGridBackground(false);
        // 禁止绘制图表边框的线
        lineChart.setDrawBorders(false);
        // 表格的的颜色，在这里是是给颜色设置一个透明度
        // lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF);
        // 设置是否启动触摸响应
        lineChart.setTouchEnabled(false);
        // 是否可以拖拽
        lineChart.setDragEnabled(false);
        // 是否可以缩放
        lineChart.setScaleEnabled(false);
        // 如果禁用，可以在x和y轴上分别进行缩放
        lineChart.setPinchZoom(false);
        // lineChart.setMarkerView(mv);
        // 设置背景色
        // lineChart.setBackgroundColor(getResources().getColor(R.color.bg_white));
        // 图例对象
        Legend mLegend = lineChart.getLegend();
        // mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        // 图例样式 (CIRCLE圆形；LINE线性；SQUARE是方块）
        mLegend.setForm(LegendForm.SQUARE);
        // 图例大小
        mLegend.setFormSize(8f);
        // 图例上的字体颜色
        mLegend.setTextColor(context.getApplicationContext().getResources().getColor(R.color.theme_color));
        mLegend.setTextSize(12f);
        // 图例字体
        // mLegend.setTypeface(mTf);
        // 图例的显示和隐藏
        mLegend.setEnabled(false);
        // 隐藏右侧Y轴（只在左侧的Y轴显示刻度）
        lineChart.getAxisRight().setEnabled(false);
        // 隐藏左侧Y轴（只在左侧的Y轴显示刻度）
        lineChart.getAxisLeft().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        // 显示X轴上的刻度值
        xAxis.setDrawLabels(false);
        // 设置X轴的数据显示在报表的下方
        xAxis.setPosition(XAxisPosition.BOTTOM);
        // 轴线
        xAxis.setDrawAxisLine(false);
        // 设置不从X轴发出纵向直线
        xAxis.setDrawGridLines(false);
        // 执行的动画,x轴（动画持续时间）
        lineChart.animateX(1500);
        lineChart.notifyDataSetChanged();
    }

    /**
     * 曲线赋值与设置
     *
     * @param context   上下文
     * @param yDataList y轴数据
     * @return LineData
     */
    private static LineData setLineData(Context context, List<Entry> yDataList,
                                        String curveLable) {
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yDataList, curveLable);
        // 用y轴的集合来设置参数
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        // 不显示坐标点的数据
        lineDataSet.setDrawValues(false);
        // 显示坐标点的小圆点
        lineDataSet.setDrawCircles(false);
        // 定位线
        lineDataSet.setHighlightEnabled(false);
        // 线宽
        lineDataSet.setLineWidth(1f);
        // 显示的圆形大小
        lineDataSet.setCircleSize(2f);
        // 显示颜色
        lineDataSet.setColor(context.getApplicationContext().getResources().getColor(R.color.chart_color));
        // 圆形的颜色
        lineDataSet.setCircleColor(context.getApplicationContext().getResources().getColor(R.color.chart_color));
        // 高亮的线的颜色
        lineDataSet.setHighLightColor(context.getApplicationContext().getResources()
                .getColor(R.color.chart_color));
        // 设置填充的颜色

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_color);
        lineDataSet.setFillDrawable(drawable);

        // 设置坐标点为空心环状
        lineDataSet.setDrawCircleHole(false);
        // lineDataSet.setValueTextSize(9f);
        lineDataSet.setFillAlpha(30);
        // 设置显示曲线和X轴围成的区域阴影
        lineDataSet.setDrawFilled(true);
        // 坐标轴在左侧
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        // 设置每条曲线图例标签名
        // lineDataSet.setLabel("标签");
        lineDataSet.setValueTextSize(14f);
        // 曲线弧度（区间0.05f-1f，默认0.2f）
        lineDataSet.setCubicIntensity(0.2f);
        // 设置为曲线显示,false为折线
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setValues(yDataList);
        // y轴的数据
        LineData lineData = new LineData(lineDataSet);
        return lineData;
    }

}

