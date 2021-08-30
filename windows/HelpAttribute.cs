using System;
namespace windows
{
    public class HelpAttribute : Attribute
    {
        string _url;
        string _topic;

        public HelpAttribute(string url) => _url = url;

        public string Url => _url;

        public string Topic
        {
            get => _topic;
            set => _topic = value;
        }
    }
}