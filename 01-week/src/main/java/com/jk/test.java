package com.jk;

public class test {

    public static void main(String[] args) {
        int [] arr={3,5,9,17,99,68};

        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]<arr[j+1]){
                    //从大到小
                    int kong=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=kong;

                }
            }
        }

        System.out.print("从大到小");
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }


    }
}
