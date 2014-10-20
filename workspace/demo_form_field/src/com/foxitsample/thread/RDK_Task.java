package com.foxitsample.thread;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.Color;


public abstract class RDK_Task extends Object {
	
	public final static int TASKTYPE_LOADDOCUEMT   = 1;
	public final static int TASKTYPE_COLSEDOCUEMT  = 2;
	public final static int TASKTYPE_LOADPAGE	   = 3;
	public final static int TASKTYPE_CLOSEPAGE 	   = 4;
	public final static int TASKTYPE_GETALLPAGESIZE= 5;
	public final static int TASKTYPE_PARTPAGEDRAW  = 6;
	public final static int TASKTYPE_THUMBNAILDRAW = 7;
	public final static int TASKTYPE_SEARCHSTART   = 8;
	public final static int TASKTYPE_SEARCHNEXT    = 9;
	public final static int TASKTYPE_SEARCHPREV    = 10;
	public final static int TASKTYPE_ADDBOOKMARK   = 11;
	public final static int TASKTYPE_SHOWALLBOOKMARKS   = 12;
	public final static int TASKTYPE_FULLSEARCH    = 13;
	public final static int TASKTYPE_ZOOMIN    	   = 14;
	public final static int TASKTYPE_ZOOMOUT       = 15;
	public final static int TASKTYPE_TAPTEXTLINK   = 16;
	public final static int TASKTYPE_UPDATEFORMFIELD   = 17;
	public final static int TASKTYPE_SETFORMFILLTEXT   = 18;
	public final static int TASKTYPE_GETDIRTYBITMAP    = 19;
	public final static int TASKTYPE_SAVEAS    = 19;
	
	protected int		mTaskTask				=-1;
	
	public static final int PRIORITY_HIGH		= 8;
	public static final int PRIORITY_MIDHIGH    = 6;  //thumbnail used it
	public static final int PRIORITY_MID		= 5;
	public static final int PRIORITY_LOW		= 1;
	
	protected int		mPriority = PRIORITY_MID;
	protected int		combineCount = 0;
	public int			getPriority() { return mPriority; }
	public void			setPriority(int priority) { mPriority = priority; }
	protected boolean	canceled = false;
	
	public boolean		canRemove() { return false; }
	public boolean		isCanceled() { return canceled; }
	
	public void			cancel() { 
		if (combineCount == 0) {
			canceled = true;
		} else {
			combineCount --;
		}
	}
	
	//Call back function has to be included in RDK TASK constructors
	public RDK_Task(ICallBack callBack) {
		if (callBack != null) {
			mCallBacks.add(callBack);
		}
		mCallBack = callBack;
	}
	
	//To be implemented by the RDK TASK for different PDF related tasks
	public abstract boolean	execute();
	
	protected ArrayList<ICallBack>	mCallBacks = new ArrayList<RDK_Task.ICallBack>();
	
	//Call back function to instantiate the RDK TASK
	protected ICallBack  mCallBack = null;
	public interface ICallBack {
		public void result(RDK_Task task);
	}
	
	public void result(boolean flag)
	{
		if(mCallBack !=null && flag){
			mCallBack.result(this);
		}
		else
			mCallBack.result(null);
	}
	
	// judge two task is equal.
	public abstract boolean	isEqual(RDK_Task task);
	
//	//Derive a device rectangle from a PDF rectangle
//	public RectF ConvertDeviceRect(RectF srcRect, PDFPage page, PDFPageView view)
//	{		  		
//		return null;
//	}
	
	public Bitmap GetHighLightMarkedRectBitmap(int width,int height,float stride)
	{
		//init a blue color bitmap   
		int[] colors=new int[width*height]; 
		for(int i = 0;i< width * height;i++)
			colors[i] = 0;
		int r = 0;  
		int g = 0;  
		int b = 255;  
		int a = 50;  
		int color_blue = Color.argb(a, r, g, b);
		for(int j =0;j< width * height;j++)
			colors[j] = color_blue;
		
		Bitmap map = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);
		return map;
	}
	
}

//exit server
class RDK_ExitTask extends RDK_Task
{

	public RDK_ExitTask(ICallBack callBack) {
		super(callBack);
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean isEqual(RDK_Task task) {
		return false;
	}
	
}

