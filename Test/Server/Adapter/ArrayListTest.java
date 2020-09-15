package Server.Adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    ArrayList list;


    @BeforeEach
    public void setUp(){
        list = new ArrayList();
        list.add(1);
        list.add("A");
        list.add(2);
    }

    //
    @Test
    void getIndexToLowNonValid(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.get(-11);
        });
    }

    @Test
    void getValidIndex(){
        assertEquals("A", list.get(1));
    }

    @Test
    void getIndexToHighNonValid(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.get(23);
        });
    }



    @Test
    void add() {
        list.add(0,"hakuna");
        list.add(1, "matata");
        list.add(1,"yo");
        String a = "{hakuna, yo, matata}";
        assertEquals(a,list.toString());
    }
    @Test
    void addOutOfBoundsException(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.add(101, "hatata");
        });
    }

    @Test
    void addEnd() {
        list.add(10);
        list.add(10);
        list.add(25);
        String b = "{10, 10, 25}";
        assertEquals(list.toString(),b);
    }

    @Test
    void addNull() {
        list.add(null);

        String h = "{null}";
        assertEquals(h,list.toString());
    }

    @Test
    void set() {
        list.add("hakuna");
        list.add("mtata");

        list.set(1,"yo");
        String a = "{hakuna, yo}";
        assertEquals(a,list.toString());
    }

    @Test
    void setOutOfBoundsException(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
          list.set(0,"rgbf");
        });
    }

    @Test
    void setNull() {
        list.add("hatata");
        list.add("matata");

        list.set(1, null);
        assertEquals("{hatata, null}",list.toString());
    }

    @Test
    void get() {
        list.add("atata");
        list.add("youihbkjb");
        assertEquals("atata",list.get(0));
    }

    @Test
    void getOutOfBoundsException(){
        list.add("St.Anger");

        assertThrows(IndexOutOfBoundsException.class, () ->{
           list.get(1);
        });
    }

    @Test
    void getNull() {
        list.add("Life");
        list.add(null);
        list.add("is");
        list.add("Blyatiful");

        assertEquals(null, list.get(1));

    }

    @Test
    void removeIfReturns() {
        list.add("bkjv");
        list.add("elo");
        list.add("yo");
        list.add("bkjv");
        assertEquals("yo", list.remove("yo"));
    }

    @Test
    void removeIfRemoving() {
        list.add("bkjv");
        list.add("elo");
        list.add("yo");
        list.add("bkjv");

        list.remove("yo");
        String a = "{bkjv, elo, bkjv}";
        assertEquals(a,list.toString());
    }

    @Test
    void removeIfSameElement() {
        list.add("yo");
        list.add("yo");
        list.add("yo");
        list.add("lol");
        list.add("kek");

        list.remove("yo");
        String k = "{yo, yo, lol, kek}";
        assertEquals(k,list.toString() );
    }

    @Test
    void removeIfNull() {
        list.add("bkjv");
        list.add("elo");
        list.add(null);
        list.add("bkjv");
        assertEquals(null, list.remove(null));
    }

    @Test
    void removeOutOfBoundsException() {
        list.add("Lol");
        list.add("Kek");

        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.remove(2);
        });
    }

    @Test
    void indexOf() {
        list.add("Java");
        list.add("is");
        list.add("Life");

        assertEquals(1,list.indexOf("is"));
    }

    @Test
    void indexOfSameElement() {
        list.add("Java");
        list.add("yo");
        list.add("Java");
        list.add("Lol");
        list.add("Java");

        assertEquals(0, list.indexOf("Java"));
    }

    @Test
    void indexOfNull() {
        list.add("Java");
        list.add("is");
        list.add(null);
        list.add("yo");

        assertEquals(2, list.indexOf(null));
    }

    @Test
    void containsNull() {
        list.add(null);
        list.add("is");
        list.add(null);

        assertEquals(false,list.contains("Love"));
        assertEquals(true, list.contains(null));
    }

    @Test
    void contains() {
        list.add("Java");
        list.add("is");
        list.add("Hope");

        assertEquals(false,list.contains("Love"));
        assertEquals(true, list.contains("Java"));
    }

    @Test
    void isEmpty() {
        assertEquals(true,list.isEmpty());

        list.add("Java");
        list.add("is");
        list.add("Power");

        assertEquals(false,list.isEmpty());
    }

    @Test
    void isEmptyNull() {
        list.add(null);
        assertEquals(false,list.isEmpty());
    }

    @Test
    void isFull() {
        assertEquals(false,list.isFull());
    }

    @Test
    void size() {
        list.add("Java");
        list.add("is");
        list.add("a");
        list.add("State");
        list.add("of");
        list.add("Mind");

        assertEquals(6,list.size());
    }

    @Test
    void sizeNull() {
        list.add("Java");
        list.add("is");
        list.add(null);
        list.add("State");
        list.add("of");
        list.add(null);

        assertEquals(6,list.size());
    }

    @Test
     void toStringTest(){
      list.add("Java");
      list.add("is");
      list.add("Magic");

      String l = "{Java, is, Magic}";
      assertEquals(l,list.toString());
    }

    @Test
    void toStringEmpty() {
        assertEquals("{}",list.toString());
    }

    @Test
    void toStringNull() {
        list.add(null);
        assertEquals("{null}",list.toString());
    }

}