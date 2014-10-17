package com.foxitsample.formfiled;

import com.foxitsample.exception.parameterException;
import com.foxitsample.pdfLib.PDFDocument;

import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.PointF;
import FoxitEMBSDK.EMBJavaSupport.RectangleF;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
    private SeekBar seekBar7;
    private TextView textview7;
    private SeekBar seekBar8;
    private TextView textview8;
    private ImageView testimage;
    private Button mainbutton;
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;
    private Button hitButton;
    private Bitmap screenImage;
    private TextView coordinatesTextView;
    DrawingButtonInitializer initializer;
    private static final String fileName = "/mnt/sdcard/HS-268%20Water%20Heater%20-%20Agreement.pdf";
    //private static final String fileName ="/mnt/sdcard/FoxitForm.pdf";
    public PDFDocument doc=null;
    public void onCreate(Bundle savedInstanceState) {    	         
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.test);
		
    	seekBar1=(SeekBar)findViewById(R.id.seekBar1);
    	seekBar2=(SeekBar)findViewById(R.id.seekBar2);
    	seekBar3=(SeekBar)findViewById(R.id.seekBar3);
    	seekBar4=(SeekBar)findViewById(R.id.seekBar4);
    	//seekBar5=(SeekBar)findViewById(R.id.seekBar5);
    	//seekBar6=(SeekBar)findViewById(R.id.seekBar6);
    	seekBar7=(SeekBar)findViewById(R.id.seekBar7);
    	seekBar8=(SeekBar)findViewById(R.id.seekBar8);
    	textview1=(TextView)findViewById(R.id.textview1);
    	textview2=(TextView)findViewById(R.id.textview2);
    	textview3=(TextView)findViewById(R.id.textview3);
    	textview4=(TextView)findViewById(R.id.textview4);
    	//textview5=(TextView)findViewById(R.id.textview5);
    	//textview6=(TextView)findViewById(R.id.textview6);
    	textview7=(TextView)findViewById(R.id.textview7);
    	textview8=(TextView)findViewById(R.id.textview8);
    	testimage=(ImageView)findViewById(R.id.testimage); 
    	mainbutton=(Button)findViewById(R.id.mainbutton);
    	leftButton=(Button)findViewById(R.id.leftButton);
    	rightButton=(Button)findViewById(R.id.rightButton);
    	upButton=(Button)findViewById(R.id.upButton);
    	downButton=(Button)findViewById(R.id.downButton);
    	hitButton = (Button)findViewById(R.id.hitButton);
    	coordinatesTextView=(TextView)findViewById(R.id.coordinatesView);
    	textview1.setText(seekBar1.getProgress() + "/" + seekBar1.getMax());
    	textview2.setText(seekBar1.getProgress() + "/" + seekBar2.getMax());
    	textview3.setText(seekBar1.getProgress() + "/" + seekBar3.getMax());
    	textview4.setText(seekBar1.getProgress() + "/" + seekBar4.getMax());
    	//textview5.setText(seekBar1.getProgress() + "/" + seekBar5.getMax());
    	//textview6.setText(seekBar1.getProgress() + "/" + seekBar6.getMax());
    	textview7.setText(seekBar1.getProgress() + "/" + seekBar7.getMax());
    	textview8.setText(seekBar1.getProgress() + "/" + seekBar8.getMax());
    	doc = new PDFDocument(fileName,this);
    	testimage.setFocusable(true);
    	testimage.setFocusableInTouchMode(true);
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
    	seekBar8.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
    			textview8.setText(progress + "/" + seekBar.getMax()); 		
    		};
    	});
    	
        boolean nRet=false;
		try {
			nRet = doc.InitFoxitFixedMemory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
  		if (nRet != true){
  			return;
  		}
  		
  		doc.LoadJbig2Decoder();
  		doc.LoadJpeg2000Decoder();
  		doc.LoadCNSFontCMap();
  		doc.LoadKoreaFontCMap();
  		doc.LoadJapanFontCMap();
  		
  		nRet = doc.InitPDFDoc();
  		if (nRet != true){
  			return;
  		}
  		
 		int nPageCount = doc.GetPageCounts();
  		
  		nRet = doc.InitPDFPage(0);
  		if (nRet != true){
  			return;
  		}
 
//    	testimage.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
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
    	
    	initializer = DrawingButtonInitializer.generateInitializer(this);
    	
}
        public void createAndroidTextField(String text){
			
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("textValue", text);
			intent.setClass(this, textfieldActivity.class);
			intent.putExtra("key", bundle);
			this.startActivityForResult(intent, 0);			
		}
        public void invalidate(float left, float top, float right, float bottom){
			int l, t, r, b;
			RectangleF rect = new EMBJavaSupport().new RectangleF();
			rect.left = left;
			rect.top = top;
			rect.right = right;
			rect.bottom = bottom;
			int startx=-seekBar1.getProgress();
			int starty=-seekBar2.getProgress();
			int size_x= (int)doc.GetPageSizeX(doc.getCurPDFPageHandler())*seekBar7.getProgress();
			int size_y= (int)doc.GetPageSizeY(doc.getCurPDFPageHandler())*seekBar8.getProgress();
			EMBJavaSupport.FPDFPagePageToDeviceRectF(doc.getCurPDFPageHandler(), startx, starty,size_x , size_y, 0, rect);
			l = (int)rect.left;
			t = (int)rect.top;
			r = (int)rect.right;
			b = (int)rect.bottom;
			Rect rc = new Rect(l,t,r,b);	
			//ImageView dirtyimage=new ImageView(this.getApplicationContext());
			
			
			
			
		}
        
        protected void onDestroy() {
        	// TODO Auto-generated method stub
        				
        		boolean nRet = doc.ClosePDFPage();
        		if (nRet != false){
        			System.exit(0);
        		}
        		
        		nRet = doc.ClosePDFDoc();
        		if (nRet != false){
        			System.exit(0);
        		}
        		
        		nRet = doc.DestroyFoxitFixedMemory();
        		if (nRet != false){
        			System.exit(0);
        		}
        		
        		super.onDestroy();
        		
        }   
        @Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (resultCode == RESULT_OK && requestCode == 0){
				Bundle bundle = data.getBundleExtra("Result");
				String text = bundle.getString("ResultValue");
				EMBJavaSupport.FPDFFormFillOnSetText(doc.getPDFFormHandler(), doc.getCurPDFPageHandler(), text, 0);
			}
			super.onActivityResult(requestCode, resultCode, data);
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
		public SeekBar getSeekBar1() {
			return seekBar1;
		}
		public SeekBar getSeekBar2() {
			return seekBar2;
		}
		public SeekBar getSeekBar3() {
			return seekBar3;
		}
		public SeekBar getSeekBar4() {
			return seekBar4;
		}
		public SeekBar getSeekBar7() {
			return seekBar7;
		}
		public SeekBar getSeekBar8() {
			return seekBar8;
		}
		public void setScreenImage(Bitmap screenImage) {
			this.screenImage = screenImage;
		}
		public PDFDocument getDoc() {
			return doc;
		}
		public TextView getCoordinatesTextView() {
			return coordinatesTextView;
		}


}
