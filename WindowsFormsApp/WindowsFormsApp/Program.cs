using log4net.Config;
using System;
using System.IO;
using System.Windows.Forms;

namespace WindowsFormsApp
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            DetectNet.saveToFile(DetectNet.netInfo());

            //DetectNet detectNet = new DetectNet();
            //detectNet.netChangeListener();

            var configFile = new FileInfo("log4net.config");
            XmlConfigurator.Configure(configFile);

            //EnumTest.Test();
            //BasicType.Test();
            //MyList<string>.Test();
            //StringTest.Test();
            //DelegateTest.Test();
            //AsyncTest.Test();
            AttrTest.Test();
            Application.Run();
        }
    }
}
