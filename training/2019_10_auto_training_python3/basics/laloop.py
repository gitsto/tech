
def first_program(time):
    """[summary]

    Arguments:
        time {[hours]} -- [description]
    """

    if 6 <= time <= 12:
        print("It is '" + str(time) + " o'clock, so: " + "Good Morning\n")
    elif 12 <= time <= 18:
        print("It is " +  str(time) + " o'clock, so: " + "Good Afternoon\n")
    else:
        print("It is " +  str(time) + " o'clock, so: " + "Good Evening\n")

if __name__ == "__main__":
        first_program(10)
        first_program(5)
        first_program(15)
