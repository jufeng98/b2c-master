using System;
namespace WindowsFormsApp
{
    public class BasicType
    {
        public override string ToString() => "This is an object";

        public static void Test()
        {
            // 有符号整型
            Console.WriteLine("sbyte range:{0},{1}", sbyte.MinValue, sbyte.MaxValue);// sbyte range:-128,127
            Console.WriteLine("short range:{0},{1}", short.MinValue, short.MaxValue);// short range:-32768,32767
            Console.WriteLine("int range:{0},{1}", int.MinValue, int.MaxValue);// int range:-2147483648,2147483647
            Console.WriteLine("long range:{0},{1}", long.MinValue, long.MaxValue);// long range:-9223372036854775808,9223372036854775807

            // 无符号整型
            Console.WriteLine("byte range:{0},{1}", byte.MinValue, byte.MaxValue);// byte range:0,255
            Console.WriteLine("ushort range:{0},{1}", ushort.MinValue, ushort.MaxValue);// ushort range:0,65535
            Console.WriteLine("uint range:{0},{1}", uint.MinValue, uint.MaxValue);// uint range:0,4294967295
            Console.WriteLine("ulong range:{0},{1}", ulong.MinValue, ulong.MaxValue);// ulong range:0,18446744073709551615

            // Unicode 字符
            char c = 'c';
            Console.WriteLine("char range:{0},{1},{2}", char.MinValue, char.MaxValue, c);
            Console.WriteLine("float range:{0},{1}", float.MinValue, float.MaxValue);
            Console.WriteLine("double range:{0},{1}", double.MinValue, double.MaxValue);
            Console.WriteLine("decimal range:{0},{1}", decimal.MinValue, decimal.MaxValue);
            Console.WriteLine("bool range:{0},{1}", bool.TrueString, bool.FalseString);

            object obj = null;
            Console.WriteLine("object null:{0}", obj);
            string name = "jufeng98";
            int age = 22;
            string s = $"{name} {age}";
            Console.WriteLine("字符串内插:{0}", s);
            DateTime dateTime = DateTime.Now;
            Console.WriteLine($"The low and high temperature on {dateTime:MM-DD-YYYY}");
            int a = 123;
            object o = a;    // Boxing
            int b = (int)o;  // Unboxing          
            int i = 1, j = 2;
            Swap(ref i, ref j);
            Console.WriteLine($"{i} {j}");    // "2 1"
            Console.WriteLine(new BasicType()); // This is an object
            int res, rem;
            Divide(10, 3, out res, out rem);
            Console.WriteLine($"{res} {rem}");  // "3 1"
            Console.WriteLine(Sum(1, 2));  // 3
            Console.WriteLine(Sum(1, 2, 3));// 6
        }

        static void Swap(ref int x, ref int y)
        {
            int temp = x;
            x = y;
            y = temp;
        }

        static void Divide(int x, int y, out int result, out int remainder)
        {
            result = x / y;
            remainder = x % y;
        }

        static int Sum(params int[] a)
        {
            int sum = 0;
            foreach (var i in a)
            {
                sum += i;
            }
            return sum;
        }   

    }
}