

public class test {
    public static void main(String[] args) {
        linkedObj fTest = new linkedObj("first", "first", "first", "first", "first", null);
        linkedObj mTest = new linkedObj("middle", "middle", "middle", "middle", "middle", null);
        linkedObj lTest = new linkedObj("last", "last", "last", "last", "last", null);
        /* 
        linkedObj[] lArr = {fTest, lTest};
        linkedMngr linked = new linkedMngr(lArr);
        linked.add(mTest);
        linked.print();
        System.out.println(linked.size());
        System.out.println(linked.toString());
        System.out.println("Index is: " + Integer.toString(linked.indexOf("last")));
        System.out.println("contains ABC ?: " + linked.contains("BC"));
        linked.clear();
        System.out.println("cleared list: " + linked.toString());
        */

        linkedMngr linked = new linkedMngr();
        linked.add(fTest);
        linked.add(mTest);
        System.out.println(linked.toString());
    }
}
