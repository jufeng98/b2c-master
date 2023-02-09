using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp
{
    public class DelegateTest
    {
        public static void Test()
        {
            double[] a = { 0.0, 0.5, 1.0 };
            double[] squares = Apply(a, (x) => x * x);
            double[] sines = Apply(a, Math.Sin);
            DelegateTest m = new DelegateTest();
            double[] doubles = Apply(a, m.Multiply);
        }

        delegate double Function(double x);

        double _factor = 1;

        public double Multiply(double x) => x * _factor;

        static double[] Apply(double[] a, Function f)
        {
            var result = new double[a.Length];
            for (int i = 0; i < a.Length; i++) result[i] = f(a[i]);
            return result;
        }
    }
}
