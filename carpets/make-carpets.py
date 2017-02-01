from collections import deque
import fileinput
import copy
'''

'''

q = deque()
result = []
target_size = 4
tries = 0
max_matches = 0
current_matches = 0

"""
no_match

Use backtrack search? DFS? 
"""

# returns False if any matching squares are found
def found_match(list_1,list_2):
    return any (i == j for i, j in zip(list_1,list_2))

def no_match(input_queue,result,tries,input_length):

    #base case 1: if result is the same size as target, return
    if len(result) == target_size:
        for element in result:
            print element
        return
    #how to design stop case?
    if tries > input_length:
        print "not possible"
        return

    if not result:
        result.append(input_queue.popleft())
        
        return no_match(input_queue,result,tries,input_length)
    
    current_item = input_queue.popleft()

    if found_match(result[-1],current_item):
        print "matched, try reversing"
        current_item.reverse()       
        if found_match(result[-1],current_item):         
            tries = tries + 1
            input_queue.append(current_item)
            return no_match(input_queue,result,tries,input_length)
    
   
    result.append(current_item)
    return no_match(input_queue,result,tries,input_length)

        


def find_matches(list_1,list_2):
    count = 0

    for i, j in zip(list_1,list_2):
        if i == j:
            count = count + 1    
    return count

    
def max_match(input_queue,result,input_length):
    if len(result) == target_size:      
        return result

    # base case: output is empty, push the first value from input to output
    if not result:
        result.append(input_queue.popleft())
        return max_match(input_queue,result,tries,input_length)

    current_item = input_queue.popleft() # take the first item off input queue
    item_reverse = copy.copy(current_item)
    item_reverse.reverse()


    if (find_matches(result[-1],current_item) > 0 and
        find_matches (result[-1],current_item) >= find_matches(result,current_item.reverse())):
        
        input_queue.append(current_item)
    else:
        current_item.reverse()
        if find_matches (result[-1],current_item) > 0:
            result.append(current_item)
            return max_match(input_queue,result,tries,input_length)


    input_queue.append(current_item)
    return no_match(input_queue,result,tries,input_length)


def main():

    # Reads the input line by line and insert into a queue 
    for line in fileinput.input():
        carpet_strip = []
        line = line.replace("\n","") # Might not be neccesary if the input has no \n character
        for char in line:
            carpet_strip.append(char)
        q.append(carpet_strip)


    no_match(q,result,tries,len(q))

    max_match(q,result,tries,len(q))

    "Next step will be determined by the flag from input"


main()
