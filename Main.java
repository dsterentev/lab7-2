package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, EOFException {
        Scanner sc = new Scanner(System.in);
        try {
            File f1 = new File("scr.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            rf.seek(0);
            System.out.println("Размер файла " + rf.length());
            System.out.println("count");
            int count = sc.nextInt();

            for (int i = 0; i < count; i++) {
                System.out.println("Input lastname:");
                String lastname = sc.next();
                rf.writeUTF(lastname);
                for (int j = 0; j < 20 - lastname.length(); j++) {
                    rf.writeByte(1);
                }
                System.out.println("Input name:");
                String name = sc.next();
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++) {
                    rf.writeByte(1);
                }
                System.out.println("Input sex:");
                String sex = sc.next();
                rf.writeUTF(sex);
                for (int j = 0; j < 20 - sex.length(); j++) {
                    rf.writeByte(1);
                }
                System.out.println("Input height:");
                int height = sc.nextInt();
                rf.writeInt(height);
            }
            System.out.println("Размер файла " + rf.length());

            File f2 = new File("lab7.txt");
            if (f2.exists()) f2.delete();
            f2.createNewFile();
            RandomAccessFile rf1 = new RandomAccessFile(f2, "rw");
            rf.seek(0);
            rf1.seek(0);
            System.out.println("Размер файла " + rf1.length());

            int found = 0;
            for (int i = 0; i < count; i++) {
                rf.seek(52*i);
                String lastname = rf.readUTF();
                rf.seek(52*i + 22);
                String name = rf.readUTF();
                rf.seek(52*i + 44);
                String sex = rf.readUTF();
                rf.seek(52*i + 48);
                int height = rf.readInt();
                if (height > 170) {
                    rf1.writeUTF(lastname);
                    for (int j = 0; j < 20 - lastname.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeUTF(name);
                    for (int j = 0; j < 20 - name.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeUTF(sex);
                    for (int j = 0; j < 20 - sex.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeInt(height);
                    found++;
                }
            }
            System.out.println("Размер файла " + rf1.length());
            System.out.println("Количество записей: " + found);
            rf1.seek(0);
            for (int i = 0; i < found; i++) {
                rf1.seek(i * 52);
                String lastname = rf1.readUTF();
                rf1.seek(i * 52 + 22);
                String name = rf1.readUTF();
                rf1.seek(i * 52 + 44);
                String sex = rf1.readUTF();
                rf1.seek(i * 52 + 48);
                int height = rf1.readInt();
                System.out.println("Surname: " + lastname + " name: " + name + " sex: " + sex + " height: " + height);
            }
            rf.close();
            rf1.close();
        } catch (EOFException eof) {
            System.out.println("EOF");
        } catch (IOException ioe) {
            System.out.println("IOE");
        }
    }
}