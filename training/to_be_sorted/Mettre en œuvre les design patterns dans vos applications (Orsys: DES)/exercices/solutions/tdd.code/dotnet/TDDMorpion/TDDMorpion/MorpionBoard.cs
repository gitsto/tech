using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TDDMorpion
{
    class MorpionBoard
    {
        private string[,] tabs = {{"","",""},{"","",""},{"","",""}};

        private bool nextPlayerIsX;

        public bool GameOver { get { return false; } set { ;} }

        public bool FirstPlayerIsX { get { return nextPlayerIsX ; } set {nextPlayerIsX = value;} }

        public void PlaceMarque(int p, int p_2)
        {
            if (p_2 < 0 || p_2 > 3)
            {
                throw new BoardException("Out of column range !");
            }
            if (p < 0 || p > 3)
            {
                throw new BoardException("Out of column range !");
            }
            if(tabs[p,p_2] != String.Empty)
            {
                throw new BoardException ("Place deja prise !");
            }

            if (nextPlayerIsX) tabs[p, p_2] = "X";
            else tabs[p, p_2] = "O";
        }

        public bool PlaceAtPositionIsX(int p, int p_2)
        {
           
            return tabs[p, p_2] == "X";
        }
    }
}
