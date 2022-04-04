import java.io.*;
import java.util.ArrayList;
class Test {
    public static void main(String[] args)
    {
        // creating ArrayList
        ArrayList<Integer> my_list = new ArrayList<Integer>();

        // adding elements
        my_list.add(10);
        my_list.add(80);
        my_list.add(30);
        my_list.add(70);
        my_list.add(5);
        my_list.add(90);
        my_list.add(19);
        my_list.add(25);

        // loop to print elements at randonm
        for (int i = 0; i < my_list.size(); i++)
        {
            // generating the index using Math.random()
            int index = (int)(Math.random() * my_list.size());

            System.out.println("Random Element is :"
                    + my_list.get(index));
        }
    }
}
