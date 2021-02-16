import sys  #Author: Bradley Assaly-Nesrallah (250779140)


def mergeSort(array):       #implement mergesort in python for given array
    if len(array) > 1:
        middle = len(array)//2    #splits the arrays until size one and calls function
        L = array[:middle]
        R = array[middle:]

        mergeSort(L)
        mergeSort(R)
        i = j = k = 0        #sets counters to 0

        while i < len(L) and j < len(R):
            if L[i] < R[j]:            #takes two lists and sorts them into a larger list
                array[k] = L[i]
                i+=1
            else:
                array[k] = R[j]
                j+=1
            k+=1

        while i< len(L):           #if an array is leftover fills the rest of the list
            array[k] = L[i]
            i+=1
            k+=1

        while j< len(R):
            array[k] = R[j]
            j+=1
            k+=1


arr = list(range(int(sys.argv[1]),0,-1))    #populates list from n to 1
print('Before:')
for i in range(0,20):
    print(arr[i], end= " ")    #prints before and after mergesort first 20 elements of array
mergeSort(arr)
print('')
print('After:')
for i in range(0,20):
    print(arr[i], end = " ")
print('')
