package com.development.simplestockmanager.common;

/**
 *
 * @author foxtrot
 */
public interface Constant {

    public interface LANGUAGE {

        public static String PATH = "com.development.simplestockmanager.common.language.language";

        public interface HEADER {

            public static String TEXT = "header.";

            public interface CLIENT {

                public static String TEXT = HEADER.TEXT + "client.";
                public static String ATTRIBUTES = TEXT + "attributes";
                public static String VISIBILITY = TEXT + "visibility";
            }

        }

        public interface LABEL {

            public static String TEXT = "label.";
            public static String FIRSTNAME = TEXT + "firstname";
            public static String LASTNAME = TEXT + "lastname";
            public static String SEXTYPE = TEXT + "sextype";
            public static String BORNDATE = TEXT + "borndate";
            public static String PHONENUMBER = TEXT + "phonenumber";
            public static String EMAIL = TEXT + "email";

            public interface ENABLE {

                public static String TEXT = LABEL.TEXT + "enable.";
                public static String CLIENT = TEXT + "client";
            }
        }
    }
}
