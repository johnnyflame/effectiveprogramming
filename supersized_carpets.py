



"""

Super Size

improved carpets



1. initialize a candidate solution (put it in a list/queue)
2. Evaluate the cost of candidate solution
3. Recursively search other combinations for better candidates
4. Pruning off bad ends.


"""

    
def better_max_match(input_queue):
    global cost
    global result

 
  
    for i in range(0,target_size):
        result.append(input_queue[i])
        if i > 0:
            cost = cost + find_non_matches(input_queue[i-1],input_queue[i])
            print "\niteration: ", i, "\nResult list:"
        for element in result:
            print element
        print "number of mismatches: ", cost

    print "\ntotal mismatches: ", cost

    for i in range (0,len(input_queue)):
        carpet_in_progress = []
      
"""
TODO: 

Return here tomorrow




"""


def recursive_max(carpet_in_progress, next_piece, cost_so_far,available_stock):
    global cost
    global result

    if len(carpet_in_progress) != 0:
        cost_so_far = cost_so_far + find_non_matches(carpet_in_progress[-1],next_piece)
        if cost_so_far >= cost:    
            return
    
    carpet_in_progress.append(next_piece)
    if len (carpet_in_progress) == target_size:
        cost = cost_so_far
        result = carpet_in_progress
        return
    return
    
