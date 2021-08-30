using System;

namespace windows
{
    class Program
    {
        static void Main(string[] args)
        {
            Net net = new Net();
            net.netChangeListener();
            Console.ReadLine();
        }
    }
}
