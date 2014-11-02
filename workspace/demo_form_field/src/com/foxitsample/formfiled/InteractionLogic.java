package com.foxitsample.formfiled;

//import com.foxitsample.config.FoxitAlgorithm;
import com.foxitsample.config.FoxitConst;
import com.foxitsample.pdfLib.FoxitPDFDocument;
import com.foxitsample.pdfLib.FoxitPDFFormField;
import com.foxitsample.pdfLib.FoxitPDFPage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;

public class InteractionLogic {

	final static int STEP = 10;
	public void setActivity(testActivity activity) {
		this.activity = activity;
	}

	testActivity activity;
	float currentX;
	float currentY;
	private FoxitPDFDocument doc=null;
	private FoxitPDFFormField currentFormField = null;
	
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
		
		doc = FoxitPDFDocument.generatePDFDocumentFromPath(testDocumentPath);//new PDFDocument(testDocumentPath, this);
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
				
				PointF point = new PointF();
				point.x = clickX;
				point.y = clickY;
								
				//Rect rect = new Rect(startX, startY, startX+imageWidth ,startY+imageHeight);
				
				//doc.hitCurrentPageAtPointInRect(point.x, point.y, rect);
				
				FoxitPDFPage page = doc.getPageAtIndex(0);
				FoxitPDFFormField formField = page.getFormFieldAt(point.x, point.y);
				
				if(formField != null)
				{
					currentFormField = formField;
					activity.createAndroidTextField(formField.getText());
				}
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
				FoxitPDFPage page = doc.getPageAtIndex(0);
				Bitmap bitmap = page.getBitmapFromRect(rect);
				activity.setScreenImage(bitmap);
				
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
	
//	private void updateScreenImageInPDFCoordinates(float left, float top, float right, float bottom)
//	{
//		RectangleF rect = new EMBJavaSupport().new RectangleF();
//		
//		rect.left = left;
//		rect.top = top;
//		rect.right = right;
//		rect.bottom = bottom;
//		int startX=activity.getSeekBarStartX().getProgress();
//		int startY=activity.getSeekBarStartY().getProgress();
//		FoxitPDFPage page = doc.getPageAtIndex(0);
//		Rect deviceRect = page.transferPDFRect(rect);
//		Rect drawingAreaRect = getDrawingAreaRect();
//		
//		FoxitAlgorithm.interceptFirstRect(deviceRect, drawingAreaRect);
//		
//		if(deviceRect.left == 0 && deviceRect.right == 0 && deviceRect.top == 0 && deviceRect.bottom == 0)
//		return;
//		
//		Bitmap bitmap = page.getBitmapFromRect(deviceRect);
//		
//		int width=bitmap.getWidth();
//		int height=bitmap.getHeight();
//		int[] dirtypixels=new int[width*height];
//		bitmap.getPixels(dirtypixels, 0, width, 0, 0, width, height);
//		
//		int smallImageInternalLeft = deviceRect.left - startX;
//		int smallImageInternalTop = deviceRect.top - startY; 
//		activity.getScreenImage().setPixels(dirtypixels, 0, width, smallImageInternalLeft, smallImageInternalTop, width, height);
//		
//	}
	
	private void updateScreenImageAfterFormFilling()
	{
		
		int startX=activity.getSeekBarStartX().getProgress();
		int startY=activity.getSeekBarStartY().getProgress();
		Rect rect = currentFormField.getRect();
		FoxitPDFPage page = doc.getPageAtIndex(0);
		Bitmap partialBitmap = page.getBitmapFromRect(rect);
		int width=partialBitmap.getWidth();
		int height=partialBitmap.getHeight();
		int[] dirtypixels=new int[width*height];
		partialBitmap.getPixels(dirtypixels, 0, width, 0, 0, width, height);
		
		int smallImageInternalLeft = rect.left - startX;
		int smallImageInternalTop = rect.top - startY;
		activity.getScreenImage().setPixels(dirtypixels, 0, width, smallImageInternalLeft, smallImageInternalTop, width, height);

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

	public void closeCurrentDocAndOpen(String filePath) {
		doc.close();
		doc = FoxitPDFDocument.generatePDFDocumentFromPath(filePath);
	}
	
	public void saveCurrentDoc(String filePath){
		doc.saveTo(filePath);
	}
	
	public void flattenCurrentDoc(){
		doc.flattenAllPages();
	}
	
	public void putTextToCurrentFormField(String text)
	{
		assert(currentFormField!=null);
		
		currentFormField.setText(text);
		
		updateScreenImageAfterFormFilling();
	}

}
