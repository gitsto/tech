﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace csharp
{
    class Movie
    {
        public const int CHILDRENS = 2;
	    public const int REGULAR = 0;
	    public const int NEW_RELEASE = 1;
	
	    private String _title;
	    private int _priceCode;
	
	    public Movie(String title, int priceCode) {
	    _title = title;
	    _priceCode = priceCode;
	    }

	    public int GetPriceCode() {
		    return _priceCode;
    	}
	    public void SetPriceCode(int arg) {
		    _priceCode = arg;
	    }
	    public String GetTitle() {
	    	return _title;
	    }
    }
}
