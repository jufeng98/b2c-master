using System;
using System.IO;
namespace windows
{
    public class FileTest
    {
        public static void testWrite()
        {
            try
            {
                using (var sw = new StreamWriter("G://test.txt"))
                {
                    sw.WriteLine("Hello");
                }
            }
            // Put the more specific exceptions first.
            catch (DirectoryNotFoundException ex)
            {
                Console.WriteLine(ex);
            }
            catch (FileNotFoundException ex)
            {
                Console.WriteLine(ex);
            }
            // Put the least specific exception last.
            catch (IOException ex)
            {
                Console.WriteLine(ex);
            }
            Console.WriteLine("Done");
        }
        
        public static void testWrite1()
        {
            // #nullable enable
            FileStream file = null;
            //Change the path to something that works on your machine.
            FileInfo fileInfo = new System.IO.FileInfo("G://test.txt");

            try
            {
                file = fileInfo.OpenWrite();
                file.WriteByte(0xF);
            }
            finally
            {
                // Closing the file allows you to reopen it immediately - otherwise IOException is thrown.
                file?.Close();
            }

            try
            {
                file = fileInfo.OpenWrite();
                Console.WriteLine("OpenWrite() succeeded");
            }
            catch (IOException)
            {
                Console.WriteLine("OpenWrite() failed");
            }
        }
    }
}