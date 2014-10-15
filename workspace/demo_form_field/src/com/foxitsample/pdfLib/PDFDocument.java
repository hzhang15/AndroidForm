package com.foxitsample.pdfLib;

import java.nio.ByteBuffer;

import com.foxitsample.exception.invalidLicenseException;
import com.foxitsample.exception.parameterException;
import com.foxitsample.formfiled.mainActivity;
import com.foxitsample.formfiled.testActivity;

import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.CPDFFormFillerInfo;
import FoxitEMBSDK.EMBJavaSupport.CPDFJsPlatform;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class PDFDocument {
	public String fileName;
	private static final String password = "";
	public int nFileRead = 0;
	public int nPDFDocHandler = 0;
	public int nPDFCurPageHandler = 0;
	
	/** form*/
	private testActivity mainView = null;
	private CPDFFormFillerInfo formFillerInfo = null;
	public int nPDFFormFillerInfo = 0;
	public CPDFJsPlatform jsPlatform = null;
	public int nPDFJsPlatform = 0;
    public int nPDFFormHandler = 0;
	
	public PDFDocument(String filePath, testActivity context)
	{
		this.fileName=filePath;
		mainView=context;
	}
	
	public Bitmap generateImage(int pageIndex, int pwscale,int phscale, Rect viewRect)
	{	
		/*boolean nRet=false;
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
  		}*/
  		Bitmap bm=this.getPageBitmap(viewRect.left,viewRect.top,viewRect.width(), viewRect.height(),pwscale,phscale);
  		return bm;
		
  		
	}
	
	/** Load jbig2 decoder.*/
	public void LoadJbig2Decoder(){
		EMBJavaSupport.FSLoadJbig2Decoder();
	}
	
	/** Load jpeg2000 decoder. */
	public void LoadJpeg2000Decoder(){
		EMBJavaSupport.FSLoadJpeg2000Decoder();
	}
	
	/** */
	public void LoadJapanFontCMap(){
		EMBJavaSupport.FSFontLoadJapanCMap();
		EMBJavaSupport.FSFontLoadJapanExtCMap();
	}
	
	/** */
	public void LoadCNSFontCMap(){
		EMBJavaSupport.FSFontLoadGBCMap();
		EMBJavaSupport.FSFontLoadGBExtCMap();
		EMBJavaSupport.FSFontLoadCNSCMap();
	}
	
	public void LoadKoreaFontCMap(){
		EMBJavaSupport.FSFontLoadKoreaCMap();
	}
	
	public boolean InitFoxitFixedMemory() throws parameterException, invalidLicenseException{
		EMBJavaSupport.FSMemInitFixedMemory(5*1024*1024);		
		EMBJavaSupport.FSInitLibrary(0);
		EMBJavaSupport.FSUnlock("SDKEDTEMP", "019BF43365F8BF984D694D44332D9223EC4C95B7");	
		
/////////formfiller implemention
		if (mainView == null) 
			return false;
		formFillerInfo = new EMBJavaSupport().new CPDFFormFillerInfo(mainView);
		if (formFillerInfo == null)
			return false;
		nPDFFormFillerInfo = EMBJavaSupport.FPDFFormFillerInfoAlloc(formFillerInfo);
		if (nPDFFormFillerInfo == 0)
			return false;
		
		jsPlatform = new EMBJavaSupport().new CPDFJsPlatform();
		if (jsPlatform == null)
			return false;
		nPDFJsPlatform = EMBJavaSupport.FPDFJsPlatformAlloc(jsPlatform);
		if (nPDFJsPlatform == 0)
			return false;
		
		EMBJavaSupport.FPDFFormFillerInfoSetJsPlatform(nPDFFormFillerInfo, nPDFJsPlatform);
		EMBJavaSupport.FPDFJsPlatformSetFormFillerInfo(nPDFJsPlatform, nPDFFormFillerInfo);		
		///////////////////
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
		
		///formfiller implemention
				nPDFFormHandler = EMBJavaSupport.FPDFDocInitFormFillEnviroument(nPDFDocHandler, nPDFFormFillerInfo);
				if (nPDFFormHandler == 0)
					return false;
				
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
		///formfiller implemention
				EMBJavaSupport.FPDFFormFillOnAfterLoadPage(nPDFFormHandler, nPDFCurPageHandler);
				///
		
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
  public Bitmap getPageBitmap(int x, int y, int displayWidth, int displayHeight, int pwscale, int phscale){
		if(nPDFCurPageHandler == 0) {
			return null;
		} 
		int pagewidth=(int)(this.GetPageSizeX(nPDFCurPageHandler)*pwscale);
		int pageheight=(int)(this.GetPageSizeY(nPDFCurPageHandler)*phscale);							
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
  
  public Bitmap getDirtyBitmap(Rect rect, int pwscale, int phscale){
		Bitmap bm = null;
		if(nPDFCurPageHandler == 0) {
			return null;
		} 
		
		bm = Bitmap.createBitmap(rect.width(),rect.height(),Bitmap.Config.ARGB_8888);
		int dib;
		try {
			dib = EMBJavaSupport.FSBitmapCreate(rect.width(), rect.height(), 7, null, 0);
	
			EMBJavaSupport.FSBitmapFillColor(dib,0xff);
			int pagewidth=(int)(this.GetPageSizeX(nPDFCurPageHandler)*pwscale);
			int pageheight=(int)(this.GetPageSizeY(nPDFCurPageHandler)*phscale);
			EMBJavaSupport.FPDFRenderPageStart(dib, nPDFCurPageHandler, -rect.left, -rect.top, pagewidth, pageheight, 0, 0, null, 0);
		
			
			///formfiller implemention
			if (nPDFFormHandler == 0)
				return null;
			EMBJavaSupport.FPDFFormFillDraw(nPDFFormHandler, dib, nPDFCurPageHandler, -rect.left, -rect.top, pagewidth, pageheight, 0, 0);
			///
			
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
  
  public int getPageHandler(int nPageIndex){
		if (nPDFDocHandler == 0){
			return EMBJavaSupport.EMBJavaSupport_RESULT_ERROR;
		}
		
		if (nPageIndex == 0 && nPDFCurPageHandler != 0)
			return nPDFCurPageHandler;
		
		int nPageHandler=0;
		try {
			nPageHandler = EMBJavaSupport.FPDFPageLoad(nPDFDocHandler, nPageIndex);

			EMBJavaSupport.FPDFPageStartParse(nPageHandler, 0, 0);

		
		///formfiller implemention
		//EMBJavaSupport.FPDF_FormFill_OnAfterLoadPage(nPageHandler, nPDFFormHandler);
		EMBJavaSupport.FPDFFormFillOnAfterLoadPage(nPDFFormHandler, nPageHandler);
		///
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return nPageHandler;
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
	public int getCurPDFPageHandler(){
		return nPDFCurPageHandler;
	}
	
	public int getPDFFormHandler(){
		return nPDFFormHandler;
	}
	/** Close a PDF Page*/
	public boolean ClosePDFPage(){
		
		if (nPDFCurPageHandler == 0){
			return false;
		}
		//EMBJavaSupport.FPDFFormFillOnBeforeClosePage(nPDFFormHandler,nPDFCurPageHandler);
		try {
			
			EMBJavaSupport.FPDFPageClose(nPDFCurPageHandler);
		} catch (parameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nPDFCurPageHandler = 0;
		
		return true;
	}
public boolean ClosePDFDoc(){
		
		if (nPDFDocHandler == 0){
			return false;
		}
		
		///formfiller implemention
		EMBJavaSupport.FPDFDocExitFormFillEnviroument(nPDFFormHandler);
		nPDFFormHandler = 0;
		
		EMBJavaSupport.FPDFFormFillerInfoRelease(nPDFFormFillerInfo);
		nPDFFormFillerInfo = 0;
		
		EMBJavaSupport.FPDFJsPlatformRelease(nPDFJsPlatform);
		nPDFJsPlatform = 0;
		/////
		
		try {
			EMBJavaSupport.FPDFDocClose(nPDFDocHandler);
		} catch (parameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nPDFDocHandler = 0;

		
		EMBJavaSupport.FSFileReadRelease(nFileRead);
		nFileRead = 0;
		
		return true;
	}
    public boolean DestroyFoxitFixedMemory(){
	EMBJavaSupport.FSDestroyLibrary();	
	EMBJavaSupport.FSMemDestroyMemory();
	return true;
}
      
}
