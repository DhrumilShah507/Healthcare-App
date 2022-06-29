package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PiechartActivity extends AppCompatActivity {

    PieChart pieChart;
    int[] Container={
            R.id.container_1,
            R.id.container_2,
            R.id.container_3,
            R.id.container_4,
            R.id.container_5,
            R.id.container_6
    };
    Maleria maleria= new Maleria();
    com.example.health.Dengue dengue=new Dengue();
    com.example.health.Typhoid typhoid=new Typhoid();
    com.example.health.Cholera cholera=new Cholera();
    com.example.health.Chikungunya chikungunya=new Chikungunya();
    com.example.health.Covid19 covid19=new Covid19();
    Fragment[] frag={maleria,dengue,typhoid,cholera,chikungunya,covid19};

    TextView Mal_per,DenPer,TyPer,CovidPer,ChoPer,ChPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        //TO CHANGE ACTION BAR TITLE
       // getSupportActionBar().setTitle("PIE CHART");
        //TO REACH PARENT ACTIVITY
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //
        Mal_per=(TextView)findViewById(R.id.Mal_Par);
        DenPer=(TextView)findViewById(R.id.Den_Per);
        TyPer=(TextView)findViewById(R.id.Ty_Per);
        ChoPer=(TextView)findViewById(R.id.Cho_Per);
        ChPer=(TextView)findViewById(R.id.Chi_Per);
        CovidPer=(TextView)findViewById(R.id.CoPer);

        Intent intent= getIntent();
        float M=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_M,0);
        float D=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_D,0);
        float T=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_T,0);
        float K=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_K,0);
        float CH=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_CH,0);
        float CO=intent.getFloatExtra(AssessmentActivity.EXTRA_PERCENTAGE_CO,0);


        pieChart=(PieChart)findViewById(R.id.piechart);

        ArrayList<PieEntry> records=new ArrayList<>();
        if(M>0){records.add(new PieEntry(M,"MALARIA"));}
        if(D>0){  records.add(new PieEntry(D,"DENGUE"));}
        if(T>0){records.add(new PieEntry(T,"TYPHOID"));}
        if(K>0){ records.add(new PieEntry(K,"CHOLERA"));}
       if(CH>0){records.add(new PieEntry(CH,"CHIKUNGUNIYA"));}
       if(CO>0){records.add(new PieEntry(CO,"COVID19"));}

        PieDataSet dataSet=new PieDataSet(records,"PieChart Reports");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);

        PieData pieData;
        pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText("Possible Disease");
        pieChart.setCenterTextSize(22);
        pieChart.animate();

        //All DOUBLE CONVERT INTO STRING FORM
               String Malaria= new DecimalFormat("0.00").format(M);
               String Dengue=new DecimalFormat("0.00").format(D);
               String Typhoid= new DecimalFormat("0.00").format(T);
               String Cholera= new DecimalFormat("0.00").format(K);
               String Chikungunya= new DecimalFormat("0.00").format(CH);
               String Covid19= new DecimalFormat("0.00").format(CO);

              // getSupportFragmentManager().beginTransaction().replace(R.id.container,new ResultFragment()).commit();
               Mal_per.setText(Malaria+"%");
               DenPer.setText(Dengue+"%");
               TyPer.setText(Typhoid+"%");
               ChoPer.setText(Cholera+"%");
               ChPer.setText(Chikungunya+"%");
               CovidPer.setText(Covid19+"%");


        /*for(int i=0;i<5;i++){
           Max[0]=M;

        }

        int m=Max[0];

        for(int i=0;i<5;i++){
            if(Max[i+1]>m){

                m=Math.max(Max[i+1],Max[i]);
            }

        }*/

        float[] max={M,D,T,K,CH,CO};
        float m=0f;
        for(int i=0;i<5;i++) {
            if (max[i + 1] > m) {

                m = Math.max(max[i + 1], max[i]);
            }
        }

        int cnt=0;
        for(int i=0;i<6;i++) {
            if (max[i] == m) {
                FragmentManager fragmentManager =getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(Container[cnt],frag[i]);
                //fragmentTransaction.add(R.id.Frame2,first_fragment);
                fragmentTransaction.commit();
                cnt++;


            }
        }


    }
}