﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace csharp
{
    class Rental
    {

        private Movie _movie;
        private int _daysRented;

        public Rental(Movie movie, int daysRented)
        {
            _movie = movie;
            _daysRented = daysRented;
        }
        public int GetDaysRented()
        {
            return _daysRented;
        }
        public Movie GetMovie()
        {
            return _movie;
        }
    }
}
