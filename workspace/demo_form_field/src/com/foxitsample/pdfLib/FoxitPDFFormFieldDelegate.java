package com.foxitsample.pdfLib;

import android.graphics.Rect;

public interface FoxitPDFFormFieldDelegate {
	public Rect getRect();//relative to its page
	public void setText(String text);
	public String getText();
}
