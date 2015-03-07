package com.infosys.hackathon.smartfert.view;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.Window;
import android.widget.LinearLayout;

import com.infosys.hackathon.smartfert.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Class name: FertliserMgmtDashActivity
 * Description: Activity for fertiliser management dashboard
 */
public class FertliserMgmtDashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.layout_fsm_dash);

        //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setCustomView(R.layout.custom_btn);
       // actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
               // | ActionBar.DISPLAY_SHOW_HOME);

        /** chart 1*/
        XYSeries npkSeries = new XYSeries("Available NPK");
        npkSeries.add(2, 100);
        npkSeries.add(4, 50);
        npkSeries.add(6,75);

        XYMultipleSeriesRenderer renderer1 = new XYMultipleSeriesRenderer();
        renderer1.setAxisTitleTextSize(16);
        renderer1.setChartTitleTextSize(20);
        renderer1.setLabelsTextSize(15);
        renderer1.setLegendTextSize(15);
        renderer1.setMargins(new int[] { 30, 40, 15, 0 });
        renderer1.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
        renderer1.setYLabelsColor(0, Color.GRAY);
        renderer1.setXLabelsColor(Color.GRAY);// transparent margins
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(Color.parseColor("#1b5e20"));
        renderer1.addSeriesRenderer(r);

        renderer1.setChartTitle("Available Fertility Macro-Nutrients");
        renderer1.setChartTitleTextSize(15);
        renderer1.setXAxisMin(0.5);
        renderer1.setXAxisMax(10.5);
        renderer1.setYAxisMin(0);
        renderer1.setYAxisMax(150);
        renderer1.addXTextLabel(2, "N");
        renderer1.addXTextLabel(4, "P");
        renderer1.addXTextLabel(6, "K");
        renderer1.setYLabelsAlign(Paint.Align.RIGHT);
        renderer1.setBarSpacing(0.5);
        renderer1.setXTitle("Macro-nutrients");
        renderer1.setLabelsColor(Color.BLACK);
        renderer1.setXLabelsColor(Color.BLACK);
        renderer1.setYLabelsColor(0, Color.BLACK);
        renderer1.setYTitle("Available Values");
        renderer1.setShowGrid(true);
        renderer1.setGridColor(Color.GRAY);
        renderer1.setXLabels(0);





        XYMultipleSeriesDataset dataset1 = new XYMultipleSeriesDataset();
        dataset1.addSeries(npkSeries);

        GraphicalView chartView1 = ChartFactory.getBarChartView(getApplicationContext(), dataset1, renderer1, BarChart.Type.DEFAULT);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout_avbl_fertility);
        layout1.addView(chartView1);
        /** end chart 1*/

        /** chart 4 */
        XYSeries series = new XYSeries("Available NPK");
        series.add(2012, 2231);
        series.add(2013, 4123);
        series.add(2014,3887);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setLineWidth(2);
        renderer.setColor(Color.RED);
        renderer.setDisplayBoundingPoints(true);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(3);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        mRenderer.setPanEnabled(false, false);
        mRenderer.setYAxisMax(8000);
        mRenderer.setYAxisMin(2000);
        mRenderer.setYTitle("Cost in Rs");

        mRenderer.setXAxisMin(2012);
        mRenderer.setXAxisMax(2018);
        mRenderer.setXTitle("Year");
        mRenderer.setShowGrid(true);

        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsColor(0, Color.BLACK);

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        GraphicalView chartView = ChartFactory.getLineChartView(getApplicationContext(), dataset, mRenderer);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_subsidyhistory);
        layout.addView(chartView);
        /** End - chart 4*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overview, menu);

        return true;
    }
}
