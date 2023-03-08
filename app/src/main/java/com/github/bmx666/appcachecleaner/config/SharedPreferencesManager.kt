package com.github.bmx666.appcachecleaner.config

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.github.bmx666.appcachecleaner.const.Constant
import java.util.*

class SharedPreferencesManager {

    class ExtraSearchText {

        companion object {

            private const val FILENAME = "ExtraSearchText"
            private const val KEY_CLEAR_CACHE = "clear_cache"
            private const val KEY_STORAGE = "storage"

            @JvmStatic
            private fun getExtraSearchTextSharedPref(context: Context): SharedPreferences {
                return context.getSharedPreferences(FILENAME, AppCompatActivity.MODE_PRIVATE)
            }

            @JvmStatic
            fun getClearCache(context: Context, locale: Locale): String? {
                return getExtraSearchTextSharedPref(context)
                    .getString("$locale,$KEY_CLEAR_CACHE", null)
            }

            @JvmStatic
            fun saveClearCache(context: Context, locale: Locale, value: CharSequence?) {
                if (value.isNullOrEmpty() or value!!.trim().isEmpty()) return

                getExtraSearchTextSharedPref(context)
                    .edit()
                    .putString("$locale,$KEY_CLEAR_CACHE", value.toString())
                    .apply()
            }

            @JvmStatic
            fun removeClearCache(context: Context, locale: Locale) {
                getExtraSearchTextSharedPref(context)
                    .edit()
                    .remove("$locale,$KEY_CLEAR_CACHE")
                    .apply()
            }

            @JvmStatic
            fun getStorage(context: Context, locale: Locale): String? {
                return getExtraSearchTextSharedPref(context)
                    .getString("$locale,$KEY_STORAGE", null)
            }

            @JvmStatic
            fun saveStorage(context: Context, locale: Locale, value: CharSequence?) {
                if (value.isNullOrEmpty() or value!!.trim().isEmpty()) return

                getExtraSearchTextSharedPref(context)
                    .edit()
                    .putString("$locale,$KEY_STORAGE", value.toString())
                    .apply()
            }

            @JvmStatic
            fun removeStorage(context: Context, locale: Locale) {
                getExtraSearchTextSharedPref(context)
                    .edit()
                    .remove("$locale,$KEY_STORAGE")
                    .apply()
            }
        }
    }

    class PackageList {

        companion object {

            private const val FILENAME = "package-list"
            private const val LIST_NAMES = "list_names"

            @JvmStatic
            private fun getCheckedPackagesListSharedPref(context: Context): SharedPreferences {
                return context.getSharedPreferences(FILENAME, AppCompatActivity.MODE_PRIVATE)
            }

            fun getNames(context: Context): Set<String> {
                return getCheckedPackagesListSharedPref(context)
                    .getStringSet(LIST_NAMES, HashSet()) ?: HashSet()
            }

            @JvmStatic
            fun get(context: Context, name: String): Set<String> {
                return getCheckedPackagesListSharedPref(context)
                    .getStringSet(name, HashSet()) ?: HashSet()
            }

            @JvmStatic
            fun save(context: Context, name: String, checkedPkgList: Set<String>) {
                val names = getNames(context) as MutableSet<String>
                names.add(name)
                getCheckedPackagesListSharedPref(context)
                    .edit()
                    .putStringSet(LIST_NAMES, names)
                    .putStringSet(name, checkedPkgList)
                    .apply()
            }

            @JvmStatic
            fun remove(context: Context, name: String) {
                val names = getNames(context) as MutableSet<String>
                names.remove(name)
                getCheckedPackagesListSharedPref(context)
                    .edit()
                    .putStringSet(LIST_NAMES, names)
                    .remove(name)
                    .apply()
            }
        }
    }

    class ExtraButtons {

        companion object {

            private const val KEY_CLOSE_APP = "show_button_close_app"
            private const val KEY_START_STOP_SERVICE = "show_button_start_stop_service"

            @JvmStatic
            private fun getDefaultSharedPref(context: Context): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(context)
            }

            @JvmStatic
            fun getShowStartStopService(context: Context): Boolean {
                return getDefaultSharedPref(context)
                    .getBoolean(KEY_START_STOP_SERVICE, false)
            }

            @JvmStatic
            fun getShowCloseApp(context: Context): Boolean {
                return getDefaultSharedPref(context)
                    .getBoolean(KEY_CLOSE_APP, false)
            }
        }
    }

    class Filter {

        companion object {

            private const val KEY_MIN_CACHE_SIZE_BYTES = "filter_min_cache_size_bytes"

            @JvmStatic
            private fun getDefaultSharedPref(context: Context): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(context)
            }

            @JvmStatic
            @RequiresApi(Build.VERSION_CODES.O)
            fun getMinCacheSize(context: Context): Long {
                return getDefaultSharedPref(context)
                    .getLong(KEY_MIN_CACHE_SIZE_BYTES, 0L)
            }

            @JvmStatic
            @RequiresApi(Build.VERSION_CODES.O)
            fun saveMinCacheSize(context: Context, value: Long) {
                getDefaultSharedPref(context)
                    .edit()
                    .putLong(KEY_MIN_CACHE_SIZE_BYTES, value)
                    .apply()
            }

            @JvmStatic
            @RequiresApi(Build.VERSION_CODES.O)
            fun removeMinCacheSize(context: Context) {
                getDefaultSharedPref(context)
                    .edit()
                    .remove(KEY_MIN_CACHE_SIZE_BYTES)
                    .apply()
            }
        }
    }

    class FirstBoot {

        companion object {
            private const val KEY_SHOW_DIALOG_HELP_CUSTOMIZED_SETTINGS_UI =
                "show_dialog_help_customized_settings_ui"

            @JvmStatic
            private fun getDefaultSharedPref(context: Context): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(context)
            }

            @JvmStatic
            fun showDialogHelpCustomizedSettingsUI(context: Context): Boolean {
                return getDefaultSharedPref(context)
                    .getBoolean(KEY_SHOW_DIALOG_HELP_CUSTOMIZED_SETTINGS_UI, true)
            }

            @JvmStatic
            fun hideDialogHelpCustomizedSettingsUI(context: Context) {
                getDefaultSharedPref(context)
                    .edit()
                    .putBoolean(KEY_SHOW_DIALOG_HELP_CUSTOMIZED_SETTINGS_UI, false)
                    .apply()
            }
        }
    }

    class UI {
        companion object {

            private const val KEY_NIGHT_MODE = "ui_night_mode"

            @JvmStatic
            private fun getDefaultSharedPref(context: Context): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(context)
            }

            @JvmStatic
            fun getNightMode(context: Context): Boolean {
                return getDefaultSharedPref(context)
                    .getBoolean(KEY_NIGHT_MODE, false)
            }
        }
    }

    class Settings {
        companion object {
            private const val KEY_MAX_WAIT_APP_TIMEOUT_MS = "settings_max_wait_app_timeout_ms"

            @JvmStatic
            private fun getDefaultSharedPref(context: Context): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(context)
            }

            @JvmStatic
            fun getMaxWaitAppTimeoutMs(context: Context): Int {
                return getDefaultSharedPref(context)
                    .getInt(KEY_MAX_WAIT_APP_TIMEOUT_MS,
                        Constant.Settings.CacheClean.DEFAULT_WAIT_APP_PERFORM_CLICK_MS)
            }

            @JvmStatic
            fun setMaxWaitAppTimeoutMs(context: Context, timeout: Int) {
                getDefaultSharedPref(context)
                    .edit()
                    .putInt(KEY_MAX_WAIT_APP_TIMEOUT_MS, timeout)
                    .apply()
            }
        }
    }
}