package FinalProj.utils;

public class SceneTracker {
    public static int pointer = 0;
    public static void incrementPointer()
    {
        pointer++;
    }
    public static void movePointer(int i)
    {
        pointer = i;
    }
    public static String resolveStringName()
    {
        return "Scene"+pointer;
    }
}
