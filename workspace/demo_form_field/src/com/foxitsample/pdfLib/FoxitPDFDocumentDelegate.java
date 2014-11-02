package com.foxitsample.pdfLib;

import com.foxit.gsdk.pdf.PDFDocument;
import com.foxit.gsdk.pdf.PDFPage;

public interface FoxitPDFDocumentDelegate {
	void close();
	PDFPage getPageHandlerFormIndex(int index);// for form callback
	FoxitPDFPage getPageAtIndex(int pageIndex);
	PDFDocument getSDKDocument();
	void setAllPageSizes(int width, int height);//when one page size is set, it will be set to all page sizes
	void flattenAllPages();
	void saveTo(String filePath);
}
