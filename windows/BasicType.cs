using System;
namespace windows
{
    public class BasicType
    {
        public override string ToString() => "This is an object";
        static void Swap(ref int x, ref int y)
        {
            int temp = x;
            x = y;
            y = temp;
        }

        public static void SwapExample()
        {
            int i = 1, j = 2;
            Swap(ref i, ref j);
            Console.WriteLine($"{i} {j}");    // "2 1"
        }
        static void Divide(int x, int y, out int result, out int remainder)
        {
            result = x / y;
            remainder = x % y;
        }

        public static void OutUsage()
        {
            Divide(10, 3, out int res, out int rem);
            Console.WriteLine($"{res} {rem}");  // "3 1"
        }
        delegate double Function(double x);

        class Multiplier
        {
            double _factor;

            public Multiplier(double factor) => _factor = factor;

            public double Multiply(double x) => x * _factor;
        }

        class DelegateExample
        {
            static double[] Apply(double[] a, Function f)
            {
                var result = new double[a.Length];
                for (int i = 0; i < a.Length; i++) result[i] = f(a[i]);
                return result;
            }

            public static void test()
            {
                double[] a = { 0.0, 0.5, 1.0 };
                double[] squares = Apply(a, (x) => x * x);
                double[] sines = Apply(a, Math.Sin);
                Multiplier m = new Multiplier(2.0);
                double[] doubles = Apply(a, m.Multiply);
            }
        }

        public static void test()
        {
            // 有符号整型
            Console.WriteLine("sbyte range:{0},{1}", sbyte.MinValue, sbyte.MaxValue);
            Console.WriteLine("short range:{0},{1}", short.MinValue, short.MaxValue);
            Console.WriteLine("int range:{0},{1}", int.MinValue, int.MaxValue);
            Console.WriteLine("long range:{0},{1}", long.MinValue, long.MaxValue);

            // 无符号整型
            Console.WriteLine("byte range:{0},{1}", byte.MinValue, byte.MaxValue);
            Console.WriteLine("ushort range:{0},{1}", ushort.MinValue, ushort.MaxValue);
            Console.WriteLine("uint range:{0},{1}", uint.MinValue, uint.MaxValue);
            Console.WriteLine("ulong range:{0},{1}", ulong.MinValue, ulong.MaxValue);

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
            int i = 123;
            object o = i;    // Boxing
            int j = (int)o;  // Unboxing

            var turnip = SomeRootVegetable.Turnip;
            var spring = Seasons.Spring;
            var startingOnEquinox = Seasons.Spring | Seasons.Autumn;
            var theYear = Seasons.All;
            Console.WriteLine(turnip + " " + spring + " " + startingOnEquinox + " " + theYear);
        }
    }
}