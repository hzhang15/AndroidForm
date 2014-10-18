package com.foxitsample.formfiled;

import FoxitEMBSDK.EMBCallbackUpdateDelegate;
import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.PointF;
import FoxitEMBSDK.EMBJavaSupport.RectangleF;
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

public class InteractionLogic implements EMBCallbackUpdateDelegate {

	final static int STEP = 10;
	public void setActivity(testActivity activity) {
		this.activity = activity;
	}

	testActivity activity;
	float currentX;
	float currentY;

	// only this one to be called
	public static InteractionLogic generateLogic(testActivity activity) {
		InteractionLogic initializer = new InteractionLogic();
		initializer.setActivity(activity);
		initializer.init();

		return initializer;
	}

	private InteractionLogic() {

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
				
				int startX = activity.getSeekBar1().getProgress();
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
	
	public void updateScreenImageInPDFCoordinates(float left, float top, float right, float bottom)
	{
		int l, t, r, b;
		RectangleF rect = new EMBJavaSupport().new RectangleF();
		rect.left = left;
		rect.top = top;
		rect.right = right;
		rect.bottom = bottom;
		int startx=-activity.getSeekBar1().getProgress();
		int starty=-activity.getSeekBar2().getProgress();
		int size_x= (int)activity.getDoc().GetPageSizeX(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar7().getProgress();
		int size_y= (int)activity.getDoc().GetPageSizeY(activity.getDoc().getCurPDFPageHandler())*activity.getSeekBar8().getProgress();
		EMBJavaSupport.FPDFPagePageToDeviceRectF(activity.getDoc().getCurPDFPageHandler(), startx, starty,size_x , size_y, 0, rect);
		l = (int)rect.left;
		//t = (int)rect.bottom;
		r = (int)rect.right;
		//b = (int)rect.top;
		b = (int)rect.bottom;
		t = (int)rect.top;	
		/*
		Canvas tempcanvas=new Canvas(dirtybm);
		tempcanvas.drawBitmap(dirtybm,0,0,null);
		testimage.setImageDrawable(new BitmapDrawable(getResources(), dirtybm));
		*/
		//surfaceview.setDirtyRect(l, t, r, b);
		//surfaceview.setDirtyBitmap(doc.getDirtyBitmap(rc, seekBar3.getProgress(),seekBar4.getProgress()));
		//surfaceview.OnDraw();
		if(l>activity.getSeekBar3().getProgress()||r<0)
		{
			return;
		}
		if(t>activity.getSeekBar4().getProgress()||b<0)
		{
			return;
		}
		if(r>activity.getSeekBar3().getProgress())
		{
			r=activity.getSeekBar3().getProgress();
		}
		if(b>activity.getSeekBar4().getProgress())
		{
			b=activity.getSeekBar4().getProgress();
		}
		if(l<0)
		{
			l=0;
		}
		if(t<0)
		{
			t=0;
		}
		Rect rc = new Rect(l,t,r,b);
		Bitmap dirtybm=activity.getDoc().getDirtyBitmap(activity.getSeekBar1().getProgress(),activity.getSeekBar2().getProgress(),rc,activity.getSeekBar7().getProgress(), activity.getSeekBar8().getProgress());
		int width=dirtybm.getWidth();
		int height=dirtybm.getHeight();
		int[] dirtypixels=new int[width*height];
		dirtybm.getPixels(dirtypixels, 0, width, 0, 0, width, height);
		activity.getScreenImage().setPixels(dirtypixels, 0, width, l, t, width, height);
	}

	@Override
	public void refresh(int page, float left, float top, float right,
			float bottom) {
		
		updateScreenImageInPDFCoordinates(left, top, right, bottom);
		
	}

	@Override
	public int getPageHandlerFromIndex(int documentHandler, int pageIndex) {
		// TODO Auto-generated method stub
		
		
		
		return activity.getDoc().getPageHandler(documentHandler);
	}

	@Override
	public int getCurrentPageHandler(int documentHandler) {
		// TODO Auto-generated method stub
		return activity.getDoc().getPageHandler(documentHandler);
	}

	@Override
	public void bringUpTextField(int field, String focustext, int nTextLen) {
		// TODO Auto-generated method stub
		activity.createAndroidTextField(focustext);
	}
}
