package com.foxitsample.pdfLib;

import FoxitEMBSDK.EMBJavaSupport.PointF;
import FoxitEMBSDK.EMBJavaSupport.RectangleF;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public interface PDFPageDelegate {
	int getPageHandler();
	void setPageSize(int pageWidth, int pageHeight);
	Bitmap getBitmapFromRect(Rect rect);
	void click(float x, float y);
	Rect transferPDFRect(RectangleF pdfRect);
	Point transferPDFPoint(PointF point);
	void setTextInActiveFormField(String text);
	void close();
}
