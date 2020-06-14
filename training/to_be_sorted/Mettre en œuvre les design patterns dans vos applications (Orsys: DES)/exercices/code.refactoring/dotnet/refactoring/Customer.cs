using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace csharp
{
    class Customer
    {
        private String _name;
        private List<Rental> _rentals = new List<Rental>();

        public Customer(String name)
        {
            _name = name;
        }
        public void AddRental(Rental arg)
        {
            _rentals.Add(arg);
        }
        public String GetName()
        {
            return _name;
        }
        public String Statement()
        {
            double totalAmount = 0;
            int frequentRenterPoints = 0;
            IEnumerator<Rental> rentals = _rentals.GetEnumerator();
            String result = "Rental Record for " + GetName() + "\n";

            while (rentals.MoveNext())
            {
                double thisAmount = 0;
                Rental each = (Rental)rentals.Current;
                switch (each.GetMovie().GetPriceCode())
                {
                    case Movie.REGULAR:
                        thisAmount += 2;
                        if (each.GetDaysRented() > 2)
                            thisAmount += (each.GetDaysRented() - 2) * 1.5;
                        break;
                    case Movie.NEW_RELEASE:
                        thisAmount += each.GetDaysRented() * 3;
                        break;
                    case Movie.CHILDRENS:
                        thisAmount += 1.5;
                        if (each.GetDaysRented() > 3)
                            thisAmount += (each.GetDaysRented() - 3) * 1.5;
                        break;
                }
                frequentRenterPoints++;
                if ((each.GetMovie().GetPriceCode() == Movie.NEW_RELEASE) &&
                    each.GetDaysRented() > 1) frequentRenterPoints++;
                result += "\t" + each.GetMovie().GetTitle() + "\t" +
                    thisAmount + "\n";
                totalAmount += thisAmount;
            }
            result += "Amount owed is " + totalAmount + "\n";
            result += "You earned " + frequentRenterPoints +
                "frequent renter points";
            return result;
        }
    }
}
