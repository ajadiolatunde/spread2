package com.plate.root.spread;

import android.content.Context;

import java.util.ArrayList;


class SingleTon {
    private Context mContext;
    private static SingleTon ourInstance ;
    private ArrayList<String> list1,flist1;
    private ArrayList<String> list2,flist2;
    private ArrayList<String> list4,flist4;
    private ArrayList<String> list3,flist3;

    static SingleTon getInstance(Context mContext) {
        if (ourInstance==null){
            ourInstance = new SingleTon(mContext);
        }
        return ourInstance;
    }

    private SingleTon(Context context) {
        mContext = context;
    }

    public void setList(String[] lt,String num){
        if (num.equals("1")){
            if (list1 ==null ) {
                list1 = new ArrayList<>();
                flist1 = new ArrayList<>();
            }else{
                list1.clear();
                flist1.clear();
            }

            for (String g:lt){
                String[] names = g.split(",");
                if (names.length>6) {

                    String all = names[0] + " " + names[3] + " " + names[4] + " " + names[5];
                    list1.add(g);
                    flist1.add(all);

                }
            }

            System.out.println("Tunde size "+list1.size());

        }
        if (num.equals("2")){
            if (list2 ==null ) {
                list2 = new ArrayList<>();
                flist2 = new ArrayList<>();
            }else{
                list2.clear();
                flist2.clear();
            }

            for (String g:lt){
                String[] names = g.split(",");
                if (names.length>6) {

                    String all = names[0] + " " + names[3] + " " + names[4] + " " + names[5];
                    list2.add(g);
                    flist2.add(all);

                }
            }

            System.out.println("Tunde size "+list2.size());
        }
        if (num.equals("3")){
            if (list3 ==null ) {
                list3 = new ArrayList<>();
                flist3 = new ArrayList<>();
            }else{
                list3.clear();
                flist3.clear();
            }

            for (String g:lt){
                String[] names = g.split(",");
                if (names.length>6) {

                    String all = names[0] + " " + names[3] + " " + names[4] + " " + names[5];
                    list3.add(g);
                    flist3.add(all);

                }
            }

            System.out.println("Tunde size "+list3.size());
        }
        if (num.equals("4")){
            if (list4 ==null ) {
                list4 = new ArrayList<>();
                flist4 = new ArrayList<>();
            }else{
                list4.clear();
                flist4.clear();
            }

            for (String g:lt){
                String[] names = g.split(",");
                if (names.length>6) {

                    String all = names[0] + " " + names[3] + " " + names[4] + " " + names[5];
                    list4.add(g);
                    flist4.add(all);

                }
            }

            System.out.println("Tunde size "+list4.size());
        }


    }
    public ArrayList<String> getList(String num){
        ArrayList<String> mlist =new ArrayList<>();

        if (num.equals("1")){
            mlist.addAll(list1);
        }
        if (num.equals("2")){
            mlist.addAll(list2);
        }
        if (num.equals("3")){
            mlist.addAll(list3);
        }
        if (num.equals("4")){
            mlist.addAll(list4);
        }

        return mlist;
    }

    public ArrayList<String> getfList(String num){
        ArrayList<String> plist =new ArrayList<>();
        if (num.equals("1")){
            plist.addAll(flist1);
        }
        if (num.equals("2")){
            plist.addAll(flist2);
        }
        if (num.equals("3")){
            plist.addAll(flist3);
        }
        if (num.equals("4")){
            plist.addAll(flist4);
        }
        return plist;
    }

}
