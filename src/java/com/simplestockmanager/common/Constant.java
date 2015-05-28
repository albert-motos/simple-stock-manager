/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.common;

/**
 *
 * @author foxtrot
 */
public interface Constant {

    public interface IDENTIFIER {

        public static long INVALID = -1;
    }

    public interface DELETE {

        public static long SUCCESS = 1;
        public static long FAILURE = 0;
    }

    public interface UPDATE {

        public static long SUCCESS = 1;
        public static long FAILURE = 0;
    }

    public interface PROJECT {

        public static String PERSISTENCE_UNIT = "SimpleStockManagerPU";
    }
}
