using Microsoft.Win32;
using System;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApp
{
    public class SysInfo
    {
        public static string getSysInfo()
        {
            StringBuilder sb = new StringBuilder();
            try
            {
                OperatingSystem MyOS = Environment.OSVersion;
                sb.Append("OS Version:").AppendLine(Environment.OSVersion.VersionString);
                RegistryKey reg = Registry.LocalMachine;
                reg = reg.OpenSubKey("HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0");
                sb.Append("OS ProcessorNameString:").AppendLine(reg.GetValue("ProcessorNameString").ToString());
                RegistryKey key18 = Registry.LocalMachine.OpenSubKey(@"SOFTWARE\Microsoft\Windows NT\CurrentVersion");
                sb.Append("OS ProductName:").AppendLine(key18.GetValue("ProductName").ToString());
                sb.Append("OS CurrentVersion:").AppendLine(key18.GetValue("CurrentVersion") + " " + key18.GetValue("CurrentBuildNumber"));
                sb.Append("OS ComputerName:").AppendLine(Environment.GetEnvironmentVariable("ComputerName"));
                sb.Append("OS UserName:").AppendLine(Environment.GetEnvironmentVariable("UserName"));
                sb.Append("OS MachineName:").AppendLine(Environment.MachineName);
                foreach (var screen in Screen.AllScreens)
                {
                    sb.AppendLine("Screen Device Name: " + screen.DeviceName);
                    sb.AppendLine("Screen Bounds: " + screen.Bounds.ToString());
                    sb.AppendLine("Screen Type: " + screen.GetType().ToString());
                    sb.AppendLine("Screen Working Area: " + screen.WorkingArea.ToString());
                    sb.AppendLine("Screen Primary Screen: " + screen.Primary.ToString());
                }
                sb.Append("CurrentDirectory:").AppendLine(Environment.CurrentDirectory);
                sb.Append("ProcessorCount:").AppendLine(Environment.ProcessorCount + "");
                sb.Append("WorkingSet:").AppendLine(Environment.WorkingSet + "");
                sb.Append("CurrentDirectory:").AppendLine(Environment.CurrentDirectory);
                sb.Append("StartupPath:").AppendLine(Application.StartupPath);
            }
            catch
            {
            }
            return sb.ToString();
        }
    }
}