package com.foxitsample.formfiled;

import com.foxitsample.config.FoxitConst;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
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
    private Button closeOpenButton;
    private Bitmap screenImage;
    private TextView coordinatesTextView;
    InteractionLogic interactionLogic;
    //private static final String fileName = "/mnt/sdcard/HS-268%20Water%20Heater%20-%20Agreement.pdf";
    //private static final String fileName ="/mnt/sdcard/FoxitForm.pdf";
    //public PDFDocument doc=null;
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
    	closeOpenButton = (Button)findViewById(R.id.closeOpenButton);
    	coordinatesTextView=(TextView)findViewById(R.id.coordinatesView);
    	textviewStartX.setText(seekBarStartX.getProgress() + "/" + seekBarStartX.getMax());
    	textviewStartY.setText(seekBarStartX.getProgress() + "/" + seekBarStartY.getMax());
    	textviewImageWidth.setText(seekBarStartX.getProgress() + "/" + seekBarImageWidth.getMax());
    	textviewImageHeight.setText(seekBarStartX.getProgress() + "/" + seekBarImageHeight.getMax());
    	textviewScaleX.setText(seekBarStartX.getProgress() + "/" + seekBarScaleX.getMax());
    	textviewScaleY.setText(seekBarStartX.getProgress() + "/" + seekBarScaleY.getMax());
    	Context context = getApplicationContext();
    	String filePath = context.getResources().getString(R.string._MNT_SDCARD_TEST_DOCUMENT);
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
    	
    	
//        boolean nRet=false;
//		try {
//			nRet = doc.InitFoxitFixedMemory();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//  		if (nRet != true){
//  			return;
//  		}
  		
//  		doc.LoadJbig2Decoder();
//  		doc.LoadJpeg2000Decoder();
//  		doc.LoadCNSFontCMap();
//  		doc.LoadKoreaFontCMap();
//  		doc.LoadJapanFontCMap();
//  		
//  		nRet = doc.InitPDFDoc();
//  		if (nRet != true){
//  			return;
//  		}
//  		
// 		doc.GetPageCounts();
//  		
//  		nRet = doc.InitPDFPage(0);
//  		if (nRet != true){
//  			return;
//  		}
// 
//    	testimage.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// 
//				int actionType=event.getAction()&MotionEvent.ACTION_MASK;
//				int actionId=event.getAction()&MotionEvent.ACTION_POINTER_ID_MASK;
//				actionId=actionId>>8;  
//				
//				PointF point = new EMBJavaSupport().new PointF();
//				point.x = event.getX();
//				point.y = event.getY();	
//				int startx=-seekBar1.getProgress();
//				int starty=-seekBar2.getProgress();
//				int size_x= (int)doc.GetPageSizeX(doc.getCurPDFPageHandler())*seekBar7.getProgress();
//				int size_y= (int)doc.GetPageSizeY(doc.getCurPDFPageHandler())*seekBar8.getProgress();
//				EMBJavaSupport.FPDFPageDeviceToPagePointF(doc.getCurPDFPageHandler(), startx,starty,size_x , size_y, 0, point);
//				//point.x=point.x*seekBar7.getProgress();
//				//point.y=point.y*seekBar8.getProgress();
//				switch(actionType){
//				case MotionEvent.ACTION_MOVE://
//					EMBJavaSupport.FPDFFormFillOnMouseMove(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), 0, point.x, point.y);
//					break;
//				case MotionEvent.ACTION_DOWN:	//	
//					EMBJavaSupport.FPDFFormFillOnMouseMove(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), 0, point.x, point.y);
//					EMBJavaSupport.FPDFFormFillOnLButtonDown(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), 0, point.x, point.y);
//					break;
//				case MotionEvent.ACTION_UP:	//
//					EMBJavaSupport.FPDFFormFillOnLButtonUp(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), 0, point.x, point.y);
//					break;
//				}
//				return true;
//			}
//		});
    	
    	
    	
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
		public Button getCloseOpenButton() {
			return closeOpenButton;
		}
		

}
