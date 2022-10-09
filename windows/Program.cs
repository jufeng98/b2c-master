using System.Threading;
using System;
using System.Windows.Forms;
using System.IO;
using log4net.Config;
using System.Drawing;

namespace windows
{
    class Program
    {
        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(typeof(Program));
        static void Main(string[] args)
        {
            Application.ThreadException += Application_ThreadException;
            AppDomain.CurrentDomain.UnhandledException += CurrentDomain_UnhandledException;

            var configFile = new FileInfo("log4net.config");
            XmlConfigurator.Configure(configFile);

            Net.saveToFile(Net.netInfo());

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            // Application.Run(new ProgramWindows());
            notify(Net.netInfo()+"\n已写入文件C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt");
            Thread.Sleep(2000);
        }

        // 主线程全局异常捕获处理
        static void Application_ThreadException(object sender, ThreadExceptionEventArgs e)
        {
            Exception ex = e.Exception;
            log.Error("系统主线程出错", ex);
            alertAndExit(ex);
        }

        // 子线程全局异常捕获处理
        static void CurrentDomain_UnhandledException(object sender, UnhandledExceptionEventArgs e)
        {
            Exception ex = e.ExceptionObject as Exception;
            log.Error("系统子线程出错", ex);
            alertAndExit(ex);
        }

        static void alertAndExit(Exception ex)
        {
            MessageBox.Show(string.Format("系统出错了,错误信息：{0}\r\n点击确定关闭程序", ex.Message), "ERROR",
                MessageBoxButtons.OK, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1, MessageBoxOptions.DefaultDesktopOnly);
            Environment.Exit(-1);
        }

        public static void notify(string content)
        {
            NotifyIcon fyIcon = new NotifyIcon();
            fyIcon.Icon = new Icon("favicon.ico");
            fyIcon.BalloonTipText = content;
            fyIcon.BalloonTipTitle = "通知";
            fyIcon.Visible = true;
            fyIcon.ShowBalloonTip(0);
        }
    }
}
