namespace WindowsFormsApp
{
    public class Pair<F, S>
    {
        public F First { get; }
        public S Second { get; }
        public static void test()
        {
            var pair = new Pair<int, string>(1, "two");
            int i = pair.First;     // TFirst int
            string s = pair.Second; // TSecond string
        }
        public Pair(F first, S second) { First = first; Second = second; }

    }
}