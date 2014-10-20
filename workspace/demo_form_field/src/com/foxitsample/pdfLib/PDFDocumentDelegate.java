package com.foxitsample.pdfLib;

import android.graphics.Bitmap;
import android.graphics.Rect;
import FoxitEMBSDK.EMBCallbackUpdateDelegate;

public interface PDFDocumentDelegate {
	void LoadDocumentFromPath(String path);
	void closeDocument();
	int getCurrentPageIndex();
	void setCurrentPageIndex(int currentPageIndex);
	void HitCurrentPageAtPoint(float x, float y);
	void setEMBCallbackUpdateDelegate(EMBCallbackUpdateDelegate delegate);
	/*
	 * will apply to all the pages
	 */
	void setPageSize(float x, float y);
	float getPageWidth();
	float getPageHeight();
	Bitmap getCurrentPageBitmapFromRect(Rect rect);
	
}
