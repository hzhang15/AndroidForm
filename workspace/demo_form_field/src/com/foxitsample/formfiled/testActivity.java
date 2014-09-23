package com.foxitsample.formfiled;

import com.foxitsample.pdfLib.PDFDocument;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class testActivity extends Activity {
	private SeekBar seekBar1;
    private TextView textview1;
    private SeekBar seekBar2;
    private TextView textview2;
    private SeekBar seekBar3;
    private TextView textview3;
    private SeekBar seekBar4;
    private TextView textview4;
    private SeekBar seekBar5;
    private TextView textview5;
    private SeekBar seekBar6;
    private TextView textview6;
    private SeekBar seekBar7;
    private TextView textview7;
    private ImageView testimage;
    private Button mainbutton;
    private PDFDocument doc;
    //private static final String fileName = "/mnt/sdcard/HS-268%20Water%20Heater%20-%20Agreement.pdf";
    private static final String fileName ="/mnt/sdcard/FoxitText.pdf";
    protected void onCreate(Bundle savedInstanceState) {    	         
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.test);
		
    	seekBar1=(SeekBar)findViewById(R.id.seekBar1);
    	seekBar2=(SeekBar)findViewById(R.id.seekBar2);
    	seekBar3=(SeekBar)findViewById(R.id.seekBar3);
    	seekBar4=(SeekBar)findViewById(R.id.seekBar4);
    	//seekBar5=(SeekBar)findViewById(R.id.seekBar5);
    	//seekBar6=(SeekBar)findViewById(R.id.seekBar6);
    	seekBar7=(SeekBar)findViewById(R.id.seekBar7);
    	textview1=(TextView)findViewById(R.id.textview1);
    	textview2=(TextView)findViewById(R.id.textview2);
    	textview3=(TextView)findViewById(R.id.textview3);
    	textview4=(TextView)findViewById(R.id.textview4);
    	//textview5=(TextView)findViewById(R.id.textview5);
    	//textview6=(TextView)findViewById(R.id.textview6);
    	textview7=(TextView)findViewById(R.id.textview7);
    	testimage=(ImageView)findViewById(R.id.testimage); 
    	mainbutton=(Button)findViewById(R.id.mainbutton);
    	textview1.setText(seekBar1.getProgress() + "/" + seekBar1.getMax());
    	textview2.setText(seekBar1.getProgress() + "/" + seekBar2.getMax());
    	textview3.setText(seekBar1.getProgress() + "/" + seekBar3.getMax());
    	textview4.setText(seekBar1.getProgress() + "/" + seekBar4.getMax());
    	//textview5.setText(seekBar1.getProgress() + "/" + seekBar5.getMax());
    	//textview6.setText(seekBar1.getProgress() + "/" + seekBar6.getMax());
    	textview7.setText(seekBar1.getProgress() + "/" + seekBar7.getMax());
    	seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview1.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview2.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview3.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBar4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview4.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	/*seekBar5.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview5.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBar6.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview6.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});*/
    	seekBar7.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		int progress = 0;
    		@Override
    		public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
    		{
    			progress = progresValue;
    		}
    		public void onStartTrackingTouch(SeekBar seekBar)
    		{
    			
    		}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekBar) 
    		{
    			textview7.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	mainbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent intent1 = new Intent(getApplicationContext(),mainActivity.class);
			    //startActivity(intent1);
				
				doc = new PDFDocument(fileName); 	
				Rect rect = new Rect();
				rect.left = seekBar1.getProgress();
				rect.top = seekBar2.getProgress();
				rect.right = seekBar1.getProgress() + seekBar3.getProgress();
				rect.bottom = seekBar2.getProgress() + seekBar4.getProgress();
				int scale=seekBar7.getProgress();
				testimage.setImageBitmap(doc.generateImage(0,scale ,rect));
			}
		});



}
}
