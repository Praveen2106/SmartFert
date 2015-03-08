package com.infosys.hackathon.smartfert.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infosys.hackathon.smartfert.R;
import com.infosys.hackathon.smartfert.data.FarmerData;
import com.infosys.hackathon.smartfert.data.LandData;
import com.infosys.hackathon.smartfert.data.SoilData;
import com.infosys.hackathon.smartfert.data.SoilFertilityData;
import com.infosys.hackathon.smartfert.http.DataParser;
import com.infosys.hackathon.smartfert.utils.HeaderUtil;
import com.infosys.hackathon.smartfert.utils.SmartFert;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class name: FertliserMgmtDashActivity
 * Description: Activity for fertiliser management dashboard
 */
public class FertliserMgmtDashActivity extends ActionBarActivity {

    public static FarmerData farmerData;
    public static LandData landData;
    public static SoilData soilData;
    public static SoilFertilityData soilFertilityData;

    public static boolean fetchOrAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.layout_fsm_dash);

        final AlertDialog dialog;
        Button addNew, fetchDetails;
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_farmer_id, null);
        dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        final EditText soilTestRptNo = (EditText) dialogView.findViewById(R.id.soilTestRptNo);
        fetchDetails = (Button) dialogView.findViewById(R.id.fetchDetails);
        addNew = (Button) dialogView.findViewById(R.id.addNewSoilReport);
        addNew.setVisibility(View.GONE);
        soilData = new SoilData();

        fetchDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    soilData.setSoilReportNumber(soilTestRptNo.getText().toString());
                    InputStream ins = new ByteArrayInputStream(HeaderUtil.getFileData(soilData.getSoilReportNumber().toLowerCase(), getApplicationContext()).getBytes());
                    //InputStream ins = getResources().openRawResource(getResources().getIdentifier("raw/report1","raw", getPackageName()));

                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();
                    DataParser parser = new DataParser();
                    saxParser.parse(ins, parser);

                    SoilFertilityData fData = parser.getFertilityDetails();
                    farmerData = fData.getFarmerDetails();
                    landData = fData.getLandDetails();
                    soilData = fData.getSoilDetails();
                    fetchOrAdd = true;
                    dialog.dismiss();

                    /** chart 1*/
                    XYSeries npkSeries = new XYSeries("Available NPK");
                    npkSeries.add(2, soilData.getAvailableNitrogenContent());
                    npkSeries.add(4, soilData.getAvailablePhosphateContent());
                    npkSeries.add(6, soilData.getAvailablePotassiumContent());

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
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Soil Test Report Number is invalid", Toast.LENGTH_LONG).show();
                    e.printStackTrace(); }
            }
        });

        fetchOrAdd = false;
        dialog.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(fetchOrAdd)
                    return;

                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overview, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_calculate) {
            SmartFert obj = new SmartFert();
            Map<SmartFert.RESULT, String> calculatedSubsidyMap = obj.calcSub(Math.round(landData.getLandArea()), Math.round(soilData.getAvailableNitrogenContent()),
                    Math.round(soilData.getAvailablePhosphateContent()), Math.round(soilData.getAvailablePotassiumContent()));

            if(calculatedSubsidyMap.size() > 0) {
                HashMap<SmartFert.RESULT, String> map = new HashMap<SmartFert.RESULT, String>(calculatedSubsidyMap);
                Intent launchReport = new Intent(getApplicationContext(), SubsidyReport.class);
                launchReport.putExtra("Result", map);
                launchReport.putExtra("FarmerID", farmerData.getFarmerId());
                launchReport.putExtra("FarmerName", farmerData.getFarmerName());
                launchReport.putExtra("AccountNumber", farmerData.getAccountNumber());
                startActivity(launchReport);
            } else {
                Toast.makeText(this, "Soil is in good health. No fertilizers are required.", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }
}
