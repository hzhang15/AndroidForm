package com.foxitsample.pdfLib;

import com.foxit.gsdk.PDFException;
import com.foxit.gsdk.pdf.PDFDocument;
import com.foxit.gsdk.pdf.PDFPage;
import com.foxit.gsdk.pdf.Progress;
import com.foxit.gsdk.pdf.RenderContext;
import com.foxit.gsdk.pdf.Renderer;
import com.foxit.gsdk.pdf.form.PDFForm;
import com.foxit.gsdk.pdf.form.PDFFormControl;
import com.foxit.gsdk.pdf.form.PDFFormField;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

public class FoxitPDFPage implements FoxitPDFPageDelegate {
	
	
	FoxitPDFDocumentDelegate documentProvider;
	FoxitPDFFormEnvironmentProviderDelegate formProvider;
	PDFPage page;
	int pageWidth; //data type: refer to iOS
	int pageHeight;
	
	
	public FoxitPDFPage(FoxitPDFDocumentDelegate documentProvider,
			FoxitPDFFormEnvironmentProviderDelegate formProvider, int pageIndex) {
		super();
		this.documentProvider = documentProvider;
		this.formProvider = formProvider;
		init(pageIndex);
	}
	
	void init(int pageIndex)
	{
		
		PDFDocument sDKDocument = documentProvider.getSDKDocument();
		//int formHandler = formProvider.getPDFFormHandler();
		
		try {
			page = sDKDocument.getPage(pageIndex);
			
			Progress progress = page.startParse(PDFPage.PARSEFLAG_NORMAL);
			
			if(progress != null)
			{
				progress.continueProgress(0);
			}
			
			progress.release();
			
			page.loadAnnots();
			
		} catch (PDFException e) {
			e.printStackTrace();
		}
		
		
		//EMBJavaSupport.FPDFFormFillOnAfterLoadPage(formHandler, pageHandler);

	}

	@Override
	public void setPageSize(int pageWidth, int pageHeight) {
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
	
	}
	
	
	//bitmap will be managed by external manager
	@Override
	public Bitmap getBitmapFromRect(Rect rect) {
		
		//int formHandler = formProvider.getPDFFormHandler();
		Bitmap bitmap;
		//TODO might waste memory iOS uses 24 bit and 16 is even suggested
		
		//startX, startY, width, height please all use int
		int width = rect.right-rect.left;
		int height = rect.bottom-rect.top;
		bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		bitmap.eraseColor(Color.WHITE);

		
		try{
		
			
		Matrix matrix = page.getDisplayMatrix(-rect.left, -rect.top, pageWidth, pageHeight, PDFPage.ROTATION_0);//big measure
		//matrix.postScale(0, -1);
		
		Renderer renderer = Renderer.create(bitmap);
		//renderer.setClipRect(rect);//big measure
		RenderContext renderContext = RenderContext.create();
		renderContext.setFlags(RenderContext.FLAG_ANNOT);
		renderContext.setMatrix(matrix);
		
		Progress progress = page.startRender(renderContext, renderer, PDFPage.RENDERFLAG_NORMAL);
		if (progress != null)
		{
			progress.continueProgress(0);
		}
		progress.release();
		renderContext.release();
		renderer.release();
		
		}
		catch(PDFException e)
		{
			e.printStackTrace();
		}
		
		return bitmap;
	}

	@Override
	public PDFPage getSDKPage() {
		return page;
	}

	@Override
	public void flatten() {
		
		try{
		page.flatten(PDFPage.FLATTENFLAG_DISPLAY);
		}
		catch(PDFException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		
		
		try {
			PDFDocument document = documentProvider.getSDKDocument();
			document.closePage(page);
		} catch (PDFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public FoxitPDFFormField getFormFieldAt(float x, float y)
	{
		
		FoxitPDFFormField formField = null;
		try{
			
			PDFForm form = formProvider.getSDKForm();
			PointF point = new PointF();
			point.x = x;
			point.y = y;
			//PDFFormControl formControl = form.getControlAtPos(page, point, 1, PDFFormField.TYPE_UNKNOWN);
			
			Point pointI = new Point();
			pointI.x = (int)x;
			pointI.y = (int)y;
			
			Matrix matrix = page.getDisplayMatrix(0, 0, pageWidth, pageHeight, 0);
			PDFFormControl formControl = form.getControlAtDevicePos(page, matrix, pointI, 1, PDFFormField.TYPE_UNKNOWN);
			
			if(formControl!=null)
			formField = new FoxitPDFFormField(formControl, formProvider, this);
		}
		catch(PDFException e)
		{
			e.printStackTrace();
		}
		return formField;
	}

	@Override
	public Rect transferPDFRect(RectF pdfRect) {
		
		Rect dstRect = new Rect();
		try {
			page.transformPageToDevice(pdfRect, dstRect, 0);
		} catch (PDFException e) {
			e.printStackTrace();
		}
		return dstRect;
	}
	
}
