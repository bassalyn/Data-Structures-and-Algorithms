import sys  #Author Bradley Assaly-Nesrallh (250779140)


def insertionSort(array):                 #implement insertionshort for a given array
    for index in range(1, len(array)):
        element = array[index]             #loops through array and sorts each element 
        next = index - 1
        while next >= 0 and element < array[next]:
            array[next + 1] = array[next]         #checks if each element is in correct position then stops
            next -= 1
        array[next + 1] = element


arr = list(range(int(sys.argv[1]),0,-1))      #populates list from n using inputed args
print('Before:')
for i in range(0,20):         #prints first 20 elements before and after sorting with insertion sort
    print(arr[i], end=" ")
insertionSort(arr)
print('')
print('After:')
for i in range(0,20):
    print(arr[i], end=" ")
print('')
