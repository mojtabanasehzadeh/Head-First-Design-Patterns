package practice.exception;

import java.io.File;

/**
 * Created by Moji on 20-Oct-17.
 * mojtaba.nasehzadeh@gmail.com
 */
public class CheckedUnchecked {

    public static void main(String[] args) {
        checkedTester();
    }

    static void checkedTester(){
        File file = new File("some pathe");
    }
}
