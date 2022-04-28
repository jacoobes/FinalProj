package FinalProj.utils;

public class SceneTracker {
    public static final int[] Scenes = new int[] {
      1, 2, 3, 4, 5, 6, 7,
    };
    public static int pointer = 0;
    public void incrementPointer()
    {
        pointer++;
    }
    public void movePointer(int i)
    {
        pointer = i;
    }
    public static String resolveStringName()
    {
        return "Scene"+pointer;
    }
}
