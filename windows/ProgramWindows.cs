using System;
using System.Windows.Forms;
using System.Threading;

namespace windows
{
    public class ProgramWindows : Form
    {
        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(typeof(ProgramWindows));
        public ProgramWindows()
        {
            log.Info("启动桌面");
            log.Info(DateTime.Now.ToString("yyyyMMdd-HHmmssfff"));
            //InitializeComponent();
        }
    }
}