

public class test {
    public static void main(String[] args) {
        linkedObj fTest = new linkedObj("first", "first", "first", "first", "first", null);
        linkedObj mTest = new linkedObj("middle", "middle", "middle", "middle", "middle", null);
        linkedObj lTest = new linkedObj("last", "last", "last", "last", "last", null);

        linkedObj[] lArr = {fTest, lTest};
        linkedMngr linked = new linkedMngr(lArr);
        linked.add(mTest);
        linked.print();
        try{
        linkedMngr.print(linked.get(1));}
        catch(Exception e){
            System.out.println(e);
        }
    }
}
