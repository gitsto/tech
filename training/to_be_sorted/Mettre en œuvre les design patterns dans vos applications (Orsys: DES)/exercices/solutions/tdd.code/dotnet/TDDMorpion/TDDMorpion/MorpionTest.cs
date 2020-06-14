using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using NUnit.Framework;

namespace TDDMorpion
{
    [TestFixture]
    class MorpionTest
    {
        private MorpionBoard theBoard;

        [SetUp]
        public void SetUp()
        {
            theBoard  = new MorpionBoard();
        }

        [Test]
        public void TestCreateBoard()
        {
           
            Assert.IsNotNull(theBoard);
            Assert.IsFalse(theBoard.GameOver);
        }
        [Test]
        public void TestSetFirstPlayer()
        {
          
            theBoard.FirstPlayerIsX = true;
            Assert.IsTrue(theBoard.FirstPlayerIsX);
            theBoard.FirstPlayerIsX = false;
            Assert.IsFalse(theBoard.FirstPlayerIsX);
        }

        [Test]
        public void TestFirstMarque()
        {
            theBoard.FirstPlayerIsX = true;
            theBoard.PlaceMarque(0, 1);
            Assert.IsTrue(theBoard.PlaceAtPositionIsX(0, 1));
        }

        [Test, ExpectedException(typeof (BoardException))]
        public void TestMarqueAtOccupiedPosition()
        {
            theBoard.FirstPlayerIsX = true;
            theBoard.PlaceMarque(0, 1);
            theBoard.PlaceMarque(0, 1);
        }

         [Test, ExpectedException(typeof(BoardException))]
        public void TestOutOfRangeColumn()
        {
            theBoard.FirstPlayerIsX = true;
            theBoard.PlaceMarque(2, 6);
        }
         [Test, ExpectedException(typeof(BoardException))]
         public void TestOutOfRangeRow()
         {
             theBoard.FirstPlayerIsX = true;
             theBoard.PlaceMarque(-1, 6);
         }
    }
}
