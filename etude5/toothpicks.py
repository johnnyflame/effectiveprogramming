from Tkinter import *

window_width = 1000
window_height = 1000

master = Tk()

master.title("Toothpicks")
w = Canvas(master, width=window_width, height=window_height,bg = "white")
w.pack()



length = window_width / 10

x1 = window_width/2 - length/2
y1 = window_height/2
x2 = window_width/2 + length/2
y2 = window_height/2
ratio = 1.5


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


        


toothpick_placement(5,x1,y1,x2,y2,length)


def set_length(generation,ratio):
    length = 1
    for i in range(1,generation):
        




"""
TODO:

1. Add scale abilities for both window and ratio

NOTE:

starting length should be a function of generation and (optionally) the ratio

function set_length(int generation, int ratio)

if ratio < 1:
  Make length big(ish) and window smallish
if ratio > 1:
  Make window big and length small(ish)


2. Parse input from command-line

"""






mainloop()
