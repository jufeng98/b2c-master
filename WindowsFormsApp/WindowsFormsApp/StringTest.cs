using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp
{
    public class StringTest
    {
        public static void Test()
        {
            Console.WriteLine($"The low and high temperature on {DateTime.Now: YYYY-MM-DD}");
            Console.WriteLine(@"C:\destop\yu");
            Console.WriteLine(@"hello ,
today is fine,
     world
");
        }
    }
}
