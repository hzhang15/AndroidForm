package FoxitEMBSDK;

public interface EMBCallbackUpdateDelegate {
	void refresh(int page, float left, float top, float right, float bottom);
	int getPageHandlerFromIndex(int documentHandler, int pageIndex);
	int getCurrentPageHandler(int documentHandler);
	void bringUpTextField(int field, String focustext, int nTextLen);
	void putTextToCurrentFormField(String text);
}
