using System;
namespace windows
{
    public class Point
    {
        public int X { get; }
        public int Y { get; }

        public static void test()
        {
            Point point = new Point(1, 2);
            Console.WriteLine(point.X + " " + point.Y);
        }
        public Point(int x, int y) => (X, Y) = (x, y);
    }
}