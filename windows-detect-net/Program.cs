using System.Threading;
using System;
using System.IO;

namespace windows_detect_net
{
    class Program
    {
        static void Main(string[] args)
        {
            // autoStart();

            DetectNet.saveToFile(DetectNet.netInfo());

            DetectNet detectNet = new DetectNet();
            detectNet.netChangeListener();

            Thread.Sleep(3000000);
        }
    }
}
