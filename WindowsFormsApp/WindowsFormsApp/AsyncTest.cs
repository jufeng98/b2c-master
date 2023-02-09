using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace WindowsFormsApp
{
    public class AsyncTest
    {
        public static void Test()
        {
            AsyncTest a = new AsyncTest();
            var task = a.RetrieveDocsHomePage();
            Console.WriteLine("length:" + task.GetAwaiter().GetResult());
        }

        public async Task<int> RetrieveDocsHomePage()
        {
            var client = new HttpClient();
            byte[] content = await client.GetByteArrayAsync("https://www.baidu.com/");
            Console.WriteLine($"{nameof(RetrieveDocsHomePage) }: Finished downloading. ");
            return content.Length;
        }
    }
}
