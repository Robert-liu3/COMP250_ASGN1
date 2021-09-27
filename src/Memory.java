import java.util.*;

public class Memory {
    LinkedList<StringInterval> intervalList = new LinkedList<StringInterval>();
    char[] memoryArray;
    static int idCount = 0;
    int start = 0;

    //Sets length of the character array
    //DONE
    public Memory(int length) {
        memoryArray = new char[length];
        idCount = 0;
        //start = 0;
    }

    public String getString(int id) { //get(String)
        //initialize variables
        String WORD = null;
        char[] arr;

        //Iteration through linked list to find the id
        for (StringInterval A: intervalList) {
            int a = A.get_Id();
            //verify id number and store string into WORD
            if (A.get_Id() == id) {
                arr = new char[A.get_length()];
                for (int i = 0; i < A.get_length(); i++) {
                    arr[i] = memoryArray[i + A.get_start()];
                }
                WORD = String.valueOf(arr);
            }

        }
        return WORD;
    }

    public int getId(String s) {

    }
    /*
    public getLength(int length) {

    }

    public removeId(int id) {

    }

    public removeString(String s) {

    }
    */
    public int put(String stringInput) {
        idCount ++; //increment id for each string added
        StringInterval newString = new StringInterval(); //creating object for StringInterval

        newString.StringInter(idCount, start, stringInput.length()); //using constructor to give values to object
        //TODO
        //WRITE DEFRAGMENT FUNCTION WHICH REMOVES GAPS IN LINKEDLIST
        for (int i = start; i < memoryArray.length; i++) {
            if (i >= stringInput.length() + start) {
                break;
            }
            memoryArray[i] = stringInput.charAt(i - start);
        }

        start = start + stringInput.length(); //incrementing start for next object;

        intervalList.add(newString);
        return idCount;
        //TODO
        //add return statement for if the function failed to store

    }

    //public void defragment() {}



    public class StringInterval{
        int id; // unique identifier for the string
        int start; //index in memory holding the first character of the string
        int length; //number of characters in the string
        public void StringInter(int id, int start, int length) {
            this.id = id;
            this.start = start;
            this.length = length;
        }
        //helper methods
        public int get_Id() {
            return id;
        }
        public int get_start() {
            return start;
        }
        public int get_length() {
            return length;
        }

    }
}
