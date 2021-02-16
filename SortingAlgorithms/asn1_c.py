import sys  #Author: Bradley Assaly-Nesrallah (250779140)


def insertionSort(array):               #insertionsort for a given array, same as in part a
    for index in range(1, len(array)):
        element = array[index]
        next = index - 1
        while next >= 0 and element < array[next]:
            array[next + 1] = array[next]
            next -= 1
        array[next + 1] = element



def mergeSort(array, k):        #mergesort algorith with two inputs array to sort and k for size min size
    if len(array) <= k:
        insertionSort(array)     #if array is less that k then insertionsort the array
    else:
        middle = len(array)//2
        L = array[:middle]     #otherwise mergesorts the array as in part b
        R = array[middle:]

        mergeSort(L, k)
        mergeSort(R, k)
        i = j = k = 0

        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                array[k] = L[i]
                i+=1
            else:
                array[k] = R[j]
                j+=1
            k+=1

        while i< len(L):
            array[k] = L[i]
            i+=1
            k+=1

        while j< len(R):
            array[k] = R[j]
            j+=1
            k+=1


arr = list(range(int(sys.argv[1]),0,-1))    #populates the array from n to 1
print('Before:')
for i in range(0,20):
    print(arr[i],end = " ")                 #prints array before and after running modified sort on it for first 20 elements
mergeSort(arr, int(sys.argv[2]))
print('')
print('After:')
for i in range(0,20):
    print(arr[i],end = " ")
print('')
