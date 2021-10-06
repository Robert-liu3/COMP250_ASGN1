import java.sql.SQLOutput;
import java.util.LinkedList;

public class Memory {
    LinkedList<StringInterval> intervalList = new LinkedList<StringInterval>();
    char[] memoryArray;
    static int idCount = 0;
    int[] emptyIndex;
    int start = 0;
    boolean[] isGarbage;

    //Sets length of the character array
    //DONE
    public Memory(int length) {
        memoryArray = new char[length];
        isGarbage =  new boolean[length];
        idCount = 0;
        //start = 0;
    }

    public void addEmptyIndex(int firstNum, int lastNum) {
        for (int i = firstNum, j = 0; i <= lastNum; i ++, j++) {
            emptyIndex[j] = i;
        }
    }

    public String get(int id) { //get the string with the id
        //initialize variables
        String WORD = null;
        char[] arr;

        //Iteration through linked list to find the id
        for (StringInterval A: intervalList) {
            //int a = A.get_Id(); testing variable
            //verify id number and store string into WORD
            if (A.get_Id() == id) {
                arr = new char[A.get_length()];
                for (int i = 0; i < A.get_length(); i++) {
                    arr[i] = memoryArray[i + A.get_start()];
                }
                WORD = String.valueOf(arr);
                break;
            } else WORD = null;

        }
        return WORD;
    }
    public void incrementDown(int id) { //takes the value of the id AFTER the id of the object that's been deleted
        //int num = 0;
        for (StringInterval A: intervalList) {
            if (A.get_Id() >= id) {
                A.set_Id(A.get_Id() - 1);
               // num = A.get_Id();
            }
        }

    }
    public int get(String s) { //get the id with the string
        int id = 0;
        int index = 0;
        int counter = 0;
        char[] sChar = new char[s.length()];

        //convert string to character array
        for (int i = 0; i < s.length(); i++) {
            sChar[i] = s.charAt(i);
        }

        int last = memoryArray.length - sChar.length;
        //checks if sChar is contained in memoryArray
        for (int i = 0; i < last; i ++) {
            counter = 0;
            if (memoryArray[i] == sChar[0]) {
                for (int j = 0; j < sChar.length; j ++) {
                    if (sChar[j] == memoryArray[i + j]) {
                        counter++;
                        if (counter == sChar.length) {
                            index = i;
                            break;
                        }
                    }
                }
            }
            if (counter == sChar.length) break;
        }
        for (StringInterval A: intervalList) {
            if (A.get_start() == index) {
                if (A.get_length() == counter) {
                    id = A.get_Id();
                    return id;
                }
            }
        }
        return -1;
    }
    public void setGarbageTrue(String s) {
        int counter;
        int index = 0;
        int lastNum = s.length() - 1;

        char[] sChar = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            sChar[i] = s.charAt(i);
        }

        int last = memoryArray.length - sChar.length;
        for (int i = 0; i < last; i ++) {
            counter = 0;
            if (memoryArray[i] == sChar[0]) {
                for (int j = 0; j < sChar.length; j ++) {
                    if (sChar[j] == memoryArray[i + j]) {
                        counter++;
                        if (counter == sChar.length) {
                            index = i;
                            break;
                        }
                    }
                }
            }
            if (counter == sChar.length) break;
        }
        for (int i = index; i < index+lastNum; i++ ) {
            isGarbage[i] = true;
        }
    }

    //TODO
    //not sure if completed, feels like it needs something extra for the string that is "remove" from character array
    public String remove(int id) { //remove object with id
        String WORD;
        //finding object to remove
        for (StringInterval A: intervalList){
            if (id == A.get_Id()) {
                intervalList.remove(id -1);
                WORD = get(id);
                incrementDown(id+1);
                return WORD;
            }
        }
        return null;
    }
    public int remove(String s) { //remove object with string
        int id = get(s);
        String WORD = remove(id);
        if (WORD == null) return -1;
        else return id;
    }

    public int put(String stringInput) {
        //check if there are missing objects in the

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
        public void set_Id(int NewId) {
            this.id = NewId;
        }
        public int get_start() {
            return start;
        }
        public int get_length() {
            return length;
        }

    }
}
