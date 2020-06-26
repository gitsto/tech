#pragma once

ref class Rental;

ref class Customer
{
private :
		 System::String^ _name;
         System::Collections::Generic::List<Rental^>^ _rentals;
public :
         Customer(System::String^ name);
        
         void AddRental(Rental^ arg);
         System::String^ GetName();
         System::String^ Statement();
};

