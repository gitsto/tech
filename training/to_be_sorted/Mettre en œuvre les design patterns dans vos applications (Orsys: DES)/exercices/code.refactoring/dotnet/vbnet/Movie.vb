Class Movie
    Public Const CHILDRENS As Integer = 2
    Public Const REGULAR As Integer = 0
    Public Const NEW_RELEASE As Integer = 1

    Private _title As [String]
    Private _priceCode As Integer

    Public Sub New(ByVal title As [String], ByVal priceCode As Integer)
        _title = title
        _priceCode = priceCode
    End Sub

    Public Function GetPriceCode() As Integer
        Return _priceCode
    End Function
    Public Sub SetPriceCode(ByVal arg As Integer)
        _priceCode = arg
    End Sub
    Public Function GetTitle() As [String]
        Return _title
    End Function
End Class

