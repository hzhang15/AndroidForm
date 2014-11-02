package com.foxitsample.pdfLib;

import com.foxit.gsdk.pdf.PDFPage;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

public interface FoxitPDFPageDelegate {
	PDFPage getSDKPage();
	void setPageSize(int pageWidth, int pageHeight);
	Bitmap getBitmapFromRect(Rect rect);
	//void click(float x, float y);
	FoxitPDFFormField getFormFieldAt(float x, float y);
	void flatten();
	Rect transferPDFRect(RectF pdfRect);
	//Point transferPDFPoint(PointF point);
	void close();
	//void setTextInActiveFormField(String text);
}
