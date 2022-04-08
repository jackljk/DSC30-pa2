/*
    Name: Jack Kai Lim
    PID:  A16919063
 */

import java.util.Arrays;
import java.util.Locale;

import static java.lang.Character.isUpperCase;

/**
 * Text Editor
 * @author Jack Kai Lim
 * @since  04/07/2022
 */
public class TextEditor {

    /* instance variables */
    private String text;
    private IntStack undo;
    private StringStack deletedText;
    private IntStack redo;
    private StringStack insertedText;

    /**
     * Constructor
     */
    public TextEditor() {
        /* Initializes the TextEditor */
        this.text = "";
        this.undo = new IntStack(10);
        this.deletedText = new StringStack(20);
        this.redo = new IntStack(10);
        this.insertedText = new StringStack(20);
    }

    public String getText() {
        /* Returns the text at it's current state. */
        return this.text;
    }

    public int length() {
        /* Returns the length of the text at it's current state. */
        return this.text.length();
    }

    public void caseConvert(int i, int j) {
        /* Iterates through the string and checks if it is an upper or lower case character, and
        switches the casing depending on what case it is. */
        if (i > this.length() || j > this.length() || j <= i) {
            /* Throws an exception if i or j is out of bounds. Or i is larger than j */
            throw new IllegalArgumentException();
        }
        StringBuilder final_text = new StringBuilder();
        for (int z = 0; z < this.length(); z++) {
            if (i <= z && j > z) {
                /* Switches char to a lower case when the string is an upper case */
                if (isUpperCase(this.text.charAt(z))) {
                    String temp = Character.toString(this.text.charAt(z)).toLowerCase();
                    final_text.append(temp);
                } else {
                    /* Switches char to a upper case when the string is a lower case */
                    String temp = Character.toString(this.text.charAt(z)).toUpperCase();
                    final_text.append(temp);
                }
            } else {
                final_text.append(Character.toString(this.text.charAt(z)));
            }
        }
        this.text = final_text.toString();
        undo.multiPush(new int[]{i, j, 0});
    }

    public void caseConvert_without_undo(int i, int j){
        /* Iterates through the string and checks if it is an upper or lower case character, and
        switches the casing depending on what case it is. */
        if (i > this.length() || j > this.length() || j <= i) {
            /* Throws an exception if i or j is out of bounds. Or i is larger than j */
            throw new IllegalArgumentException();
        }
        StringBuilder final_text = new StringBuilder();
        for (int z = 0; z < this.length(); z++) {
            if (i <= z && j > z) {
                /* Switches char to a lower case when the string is an upper case */
                if (isUpperCase(this.text.charAt(z))) {
                    String temp = Character.toString(this.text.charAt(z)).toLowerCase();
                    final_text.append(temp);
                } else {
                    /* Switches char to a upper case when the string is a lower case */
                    String temp = Character.toString(this.text.charAt(z)).toUpperCase();
                    final_text.append(temp);
                }
            } else {
                final_text.append(Character.toString(this.text.charAt(z)));
            }
        }
        this.text = final_text.toString();
    }

    public void insert(int i, String input) {
        /* Insert a string into the position specified by the integer. */
        StringBuilder final_text = new StringBuilder();
        if (input == null) {
            /* Throws exception if the input is null */
            throw new NullPointerException();
        } else if (i > this.length()) {
            /* Throws exception if i is longer than the string. */
            throw new IllegalArgumentException();
        } else {
            if (i == this.length()) {
                final_text.append(this.text);
                final_text.append(input);
            } else {
                for (int j = 0; j < this.length(); j++) {
                    if (i == j) {
                        /* Inserting the input text */
                        final_text.append(input);
                        final_text.append(Character.toString(this.text.charAt(i)));
                    } else {
                        final_text.append(Character.toString(this.text.charAt(j)));
                    }
                }
            }
            this.text = final_text.toString();
            undo.multiPush(new int[]{i, i + input.length(), 1});
            insertedText.push(input);
        }
    }

    public void insert_without_undo(int i, String input) {
        /* Insert a string into the position specified by the integer. */
        StringBuilder final_text = new StringBuilder();
        if (input == null) {
            /* Throws exception if the input is null */
            throw new NullPointerException();
        } else if (i > this.length()) {
            /* Throws exception if i is longer than the string. */
            throw new IllegalArgumentException();
        } else {
            if (i == this.length()) {
                final_text.append(this.text);
                final_text.append(input);
            } else {
                for (int j = 0; j < this.length(); j++) {
                    if (i == j) {
                        /* Inserting the input text */
                        final_text.append(input);
                        final_text.append(Character.toString(this.text.charAt(i)));
                    } else {
                        final_text.append(Character.toString(this.text.charAt(j)));
                    }
                }
            }
            this.text = final_text.toString();
        }
    }

    public void delete(int i, int j) {
        /* Deletes a part of the string based of the 2 integers provided */
        StringBuilder final_text = new StringBuilder();
        if (i > this.length() || j > this.length() || j <= i){
            /* Throws an exception if i or j is out of bounds. Or i is larger than j */
            throw new IllegalArgumentException();
        } else {
            for (int z = 0;z<this.length();z++){
                /* Does nothing is the chars are in between i and j */
                if (z >=i && z < j){
                    deletedText.push(Character.toString(this.text.charAt(z)));
                } else {
                    /* Adds the chars of the string is not in between the chars. */
                    final_text.append(Character.toString(this.text.charAt(z)));
                }
            }
        }
        this.text = final_text.toString();
        undo.multiPush(new int[]{i, j, 2});
    }

    public void delete_without_undo(int i, int j){
        StringBuilder final_text = new StringBuilder();
        if (i > this.length() || j > this.length() || j <= i){
            /* Throws an exception if i or j is out of bounds. Or i is larger than j */
            throw new IllegalArgumentException();
        } else {
            for (int z = 0;z<this.length();z++){
                /* Does nothing is the chars are in between i and j */
                if (z >=i && z < j){
                    deletedText.push(Character.toString(this.text.charAt(z)));
                } else {
                    /* Adds the chars of the string is not in between the chars. */
                    final_text.append(Character.toString(this.text.charAt(z)));
                }
            }
        }
        this.text = final_text.toString();
    }

    public boolean undo() {
        /* TODO */
        if (undo.size() == 0){
            return false;
        } else if (undo.peek() == 0){
            int cas = undo.pop();
            int end = undo.pop();
            int start = undo.pop();
            this.caseConvert_without_undo(start, end);
            redo.multiPush(new int[]{start, end, cas});
            return true;
        } else if (undo.peek() == 1){
            int cas = undo.pop();
            int end = undo.pop();
            int start = undo.pop();
            this.delete_without_undo(start, end);
            redo.multiPush(new int[]{start, end, cas});
            return true;
        } else if (undo.peek() == 2){
            int cas = undo.pop();
            int end = undo.pop();
            int start = undo.pop();
            String[] deleted_text = deletedText.multiPop(end - start);
            StringBuilder in = new StringBuilder();
            for (int i = deleted_text.length - 1;i >= 0;i--){
                in.append(deleted_text[i]);
            }
            this.insert_without_undo(start , in.toString());
            redo.multiPush(new int[]{start, end, cas});
            return true;
        }
        return false;
    }

    public boolean redo() {
        /* TODO */
        if (redo.size() == 0){
            return false;
        } else if (redo.peek() == 0){
            int cas = redo.pop();
            int end = redo.pop();
            int start = redo.pop();
            this.caseConvert(start, end);
            return true;
        } else if (redo.peek() == 1){
            int cas = redo.pop();
            int end = redo.pop();
            int start = redo.pop();
            this.insert(start, insertedText.pop());
            return true;
        } else if (redo.peek() == 2){
            int cas = redo.pop();
            int end = redo.pop();
            int start = redo.pop();
            this.delete(start, end);
            return true;
        }
        return false;
    }
}