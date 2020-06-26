#include "StdAfx.h"
#include "Movie.h"


Movie::Movie(System::String^ title, int priceCode) {
	    _title = title;
	    _priceCode = priceCode;
	    }

int Movie::GetPriceCode() {
		    return _priceCode;
}
void Movie::SetPriceCode(int arg) {
		    _priceCode = arg;
	    }
System::String^ Movie::GetTitle() {
	    	return _title;
	    }
