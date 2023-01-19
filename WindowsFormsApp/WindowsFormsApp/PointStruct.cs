namespace WindowsFormsApp
{
    // 结构类型是较为简单的类型，其主要目的是存储数据值。 
    // 结构不能声明基类型；它们从 System.ValueType 隐式派生。
    // 不能从 struct 类型派生其他 struct 类型。 这些类型已隐式密封。
    public struct PointStruct
    {
        public double X { get; }
        public double Y { get; }

        public static void test()
        {
            PointStruct pointStruct = new PointStruct(1, 2);
        }
        public PointStruct(double x, double y) { X = x; Y = y; }
    }
}