package com.foxitsample.pdfLib;

import com.foxit.gsdk.PDFException;
import com.foxit.gsdk.PDFLibrary;
import com.foxit.gsdk.pdf.PDFDocument;
import com.foxit.gsdk.pdf.PDFPage;
import com.foxit.gsdk.pdf.Progress;
import com.foxit.gsdk.pdf.form.PDFForm;
import com.foxit.gsdk.utils.FileHandler;
import com.foxitsample.exception.invalidLicenseException;
import com.foxitsample.exception.parameterException;


public class FoxitPDFDocument implements FoxitPDFDocumentDelegate, FoxitPDFFormEnvironmentProviderDelegate {
	public String fileName;
	private PDFDocument document;
	private PDFForm form;
	FoxitPDFPage[] pdfPageArray;
	
    FileHandler fileHandler;
    PDFLibrary library;
	
    static{
    	System.loadLibrary("fsdk_android");
    }
    
	private FoxitPDFDocument(String filePath)
	{
		this.fileName=filePath;
		init();
	}
	
	private void init()
	{
		
		library = PDFLibrary.getInstance();
        boolean nRet=false;
		try {
			nRet = InitFoxitFixedMemory();
		} catch (Exception e) {
			e.printStackTrace();
		} 
  		if (nRet != true){
  			return;
  		}
  		
//  		LoadJbig2Decoder();
//  		LoadJpeg2000Decoder();
//  		LoadCNSFontCMap();
//  		LoadKoreaFontCMap();
//  		LoadJapanFontCMap();
  		
  		nRet = InitPDFDoc();//do the file reading work
  		if (nRet != true){
  			return;
  		}
  		
  		int pageCount = 0;
  		
  		try{
  		
  		pageCount = document.countPages();
  		
  		}catch(PDFException e)
  		{
  			e.printStackTrace();
  		}
  		
 		
  		pdfPageArray = new FoxitPDFPage[pageCount];
  		
  		for(int i = 0; i<pageCount; i++)
  		{
  			pdfPageArray[i] = new FoxitPDFPage(this, this, i);
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
	
//	/** Load jbig2 decoder.*/
//	private void LoadJbig2Decoder(){
//		EMBJavaSupport.FSLoadJbig2Decoder();
//	}
//	
//	/** Load jpeg2000 decoder. */
//	private void LoadJpeg2000Decoder(){
//		EMBJavaSupport.FSLoadJpeg2000Decoder();
//	}
//	
//	/** */
//	private void LoadJapanFontCMap(){
//		EMBJavaSupport.FSFontLoadJapanCMap();
//		EMBJavaSupport.FSFontLoadJapanExtCMap();
//	}
//	
//	/** */
//	private void LoadCNSFontCMap(){
//		EMBJavaSupport.FSFontLoadGBCMap();
//		EMBJavaSupport.FSFontLoadGBExtCMap();
//		EMBJavaSupport.FSFontLoadCNSCMap();
//	}
//	
//	private void LoadKoreaFontCMap(){
//		EMBJavaSupport.FSFontLoadKoreaCMap();
//	}
	
	private boolean InitFoxitFixedMemory() throws parameterException, invalidLicenseException{
		
		try{
		library.initialize(10*1024*1024, true);
		library.unlock("m/lBBM/Yp0YDPw9qZfQ3sNgLOBSqN8p8l3Tigb/RrK0ArnNx+gXU1w==", "8f3o18GNtRkNBDdqtFZS8bag26nXuHklApT0VWe+bhvKr+VRcLIrhrfybL+GD4nHniwSs/l2gmQzoWcIcD5qyq8sgR1eenu5z9fR+SgEbApWk3hGgQsMmYp0zTcaQxqDC52pzeaPz6u05/MKG8iwOFYQsIGqlxYhYdLUrT/4eE7TYkY7mqDvZfheCdHsClBZ1jQVLWA02jpfGTqLzM2xe/HYmso2tcaqjTyemTTw1IimFK+cHnlFaCWwkNlnGt4DY3B6MTQWXU9+WnnxpRKlNlKQ8Pcre8QS8MTq4VIWTnj6AT9F37DGa46zlX0fcBMcMcs6sjvMi+ZuGWVmXIzUKonM9FPIW52fEsxrLSxwZIzFkwRTPUidcdrrRPp4fEi4z/elYjLT004OaPA+z/ePYtKoPUUYRPvCWkNYyxnlf7GjO/BJZw69b/GNof67b9BsoSOhFhOGwPbftNa3cbgqp/KKq4lj00jRIsy5YltsEAXYq5zr06ywmpW2tk1g+SHCFTpEQcEYPs7HsELwO6Q9Sr/C6Lhqz9dyBy9H9hp4r/oxUwvonVhA5hUbZ2yXMttMxWHiP3QwH4xf/lcNwYePK8gff+/UsjXIfC3xLTMsu3PqEM5QUwMrkawnmZ1UoMD/VXWik+VpBHMGI1Hhz0jmxqbIjAd9yqDvZ3F9txozt4pf1KsuNk7fgdD0fD0ViPrliV5zYkYV6byOQnat0GbqnNxcaA9NO00oBIEv9DCFz8WfgRDL4qksySreg17CJTeATeYQTLsMMb/tLEqY0fFHOg+CObn6FTWFk3jzaZJkf2/AkJDBgF++5KVhk3QhfB7eCAxz6E/yHNStQZdmRLd7yB1WEQ3kv5PIOWqttZnlYKQj312JFhnDH0EqayHdC2F+hBCOBY/+YJnolQzb+QyzsFu4sYK1UGfTvCiF0KdNlxTBrxoi81LO9gCfYaKaFYS9/tay3X341iEyhSo2rok+iKvpK0fYE7Aw6oAjnXxMMSEqYRhsIzs0OOgqzn8+hvn3/Wg+5zC8fqYP/SoLY4qJ1QHpMeAID2xDuDYsABIYzb8TfWj1VXzpfxsz3uib7rfHlF2begu39fyHA8vNZ77MaJ7NnZCiMTJpZ1EqvVOkyAS9o0kcquICJeGrbW5NJfB+qcVe+M5rw/QBfRy8F9KwS/ZMN/m+an3tTi/7A3tBxFzoQOopIom+SRcJzOAVwOuJUfio7jj6sBh3GagV3drrHAycQq81lBDBE3qAELbzmZ4NgxHBXweY6SMi5KyWwIEPovEkP3Xxv34yZUbw0IuH0dEtsbMHMXjlHd14U5n04aOTLwnQ3rNcxnBoMvPN1he/r6J6OLxpgVkloHgj6kEpmconDGq1vQmn9tDDFJfLNN2Z592ZL+bkuGrlYWyjMt9nmVTnqnrylZf8nVtZMJJHPa0j3eNRzOyxChLbAzN5o/6NmE7wvWhq2acy+dZoIrHGTEwX8UrXJYzoo/U8ia978Wy7dj/jvHZz7/oGL1g/LBByhCahJ6NOuJtW7sF63paZHsM6zlZbpRtkuvHchfNoQR5lcLRQOJ/mhLo+Y/Ej6on2YcvWsFTBzcte7znOO9ynDSXMHK2XOBT94FkYS0MhFeCemxLT6TIJ+nwSWozkaFQMH6bAPv1Wh6aDvYucXaXFcGKHgnnbby7qEsI4MVV16Uj9w7ultMvk3Z8urW3SkIA5RuIz5tb78EzFhI2c8kctIjAQgNOEfNN4bLmwtWVPi0KkiQ==");
		}
		catch(PDFException e)
		{
			e.printStackTrace();
		}
		
	return true;
	}
	
	
	private boolean InitPDFDoc(){
		try {
			//nFileRead = EMBJavaSupport.FSFileReadAlloc(fileName);
			//nPDFDocHandler = EMBJavaSupport.FPDFDocLoad(nFileRead, password);
			
			fileHandler = FileHandler.create(fileName, FileHandler.FILEMODE_READONLY);
			document = PDFDocument.open(fileHandler, null);
			form = document.loadForm();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return true;
	}

	@Override
	public void close() {
		
		for(FoxitPDFPage page: pdfPageArray)
		{
			page.close();
		}
		
		try{
		fileHandler.release();
		document.close();
		}
		catch(PDFException e)
		{
			e.printStackTrace();
		}
		
		library.destroy();

	}

	
	//assuming already inited all the pagesŒ
	@Override
	public PDFPage getPageHandlerFormIndex(int index) {
		return pdfPageArray[index].getSDKPage();
	}

      
	public static FoxitPDFDocument generatePDFDocumentFromPath(String path)
	{
		
		FoxitPDFDocument pdfDocument = new FoxitPDFDocument(path);
		return pdfDocument;
		
	}
	
	//all the PDFPage objects are initialized during construction 
	@Override
	public FoxitPDFPage getPageAtIndex(int pageIndex) {
		return pdfPageArray[pageIndex];
	}

	@Override
	public PDFDocument getSDKDocument() {
		return document;
	}

	@Override
	public void setAllPageSizes(int width, int height) {
		
		for(FoxitPDFPage pdfPage : pdfPageArray)
		{
			pdfPage.setPageSize(width, height);
		}
		
	}

	@Override
	public PDFForm getSDKForm() {
		return form;
	}

	@Override
	public void flattenAllPages() {
		for(FoxitPDFPage page: pdfPageArray)
		{
			page.flatten();
		}
	}

	@Override
	public void saveTo(String filePath) {
		try {
			
			FileHandler fileHandler = FileHandler.create(filePath, FileHandler.FILEMODE_WRITE);
			Progress progress = document.startSaveToFile(fileHandler, PDFDocument.SAVEFLAG_INCREMENTAL);
			
			if(progress != null)
			{
				progress.continueProgress(0);
			}
			
			progress.release();
			
		} catch (PDFException e) {
			e.printStackTrace();
		} 
		
	}
	
}
