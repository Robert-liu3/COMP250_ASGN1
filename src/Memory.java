import java.util.LinkedList;

public class Memory {
    LinkedList<StringInterval> intervalList = new LinkedList<StringInterval>();
    char[] memoryArray;
    static int idCount = 0;
   // int[] emptyIndex;
    //int start = 0;
    boolean[] isGarbage;

    //Sets length of the character array
    //DONE
    public Memory(int length) {
        memoryArray = new char[length];
        isGarbage =  new boolean[length];
        idCount = 0;
        for (int i = 0; i < isGarbage.length; i++) {
            isGarbage[i] = true;
        }
        //start = 0;
    }

//    public void addEmptyIndex(int firstNum, int lastNum) {
//        for (int i = firstNum, j = 0; i <= lastNum; i ++, j++) {
//            emptyIndex[j] = i;
//        }
//    }

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


    //TODO
    //not sure if completed, feels like it needs something extra for the string that is "remove" from character array
    public String remove(int id) { //remove object with id
        String WORD;
        //finding object to remove
        for (StringInterval A: intervalList){
            if (id == A.get_Id()) {
                WORD = get(id);
                intervalList.remove(id);
                setGarbageTrue(WORD);
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
        int start = 0;
        int lengthOfEmpty = 0;
        boolean foundGap = false;
        for (int i = 0; i < memoryArray.length-1; i++) {
            //if (i == memoryArray.length-2 && isGarbage[i+1]==true) lengthOfEmpty ++;
            if (isGarbage[i] == true) {
                lengthOfEmpty ++;
                if (lengthOfEmpty == stringInput.length()) {
                    foundGap = true;
                    start = i - lengthOfEmpty + 1;
                    break;
                } else if (isGarbage[i+1] == false){
                    lengthOfEmpty = 0;
                    defragment();
                }
            }
        }
        if (foundGap == false) {
            return -1;
        } else if (foundGap == true) {
            StringInterval newString = new StringInterval(idCount, start, stringInput.length()); //creating object for StringInterval
            //newString =  StringInterval(idCount, start, stringInput.length()); //using constructor to give values to object
            idCount ++; //increment idCount
            for (int i = start; i < memoryArray.length; i++) {
                if (i >= stringInput.length() + start) {
                    break;
                }
                memoryArray[i] = stringInput.charAt(i - start);
                isGarbage[i] = false;
            }
            intervalList.add(newString);
            return idCount;
        }
        return -1;
    }

    public void defragment() {
        int lengthOfGap = 0;
        int index = 0;
        for (int i = 0; i < memoryArray.length -1; i++) {
            if (isGarbage[i] == true) {
                lengthOfGap ++;
                if (isGarbage[i + 1] == false) {
                    index = i - lengthOfGap + 1;
                    break;
                }
            }
        }
        for (int j = 0; j < lengthOfGap; j++) {
            for (int i = 0; i < memoryArray.length-1; i++) {
//                if (i == (memoryArray.length - 2)) {
//                    if (isGarbage[i+1] == true) {
//                        memoryArray[i] = memoryArray[i + 1];
//                        isGarbage[i] = isGarbage[i+1];
//                        isGarbage[i+1] = true;
//                        break;
//                    }
//                }
                memoryArray[i] = memoryArray[i + 1];
                isGarbage[i] = isGarbage[i+1];
            }
        }
    }

    //ALL NEW FUNCTIONS

    public void setGarbageTrue(String WORD) {
        int counter;
        int index = 0;
        int lengthWORD = WORD.length();

        char[] sChar = new char[WORD.length()];

        for (int i = 0; i < WORD.length(); i++) {
            sChar[i] = WORD.charAt(i);
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
        for (int i = index; i < lengthWORD + index; i++ ) {
            isGarbage[i] = true;
        }
    }
    public void setGarbageFalse(String WORD) {
        int counter;
        int index = 0;
        int lengthWORD = WORD.length();

        char[] sChar = new char[WORD.length()];

        for (int i = 0; i < WORD.length(); i++) {
            sChar[i] = WORD.charAt(i);
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
        for (int i = index; i < lengthWORD + index; i++ ) {
            isGarbage[i] = false;
        }
    }
//    public void incrementUp(int id) { //takes the value of the id AFTER the id of the object that's been added
//        //int num = 0;
//        for (StringInterval A: intervalList) {
//            if (A.get_Id() >= id) {
//                A.set_Id(A.get_Id() + 1);
//                // num = A.get_Id();
//            }
//        }
//    }


    public class StringInterval{
        int id; // unique identifier for the string
        int start; //index in memory holding the first character of the string
        int length; //number of characters in the string
        StringInterval(int id, int start, int length) {
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
