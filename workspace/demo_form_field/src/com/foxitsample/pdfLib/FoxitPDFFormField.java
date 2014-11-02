package com.foxitsample.pdfLib;

import android.graphics.Rect;
import android.graphics.RectF;

import com.foxit.gsdk.PDFException;
import com.foxit.gsdk.pdf.form.PDFForm;
import com.foxit.gsdk.pdf.form.PDFFormControl;
import com.foxit.gsdk.pdf.form.PDFFormField;

public class FoxitPDFFormField implements FoxitPDFFormFieldDelegate {
	private PDFFormField formField;
	private PDFFormControl formControl;
	private FoxitPDFPageDelegate pageProvider;
	//private FoxitPDFFormEnvironmentProviderDelegate formProvider;
	
	FoxitPDFFormField(PDFFormControl formControl, FoxitPDFFormEnvironmentProviderDelegate formProvider, FoxitPDFPageDelegate pageProvider)
	{
		this.formControl = formControl;
		this.pageProvider = pageProvider;
		
		PDFForm form = formProvider.getSDKForm();
		try {
			String fieldName = formControl.getFieldName();
			formField = form.getField(fieldName, 0);
		} catch (PDFException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Rect getRect() {
		
		Rect rect = null;
		try {
			RectF rectF = formControl.getRect();
			rect = pageProvider.transferPDFRect(rectF);
			
		} catch (PDFException e) {
			e.printStackTrace();
		}
		
		return rect;
	}

	@Override
	public void setText(String text) {
		try {
			formField.setValue(text);
		} catch (PDFException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getText() {
		
		String ret = null;
		
		try {
			ret = formField.getValue();
		} catch (PDFException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	
}
