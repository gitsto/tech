#pragma once

ref class Movie;

ref class Rental
{

private :
	 Movie^ _movie;
     int _daysRented;

public :

       Rental(Movie^ movie, int daysRented);
       int GetDaysRented();
       Movie^ GetMovie();
};

