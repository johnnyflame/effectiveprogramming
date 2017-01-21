import Tkinter as tk

root = tk.Tk()
root.geometry("500x500")
root.title("Drawing lines to a canvas")

cv = tk.Canvas(root,height="500",width="500",bg="white")
cv.pack()

def linemaker(screen_points):
    """ Function to take list of points and make them into lines
    """
    is_first = True
    # Set up some variables to hold x,y coods
    x0 = y0 = 0
    # Grab each pair of points from the input list
    for (x,y) in screen_points:
        # If its the first point in a set, set x0,y0 to the values
        if is_first:
            x0 = x
            y0 = y
            is_first = False
        else:
            # If its not the fist point yeild previous pair and current pair
            yield x0,y0,x,y
            # Set current x,y to start coords of next line
            x0,y0 = x,y

list_of_screen_coods = [(50,250),(150,100),(250,250),(350,100)]

for (x0,y0,x1,y1) in linemaker(list_of_screen_coods):
    cv.create_line(x0,y0,x1,y1, width=1,fill="red")

root.mainloop()
