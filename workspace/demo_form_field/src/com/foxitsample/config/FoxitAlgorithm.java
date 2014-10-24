package com.foxitsample.config;

import android.graphics.Rect;

public class FoxitAlgorithm {
	public static void interceptFirstRect(Rect rectFirst, Rect rectSecond)
	{
		
		if(rectFirst.left > rectSecond.right || rectFirst.right < rectSecond.left || rectFirst.top > rectSecond.bottom || rectFirst.bottom < rectSecond.top)
		{
			rectFirst.left = 0;
			rectFirst.right = 0;
			rectFirst.top = 0;
			rectFirst.bottom = 0;
			return;
		}
		
		rectFirst.left = Math.max(rectFirst.left, rectSecond.left);
		rectFirst.right = Math.min(rectFirst.right, rectSecond.right);
		rectFirst.top = Math.max(rectFirst.top, rectSecond.top);
		rectFirst.bottom = Math.min(rectFirst.bottom, rectSecond.bottom);
		
		
		
	}
}
