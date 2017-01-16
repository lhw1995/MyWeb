package com.restaurant.function;

import java.io.File;

/**
 * Created by lhw on 2016/12/23.
 */
public class DeleteFile {
    public static void delete(String path,String fileName){
        File file = new File(path+fileName);
        if (file.exists()){
            file.delete();
        }
    }
}
