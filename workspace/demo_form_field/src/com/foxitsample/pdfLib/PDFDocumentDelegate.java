package com.foxitsample.pdfLib;

public interface PDFDocumentDelegate {
	void close();
	int getPageHandlerFormIndex(int index);// for form callback
	PDFPage getPageAtIndex(int pageIndex);
	int getDocumentHandler();
	void setAllPageSizes(int width, int height);//when one page size is set, it will be set to all page sizes

}
