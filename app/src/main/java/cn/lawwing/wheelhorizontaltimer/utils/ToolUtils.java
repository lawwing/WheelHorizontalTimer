package cn.lawwing.wheelhorizontaltimer.utils;

/**
 * author lawwing time 2017/7/27 10:27 describe
 **/
public class ToolUtils
{
    // 屏幕尺寸
    private static int screenW = 0;
    
    private static int screenH = 0;
    
    public static int getScreenW()
    {
        
        if (screenW == 0)
        {
            screenW = 720; // 强制纠错
        }
        
        return screenW;
    }
    
    public static void setScreenW(int screenW)
    {
        ToolUtils.screenW = screenW;
        // Log.e("--", "w=" + screenW);
    }
    
    public static int getScreenH()
    {
        
        if (screenH == 0)
        {
            screenW = 1280;
        }
        return screenH;
    }
    
    public static void setScreenH(int screenH)
    {
        ToolUtils.screenH = screenH;
        // Log.e("--", "h=" + screenH);
    }
}
