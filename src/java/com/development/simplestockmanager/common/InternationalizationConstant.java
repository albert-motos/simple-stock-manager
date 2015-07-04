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
        public static String USERNAME = TEXT + ".username";
        public static String PASSWORD = TEXT + ".password";
        public static String EMPLOYEE_TYPE = TEXT + ".employeetype";
        public static String LANGUAGE_TYPE = TEXT + ".languagetype";
        public static String NAME = TEXT + ".name";
        public static String DESCRIPTION = TEXT + ".description";
        public static String IDENTIFIER = TEXT + ".identifier";

        public static String NON_SELECTION = TEXT + ".nonselection";

        public interface ENABLE {

            public static String TEXT = LABEL.TEXT + ".enable";
            public static String BRAND = TEXT + ".brand";
            public static String CLIENT = TEXT + ".client";
            public static String EMPLOYEE = TEXT + ".employee";
            public static String PROVIDER = TEXT + ".provider";
        }
    }

    public interface HEADER {

        public static String TEXT = "header";

        public interface BRAND {

            public static String TEXT = HEADER.TEXT + ".brand";
            public static String ATTRIBUTES = TEXT + ".attributes";
            public static String VISIBILITY = TEXT + ".visibility";
        }

        public interface CLIENT {

            public static String TEXT = HEADER.TEXT + ".client";
            public static String ATTRIBUTES = TEXT + ".attributes";
            public static String VISIBILITY = TEXT + ".visibility";
        }

        public interface EMPLOYE {

            public static String TEXT = HEADER.TEXT + ".employee";
            public static String ATTRIBUTES = TEXT + ".attributes";
            public static String CREDENTIALS = TEXT + ".credentials";
            public static String VISIBILITY = TEXT + ".visibility";
        }
        
        public interface PROVIDER {

            public static String TEXT = HEADER.TEXT + ".provider";
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

    public interface MESSAGE {

        public static String TEXT = "message";

        public interface WARNING {

            public static String TEXT = MESSAGE.TEXT + ".warning";
            public static String FIRSTNAME = TEXT + ".firstname";
            public static String LASTNAME = TEXT + ".lastname";
            public static String SEX_TYPE = TEXT + ".sextype";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String PHONE_NUMBER = TEXT + ".phonenumber";
            public static String EMAIL = TEXT + ".email";
            public static String SUMMARY = TEXT + ".summary";
            public static String EMPLOYEE_TYPE = TEXT + ".employeetype";
            public static String LANGUAGE_TYPE = TEXT + ".languagetype";
            public static String USERNAME = TEXT + ".username";
            public static String PASSWORD = TEXT + ".password";
            public static String NAME = TEXT + ".name";
            public static String DESCRIPTION = TEXT + ".description";
            public static String IDENTIFIER = TEXT + ".identifier";

            public interface DETAIL {

                public static String TEXT = WARNING.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

        public interface ERROR {

            public static String TEXT = MESSAGE.TEXT + ".error";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String USERNAME = TEXT + ".username";
            public static String NAME = TEXT + ".name";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = ERROR.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

        public interface FATAL {

            public static String TEXT = MESSAGE.TEXT + ".fatal";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = FATAL.TEXT + ".detail";
                public static String DATABASE = TEXT + ".database";
            }
        }

        public interface INFO {

            public static String TEXT = MESSAGE.TEXT + ".info";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = INFO.TEXT + ".detail";

                public interface OBJECT {

                    public static String TEXT = DETAIL.TEXT + ".object";
                    public static String BRAND = TEXT + ".brand";
                    public static String CLIENT = TEXT + ".client";
                    public static String EMPLOYEE = TEXT + ".employee";
                    public static String PROVIDER = TEXT + ".provider";
                }

                public interface ACTION {

                    public static String TEXT = DETAIL.TEXT + ".action";
                    public static String CREATE = TEXT + ".create";
                }
            }
        }
    }
}
