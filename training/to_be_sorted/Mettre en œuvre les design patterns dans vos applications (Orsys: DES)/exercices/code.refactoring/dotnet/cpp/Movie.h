#pragma once
ref class Movie
{
public:
		 static const int CHILDRENS = 2;
	     static const int REGULAR = 0;
	     static const int NEW_RELEASE = 1;
private:
	     System::String^ _title;
	     int _priceCode;
	
public :
	     Movie(System::String^ title, int priceCode);
	     int GetPriceCode();
	     void SetPriceCode(int arg);
	     System::String^ GetTitle() ;
};

