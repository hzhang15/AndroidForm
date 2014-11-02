package com.foxitsample.formfiled;

import com.foxitsample.config.FoxitConst;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class testActivity extends Activity {
	private SeekBar seekBarStartX;
    private TextView textviewStartX;
    private SeekBar seekBarStartY;
    private TextView textviewStartY;
    private SeekBar seekBarImageWidth;
    private TextView textviewImageWidth;
    private SeekBar seekBarImageHeight;
    private TextView textviewImageHeight;
    private SeekBar seekBarScaleX;
    private TextView textviewScaleX;
    private SeekBar seekBarScaleY;
    private TextView textviewScaleY;
    private ImageView testimage;
    private Button mainbutton;
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;
    private Button hitButton;
    private Button closeTestButton;
    private Bitmap screenImage;
    private TextView coordinatesTextView;
    private RadioGroup docSelectRadioGroup;
    InteractionLogic interactionLogic;


    
    public void onCreate(Bundle savedInstanceState) {    	         
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.test);
		
    	seekBarStartX=(SeekBar)findViewById(R.id.seekBar1);
    	seekBarStartY=(SeekBar)findViewById(R.id.seekBar2);
    	seekBarImageWidth=(SeekBar)findViewById(R.id.seekBar3);
    	seekBarImageHeight=(SeekBar)findViewById(R.id.seekBar4);
    	seekBarScaleX=(SeekBar)findViewById(R.id.seekBar7);
    	seekBarScaleY=(SeekBar)findViewById(R.id.seekBar8);
    	textviewStartX=(TextView)findViewById(R.id.textview1);
    	textviewStartY=(TextView)findViewById(R.id.textview2);
    	textviewImageWidth=(TextView)findViewById(R.id.textview3);
    	textviewImageHeight=(TextView)findViewById(R.id.textview4);
    	textviewScaleX=(TextView)findViewById(R.id.textview7);
    	textviewScaleY=(TextView)findViewById(R.id.textview8);
    	testimage=(ImageView)findViewById(R.id.testimage); 
    	mainbutton=(Button)findViewById(R.id.mainbutton);
    	leftButton=(Button)findViewById(R.id.leftButton);
    	rightButton=(Button)findViewById(R.id.rightButton);
    	upButton=(Button)findViewById(R.id.upButton);
    	downButton=(Button)findViewById(R.id.downButton);
    	hitButton = (Button)findViewById(R.id.hitButton);
    	closeTestButton = (Button)findViewById(R.id.closeTestButton);
    	coordinatesTextView=(TextView)findViewById(R.id.coordinatesView);
    	docSelectRadioGroup = (RadioGroup)findViewById(R.id.docRadioGroup);
    	textviewStartX.setText(seekBarStartX.getProgress() + "/" + seekBarStartX.getMax());
    	textviewStartY.setText(seekBarStartX.getProgress() + "/" + seekBarStartY.getMax());
    	textviewImageWidth.setText(seekBarStartX.getProgress() + "/" + seekBarImageWidth.getMax());
    	textviewImageHeight.setText(seekBarStartX.getProgress() + "/" + seekBarImageHeight.getMax());
    	textviewScaleX.setText(seekBarStartX.getProgress() + "/" + seekBarScaleX.getMax());
    	textviewScaleY.setText(seekBarStartX.getProgress() + "/" + seekBarScaleY.getMax());
    	final Context context = getApplicationContext();
    	String filePath = context.getResources().getString(R.string._MNT_SDCARD_TEST_DOCUMENT1);
    	interactionLogic = InteractionLogic.generateLogic(this, filePath);
    	Display display = getWindowManager().getDefaultDisplay();
    	Point point = new Point();
    	display.getSize(point);
    	FoxitConst.SCREEN_WIDTH =  point.x;
    	FoxitConst.SCREEN_HEIGHT = point.y;
    	testimage.setFocusable(true);
    	testimage.setFocusableInTouchMode(true);
    	seekBarStartX.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewStartX.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBarStartY.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewStartY.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBarImageWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewImageWidth.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBarImageHeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewImageHeight.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBarScaleX.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewScaleX.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	seekBarScaleY.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textviewScaleY.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	closeTestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
		    	String filePath = context.getResources().getString(R.string._MNT_SDCARD_TEST_DOCUMENT1);
				interactionLogic.closeCurrentDocAndOpen(filePath);
			}
		});
    	docSelectRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId)
				{
					case R.id.doc1Radio:
						interactionLogic.closeCurrentDocAndOpen(context.getString(R.string._MNT_SDCARD_TEST_DOCUMENT1));
						break;
					case R.id.doc2Radio:
						interactionLogic.closeCurrentDocAndOpen(context.getString(R.string._MNT_SDCARD_TEST_DOCUMENT2));
						break;
					case R.id.savedRadio:
						interactionLogic.saveCurrentDoc(context.getString(R.string._DATA_DATA_TEST_SAVED_DOCUMENT));
						interactionLogic.closeCurrentDocAndOpen(context.getString(R.string._DATA_DATA_TEST_SAVED_DOCUMENT));
						break;
					case R.id.finaledRadio:
						interactionLogic.flattenCurrentDoc();
						interactionLogic.saveCurrentDoc(context.getString(R.string._DATA_DATA_TEST_FINALED_DOCUMENT));
						interactionLogic.closeCurrentDocAndOpen(context.getString(R.string._DATA_DATA_TEST_FINALED_DOCUMENT));
						break;

				}
			}
		});

    	
    	
    	
}
        public void createAndroidTextField(String text){
			
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("textValue", text);
			intent.setClass(this, textfieldActivity.class);
			intent.putExtra("key", bundle);
			this.startActivityForResult(intent, 0);			
		}
        
        protected void onDestroy() {
        		
        		super.onDestroy();
        		
        		interactionLogic.sDKRelease();
        		
        }   
        @Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (resultCode == RESULT_OK && requestCode == 0){
				Bundle bundle = data.getBundleExtra("Result");
				String text = bundle.getString("ResultValue");
				//EMBJavaSupport.FPDFFormFillOnSetText(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), text, 0);
				interactionLogic.putTextToCurrentFormField(text);
				interactionLogic.refresh();
			}
			super.onActivityResult(requestCode, resultCode, data);
			//EMBJavaSupport.FPDFFormFillOnKillFocus(doc.getCurPDFPageHandler());
			//testimage.setImageBitmap(screenImage);
			
		}
        
		public Bitmap getScreenImage() {
			return screenImage;
		}
		public Button getLeftButton() {
			return leftButton;
		}
		public Button getRightButton() {
			return rightButton;
		}
		public Button getUpButton() {
			return upButton;
		}
		public Button getDownButton() {
			return downButton;
		}
		public Button getHitButton() {
			return hitButton;
		}
		public ImageView getTestimage() {
			return testimage;
		}
		public Button getMainbutton() {
			return mainbutton;
		}
		public SeekBar getSeekBarStartX() {
			return seekBarStartX;
		}
		public SeekBar getSeekBarStartY() {
			return seekBarStartY;
		}
		public SeekBar getSeekBarImageWidth() {
			return seekBarImageWidth;
		}
		public SeekBar getSeekBarImageHeight() {
			return seekBarImageHeight;
		}
		public SeekBar getSeekBarScaleX() {
			return seekBarScaleX;
		}
		public SeekBar getSeekBarScaleY() {
			return seekBarScaleY;
		}
		public void setScreenImage(Bitmap screenImage) {
			this.screenImage = screenImage;
		}
		public TextView getCoordinatesTextView() {
			return coordinatesTextView;
		}


}
