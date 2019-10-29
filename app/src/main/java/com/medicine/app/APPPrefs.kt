package com.medicine.app

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.Gson
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException


object APPPrefs {
    private val DEFAULT_SUFFIX = "_aqprefs"
    private val LENGTH = "#LENGTH"
    private var mPrefs: SharedPreferences? = null
    private val GSON = Gson()

    /**
     * Returns an instance of the shared preference for this app.
     *
     * @return an Instance of the SharedPreference
     *
     * @throws RuntimeException
     * if sharedpreference instance has not been instatiated yet.
     */
    val preferences: SharedPreferences
        get() {
            if (mPrefs != null) {
                return this.mPrefs!!
            }
            throw RuntimeException(
                "Prefs class not correctly instantiated please call Builder.setContext().build(); in the AppController class onCreate."
            )
        }

    /**
     * @return Returns a map containing a list of pairs key/value representing
     * the preferences.
     * @see SharedPreferences.getAll
     */
    val all: Map<String, *>
        get() = preferences.all

    /**
     * Initialize the Prefs helper class to keep a reference to the
     * SharedPreference for this application the SharedPreference will use the
     * package name of the application as the Key.
     *
     * This method
     *
     * @param context
     * the AppController context.
     */
    @Deprecated("")
    fun initPrefs(context: Context) {
        Builder().setContext(context).build()
    }

    /**
     * @hide
     */
    private fun initPrefs(context: Context, prefsName: String, mode: Int) {
        mPrefs = context.getSharedPreferences(prefsName, mode)
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not an int.
     * @see SharedPreferences.getInt
     */
    fun getInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean.
     * @see SharedPreferences.getBoolean
     */
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long.
     * @see SharedPreferences.getLong
     */
    fun getLong(key: String, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    /**
     * Returns the double that has been saved as a long raw bits value in the
     * long preferences.
     *
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * the double Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long.
     * @see SharedPreferences.getLong
     */
    fun getDouble(key: String, defValue: Double): Double {
        return java.lang.Double.longBitsToDouble(
            preferences.getLong(
                key,
                java.lang.Double.doubleToLongBits(defValue)
            )
        )
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float.
     * @see SharedPreferences.getFloat
     */
    fun getFloat(key: String, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a String.
     * @see SharedPreferences.getString
     */
    fun getString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }

    /**
     * @param key
     * The name of the preference to retrieve.
     * @param defValue
     * Value to return if this preference does not exist.
     * @return Returns the preference values if they exist, or defValues. Throws
     * ClassCastException if there is a preference with this name that
     * is not a Set.
     * @see SharedPreferences.getStringSet
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getStringSet(
        key: String,
        defValue: Set<String>
    ): Set<String>? {
        val prefs = preferences
        return prefs.getStringSet(key, defValue)
    }

    fun <T> getObject(key: String, a: Class<T>): T? {
        val prefs = preferences
        val gson = prefs.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, a)
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Object storaged with key "
                            + key + " is instanceof other class"
                )
            }

        }
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putLong
     */
    fun putLong(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putInt
     */
    fun putInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Saves the double as a long raw bits inside the preferences.
     *
     * @param key
     * The name of the preference to modify.
     * @param value
     * The double value to be save in the preferences.
     * @see Editor.putLong
     */
    fun putDouble(key: String, value: Double) {
        val editor = preferences.edit()
        editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putFloat
     */
    fun putFloat(key: String, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putBoolean
     */
    fun putBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putString
     */
    fun putString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to modify.
     * @param value
     * The new value for the preference.
     * @see Editor.putStringSet
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun putStringSet(key: String, value: Set<String>) {
        val editor = preferences.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun putObject(key: String?, `object`: Any?) {
        val editor = preferences.edit()

        if (`object` == null) {
            throw IllegalArgumentException("object is null")
        }

        if (key == "" || key == null) {
            throw IllegalArgumentException("key is empty or null")
        }

        editor.putString(key, GSON.toJson(`object`))

        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to remove.
     * @see Editor.remove
     */
    fun remove(key: String) {
        val prefs = preferences
        val editor = prefs.edit()
        if (prefs.contains(key + LENGTH)) {
            // Workaround for pre-HC's lack of StringSets
            val stringSetLength = prefs.getInt(key + LENGTH, -1)
            if (stringSetLength >= 0) {
                editor.remove(key + LENGTH)
                for (i in 0 until stringSetLength) {
                    editor.remove("$key[$i]")
                }
            }
        }
        editor.remove(key)

        editor.apply()
    }

    /**
     * @param key
     * The name of the preference to check.
     * @see SharedPreferences.contains
     * @return true if preference contains this key value.
     */
    operator fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    /**
     * @see Editor.clear
     * @return the [Editor] that has been cleared.
     */
    fun clear(): Editor {
        return preferences.edit().clear()
    }

    fun backupUserPrefs(context: Context) {

//        val prefsFile = File(
//            context.filesDir, "../shared_prefs/" +
//                    context.packageName + ".xml"
//        )

        val prefsFile =
            File("/data/data/au.com.dsprima.eqpairkiosk.individualpereg/shared_prefs/au.com.dsprima.eqpairkiosk.individualpereg_aqprefs.xml")


        val backupFile = File(
            Environment.getExternalStorageDirectory(),
            "preferenceBackup.xml"
        )
        var error = ""

        try {
            val src = FileInputStream(prefsFile).getChannel()
            val dst = FileOutputStream(backupFile).getChannel()

            dst.transferFrom(src, 0, src.size())

            src.close()
            dst.close()

            return
        } catch (e: FileNotFoundException) {
            error = e.message!!
            e.printStackTrace()
        } catch (e: IOException) {
            error = e.message!!
            e.printStackTrace()
        }

        val toast = Toast.makeText(
            context, error, Toast.LENGTH_LONG
        )
        toast.show()

    }

    fun restoreUserPrefs(context: Context): Boolean {
        val backupFile = File(
            Environment.getExternalStorageDirectory(),
            "preferenceBackup.xml"
        )
        var error = ""

        try {

            val editor = preferences.edit()

            val inputStream = FileInputStream(backupFile)

            val docFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = docFactory.newDocumentBuilder()

            val doc = docBuilder.parse(inputStream)
            val root = doc.getDocumentElement()

            var child = root.getFirstChild()
            while (child != null) {
                if (child!!.getNodeType() === Node.ELEMENT_NODE) {

                    val element = child as Element

                    val type = element.getNodeName()
                    val name = element.getAttribute("name")

                    // In my app, all prefs seem to get serialized as either "string" or
                    // "boolean" - this will need expanding if yours uses any other types!
                    if (type == "string") {
                        val value = element.getTextContent()
                        editor.putString(name, value)
                    } else if (type == "boolean") {
                        val value = element.getAttribute("value")
                        editor.putBoolean(name, value == "true")
                    }
                }

                child = child.getNextSibling()

            }

            editor.commit()

//            val toast =
//                Toast.makeText(context, "Restored user prefs from " + backupFile.absolutePath, Toast.LENGTH_SHORT)
//            toast.show()

            return true

        } catch (e: FileNotFoundException) {
            error = e.message!!
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            error = e.message!!
            e.printStackTrace()
        } catch (e: SAXException) {
            error = e.message!!
            e.printStackTrace()
        } catch (e: IOException) {
            error = e.message!!
            e.printStackTrace()
        }

        val toast = Toast.makeText(
            context, "Failed to restore user prefs from " +
                    backupFile.absolutePath + " - " + error, Toast.LENGTH_SHORT
        )
        toast.show()

        return false
    }

    /**
     * Builder class for the EasyPrefs instance. You only have to call this once
     * in the AppController onCreate. And in the rest of the code base you can
     * call Prefs.method name.
     */
    class Builder {
        private var mKey: String? = null
        private var mContext: Context? = null
        private var mMode = -1
        private var mUseDefault = false

        /**
         * Set the filename of the sharedprefence instance usually this is the
         * applications packagename.xml but for migration purposes or
         * customization.
         *
         * @param prefsName
         * the filename used for the sharedpreference
         * @return the {@linkcom.pixplicity.easyprefs.library.Prefs.Builder}
         * object.
         */
        fun setPrefsName(prefsName: String): Builder {
            mKey = prefsName
            return this
        }

        /**
         * Set the context used to instantiate the sharedpreferences
         *
         * @param context
         * the application context
         * @return the {@linkcom.pixplicity.easyprefs.library.Prefs.Builder}
         * object.
         */
        fun setContext(context: Context): Builder {
            mContext = context
            return this
        }

        /**
         * Set the mode of the sharedpreference instance.
         *
         * @param mode
         * Operating mode. Use 0 or [Context.MODE_PRIVATE] for
         * the default operation, [Context.MODE_WORLD_READABLE]
         * @return the {@linkcom.pixplicity.easyprefs.library.Prefs.Builder}
         * object.
         * @see Context.getSharedPreferences
         */
        fun setMode(mode: Int): Builder {
            if (mode == ContextWrapper.MODE_PRIVATE || mode == ContextWrapper.MODE_MULTI_PROCESS) {
                mMode = mode
            } else {
                throw RuntimeException(
                    "The mode in the sharedpreference can only be set too ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS"
                )
            }

            return this
        }

        /**
         * Set the default sharedpreference file name. Often the package name of
         * the application is used, but if the
         * [android.preference.PreferenceActivity] or
         * [android.preference.PreferenceFragment] is used android append
         * that with _preference.
         *
         * @param defaultSharedPreference
         * true if default sharedpreference name should used.
         * @return the {@linkcom.pixplicity.easyprefs.library.Prefs.Builder}
         * object.
         */
        fun setUseDefaultSharedPreference(defaultSharedPreference: Boolean): Builder {
            mUseDefault = defaultSharedPreference
            return this
        }

        /**
         * Initialize the sharedpreference instance to used in the application.
         *
         * @throws RuntimeException
         * if context has not been set.
         */
        fun build() {
            if (mContext == null) {
                throw RuntimeException("Context not set, please set context before building the Prefs instance.")
            }

            if (TextUtils.isEmpty(mKey)) {
                mKey = mContext!!.packageName
            }

            if (mUseDefault) {
                mKey += DEFAULT_SUFFIX
            }

            if (mMode == -1) {
                mMode = ContextWrapper.MODE_PRIVATE
            }

            APPPrefs.initPrefs(mContext!!, mKey.toString(), mMode)
        }
    }
}