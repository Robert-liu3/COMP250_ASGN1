import java.util.*;

public class Memory {
    LinkedList<StringInterval> intervalList = new LinkedList<StringInterval>();
    char[] memoryArray;
    static int idCount = 0;
    static int start = 0;

    public Memory(int length) {
        memoryArray = new char[length];
        idCount = 0;
    }
    /*
    public getString(int id) {

    }

    public getId(String s) {

    }

    public getLength(int length) {

    }

    public removeId(int id) {

    }

    public removeString(String s) {

    }
    */
    public put(String stringInput) {
        idCount ++; //increment id for each string added
        StringInterval newString = new StringInterval(); //creating object for StringInterval

        newString.StringInter(start, idCount, stringInput.length()); //using constructor to give values to object

        for (int i = start; i < memoryArray.length; i++) {
            memoryArray[i] = stringInput.charAt(i - start);
        }

        start = start + stringInput.length(); //incrementing start for next object;

        intervalList.add(newString);


        // if statement with defragment

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
        //hello
            //testingTESTING
        }

    }
}
