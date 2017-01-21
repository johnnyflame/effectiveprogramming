from Tkinter import *

window_width = 1000
window_height = 1000

master = Tk()

master.title("Toothpicks")
w = Canvas(master, width=window_width, height=window_height,bg = "white")
w.pack()

# create line(x1,y1,x2,y2)
#initial coordinates


length = 100
width = 2

x1 = window_width/2 - length/2
y1 = window_height/2
x2 = window_width/2 + length/2
y2 = window_height/2


def toothpick_placement(n):
    if n == 1:
        w.create_line(x1,y1,x2,y2,width = width)
    else:
        w.create_line()



toothpick_placement(1)







"""
Recursively draw lines


idea:

base case:

when n == 1, return/draw the first line near the centre of the screen

draw lines such that line(n-1)'s ends are covered, in perpendicular 
orientation

"""






mainloop()
