using System.Net.Mime;
using System;
using System.Windows.Forms;

namespace windows
{
    class Program
    {
        static void Main(string[] args)
        {
            Net net = new Net();
            net.netChangeListener();
            Console.WriteLine(SysInfo.getSysInfo());
            // Console.ReadLine();

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new ProgramWindows());
        }
    }
}
