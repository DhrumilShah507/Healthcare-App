package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.collection.LLRBBlackValueNode;

public class AssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_PERCENTAGE_M= "com.example.health.example.EXTRA_PERCENTAGE_M";
    public static final String EXTRA_PERCENTAGE_D= "com.example.health.example.EXTRA_PERCENTAGE_D";
    public static final String EXTRA_PERCENTAGE_T= "com.example.health.example.EXTRA_PERCENTAGE_T";
    public static final String EXTRA_PERCENTAGE_K= "com.example.health.example.EXTRA_PERCENTAGE_K";
    public static final String EXTRA_PERCENTAGE_CH= "com.example.health.example.EXTRA_PERCENTAGE_CH";
    public static final String EXTRA_PERCENTAGE_CO= "com.example.health.example.EXTRA_PERCENTAGE_CO";


    int cnt1,cnt2;
    TextView q1,q2,q3;
    CheckBox Fev,Cou,Wee,Pla,Bod,Hed,Vom,LBP,Dia,Res,Che,Sto,Nst,Bre,Mus,Thi;
   // Button MFev,D_CFev,covidFev,MCou,D_TCou,covidCou,Nxt,NxtFnC,NxtF,NxtC;
    Button Nxt;
    LinearLayout Main,Fever,Cough;
    ProgressBar p;
    Animation frombottom;
 //   FrameLayout F;
    float M=0,D=0,T=0,K=0,Co=0,Ch=0,Total=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        cnt1=0;
        cnt2=0;

        //FRAME LAYOUT
       // F=(FrameLayout)findViewById(R.id.container);

        final ScrollView s=findViewById(R.id.scrollView);
        //TO CHANGE ACTION BAR TITLE
        //getSupportActionBar().setTitle("ASSESSMENT TEST");
        //TO REACH PARENT ACTIVITY
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LINKING ALL QUESTION WITH OBJ
        q1=(TextView)findViewById(R.id.Q1);
       // q2=(TextView)findViewById(R.id.typFev);
      //  q3=(TextView)findViewById(R.id.typCou);

        //
       // p=(ProgressBar)findViewById(R.id.progressBar_sign);
        //LINKING ALL CHECKBOX WITH OBJ
        Fev=(CheckBox)findViewById(R.id.fevCh);
        Cou=(CheckBox)findViewById(R.id.CouCh);
        Wee=(CheckBox)findViewById(R.id.WeekCh);
        Pla=(CheckBox)findViewById(R.id.PlaCh);
        Bod=(CheckBox)findViewById(R.id.bjPainCh);
        Hed=(CheckBox)findViewById(R.id.headCh);
        Vom=(CheckBox)findViewById(R.id.VomCh);
        LBP=(CheckBox)findViewById(R.id.LBPCh);
        Dia=(CheckBox)findViewById(R.id.DiaCh);
        Res=(CheckBox)findViewById(R.id.RestCh);
        Che=(CheckBox)findViewById(R.id.CheCh);
        Sto=(CheckBox)findViewById(R.id.StomCh);
        Nst=(CheckBox)findViewById(R.id.NoSnTCh);
        Bre=(CheckBox)findViewById(R.id.BreCh);
        Mus=(CheckBox)findViewById(R.id.MusCh);
        Thi=(CheckBox)findViewById(R.id.ThaCh);

        //LINKING ALL BUTTON WITH OBJ
        Nxt=(Button)findViewById(R.id.NxtBtn);
      

        //FOR ANIMATION
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);

        //COUNTER QUESTION IF USER CHECKED FEVER CHECKBOX
        Fev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(cnt1==0) {
                    cnt1++;
                    LinearLayout FevQue = (LinearLayout) findViewById(R.id.FevQue);
                    TextView Fevtv = new TextView(AssessmentActivity.this);
                    Fevtv.setText("Which of the following fever are you facing?");
                    Fevtv.setBackgroundColor(getResources().getColor(R.color.bg));
                    Fevtv.setTextSize(22);
                    Fevtv.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                    final Button typ1 = new Button(AssessmentActivity.this);
                    typ1.setText("From 2-3 Days at A Certain Time");
                    typ1.setBackgroundColor(getResources().getColor(R.color.High));
                    final Button typ2 = new Button(AssessmentActivity.this);
                    typ2.setText("High Fever");
                    typ2.setBackgroundColor(getResources().getColor(R.color.Medium));
                    final Button typ3 = new Button(AssessmentActivity.this);
                    typ3.setText("Normal Fever");
                    typ3.setBackgroundColor(getResources().getColor(R.color.Low));
                    FevQue.addView(Fevtv);
                    FevQue.addView(typ1);
                    FevQue.addView(typ2);
                    FevQue.addView(typ3);

                    typ1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt1==1){
                                M+=10;
                                D-=5;
                                Co-=5;
                                T-=5;
                                Ch-=5;
                                Total+=10;
                                typ1.setBackgroundColor(getResources().getColor(R.color.High_selected));


                            }
                            cnt1++;


                        }
                    });
                    typ2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt1==1){
                                D+=10;
                                M-=5;
                                Co-=5;
                                T-=5;
                                Ch+=5;
                                Total+=10;
                                typ2.setBackgroundColor(getResources().getColor(R.color.Medium_selected));

                            }
                            cnt1++;
                        }
                    });
                    typ3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt1==1){
                                Co+=10;
                                D-=5;
                                M-=5;
                                T+=5;
                                Ch-=5;
                                Total+=10;
                                typ3.setBackgroundColor(getResources().getColor(R.color.Low_selected));
                            }
                            cnt1++;
                        }
                    });
                }


            }
        });

        //COUNTER QUESTION IF USER CHECKED COUGH CHECKBOX
        final CheckBox Cou=(CheckBox)findViewById(R.id.CouCh);

        Cou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cnt2==0){
                    cnt2++;
                    LinearLayout CouQue=(LinearLayout)findViewById(R.id.CoughQue);
                    TextView  Coutv= new TextView(AssessmentActivity.this);
                    Coutv.setText(R.string.FevQue);
                    Coutv.setBackgroundColor(getResources().getColor(R.color.bg));
                    Coutv.setTextSize(22);
                    Coutv.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                    final Button typ1 =new Button(AssessmentActivity.this);
                    typ1.setText("Non Stop Dry Cough");
                    typ1.setBackgroundColor(getResources().getColor(R.color.High));
                    final Button typ2 =new Button(AssessmentActivity.this);
                    typ2.setText("Dry Cough");
                    typ2.setBackgroundColor(getResources().getColor(R.color.Medium));
                    final Button typ3 =new Button(AssessmentActivity.this);
                    typ3.setText("Cold cough");
                    typ3.setBackgroundColor(getResources().getColor(R.color.Low));
                    CouQue.addView(Coutv);
                    CouQue.addView(typ1);
                    CouQue.addView(typ2);
                    CouQue.addView(typ3);

                    typ1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt2==1){
                                M+=10;
                                T-=5;
                                Co-=5;
                                D-=5;
                                Total+=10;
                                typ1.setBackgroundColor(getResources().getColor(R.color.High_selected));

                            }
                            cnt2++;
                        }
                    });
                    typ2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt2==1){
                                Co+=10;
                                M-=5;
                                T-=5;
                                D+=10;
                                Total+=10;
                                typ2.setBackgroundColor(getResources().getColor(R.color.Medium_selected));

                            }
                            cnt2++;
                        }
                    });
                    typ3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cnt2==1){
                                T+=10;
                                M-=5;
                                Co-=5;
                                D-=5;
                                Total+=10;
                                typ3.setBackgroundColor(getResources().getColor(R.color.Low_selected));

                            }
                            cnt2++;
                        }
                    });
                }

            }
        });



        //NXT BUTTON ON CLICK EVENT
       Nxt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //INITIALIZED COUNT VARIABLE
               cnt1=0;
               cnt2=0;


             
                //ASSESSMENT LOGIC BY CHECKLIST

               if(Fev.isChecked())
               {
                   M+=10;
                   D+=10;
                   T+=10;
                   Ch+=10;
                   Co+=10;
                   K-=5;
                   Total+=10;


               }
               if(Cou.isChecked())
               {
                   M+=10;
                   D+=10;
                   Co+=10;
                   T+=10;
                   Ch-=5;
                   K-=5;
                   Total+=10;
               }
               if(Wee.isChecked())
               {
                   M+=10;
                   T+=10;
                   D-=5;
                   Co-=5;
                   T-=5;
                   Ch-=5;
                   K-=5;
                   Total+=10;
               }
               if(Pla.isChecked())
               {
                   D+=15;
                   M-=5;
                   T-=5;
                   Co-=5;
                   Ch-=5;
                   K-=5;
                   Total+=10;

               }
               if(Bod.isChecked())
               {
                   D+=10;
                   M+=10;
                   Ch+=10;
                   T-=5;
                   Co-=5;
                   K-=5;
                   Total+=10;
               }
               if(Hed.isChecked())
               {
                   M+=10;
                   D+=10;
                   T+=10;
                   Co+=10;
                   K-=5;
                   Ch-=5;
                   Total+=10;


               }
               if(LBP.isChecked())
               {
                   K+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Vom.isChecked())
               {
                   K+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Dia.isChecked())
               {

                   T+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   K-=5;
                   Total+=15;
               }
               if(Res.isChecked())
               {

                   K+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Che.isChecked())
               {
                   Co+=15;
                   M-=5;
                   D-=5;
                   K-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Sto.isChecked())
               {
                   T+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   K-=5;
                   Total+=15;
               }
               if(Nst.isChecked())
               {
                   Co+=15;
                   M-=5;
                   D-=5;
                   K-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Bre.isChecked())
               {
                   Co+=15;
                   M-=5;
                   D-=5;
                   K-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;
               }
               if(Mus.isChecked())
               {
                   M+=10;
                   D+=10;
                   K+=10;
                   Ch-=5;
                   T-=5;
                   Co-=5;
                   Total+=10;
               }
               if(Thi.isChecked())
               {
                   K+=15;
                   M-=5;
                   D-=5;
                   Co-=5;
                   Ch-=5;
                   T-=5;
                   Total+=15;

               }



               //FIND PERCENTAGE OF ALL DISEASES
               M=(M/Total)*100;
               if(M<0){M=0;}
               Co=(Co/Total)*100;
               if(Co<0){Co=0;}
               D=(D/Total)*100;
               if(D<0){D=0;}
               T=(T/Total)*100;
               if(T<0){T=0;}
               K=(K/Total)*100;
               if(K<0){K=0;}
               Ch=(Ch/Total)*100;
               if(Ch<0){Ch=0;}
                Total=M+Co+D+T+K+Ch;
                M=(M/Total)*100;
               Co=(Co/Total)*100;
               D=(D/Total)*100;
               T=(T/Total)*100;
               K=(K/Total)*100;
               Ch=(Ch/Total)*100;


               Intent intent=new Intent(AssessmentActivity.this,PiechartActivity.class);
               //float number =Float.parseFloat();
               intent.putExtra(EXTRA_PERCENTAGE_M,M);
               intent.putExtra(EXTRA_PERCENTAGE_D,D);
               intent.putExtra(EXTRA_PERCENTAGE_T,T);
               intent.putExtra(EXTRA_PERCENTAGE_CO,Co);
               intent.putExtra(EXTRA_PERCENTAGE_K,K);
               intent.putExtra(EXTRA_PERCENTAGE_CH,Ch);
               startActivity(intent);



           }
       });


    }
}