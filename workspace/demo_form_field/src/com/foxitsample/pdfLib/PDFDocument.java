package com.foxitsample.pdfLib;

import com.foxitsample.exception.invalidLicenseException;
import com.foxitsample.exception.parameterException;

import FoxitEMBSDK.EMBCallbackUpdateDelegate;
import FoxitEMBSDK.EMBJavaSupport;
import FoxitEMBSDK.EMBJavaSupport.CPDFFormFillerInfo;
import FoxitEMBSDK.EMBJavaSupport.CPDFJsPlatform;

public class PDFDocument implements PDFDocumentDelegate, PDFFormEnvironmentProviderDelegate {
	public String fileName;
	private static final String password = "";
	public int nFileRead = 0;
	public int nPDFDocHandler = 0;
	public int nPDFCurPageHandler = 0;
	PDFPage[] pdfPageArray;
	
	/** form*/
	private EMBCallbackUpdateDelegate delegate = null;
	private CPDFFormFillerInfo formFillerInfo = null;
	public int nPDFFormFillerInfo = 0;
	public CPDFJsPlatform jsPlatform = null;
	public int nPDFJsPlatform = 0;
    public int nPDFFormHandler = 0;
	
	private PDFDocument(String filePath, EMBCallbackUpdateDelegate delegate)
	{
		this.fileName=filePath;
		this.delegate=delegate;
		init();
	}
	
	private void init()
	{
        boolean nRet=false;
		try {
			nRet = InitFoxitFixedMemory();
		} catch (Exception e) {
			e.printStackTrace();
		} 
  		if (nRet != true){
  			return;
  		}
  		
  		LoadJbig2Decoder();
  		LoadJpeg2000Decoder();
  		LoadCNSFontCMap();
  		LoadKoreaFontCMap();
  		LoadJapanFontCMap();
  		
  		nRet = InitPDFDoc();//do the file reading work
  		if (nRet != true){
  			return;
  		}
  		
 		int pageCount = GetPageCounts();
  		pdfPageArray = new PDFPage[pageCount];
  		
  		for(int i = 0; i<pageCount; i++)
  		{
  			pdfPageArray[i] = new PDFPage(this, this, i);
  		}
 		
	}
	
//	private Bitmap generateImage(int pageIndex, int scaleX,int scaleY, Rect viewRect)
//	{	
//		/*boolean nRet=false;
//		try {
//			nRet = this.InitFoxitFixedMemory();
//		} catch (Exception e) {
//			// 
//			e.printStackTrace();
//		} 
//  		if (nRet != true){
//  			return null;
//  		}
//  		nRet = this.InitPDFDoc();
//  		if (nRet != true){
//  			return null;
//  		}
//  		
// 		int nPageCount = this.GetPageCounts();
//  		
//  		nRet = this.InitPDFPage(0);
//  		if (nRet != true){
//  			return null;
//  		}*/
//  		Bitmap bm=this.getPageBitmap(viewRect.left,viewRect.top,viewRect.width(), viewRect.height(),scaleX,scaleY);
//  		return bm;
//		
//  		
//	}
	
	/** Load jbig2 decoder.*/
	private void LoadJbig2Decoder(){
		EMBJavaSupport.FSLoadJbig2Decoder();
	}
	
	/** Load jpeg2000 decoder. */
	private void LoadJpeg2000Decoder(){
		EMBJavaSupport.FSLoadJpeg2000Decoder();
	}
	
	/** */
	private void LoadJapanFontCMap(){
		EMBJavaSupport.FSFontLoadJapanCMap();
		EMBJavaSupport.FSFontLoadJapanExtCMap();
	}
	
	/** */
	private void LoadCNSFontCMap(){
		EMBJavaSupport.FSFontLoadGBCMap();
		EMBJavaSupport.FSFontLoadGBExtCMap();
		EMBJavaSupport.FSFontLoadCNSCMap();
	}
	
	private void LoadKoreaFontCMap(){
		EMBJavaSupport.FSFontLoadKoreaCMap();
	}
	
	private boolean InitFoxitFixedMemory() throws parameterException, invalidLicenseException{
		EMBJavaSupport.FSMemInitFixedMemory(8*1024*1024);		
		EMBJavaSupport.FSInitLibrary(0);
		//EMBJavaSupport.FSUnlock("SDKEDTEMP", "019BF43365F8BF984D694D44332D9223EC4C95B7");	
		EMBJavaSupport.FSUnlock("SDKEDFZ1560", "759C262EECD8CD6828F31AB1D6637B806A1C82F6");
		
/////////formfiller implemention
		if (delegate == null) 
			return false;
		formFillerInfo = new EMBJavaSupport().new CPDFFormFillerInfo(delegate);
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
	private boolean InitPDFDoc(){
		try {
			nFileRead = EMBJavaSupport.FSFileReadAlloc(fileName);
			nPDFDocHandler = EMBJavaSupport.FPDFDocLoad(nFileRead, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		///formfiller implemention
				nPDFFormHandler = EMBJavaSupport.FPDFDocInitFormFillEnviroument(nPDFDocHandler, nPDFFormFillerInfo);
				if (nPDFFormHandler == 0)
					return false;
				
		return true;
	}
    
	private int GetPageCounts(){
		
		if (nPDFDocHandler == 0){
			return EMBJavaSupport.EMBJavaSupport_RESULT_ERROR;
		}
		
		int nPageCount=-1;
		try {
			nPageCount = EMBJavaSupport.FPDFDocGetPageCount(nPDFDocHandler);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return nPageCount;
	}

	@Override
	public void close() {
		
		for(PDFPage page: pdfPageArray)
		{
			page.close();
		}
		
		EMBJavaSupport.FPDFFormFillOnKillFocus(nPDFFormHandler);
		
		EMBJavaSupport.FPDFDocExitFormFillEnviroument(nPDFFormHandler);
		
		EMBJavaSupport.FPDFFormFillerInfoRelease(nPDFFormFillerInfo);
		
		EMBJavaSupport.FPDFJsPlatformRelease(nPDFJsPlatform);
		
		try {
			EMBJavaSupport.FPDFDocClose(nPDFDocHandler);
		} catch (parameterException e) {
			e.printStackTrace();
		}
		
		EMBJavaSupport.FSFileReadRelease(nFileRead);
		
		EMBJavaSupport.FSDestroyLibrary();	
		EMBJavaSupport.FSMemDestroyMemory();
	}

	
	//assuming already inited all the pagesŒ
	@Override
	public int getPageHandlerFormIndex(int index) {
		return pdfPageArray[index].getPageHandler();
	}

      
	public static PDFDocument generatePDFDocumentFromPath(String path, EMBCallbackUpdateDelegate delegate)
	{
		
		PDFDocument pdfDocument = new PDFDocument(path, delegate);
		return pdfDocument;
		
	}
	
	//all the PDFPage objects are initialized during construction 
	@Override
	public PDFPage getPageAtIndex(int pageIndex) {
		return pdfPageArray[pageIndex];
	}

	@Override
	public int getDocumentHandler() {
		return nPDFDocHandler;
	}

	@Override
	public void setAllPageSizes(int width, int height) {
		
		for(PDFPage pdfPage : pdfPageArray)
		{
			pdfPage.setPageSize(width, height);
		}
		
	}

	@Override
	public int getPDFFormHandler() {
		return nPDFFormHandler;
	}
	
}
