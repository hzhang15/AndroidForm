package com.foxitsample.pdfLib;

import java.nio.ByteBuffer;

import com.foxitsample.exception.invalidLicenseException;
import com.foxitsample.exception.parameterException;
import com.foxitsample.formfiled.testActivity;

import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.CPDFFormFillerInfo;
import FoxitEMBSDK.EMBJavaSupport.CPDFJsPlatform;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class PDFDocument {
	private int nPDFFormHandler = 0;
	public String fileName = "/mnt/sdcard/HS-268%20Water%20Heater%20-%20Agreement.pdf";
	private static final String password = "";
	private int nFileRead = 0;
	private int nPDFDocHandler = 0;
	private int nPDFCurPageHandler = 0;
	public PDFDocument(String filePath)
	{
		this.fileName=filePath;
	}
	
	public Bitmap generateImage(int pageIndex, int scale, Rect viewRect)
	{	
		boolean nRet=false;
		try {
			nRet = this.InitFoxitFixedMemory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
  		if (nRet != true){
  			return null;
  		}
  		nRet = this.InitPDFDoc();
  		if (nRet != true){
  			return null;
  		}
  		
 		int nPageCount = this.GetPageCounts();
  		
  		nRet = this.InitPDFPage(0);
  		if (nRet != true){
  			return null;
  		}
  		Bitmap bm=this.getPageBitmap(viewRect.left,viewRect.top,viewRect.width(), viewRect.height(),scale);
  		return bm;
		
  		
	}
	public boolean InitFoxitFixedMemory() throws parameterException, invalidLicenseException{
		EMBJavaSupport.FSMemInitFixedMemory(5*1024*1024);		
		EMBJavaSupport.FSInitLibrary(0);
		EMBJavaSupport.FSUnlock("SDKEDTEMP", "019BF43365F8BF984D694D44332D9223EC4C95B7");			
		return true;
	}
    public boolean InitPDFDoc(){
		try {
			nFileRead = EMBJavaSupport.FSFileReadAlloc(fileName);
			nPDFDocHandler = EMBJavaSupport.FPDFDocLoad(nFileRead, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return true;
	}
    
public boolean InitPDFPage(int nPageIndex){
		
		if (nPDFDocHandler == 0){
			return false;
		}
		
		try {
			nPDFCurPageHandler = EMBJavaSupport.FPDFPageLoad(nPDFDocHandler, nPageIndex);
		} catch (parameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		try {
			EMBJavaSupport.FPDFPageStartParse(nPDFCurPageHandler, 0, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return true;
	}
    
  public int GetPageCounts(){
		
		if (nPDFDocHandler == 0){
			return EMBJavaSupport.EMBJavaSupport_RESULT_ERROR;
		}
		
		int nPageCount=-1;
		try {
			nPageCount = EMBJavaSupport.FPDFDocGetPageCount(nPDFDocHandler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return nPageCount;
	}
  public Bitmap getPageBitmap(int x, int y, int displayWidth, int displayHeight, int scale){
		if(nPDFCurPageHandler == 0) {
			return null;
		} 
		int pagewidth=(int)(this.GetPageSizeX(nPDFCurPageHandler)*scale);
		int pageheight=(int)(this.GetPageSizeY(nPDFCurPageHandler)*scale);							
		Bitmap bm;
	    bm = Bitmap.createBitmap(displayWidth,displayHeight,Bitmap.Config.ARGB_8888);	
		int dib;
		try {
			dib = EMBJavaSupport.FSBitmapCreate(displayWidth, displayHeight, 7, null, 0);
	
		EMBJavaSupport.FSBitmapFillColor(dib,0xff);
		EMBJavaSupport.FPDFRenderPageStart(dib, nPDFCurPageHandler, -x, -y, pagewidth,pageheight, 0, 0, null, 0);
		byte[] bmpbuf=EMBJavaSupport.FSBitmapGetBuffer(dib);
		
		ByteBuffer bmBuffer = ByteBuffer.wrap(bmpbuf); 
		bm.copyPixelsFromBuffer(bmBuffer);
		
		EMBJavaSupport.FSBitmapDestroy(dib);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bm;
	}
  public float GetPageSizeX(int pageIndex)
	{
		try {
			return EMBJavaSupport.FPDFPageGetSizeX(nPDFCurPageHandler);
		} catch (parameterException e) {
			return 0;
		}
	}
	
	public float GetPageSizeY(int pageIndex)
	{
		
		try {
			return EMBJavaSupport.FPDFPageGetSizeY(nPDFCurPageHandler);
		} catch (parameterException e) {
			return 0;
		}
	}
}
