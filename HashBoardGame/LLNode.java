public class LLNode {

    private Configuration data;
    private LLNode next;            //initiate instance variable

    public LLNode(Configuration data) {
        this.data = data;                       //constructor for the node, sets next as null
        this.next = null;
    }

    public Configuration getData() {
        return data;
    }       //getter for the data

    public LLNode getNext() {
        return next;
    }               //getter for next reference

    public void setNext(LLNode next) {                  //setter to set next node in LL
        this.next = next;
    }

    public void removeNode(LLNode root, String data) {
        LLNode tempNode = null;                         //removes a node from the linked list
        if (root == null) {
            return;
        }
        if (root.getData().getStringConfiguration().equals(data)) {
            tempNode = root;                              //removes the node from the Linked list
            root = null;
            return;
        }
        while (!(root.next == null)) {                  //loops through the linked list
            if (root.next.getData().getStringConfiguration().equals(data)) {
                tempNode = root.next;
                root.next = root.next.next;
                break;
            }
            root = root.next;
        }
        return;
    }
}

