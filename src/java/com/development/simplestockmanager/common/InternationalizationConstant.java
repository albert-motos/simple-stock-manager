package com.development.simplestockmanager.common;

/**
 *
 * @author foxtrot
 */
public interface InternationalizationConstant {

    public static String PATH = "com.development.simplestockmanager.common.internationalization.internationalization";

    public interface MENU {

        public static String TEXT = "menu";

        public interface ENTITY {

            public static String TEXT = MENU.TEXT + ".entity";

            public interface BRAND {

                public static String TEXT = ENTITY.TEXT + ".brand";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface CLIENT {

                public static String TEXT = ENTITY.TEXT + ".client";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface EMPLOYEE {

                public static String TEXT = ENTITY.TEXT + ".employee";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PRODUCT {

                public static String TEXT = ENTITY.TEXT + ".product";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PROVIDER {

                public static String TEXT = ENTITY.TEXT + ".provider";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface STORE {

                public static String TEXT = ENTITY.TEXT + ".store";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }
        }
    }

    public interface LABEL {

        public static String TEXT = "label";
        public static String FIRSTNAME = TEXT + ".firstname";
        public static String LASTNAME = TEXT + ".lastname";
        public static String SEX_TYPE = TEXT + ".sextype";
        public static String BORN_DATE = TEXT + ".borndate";
        public static String PHONE_NUMBER = TEXT + ".phonenumber";
        public static String EMAIL = TEXT + ".email";

        public interface ENABLE {

            public static String TEXT = LABEL.TEXT + ".enable";
            public static String CLIENT = TEXT + ".client";
        }
    }

    public interface HEADER {

        public static String TEXT = "header";

        public interface CLIENT {

            public static String TEXT = HEADER.TEXT + ".client";
            public static String ATTRIBUTES = TEXT + ".attributes";
            public static String VISIBILITY = TEXT + ".visibility";
        }
    }

    public interface BUTTON {

        public static String TEXT = "button";
        public static String ADD = TEXT + ".add";
        public static String CANCEL = TEXT + ".cancel";
        public static String FINISH = TEXT + ".finish";
    }
}
