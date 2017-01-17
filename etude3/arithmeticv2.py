import sys
import itertools

"""
Python 2.7

COSC 326 etude 3
Arithmetic 

Jan 2016

Author: Johnny Flame Lee
ID: 83924
"""


def eval_left_to_right(input_list,target):    

    for i in itertools.product([0,1], repeat=len(input_list) - 1):
        result = input_list[0]
        operators = [] 
        for j in range(0,len(input_list)-1):
            if i[j] == 0:                               
                result = result + input_list[j + 1]                            
            elif i[j] == 1:                 
                result = result * input_list[j + 1]
        if result == target:
            for j in range(0,len(i)):
                if i[j] == 0:
                    operators.append("+")
                else:
                    operators.append("*")
            
            output_line = [None] * (len(input_list) + len(i))
            output_line[::2] = input_list
            output_line[1::2] = operators
            print ' '.join(map(str, output_line))
            return
            
    print "impossible"
    return


def eval_normal(input_list,target):   
    for i in itertools.product([0,1], repeat=len(input_list) - 1):
        result = 0
        working = input_list[0]
        operators = [] 
        for j in range(0,len(i)):                  
            if i[j] == 0:
                result += working
                working = input_list[j + 1]
            elif i[j] == 1:
                working = working * input_list[j + 1]
    
        result += working
        if result == target:
            for j in range(0,len(i)):
                if i[j] == 0:
                    operators.append("+")
                else:
                    operators.append("*")
            
            output_line = [None] * (len(input_list) + len(i))
            output_line[::2] = input_list
            output_line[1::2] = operators
            print ' '.join(map(str, output_line))
            return
            
    print "impossible"
    return
 
def searchSolution(input_list, target, left_to_right):
    if left_to_right is True:   
        eval_left_to_right(input_list,target)
    else:
        eval_normal(input_list,target)




count = 0
target = 0
left_to_right = False


"""
Code for parsing, not robust against bad input
How do I protect against it?
"""

for line in sys.stdin:
    count = count + 1
    #    print count
    if count % 2 != 0:
        input_list = map(int, line.split(' '))
    else:
        target =  int(line.split()[0])
        NL = line.split()[1]
        left_to_right = True if NL is 'L' else False       
        print NL + "",
        searchSolution(input_list,target,left_to_right)
