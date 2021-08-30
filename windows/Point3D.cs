namespace windows
{
    public class Point3D : Point
    {
        public int Z { get; set; }

        public static void test1()
        {
            Point a = new Point(10, 20);
            Point b = new Point3D(10, 20, 30);
        }
        public Point3D(int x, int y, int z) : base(x, y)
        {
            Z = z;
        }
    }
}