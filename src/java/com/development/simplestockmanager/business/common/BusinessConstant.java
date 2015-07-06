/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.common;

/**
 *
 * @author foxtrot
 */
public interface BusinessConstant {

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
        public static String WEB = "/SimpleStockManager";
    }

    public interface URL {

        public static String INDEX = PROJECT.WEB + "/index.xhtml";

        public interface ADD {

            public static String ADD = PROJECT.WEB + "/add";
            public static String CLIENT = ADD + "/client.xhtml";
        }
    }

}
