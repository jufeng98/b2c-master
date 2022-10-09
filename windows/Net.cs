using System.IO;
using System.Text;
using System;
using System.Net.NetworkInformation;

namespace windows
{
    public class Net
    {
        public void netChangeListener()
        {
            NetworkChange.NetworkAddressChanged += (sender, e) =>
            {
                Console.WriteLine("net change:" + e.GetType());
                var str = netInfo();
                saveToFile(str);
            };
        }
        public static string netInfo()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("127.0.0.1     localhost\n");
            sb.Append("::1           ip6-localhost\n");
            NetworkInterface[] networkinterfaces = NetworkInterface.GetAllNetworkInterfaces();
            foreach (NetworkInterface adapter in networkinterfaces)
            {
                if (adapter.NetworkInterfaceType == NetworkInterfaceType.Loopback)
                {
                    continue;
                }
                if (adapter.OperationalStatus == OperationalStatus.Down)
                {
                    continue;
                }
                IPInterfaceProperties properties = adapter.GetIPProperties();
                foreach (UnicastIPAddressInformation information in properties.UnicastAddresses)
                {
                    if (information.Address.IsIPv6LinkLocal)
                    {
                        continue;
                    }
                    sb.Append("#").Append(adapter.Name).Append(" ").Append(adapter.NetworkInterfaceType).Append("\n");
                    var infomations = information.Address.GetAddressBytes();
                    StringBuilder ipSb = new StringBuilder();
                    for (int i = 0; i < infomations.Length; i++)
                    {
                        int part = infomations[i];
                        ipSb.Append(part);
                        if (i != infomations.Length - 1)
                        {
                            ipSb.Append(".");
                        }
                    }
                    var ip = ipSb.ToString();
                    sb.Append(ip).Append("      agent.javamaster.org\n");
                    Console.WriteLine("system ip is:" + adapter.Name + " " + adapter.NetworkInterfaceType + " " + ip);
                }
            }
            return sb.ToString();
        }

        public static void saveToFile(string str)
        {
            using (FileStream fileStream = File.OpenWrite("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt"))
            {
                using (StreamWriter streamWriter = new StreamWriter(fileStream))
                {
                    streamWriter.Write(str);
                }
            }
        }
    }
}