import re

print("Our calculator")
print("Type quit to exit\n")

previous  = 0
run = True

def performMath():
    global run, previous
    
    equation = input("Enter equation:")

    if equation == 'quit':
        run = False
    else:
        previous = eval(equation)
        print("You typed", previous)

while run: 
    performMath()
