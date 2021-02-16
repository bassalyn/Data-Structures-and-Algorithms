public class TestBoardGame {

    public static void main(String[] args){
        BoardGame b1 = new BoardGame(3,0,5);
        HashDictionary t1 = b1.makeDictionary();
        b1.savePlay(0,0,'b');
        b1.savePlay(0,1,'o');
        b1.savePlay(0,2,'b');
        b1.savePlay(1,0,'b');
        b1.savePlay(1,1,'o');
        b1.savePlay(1,2,'b');
        b1.savePlay(2,0,'o');
        b1.savePlay(2,1,'b');
        b1.savePlay(2,2,'o');

        System.out.println(b1.wins('o'));
        System.out.println(b1.wins('b'));
        System.out.println(b1.isDraw('b',0));
        System.out.println(b1.evalBoard('o',1));

        BoardGame b2 = new BoardGame(3,1,5);
        HashDictionary t2 = b2.makeDictionary();
        b2.savePlay(0,0,'b');
        b2.savePlay(0,1,'o');
        b2.savePlay(0,2,'b');
        b2.savePlay(1,0,'o');
        b2.savePlay(1,1,'b');
        b2.savePlay(1,2,'b');
        b2.savePlay(2,0,'o');
        b2.savePlay(2,1,'o');
        b2.savePlay(2,2,'b');

        System.out.println(b2.wins('o'));
        System.out.println(b2.wins('b'));
        System.out.println(b2.isDraw('b',0));
        System.out.println(b2.evalBoard('o',1));


        BoardGame b3 = new BoardGame(3,1,5);
        HashDictionary t3 = b2.makeDictionary();
        b3.savePlay(0,0,'b');
        b3.savePlay(0,1,'o');
        b3.savePlay(0,2,'o');
        b3.savePlay(1,0,'o');
        b3.savePlay(1,1,'o');
        b3.savePlay(1,2,'b');
        b3.savePlay(2,0,'o');
        b3.savePlay(2,1,'o');
        b3.savePlay(2,2,'b');

        System.out.println(b3.wins('o'));
        System.out.println(b3.wins('b'));
        System.out.println(b3.isDraw('b',0));
        System.out.println(b3.evalBoard('o',1));

        BoardGame b4 = new BoardGame(3,1,5);
        HashDictionary t4 = b2.makeDictionary();
        b4.savePlay(0,0,'b');
        b4.savePlay(0,1,'o');
        b4.savePlay(0,2,'g');
        b4.savePlay(1,0,'o');
        b4.savePlay(1,1,'o');
        b4.savePlay(1,2,'g');

        System.out.println(b4.wins('o'));
        System.out.println(b4.wins('b'));
        System.out.println(b4.isDraw('b',5));
        System.out.println(b4.evalBoard('b',5));










    }
}
