using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TDDMorpion
{
    class BoardException : Exception
    {
        public BoardException(string msg)
            : base(msg)
        {
        }
    }
}
