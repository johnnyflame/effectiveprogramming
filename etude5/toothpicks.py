from Tkinter import *
import sys
import math


"""
COSC326 JAN 2016

toothpicks.py written in Python 2.7

Author:
Johnny Flame Lee

ID:
83924

"""

window_width = 1000
window_height = 1000
border = 50

master = Tk()

master.title("Toothpicks")
w = Canvas(master, width=window_width, height=window_height,bg = "white")
w.pack()


def set_length(generation,ratio):
    denomonator = 1
    for i in range(2,generation):
        if i % 2 == 0:
            denomonator = denomonator + ratio ** i
        else:
            denomonator = denomonator + ratio ** (i-1)

    return (window_width - border) / denomonator


def toothpick_placement(n,x1,y1,x2,y2,length):
    
    w.create_line(x1,y1,x2,y2)
    if(n==0):
        return 
    length *= ratio
    if y1 == y2:
        toothpick_placement(n - 1,x1,y1 - length/2,x1,y2 + length/2,length)
        toothpick_placement(n - 1,x2,y1 - length/2,x2, y2 + length/2,length)        
    else:
        toothpick_placement(n - 1,x1 - length/2,y1,x2 + length/2,y1,length)
        toothpick_placement(n - 1,x1 - length/2,y2,x2 + length/2,y2,length)




        
generation = 0
ratio = 1
generation = int(sys.argv[1])
if len(sys.argv) == 3:
    ratio = float(sys.argv[2])

length = set_length(generation,ratio)

x1 = window_width/2 - length/2
y1 = window_height/2
x2 = window_width/2 + length/2
y2 = window_height/2


toothpick_placement(generation,x1,y1,x2,y2,length)

mainloop()
