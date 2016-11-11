package com.company;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/*String del = "newPart";

        os.write(Task.getName().getBytes());
        os.write(del.getBytes());

        os.write(Task.getDescriptionOfTask().getBytes());
        os.write(del.getBytes());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        os.write(sdf.format(Task.getDateOfMessage().getTime()).getBytes());
        os.write(del.getBytes());

        os.write(Task.getContacts().getBytes());*/

/**
 * Created by daryo on 10.11.2016.
 */
public class TaskManager {

    public static void addTaskToFile(Task Task) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Task);
    }

    public static Task getTaskFromFile() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        Task ts = (Task) oin.readObject();
        return ts;
    }
}
