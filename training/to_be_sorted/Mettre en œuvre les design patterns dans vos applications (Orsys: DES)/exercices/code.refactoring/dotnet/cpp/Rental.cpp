#include "StdAfx.h"
#include "Rental.h"


Rental::Rental(Movie^ movie, int daysRented)
        {
            _movie = movie;
            _daysRented = daysRented;
        }
int Rental::GetDaysRented()
        {
            return _daysRented;
        }
Movie^ Rental::GetMovie()
        {
            return _movie;
        }