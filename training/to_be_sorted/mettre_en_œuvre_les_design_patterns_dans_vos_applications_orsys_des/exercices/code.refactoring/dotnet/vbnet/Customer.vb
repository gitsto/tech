Class Customer
    Private _name As [String]
    Private _rentals As New List(Of Rental)()

    Public Sub New(ByVal name As [String])
        _name = name
    End Sub
    Public Sub AddRental(ByVal arg As Rental)
        _rentals.Add(arg)
    End Sub
    Public Function GetName() As [String]
        Return _name
    End Function
    Public Function Statement() As [String]
        Dim totalAmount As Double = 0
        Dim frequentRenterPoints As Integer = 0
        Dim rentals As IEnumerator(Of Rental) = _rentals.GetEnumerator()
        Dim result As [String] = "Rental Record for " & Convert.ToString(GetName()) & vbLf

        While rentals.MoveNext()
            Dim thisAmount As Double = 0
            Dim [each] As Rental = DirectCast(rentals.Current, Rental)
            Select Case [each].GetMovie().GetPriceCode()
                Case Movie.REGULAR
                    thisAmount += 2
                    If [each].GetDaysRented() > 2 Then
                        thisAmount += ([each].GetDaysRented() - 2) * 1.5
                    End If
                    Exit Select
                Case Movie.NEW_RELEASE
                    thisAmount += [each].GetDaysRented() * 3
                    Exit Select
                Case Movie.CHILDRENS
                    thisAmount += 1.5
                    If [each].GetDaysRented() > 3 Then
                        thisAmount += ([each].GetDaysRented() - 3) * 1.5
                    End If
                    Exit Select
            End Select
            frequentRenterPoints += 1
            If ([each].GetMovie().GetPriceCode() = Movie.NEW_RELEASE) AndAlso [each].GetDaysRented() > 1 Then
                frequentRenterPoints += 1
            End If
            result += vbTab & [each].GetMovie().GetTitle() & vbTab & thisAmount & vbLf
            totalAmount += thisAmount
        End While
        result += "Amount owed is " & totalAmount & vbLf
        result += "You earned " & frequentRenterPoints & "frequent renter points"
        Return result
    End Function
End Class

