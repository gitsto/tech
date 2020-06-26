Class Rental

    Private _movie As Movie
    Private _daysRented As Integer

    Public Sub New(ByVal movie As Movie, ByVal daysRented As Integer)
        _movie = movie
        _daysRented = daysRented
    End Sub
    Public Function GetDaysRented() As Integer
        Return _daysRented
    End Function
    Public Function GetMovie() As Movie
        Return _movie
    End Function
End Class
