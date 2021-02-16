import java.util.Hashtable;

public class HashDictionary implements DictionaryADT{

    private LLNode[] dictionary;
    private int size;                           //initiate instance variables

    public HashDictionary(int size){            //constructor for Hashdictionary
        this.size = size;
        dictionary = new LLNode[size];
        for (int i=0;i<size;i++){               //initailizes all the values as null
            dictionary[i]=null;
        }
    }

    public int PolynomialHash(String s){        //polynomial hash function
        int hash =0;
        int x = 33;
        for (int i=0; i<s.length();i++){
            hash = (x*hash + s.charAt(i))%this.size;  //calculate hash with horners rule Modulo size of the dictionary
        }
        return hash;
    }

    @Override
    public int put(Configuration data) throws DictionaryException {     //puts a configuration into the dictionary
        int hash = PolynomialHash(data.getStringConfiguration());
        if (dictionary[hash]==null) {
            dictionary[hash] = new LLNode(data);
            return 0;                                                       //if empty adds a new node with the data
        }
        else{
            LLNode listnode = dictionary[hash];
            if (listnode.getData().getStringConfiguration().equals(data.getStringConfiguration())){
                throw new DictionaryException("Configuration already in the dictionary");
            }
            while (!(listnode.getNext()== null) && !(listnode.getData().getStringConfiguration().equals(data.getStringConfiguration()))){
                listnode = listnode.getNext();                                  //loops through the linked list
            }
            if (listnode.getData().equals(data)){
                throw new DictionaryException("Configuration already in the dictionary");
            }else {
                listnode.setNext(new LLNode(data));    //creates a new node with the data in the linked list
                return 1;
            }
        }
    }

    @Override
    public void remove(String config) throws DictionaryException {          //removes config from the dictionary
        int hash = PolynomialHash(config);
        if (dictionary[hash]==null){                                //if not there throws exception
            throw new DictionaryException("Configuration not in the dictionary");
        }else{
            LLNode listnode = dictionary[hash];
            while (!(listnode.getNext() == null) && !(listnode.getData().getStringConfiguration().equals(config))){
                listnode = listnode.getNext();                  //loops through the linked list
            }
            if (listnode.getData().getStringConfiguration().equals(config)){
                LLNode root = dictionary[hash];                             //removes the node from the linked list
                root.removeNode(root,config);
            }else {
                throw new DictionaryException("Configuration not in the dictionary");
            }
        }


    }

    @Override
    public int getScore(String config) {                    //gets the score of the configuration
        int hash = PolynomialHash(config);
        if (dictionary[hash]==null){
            return -1;                                      //returns -1 if not there
        }else{
            LLNode listnode = dictionary[hash];                     //loops through the array
            while (!(listnode.getNext() == null) && !(listnode.getData().getStringConfiguration().equals(config))){
                listnode = listnode.getNext();
            }
        if (listnode.getData().getStringConfiguration().equals(config)){
            return listnode.getData().getScore();           //if there returns the score of the configuration
        }else {
            return -1;
        }
    }
    }
}
