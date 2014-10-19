package com.foxitsample.formfiled;

import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.PointF;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DrawingButtonInitializer {

	final static int STEP = 10;
	public void setActivity(testActivity activity) {
		this.activity = activity;
	}

	testActivity activity;
	float currentX;
	float currentY;
	float touchup;
	

	// only this one to be called
	public static DrawingButtonInitializer generateInitializer(testActivity activity) {
		DrawingButtonInitializer initializer = new DrawingButtonInitializer();
		initializer.setActivity(activity);
		initializer.init();

		return initializer;
	}

	private DrawingButtonInitializer() {

	}

	private void init() {
		activity.getUpButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				coordsUp();
				drawLines();

			}

		});
		
		activity.getDownButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				coordsDown();
				drawLines();

			}

		});
		
		activity.getLeftButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				coordsLeft();
				drawLines();

			}

		});
		
		activity.getRightButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				coordsRight();
				drawLines();

			}

		});
		
		activity.getHitButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/*int startX = activity.getSeekBar1().getProgress();
				int startY = activity.getSeekBar2().getProgress();
				float clickX = startX + currentX;
				float clickY = startY + currentY;
				
				int size_x= (int)activity.getDoc().GetPageSizeX(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar7().getProgress();
				int size_y= (int)activity.getDoc().GetPageSizeY(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar8().getProgress();
				
				String coordinatesText = "touch X:" + clickX + " touch Y:" + clickY;
				activity.getCoordinatesTextView().setText(coordinatesText);
				
				PointF point = new EMBJavaSupport().new PointF();
				point.x = currentX;//clickX;
				point.y = currentY;//clickY;
				
				EMBJavaSupport.FPDFPageDeviceToPagePointF(activity.getDoc().getCurPDFPageHandler(), -startX,-startY,size_x , size_y, 0, point);
		
				EMBJavaSupport.FPDFFormFillOnMouseMove(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
				EMBJavaSupport.FPDFFormFillOnLButtonDown(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);

				EMBJavaSupport.FPDFFormFillOnMouseMove(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
				EMBJavaSupport.FPDFFormFillOnLButtonUp(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
*/
				
			}

		});
		
		activity.getMainbutton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Rect rect = new Rect();
				rect.left = activity.getSeekBar1().getProgress();
				rect.top = activity.getSeekBar2().getProgress();
				rect.right = activity.getSeekBar1().getProgress() + activity.getSeekBar3().getProgress();
				rect.bottom = activity.getSeekBar2().getProgress() + activity.getSeekBar4().getProgress();
				int pwscale=activity.getSeekBar7().getProgress();
				int phscale=activity.getSeekBar8().getProgress();
				/*if (doc.nPDFCurPageHandler!=0)
				{
					doc.ClosePDFPage();	
				}
				if (doc.nPDFDocHandler != 0)
				{   
					doc.ClosePDFDoc();
				    doc.DestroyFoxitFixedMemory();	    
				}*/
				activity.getTestimage().getLayoutParams().width = activity.getSeekBar3().getProgress();
				activity.getTestimage().getLayoutParams().height = activity.getSeekBar4().getProgress();
				activity.setScreenImage(null);
				activity.setScreenImage(activity.getDoc().generateImage(0,pwscale,phscale,rect));
				
				renewCoords();
				drawLines();

			}

		});
		
		activity.getTestimage().setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				currentX =event.getX();
				currentY=event.getY();
				drawLines();
				int startX = activity.getSeekBar1().getProgress();
				int startY = activity.getSeekBar2().getProgress();
				float clickX = startX + currentX;
				float clickY = startY + currentY;
				int size_x= (int)activity.getDoc().GetPageSizeX(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar7().getProgress();
				int size_y= (int)activity.getDoc().GetPageSizeY(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar8().getProgress();
				switch (event.getAction())
				{
					
					case MotionEvent.ACTION_DOWN:

						//activity.getTouchCoordinateView().setText("##X:" + (event.getX()+startX) + "##Y:" + (event.getY()+startY));

						
						
						
						String coordinatesText = "touch X:" + clickX + " touch Y:" + clickY;
						activity.getCoordinatesTextView().setText(coordinatesText);
						
						PointF point = new EMBJavaSupport().new PointF();
						point.x = currentX;//clickX;
						point.y = currentY;//clickY;
						
						EMBJavaSupport.FPDFPageDeviceToPagePointF(activity.getDoc().getCurPDFPageHandler(), -startX,-startY,size_x , size_y, 0, point);
						EMBJavaSupport.FPDFFormFillOnMouseMove(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
						EMBJavaSupport.FPDFFormFillOnLButtonDown(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
					case MotionEvent.ACTION_UP:
						//EMBJavaSupport.FPDFFormFillOnMouseMove(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
						//EMBJavaSupport.FPDFFormFillOnLButtonUp(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);
					case MotionEvent.ACTION_MOVE:
						/*activity.getTouchCoordinateView().setText("##X:" + (event.getX()+startX) + "##Y:" + (event.getY()+startY));
						activity.getTestimage().setImageBitmap(activity.getDoc().getPageBitmapQD(-((int)clickX), (int)clickY, activity.getSeekBar3().getProgress(), activity.getSeekBar4().getProgress(), activity.getSeekBar7().getProgress(), activity.getSeekBar8().getProgress()));
						activity.getTestimage().setImageBitmap(activity.getDoc().getPageBitmap(-((int)clickX), (int)clickY, activity.getSeekBar3().getProgress(), activity.getSeekBar4().getProgress(), activity.getSeekBar7().getProgress(), activity.getSeekBar8().getProgress()));
						//activity.getTouchCoordinateView().setText("##X:"+event.getAxisValue(0)+"##Y"+event.getAxisValue(1));
						//EMBJavaSupport.FPDFFormFillOnMouseMove(activity.getDoc().getPDFFormHandler(), activity.getDoc().getCurPDFPageHandler(), 0, point.x, point.y);*/
				}
				return true;
			}
		});
	}
	
	private void renewCoords()
	{
		int viewWidth = activity.getScreenImage().getWidth();
		int viewHeight = activity.getScreenImage().getHeight();
		
		currentX = viewWidth/2;
		currentY = viewHeight/2;
	}
	
	private void coordsUp()
	{
		if(currentY > STEP) currentY-=STEP;
	}
	
	private void coordsDown()
	{
		int viewHeight = activity.getScreenImage().getHeight();
		if(currentY+STEP < viewHeight) currentY+=STEP;
	}
	
	private void coordsLeft()
	{
		if(currentX > STEP) currentX-=STEP;
	}
	
	private void coordsRight()
	{
		int viewWidth = activity.getScreenImage().getWidth();
		if(currentX+STEP < viewWidth) currentX+=STEP;
	}
	
	private void drawLines() {
		
		int viewWidth = activity.getScreenImage().getWidth();
		int viewHeight = activity.getScreenImage().getHeight();
		
		Bitmap currentScreen = Bitmap.createBitmap(activity.getScreenImage());
		Canvas c = new Canvas(currentScreen);

		Paint p = new Paint();
		p.setColor(Color.RED);
		c.drawLine(0, currentY, viewWidth, currentY, p);
		c.drawLine(currentX, 0, currentX, viewHeight, p);
		activity.getTestimage().setImageBitmap(currentScreen);
	}
	
	public void refresh()
	{
		drawLines();
	}
}
