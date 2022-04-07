import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {
    private IntStack a;
    private IntStack b;

    @Before
    public void init(){
        /** Test with proper stack and proper inputs.*/
        a = new IntStack(8, 0.8, 0.3);
        b = new IntStack(5);
        b.multiPush(new int[]{1, 2, 3});
    }

    @Test
    public void isEmpty() {
        /** Stack a should be empty*/
        assertTrue(a.isEmpty());
        a.push(100); /** add 1 element to top of stack */
        assertFalse(a.isEmpty()); /** Should not be empty anymore */
    }

    @Test
    public void clear() {
        a.multiPush(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        a.clear();
        assertEquals(8, a.capacity());
        assertTrue(a.isEmpty());
    }

    @Test
    public void size() {
        a.multiPush(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertFalse(a.isEmpty());
        assertEquals(9, a.size());
        a.push(10);
        assertEquals(10, a.size());
    }

    @Test
    public void capacity() {
        assertEquals(8, a.capacity());
    }

    @Test
    public void peek() {
        a.push(100);
        assertEquals(100, a.peek());
    }

    @Test
    public void push() {
        a.push(10);
        assertFalse(a.isEmpty());
    }

    @Test
    public void pop() {
        a.push(10);
        a.push(100);
        assertFalse(a.isEmpty());
        a.pop();
        a.pop();
        assertTrue(a.isEmpty());
        assertEquals(5, a.capacity());
    }

    @Test
    public void multiPush() {
        a.multiPush(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}); /** Testing multipush */
        assertEquals(9, a.size());
        assertEquals(16, a.capacity());
    }

    @Test
    public void multiPop() {
        a.multiPush(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertEquals(9, a.size());
        assertEquals(16, a.capacity());
        int[] returns = a.multiPop(4);
        assertEquals(5, a.size());
        assertEquals(16, a.capacity());
        assertArrayEquals(new int[]{9, 8, 7, 6}, returns);
        a.multiPop(2);
        assertEquals(3, a.size());
        assertEquals(8, a.capacity());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testException_Constructor_load(){
        IntStack test_load = new IntStack(6, 100);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testException_Constructor_shrink(){
        IntStack test_load = new IntStack(6, 0.75, 100);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testException_Constructor_cap(){
        IntStack test_load = new IntStack(2);
    }
    @Test (expected = EmptyStackException.class)
    public void testException_peek(){
        a.peek();
    }
    @Test (expected = EmptyStackException.class)
    public void testException_pop(){
        a.pop();
    }
    @Test (expected = IllegalArgumentException.class)
    public void testException_multipush(){
        a.multiPush(null);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testException_multipop(){
        a.multiPop(-1);
    }
}