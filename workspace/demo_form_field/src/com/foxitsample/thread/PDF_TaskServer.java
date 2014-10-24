package com.foxitsample.thread;

import java.util.ArrayList;

import android.os.Process;
import android.util.Log;

//The main controller to handle all the RDK Task in an ordered synchronized manner
public class PDF_TaskServer {
	
	//a queue holding tasks to be executed
	public ArrayList<RDK_Task> mTaskList = null;
	public PDF_TaskThread mPDFThread = null;
	
	public PDF_TaskServer() {
		mTaskList = new ArrayList<RDK_Task>();
		mPDFThread = new  PDF_TaskThread(this);
	}
	
	public void startTaskServer()  // begin task server
	{
		if(mPDFThread.isAlive() == false)
			mPDFThread.start();
	}
	
	public void endTaskServer()   // end task server
	{
		removeAllTasks();
		while(mPDFThread.isAlive())
		{
			mPDFThread.interrupt();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Add task to the queue lining up waiting for execution
	public RDK_Task addTask(RDK_Task task)
	{
		synchronized (mTaskList) {
			int pos =  mTaskList.size();
			for(int i = mTaskList.size() - 1 ; i >= 0; i--)
			{
				RDK_Task t = mTaskList.get(i);
				
				if(t.isEqual(task))
					return task;
				
				if(t.getPriority() > task.getPriority())
				{
					pos = i + 1;
					break;
				}
				
			}
			return insertTask(pos, task);
		}
	}
	
	//Add one task to the queue
	public RDK_Task insertTask(int index, RDK_Task task)
	{
		synchronized (mTaskList) {
			mTaskList.add(index, task);
			return task;
		}
	}
	
	public RDK_Task getTask()
	{
		synchronized (mTaskList) {
			if(mTaskList.size() > 0)
				return mTaskList.get(0);
			else
				return null;
		}
	}
	
	//Remove one task form the queue
	public RDK_Task removeTask(int index)
	{
		synchronized (mTaskList) {
			if(mTaskList.size() > index)
				 return mTaskList.remove(index);
			else
				return null;
		}
	}
	
	
	public void removeTask(RDK_Task task)
	{
		synchronized (mTaskList) {
			if(mTaskList.size() > 0)
				 mTaskList.remove(task);
		}
	}
	
	public void removeAllTasks()
	{
		synchronized (mTaskList) {
			for(int i = mTaskList.size()-1; i >= 0; i--)
			{
				RDK_Task t = mTaskList.get(i);
				t.cancel();
				mTaskList.remove(i);
			}
		}
	}
	
}

// thread control task
class PDF_TaskThread  extends Thread {

	private PDF_TaskServer mtasks = null;
	public PDF_TaskThread(PDF_TaskServer tasks)
	{
		mtasks = tasks;
	}
	
	public void run()
	{
		Process.setThreadPriority(10);
		boolean retExe = true;
		
		//running a synchronized RDK_TASK
		while(!Thread.currentThread().isInterrupted())
		{
			
			RDK_Task task = mtasks.getTask();
			
			if(task != null )
			{
				if(task.isCanceled() == false)
				{
				//	Log.i("execute begin","...................................");
					retExe = task.execute();
					task.result(true);
				//	Log.i("execute end","...................................");
				}
				else{
					task.result(false);
				//	Log.i("cancel","...................................");
				}
				
				mtasks.removeTask(task);
								
			} else
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			
			
			if(retExe == false) 
				break;   // thread exit
			
		}
		
	Log.i("thread exit","..........................................");	
	}
}
