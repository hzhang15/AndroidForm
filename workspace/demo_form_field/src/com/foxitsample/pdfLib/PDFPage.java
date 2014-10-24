package com.foxitsample.pdfLib;

import java.nio.ByteBuffer;

import com.foxitsample.exception.parameterException;
import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.PointF;
import FoxitEMBSDK.EMBJavaSupport.RectangleF;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.Rect;

public class PDFPage implements PDFPageDelegate {
	
	
	PDFDocumentDelegate documentProvider;
	PDFFormEnvironmentProviderDelegate formProvider;
	int pageHandler;
	int pageWidth; //data type: refer to iOS
	int pageHeight;
	
	public PDFPage(PDFDocumentDelegate documentProvider,
			PDFFormEnvironmentProviderDelegate formProvider, int pageIndex) {
		super();
		this.documentProvider = documentProvider;
		this.formProvider = formProvider;
		init(pageIndex);
	}
	
	void init(int pageIndex)
	{
		
		int documentHandler = documentProvider.getDocumentHandler();
		int formHandler = formProvider.getPDFFormHandler();
		
		try {
			pageHandler = EMBJavaSupport.FPDFPageLoad(documentHandler, pageIndex);
		} catch (parameterException e1) {
			e1.printStackTrace();
		}	
		try {
			EMBJavaSupport.FPDFPageStartParse(pageHandler, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		EMBJavaSupport.FPDFFormFillOnAfterLoadPage(formHandler, pageHandler);

	}

	@Override
	public void setPageSize(int pageWidth, int pageHeight) {
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
	}
	
	
	//bitmap will be managed by external manager
	@Override
	public Bitmap getBitmapFromRect(Rect rect) {
		
		int formHandler = formProvider.getPDFFormHandler();
		Bitmap bitmap;
		//TODO might waste memory iOS uses 24 bit and 16 is even suggested
		
		//startX, startY, width, height please all use int
		int width = rect.right-rect.left;
		int height = rect.bottom-rect.top;
		bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		int dib;
		EMBJavaSupport.Rectangle clip = new EMBJavaSupport().new Rectangle();
		clip.left = 0;
		clip.right = width;
		clip.top = 0;
		clip.bottom = height;
		
		try{
			dib = EMBJavaSupport.FSBitmapCreate(width, height, 7, null, 0);
			EMBJavaSupport.FSBitmapFillColor(dib, 0xff);
			EMBJavaSupport.FPDFRenderPageStart(dib, pageHandler, -rect.left, -rect.top, pageWidth, pageHeight, 0, 0, clip, 0);
			EMBJavaSupport.FPDFFormFillDraw(formHandler, dib, pageHandler, -rect.left, -rect.top, pageWidth, pageHeight, 0, 0);
			
			byte[] bitmapBuffer = EMBJavaSupport.FSBitmapGetBuffer(dib);
			ByteBuffer bitmapByteBuffer = ByteBuffer.wrap(bitmapBuffer);
			bitmap.copyPixelsFromBuffer(bitmapByteBuffer);
			
			EMBJavaSupport.FSBitmapDestroy(dib);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return bitmap;
	}

	@Override
	public void click(float x, float y) {
		
		EMBJavaSupport.PointF point = new EMBJavaSupport().new PointF();
		point.x = x;
		point.y = y;
		
		int pdfFormHandler = formProvider.getPDFFormHandler();
		
		EMBJavaSupport.FPDFFormFillOnKillFocus(pdfFormHandler);
		EMBJavaSupport.FPDFPageDeviceToPagePointF(pageHandler, 0, 0, (int)pageWidth, (int)pageHeight, 0, point);
		
		EMBJavaSupport.FPDFFormFillOnMouseMove(pdfFormHandler, pageHandler, 0, point.x, point.y);
		EMBJavaSupport.FPDFFormFillOnLButtonDown(pdfFormHandler, pageHandler, 0, point.x, point.y);

		EMBJavaSupport.FPDFFormFillOnMouseMove(pdfFormHandler, pageHandler, 0, point.x, point.y);
		EMBJavaSupport.FPDFFormFillOnLButtonUp(pdfFormHandler, pageHandler, 0, point.x, point.y);
	}

	@Override
	public int getPageHandler() {
		return pageHandler;
	}

	@Override
	public void close() {
		
	//assuming already asserted since the algorithm now always init
		
		try {
			EMBJavaSupport.FPDFPageClose(pageHandler);
		} catch (parameterException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Rect transferPDFRect(RectangleF pdfRect) {
		EMBJavaSupport.FPDFPagePageToDeviceRectF(pageHandler, 0, 0, pageWidth , pageHeight, 0, pdfRect);
		Rect rect = new Rect();
		rect.left = (int) pdfRect.left;
		rect.right = (int) pdfRect.right;
		rect.top = (int) pdfRect.top;
		rect.bottom = (int) pdfRect.bottom;
		
		return rect;
	}

	@Override
	public void setTextInActiveFormField(String text) {
		
		EMBJavaSupport.FPDFFormFillOnSetText(formProvider.getPDFFormHandler(), pageHandler, text, 0);
		EMBJavaSupport.FPDFFormFillOnKillFocus(formProvider.getPDFFormHandler());
		
	}
 
	@Override
	public Point transferPDFPoint(PointF point) {
		// TODO Auto-generated method stub
		
		EMBJavaSupport.FPDFPagePageToDevicePointF(pageHandler, 0, 0, pageWidth , pageHeight, 0, point);
		Point p = new Point();
		p.x = (int)point.x;
		p.y = (int)point.y;
		return p;
	}

}
