package com.softdesign.school.utils;


import android.util.Log;


/**
 * Класс логирования. Обертка над классом android.util.Log
 */
public class Lg {

    /**
     * Константа определяющая источник сообщения
     */
    private static final String PREFIX = "SCHOOL ";

    /**
     * Константа. Максимальная длина выводимого сообщения в строке
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    private Lg() {
    }

    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
        return true;
//        return false;
    }

    /**
     * Уровень лога INFO.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void i(String tag, String text) {
        printLog(Log.INFO, tag, text);
    }

    /**
     * Уровень лога ERROR.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void e(String tag, String text) {
        printLog(Log.ERROR, tag, text);
    }

    /**
     * Уровень лога WARN.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void w(String tag, String text) {
        printLog(Log.WARN, tag, text);
    }

    /**
     * Уровень лога DEBUG.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void d(String tag, String text) {
        printLog(Log.DEBUG, tag, text);
    }

    /**
     * Уровень лога VERBOSE.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void v(String tag, String text) {
        printLog(Log.VERBOSE, tag, text);
    }

    /**
     * Уровень лога ASSERT.
     *
     * @param tag  Источник инициировавший сообщение
     * @param text Текст сообщения
     */
    public static void a(String tag, String text) {
        printLog(Log.ASSERT, tag, text);
    }

    /**
     * Проверяет необходимость вывода лога. Форматирует текст выводимого лога
     * под определенное количестов символов. Выводит лог используя встроенный
     * класс android.util.Log.
     *
     * @param logCode константа определяющая уровень выводимого лога
     * @param tag     константа содержащая источник инициировавший сообщение
     * @param text    Сообщение для вывода
     */
    public static void printLog(Integer logCode, String tag, String text) {

        if (shouldLog()) {

            String tmpStr = text;

            while (tmpStr.length() > LOGCAT_BUFFER_SIZE) {
                String s1 = tmpStr.substring(0, LOGCAT_BUFFER_SIZE);
                tmpStr = tmpStr.substring(LOGCAT_BUFFER_SIZE);
                Log.println(logCode, PREFIX + tag, s1);
            }

            Log.println(logCode, PREFIX + tag, tmpStr);

        }

    }
}
