package com.foxitsample.formfiled;

import com.foxitsample.config.FoxitAlgorithm;
import com.foxitsample.config.FoxitConst;
import com.foxitsample.pdfLib.PDFDocument;
import com.foxitsample.pdfLib.PDFPage;

import FoxitEMBSDK.EMBCallbackUpdateDelegate;
import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.PointF;
import FoxitEMBSDK.EMBJavaSupport.RectangleF;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class InteractionLogic implements EMBCallbackUpdateDelegate {

	final static int STEP = 10;
	public void setActivity(testActivity activity) {
		this.activity = activity;
	}

	testActivity activity;
	float currentX;
	float currentY;
	private PDFDocument doc=null;
	protected String documentPath;
	
	// only this one to be called
	public static InteractionLogic generateLogic(testActivity activity, String testDocumentPath) {
		InteractionLogic initializer = new InteractionLogic();
		initializer.setActivity(activity);
		initializer.init(testDocumentPath);

		return initializer;
	}

	private InteractionLogic() {

	}

	private void init(String testDocumentPath) {
		
		documentPath = testDocumentPath;
		doc = PDFDocument.generatePDFDocumentFromPath(testDocumentPath, this);//new PDFDocument(testDocumentPath, this);
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
				
				int startX = activity.getSeekBarStartX().getProgress();
				int startY = activity.getSeekBarStartY().getProgress();
				
				//int imageWidth = activity.getSeekBarImageWidth().getProgress();
				//int imageHeight = activity.getSeekBarImageHeight().getProgress();
				
				float clickX = startX + currentX;
				float clickY = startY + currentY;
				
				int sizeX = FoxitConst.SCREEN_WIDTH * activity.getSeekBarScaleX().getProgress();
				int sizeY = FoxitConst.SCREEN_HEIGHT * activity.getSeekBarScaleY().getProgress();
				
				doc.setAllPageSizes(sizeX, sizeY);
				
				String coordinatesText = "touch X:" + clickX + " touch Y:" + clickY;
				activity.getCoordinatesTextView().setText(coordinatesText);
				
				PointF point = new EMBJavaSupport().new PointF();
				point.x = clickX;
				point.y = clickY;
								
				//Rect rect = new Rect(startX, startY, startX+imageWidth ,startY+imageHeight);
				
				//doc.hitCurrentPageAtPointInRect(point.x, point.y, rect);
				
				PDFPage page = doc.getPageAtIndex(0);
				page.click(point.x, point.y);
				
			}

		});
		
		activity.getMainbutton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Rect rect = new Rect();
				rect.left = activity.getSeekBarStartX().getProgress();
				rect.top = activity.getSeekBarStartY().getProgress();
				rect.right = activity.getSeekBarStartX().getProgress() + activity.getSeekBarImageWidth().getProgress();
				rect.bottom = activity.getSeekBarStartY().getProgress() + activity.getSeekBarImageHeight().getProgress();
				int scaleX = activity.getSeekBarScaleX().getProgress();
				int scaleY = activity.getSeekBarScaleY().getProgress();
			
				int sizeX = FoxitConst.SCREEN_WIDTH * scaleX;
				int sizeY = FoxitConst.SCREEN_HEIGHT * scaleY;
				
				doc.setAllPageSizes(sizeX, sizeY);

				activity.getTestimage().getLayoutParams().width = activity.getSeekBarImageWidth().getProgress();
				activity.getTestimage().getLayoutParams().height = activity.getSeekBarImageHeight().getProgress();
				PDFPage page = doc.getPageAtIndex(0);
				Bitmap bitmap = page.getBitmapFromRect(rect);
				activity.setScreenImage(bitmap);
				
				renewCoords();
				drawLines();

			}

		});
		
		activity.getCloseOpenButton().setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doc.close();
				doc = PDFDocument.generatePDFDocumentFromPath(documentPath, InteractionLogic.this);
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
	
	private void updateScreenImageInPDFCoordinates(float left, float top, float right, float bottom)
	{
		RectangleF rect = new EMBJavaSupport().new RectangleF();
		
		rect.left = left;
		rect.top = top;
		rect.right = right;
		rect.bottom = bottom;
		int startX=activity.getSeekBarStartX().getProgress();
		int startY=activity.getSeekBarStartY().getProgress();
		//int size_x= (int)FoxitConst.SCREEN_WIDTH*activity.getSeekBarScaleX().getProgress();
		//int size_y= (int)FoxitConst.SCREEN_HEIGHT*activity.getSeekBarScaleY().getProgress();
		//EMBJavaSupport.FPDFPagePageToDeviceRectF(activity.getDoc().getCurPDFPageHandler(), startx, starty,size_x , size_y, 0, rect);
		
		PDFPage page = doc.getPageAtIndex(0);
		Rect deviceRect = page.transferPDFRect(rect);
		Rect drawingAreaRect = getDrawingAreaRect();
		
		PointF leftTop = new EMBJavaSupport().new PointF();
		PointF rightBottom = new EMBJavaSupport().new PointF();
		leftTop.x = left;
		leftTop.y = bottom;
		rightBottom.x = right;
		rightBottom.y = top;
		
		Point deviceLeftUp = page.transferPDFPoint(leftTop);
		Point deviceRightBottom = page.transferPDFPoint(rightBottom);
		
		Log.i("InterationLogic", "left up" + deviceLeftUp.x + " "+ deviceLeftUp.y);
		Log.i("InteractionLogic", "right bottom" + deviceRightBottom.x + " " + deviceRightBottom.y);
		
		FoxitAlgorithm.interceptFirstRect(deviceRect, drawingAreaRect);
		
		if(deviceRect.left == 0 && deviceRect.right == 0 && deviceRect.top == 0 && deviceRect.bottom == 0)
		return;
		
		Bitmap bitmap = page.getBitmapFromRect(deviceRect);
		
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();
		int[] dirtypixels=new int[width*height];
		bitmap.getPixels(dirtypixels, 0, width, 0, 0, width, height);
		
		int smallImageInternalLeft = deviceRect.left - startX;
		int smallImageInternalTop = deviceRect.top - startY; 
		activity.getScreenImage().setPixels(dirtypixels, 0, width, smallImageInternalLeft, smallImageInternalTop, width, height);
		
	}

	@Override
	public void refresh(int page, float left, float top, float right,
			float bottom) {
		
		updateScreenImageInPDFCoordinates(left, top, right, bottom);
		
	}

	@Override
	public int getPageHandlerFromIndex(int documentHandler, int pageIndex) {
		
		//return activity.getDoc().getPageHandler(documentHandler);
		return doc.getPageHandlerFormIndex(pageIndex);
		
	}

	@Override
	public int getCurrentPageHandler(int documentHandler) {
		//return activity.getDoc().getPageHandler(documentHandler);
		
		PDFPage page = doc.getPageAtIndex(0);
		return page.getPageHandler();
		//return doc.getCurrentPDFPageHandler();
	}

	@Override
	public void bringUpTextField(int field, String focustext, int nTextLen) {
		activity.createAndroidTextField(focustext);
	}
	
	Rect getDrawingAreaRect()
	{
		int startX = activity.getSeekBarStartX().getProgress();
		int startY = activity.getSeekBarStartY().getProgress();
		
		int imageWidth = activity.getSeekBarImageWidth().getProgress();
		int imageHeight = activity.getSeekBarImageHeight().getProgress();
		
		Rect rect = new Rect();
		rect.left = startX;
		rect.right = startX + imageWidth;
		rect.top = startY;
		rect.bottom = startY + imageHeight;
		
		return rect;
	}
	
	public void sDKRelease()
	{
		doc.close();
	}

	@Override
	public void putTextToCurrentFormField(String text) {
		PDFPage page = doc.getPageAtIndex(0);
		page.setTextInActiveFormField(text);
	}
}
