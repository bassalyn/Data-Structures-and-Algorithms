public class heap { //implementation of min heap data structure in java using array of indexes

    private int[] keys;  //declare variables, array of keys
    private int[] heap;  //heap of indexes
    private int size;    //size of the keys and count for decrements
    private int count;

    public heap(int[] keys, int n){   //heapification for min heap array of keys of size n
        this.keys = keys;
        this.size = n;
        this.count = size;           //initialize variables
        this.heap = new int[2*n];    //setup heap of size 2n
        for (int i = n; i < (2*n) ; i++){  //fill the branches of the min heap
            heap[i] = i - n + 1;
        }
        for (int i = n-1; i >= 1 ; i--){     //heapify the min heap by comparing elements
            if (keys[heap[2*i]] < keys[heap[2*i+1]]){
                heap[i] = heap[2*i];
            }else{
                heap[i] = heap[2*i+1];
            }
        }
    }

    public boolean in_heap(int id){         //function to determine if an id is in the heap
        for (int i = 0; i< size*2 ; i++){
            if (heap[i] == id){             //checks returns true if so false otherwise
                return true;
            }
        }
        return false;
    }

    public int min_key(){
        return keys[heap[1]];               //returns the minimum key
    }

    public int min_id(){                    //returns the minimum id
        return heap[1];
    }

    public int key(int id){                 //returns a key for a given id
        return keys[id];
    }

    public int delete_min(){                //deletes amd returns the minimum in the heap
        keys[0] = Integer.MAX_VALUE;
        heap[heap[1]+ size -1] = 0;         //sets value to zero
        int v = keys[heap[1]];              //saves the minimum value to return
        int i = (int) Math.floor((heap[1]+size-1)/2);  //uses floor function to round the id down
        while (i>= 1){
            if (keys[heap[2*i]]<keys[heap[2*i+1]]){     //now removes the minimum from the heap
                heap[i] = heap[2*i];                    //updates the heap
            }else{
                heap[i] = heap[2*i+1];
            }
            i = (int) Math.floor((i/2));
        }
        count = count-1;                               //increments the counter for number of deletions
        return v;                                      //returns the minimum value from the heap
    }

    public void decrease_key(int id, int new_key){     //function decreases a key for a given id if less than current
        if (keys[id] > new_key){                       //checks if less than current
            keys[id] = new_key;
            id = (int) Math.floor((id + size-1)/2);    //uses floor function to round id down
            while (id >= 1){
                if (keys[heap[2*id]]<keys[heap[2*id+1]]){  //decreases the key then updates the heap
                    heap[id] = heap[2*id];
                }else{
                    heap[id] = heap[2*id +1];
                }
                id = (int) Math.floor(id/2);
            }
        }
    }

    public boolean is_empty(){                         //boolean function determines if the heap is empty
        if (count < 1){
            return true;                               //returns true if so false otherwise
        }else{
            return false;
        }
    }

}
