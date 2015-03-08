package com.infosys.hackathon.smartfert.view;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.infosys.hackathon.smartfert.R;
import com.infosys.hackathon.smartfert.utils.SmartFert;

import org.w3c.dom.Text;

import java.util.HashMap;

public class SubsidyReport extends ActionBarActivity {

    TextView tvbillNumer, tvbillDate, tvFertiliserDetails, tvDealer, tvActualCost, tvSubsidy, tvFarmerID, tvFarmerName, tvSubDetails, tvAcct, tv_seedingUrea, tv_seedingP, tv_seedingK, tv_transUrea, tv_transP, tv_transK, tv_panicleUrea, tv_panicleK, tv_panicleP, tv_tillerUrea, tv_tillerK, tv_tillerP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        setContentView(R.layout.activity_subsidy_report);
        HashMap<SmartFert.RESULT, String> mapObj = (HashMap<SmartFert.RESULT, String>) getIntent().getSerializableExtra("Result");

        tvFertiliserDetails = (TextView) findViewById(R.id.fert_details);
        tvFertiliserDetails.setText(mapObj.get(SmartFert.RESULT.UREA_PER_FARMER)+ " bags of Urea, "+mapObj.get(SmartFert.RESULT.SSP_PER_FARMER)+" bags of SSP, "+mapObj.get(SmartFert.RESULT.MOP_PER_FARMER)+" bags of MOP");
        tvActualCost = (TextView) findViewById(R.id.actual_cost);
        tvActualCost.setText("Rs. "+mapObj.get(SmartFert.RESULT.SUBSIDY_PER_FARMER));
        tvSubsidy = (TextView) findViewById(R.id.eligible_subsidy);
        tvSubsidy.setText("Rs. "+mapObj.get(SmartFert.RESULT.SUBSIDY_PER_FARMER));
        tvSubDetails = (TextView) findViewById(R.id.subsidy_details);
        tvSubDetails.setText("Rs. "+mapObj.get(SmartFert.RESULT.SUBSIDY_PER_FARMER));
        tvFarmerID = (TextView) findViewById(R.id.farmerid);
        String fid = getIntent().getStringExtra("FarmerID");
        tvFarmerID.setText(fid);
        tvFarmerName = (TextView) findViewById(R.id.farmer_name);
        tvFarmerName.setText(getIntent().getStringExtra("FarmerName"));
        tvAcct = (TextView) findViewById(R.id.acct_number);
        tvAcct.setText(getIntent().getStringExtra("AccountNumber"));

        tv_seedingUrea = (TextView) findViewById(R.id.seeding_urea);
        tv_seedingUrea.setText(mapObj.get(SmartFert.RESULT.UREA_PER_STAGE1) + " bags of Urea");

        tv_seedingP = (TextView) findViewById(R.id.seeding_ssp);
        tv_seedingP.setText(mapObj.get(SmartFert.RESULT.SSP_PER_STAGE1) + " bags of SSP");

        tv_seedingK = (TextView) findViewById(R.id.seeding__mop);
        tv_seedingK.setText(mapObj.get(SmartFert.RESULT.MOP_PER_STAGE1) + " bags of MOP");

        tv_transUrea = (TextView) findViewById(R.id.tp_urea);
        tv_transUrea.setText(mapObj.get(SmartFert.RESULT.UREA_PER_STAGE2) + " bags of Urea");

        tv_transP = (TextView) findViewById(R.id.tp_ssp);
        tv_transP.setText(mapObj.get(SmartFert.RESULT.SSP_PER_STAGE2) + " bags of SSP");

        tv_transK = (TextView) findViewById(R.id.tp_mop);
        tv_transK.setText(mapObj.get(SmartFert.RESULT.MOP_PER_STAGE2) + " bags of MOP");

        tv_tillerUrea = (TextView) findViewById(R.id.tiller_urea);
        tv_tillerUrea.setText(mapObj.get(SmartFert.RESULT.UREA_PER_STAGE3) + " bags of Urea");

        tv_tillerP = (TextView) findViewById(R.id.tiller_ssp);
        tv_tillerP.setText(mapObj.get(SmartFert.RESULT.SSP_PER_STAGE3) + " bags of SSP");

        tv_tillerK = (TextView) findViewById(R.id.tiller_mop);
        tv_tillerK.setText(mapObj.get(SmartFert.RESULT.MOP_PER_STAGE3) + " bags of MOP");

        tv_panicleUrea = (TextView) findViewById(R.id.panicle_urea);
        tv_panicleUrea.setText(mapObj.get(SmartFert.RESULT.UREA_PER_STAGE4) + " bags of Urea");

        tv_panicleP = (TextView) findViewById(R.id.panicle_ssp);
        tv_panicleP.setText(mapObj.get(SmartFert.RESULT.SSP_PER_STAGE4) + " bags of SSP");

        tv_panicleK = (TextView) findViewById(R.id.panicle_mop);
        tv_panicleK.setText(mapObj.get(SmartFert.RESULT.MOP_PER_STAGE4) + " bags of MOP");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_print) {
            return true;
        }

        if(id == R.id.action_send_sms) {
            Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
