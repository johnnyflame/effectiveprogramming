import sys
import itertools

x = [1,2,3]
y = 9 

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
        result = input_list[0]
        operators = []
        tmp = 1
        for j in range(0,len(input_list)-1):
            if i[j] == 1:
                print "*"
                input_list[i]
               
            else:
                print '+'
                result = result + input_list[j + 1] + tmp
                tmp = 0
            print "Current combination: ",i , "Current result: ", result



eval_normal(x,y)

 
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

        """
        print NL + "",
        searchSolution(input_list,target,left_to_right)
"""


   





 



""" 
*assuming the input is not corrupted (Error handling later)

1. add all tokens from line 1 into a list of ints
2. add first token of line 2 to the variable target
3. add second token of line 2 to a boolean

*Algorithm for generating permutation

Normal is done

Now, left to right:


From a list of all operators and numbers:

1. pop first 3 elements
2. evaluate them(maybe in a seperate function)
3. add the result back to the list
4. repeat until len(list) == 1

"""
'''

            if i[j] == 0:
                result += input_list[j+1]
                print "+"
                print result
                print "i[j]", i[j]
                print "i", i
                print "j", j
                
            elif i[j] == 1:
                result *= input_list[j+1]
                print "*"
                print result



        if result == target:
            print "TARGET: ", target
            print input_list
            operators = ""
            for num in i:
                if i[num] == 0:
                    operators += '+'
                else:
                    operators += '*'


                print operators
                
        print "impossible"
        return
'''
