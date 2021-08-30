using System;
namespace windows
{
    public class Stack<T>
    {
        Entry _top;

        public static void test()
        {
            var s = new Stack<int>();
            s.Push(1); // stack contains 1
            s.Push(10); // stack contains 1, 10
            s.Push(100); // stack contains 1, 10, 100
            Console.WriteLine(s.Pop()); // stack contains 1, 10
            Console.WriteLine(s.Pop()); // stack contains 1
            Console.WriteLine(s.Pop()); // stack is empty
        }
        public void Push(T data)
        {
            _top = new Entry(_top, data);
        }

        public T Pop()
        {
            if (_top == null)
            {
                throw new InvalidOperationException();
            }
            T result = _top.Data;
            _top = _top.Next;

            return result;
        }

        class Entry
        {
            public Entry Next { get; set; }
            public T Data { get; set; }

            public Entry(Entry next, T data)
            {
                Next = next;
                Data = data;
            }
        }
    }
}