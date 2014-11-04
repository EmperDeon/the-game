package utils.json;
/*
 * Copyright (c) 2002 JSON.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * The Software shall be used for Good, not Evil.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A JSONObject is an unordered collection of name/value pairs. Its external
 * form is a string wrapped in curly braces with colons between the names and
 * values, and commas between the values and names. The internal form is an
 * object having <code>get</code> and <code>get</code> methods for accessing
 * the values by name, and <code>put</code> methods for adding or replacing
 * values by name. The values can be any of these types: <code>Boolean</code>,
 * <code>JSONArray</code>, <code>JSONObject</code>, <code>Number</code>,
 * <code>String</code>, or the <code>JSONObject.NULL</code> object. A
 * JSONObject constructor can be used to convert an external form JSON text
 * into an internal form whose values can be retrieved with the
 * <code>get</code> and <code>get</code> methods, or to convert values into a
 * JSON text using the <code>put</code> and <code>toString</code> methods. A
 * <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>get</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining getional values.
 * <p>
 * The generic <code>get()</code> and <code>get()</code> methods return an
 * object, which you can cast or query for type. There are also typed
 * <code>get</code> and <code>get</code> methods that do type checking and type
 * coercion for you. The get methods differ from the get methods in that they
 * do not throw. Instead, they return a specified value, such as null.
 * <p>
 * The <code>put</code> methods add or replace values in an object. For
 * example,
 *
 * <pre>
 * myString = new JSONObject()
 *         .put(&quot;JSON&quot;, &quot;Hello, World!&quot;).toString();
 * </pre>
 *
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * the JSON syntax rules. The constructors are more forgiving in the texts they
 * will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 * before the closing brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 * quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a
 * quote or single quote, and if they do not contain leading or trailing
 * spaces, and if they do not contain any of these characters:
 * <code>{ } [ ] / \ : , #</code> and if they do not look like numbers and
 * if they are not the reserved words <code>true</code>, <code>false</code>,
 * or <code>null</code>.</li>
 * </ul>
 *
 * @author JSON.org
 * @version 2013-06-17
 */
public final class JSONObject {

 /**
  * The map where the JSONObject's properties are kept.
  */
 private final Map<String , Object> map = new HashMap<>();

 public JSONObject () {
 }

 public JSONObject ( String file ) {
  load(file);
 }

 public JSONObject ( Map<String , Object> map ) {
  if ( map != null ) {
   Iterator<Entry<String , Object>> i = map.entrySet().iterator();
   while ( i.hasNext() ) {
    Entry<String , Object> entry = i.next();
    Object value = entry.getValue();
    if ( value != null ) {
     this.map.put(entry.getKey() , wrap(value));
    }
   }
  }
 }

 public JSONObject ( Object bean ) {
  this.populateMap(bean);
 }

 /**
  * Accumulate values under a key. It is similar to the put method except
  * that if there is already an object stored under the key then a JSONArray
  * is stored under the key to hold all of the accumulated values. If there
  * is already a JSONArray, then the new value is appended to it. In
  * contrast, the put method replaces the previous value.
  *
  * If only one value is accumulated that is not a JSONArray, then the result
  * will be the same as using put. But if multiple values are accumulated,
  * then the result will be like append.
  *
  * @param key
  *              A key string.
  * @param value
  *              An object to be accumulated under the key.
  *
  * @return this.
  *
  * @throws JSONException
  *                       If the value is an invalid number or if the key is null.
  */
 public JSONObject accumulate ( String key , Object value ) throws JSONException {
  testValidity(value);
  Object object = this.get(key);
  if ( object == null ) {
   this.put(key ,
            value instanceof JSONArray ? new JSONArray().put(value)
                    : value);
  } else if ( object instanceof JSONArray ) {
   ( ( JSONArray ) object ).put(value);
  } else {
   this.put(key , new JSONArray().put(object).put(value));
  }
  return this;
 }

 /**
  * Append values to the array under a key. If the key does not exist in the
  * JSONObject, then the key is put in the JSONObject with its value being a
  * JSONArray containing the value parameter. If the key was already
  * associated with a JSONArray, then the value parameter is appended to it.
  *
  * @param key
  *              A key string.
  * @param value
  *              An object to be accumulated under the key.
  *
  * @return this.
  *
  * @throws JSONException
  *                       If the key is null or if the current value associated with
  *                       the key is not a JSONArray.
  */
 public JSONObject append ( String key , Object value ) throws JSONException {
  testValidity(value);
  Object object = this.get(key);
  if ( object == null ) {
   this.put(key , new JSONArray().put(value));
  } else if ( object instanceof JSONArray ) {
   this.put(key , ( ( JSONArray ) object ).put(value));
  } else {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "JSONObject[" + key
                      + "] is not a JSONArray."));
  }
  return this;
 }

 /**
  * Produce a string from a double. The string "null" will be returned if the
  * number is not finite.
  *
  * @param d
  *          A double.
  *
  * @return A String.
  */
 public static String doubleToString ( double d ) {
  if ( Double.isInfinite(d) || Double.isNaN(d) ) {
   return "null";
  }

// Shave off trailing zeros and decimal point, if possible.
  String string = Double.toString(d);
  if ( string.indexOf('.') > 0 && string.indexOf('e') < 0
       && string.indexOf('E') < 0 ) {
   while ( string.endsWith("0") ) {
    string = string.substring(0 , string.length() - 1);
   }
   if ( string.endsWith(".") ) {
    string = string.substring(0 , string.length() - 1);
   }
  }
  return string;
 }

 /**
  * Get the value object associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return The object associated with the key.
  *
  * @throws JSONException
  *                       if the key is not found.
  */
 public Object get ( String key ) throws JSONException {
  if ( key == null ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException("Null key."));
  }
  Object object = this.opt(key);
  if ( object == null ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "JSONObject[" + quote(key) + "] not found."));
  }
  return object;
 }

 /**
  * Get the boolean value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return The truth.
  *
  * @throws JSONException
  *                       if the value is not a Boolean or the String "true" or
  *                       "false".
  */
 public boolean getBoolean ( String key ) throws JSONException {
  Object object = this.get(key);
  if ( object.equals(Boolean.FALSE)
       || ( object instanceof String && ( ( String ) object )
          .equalsIgnoreCase("false") ) ) {
   return false;
  } else if ( object.equals(Boolean.TRUE)
              || ( object instanceof String && ( ( String ) object )
          .equalsIgnoreCase("true") ) ) {
   return true;
  }
  main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                     "JSONObject[" + quote(key)
                     + "] is not a Boolean."));
  return true;
 }

 /**
  * Get the double value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return The numeric value.
  *
  * @throws JSONException
  *                       if the key is not found or if the value is not a Number
  *                       object and cannot be converted to a number.
  */
 public double getDouble ( String key ) throws JSONException {
  Object object = this.get(key);
  try {
   return object instanceof Number ? ( ( Number ) object ).doubleValue()
           : Double.parseDouble(( String ) object);
  } catch ( Exception e ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "JSONObject[" + quote(key)
                      + "] is not a number."));
  }
  return 0;
 }

 /**
  * Get the int value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return The integer value.
  *
  * @throws JSONException
  *                       if the key is not found or if the value cannot be converted
  *                       to an integer.
  */
 public int getInt ( String key ) throws JSONException {
  Object object = this.get(key);
  try {
   return object instanceof Number ? ( ( Number ) object ).intValue()
           : Integer.parseInt(( String ) object);
  } catch ( Exception e ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "JSONObject[" + quote(key)
                      + "] is not an int."));
   return 0;
  }
 }

 /**
  * Get the JSONArray value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return A JSONArray which is the value.
  *
  * @throws JSONException
  *                       if the key is not found or if the value is not a JSONArray.
  */
 public JSONArray getJSONArray ( String key ) throws JSONException {
  Object object = this.get(key);
  if ( object instanceof JSONArray ) {
   return ( JSONArray ) object;
  }
  main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                     "JSONObject[" + quote(key)
                     + "] is not a JSONArray."));
  return null;
 }

 /**
  * Get the JSONObject value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return A JSONObject which is the value.
  *
  * @throws JSONException
  *                       if the key is not found or if the value is not a JSONObject.
  */
 public JSONObject getJSONObject ( String key ) throws JSONException {
  Object object = this.get(key);
  if ( object instanceof JSONObject ) {
   return ( JSONObject ) object;
  }
  main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                     "JSONObject[" + quote(key)
                     + "] is not a JSONObject."));
  return null;
 }

 /**
  * Get the long value associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return The long value.
  *
  * @throws JSONException
  *                       if the key is not found or if the value cannot be converted
  *                       to a long.
  */
 public long getLong ( String key ) throws JSONException {
  Object object = this.get(key);
  try {
   return object instanceof Number ? ( ( Number ) object ).longValue()
           : Long.parseLong(( String ) object);
  } catch ( Exception e ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "JSONObject[" + quote(key)
                      + "] is not a long."));
  }
  return 0;
 }

 /**
  * Get an array of field names from a JSONObject.
  *
  * @param jo
  *
  * @return An array of field names, or null if there are no names.
  */
 public static String[] getNames ( JSONObject jo ) {
  int length = jo.length();
  if ( length == 0 ) {
   return null;
  }
  Iterator iterator = jo.keys();
  String[] names = new String[length];
  int i = 0;
  while ( iterator.hasNext() ) {
   names[i] = ( String ) iterator.next();
   i += 1;
  }
  return names;
 }

 /**
  * Get an array of field names from an Object.
  *
  * @param object
  *
  * @return An array of field names, or null if there are no names.
  */
 public static String[] getNames ( Object object ) {
  if ( object == null ) {
   return null;
  }
  Class klass = object.getClass();
  Field[] fields = klass.getFields();
  int length = fields.length;
  if ( length == 0 ) {
   return null;
  }
  String[] names = new String[length];
  for ( int i = 0 ; i < length ; i += 1 ) {
   names[i] = fields[i].getName();
  }
  return names;
 }

 /**
  * Get the string associated with a key.
  *
  * @param key
  *            A key string.
  *
  * @return A string which is the value.
  *
  * @throws JSONException
  *                       if there is no string value for the key.
  */
 public String getString ( String key ) throws JSONException {
  Object object = this.get(key);
  if ( object instanceof String ) {
   return ( String ) object;
  }
  main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                     "JSONObject[" + quote(key) + "] not a string."));
  return null;
 }

 /**
  * Determine if the JSONObject contains a specific key.
  *
  * @param key
  *            A key string.
  *
  * @return true if the key exists in the JSONObject.
  */
 public boolean has ( String key ) {
  return this.map.containsKey(key);
 }

 /**
  * Increment a property of a JSONObject. If there is no such property,
  * create one with a value of 1. If there is such a property, and if it is
  * an Integer, Long, Double, or Float, then add one to it.
  *
  * @param key
  *            A key string.
  *
  * @return this.
  *
  * @throws JSONException
  *                       If there is already a property with this name that is not an
  *                       Integer, Long, Double, or Float.
  */
 public JSONObject increment ( String key ) throws JSONException {
  Object value = this.get(key);
  if ( value == null ) {
   this.put(key , 1);
  } else if ( value instanceof Integer ) {
   this.put(key , ( ( Integer ) value ) + 1);
  } else if ( value instanceof Long ) {
   this.put(key , ( ( Long ) value ) + 1);
  } else if ( value instanceof Double ) {
   this.put(key , ( ( Double ) value ) + 1);
  } else if ( value instanceof Float ) {
   this.put(key , ( ( Float ) value ) + 1);
  } else {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "Unable to increment [" + quote(key) + "]."));
  }
  return this;
 }

 /**
  * Determine if the value associated with the key is null or if there is no
  * value.
  *
  * @param key
  *            A key string.
  *
  * @return true if there is no value associated with the key or if the value
  *         is the JSONObject.NULL object.
  */
 public boolean isNull ( String key ) {
  return this.map.containsKey(key);
 }

 /**
  * Get an enumeration of the keys of the JSONObject.
  *
  * @return An iterator of the keys.
  */
 public Iterator keys () {
  return this.keySet().iterator();
 }

 /**
  * Get a set of keys of the JSONObject.
  *
  * @return A keySet.
  */
 public Set keySet () {
  return this.map.keySet();
 }

 /**
  * Get the number of keys stored in the JSONObject.
  *
  * @return The number of keys in the JSONObject.
  */
 public int length () {
  return this.map.size();
 }

 public void load ( String file ) {
  JSONTokener x = null;
  try {
   x = new JSONTokener(new InputStreamReader(new FileInputStream(file)));
  } catch ( FileNotFoundException ex ) {

  }

  char c;
  String key;

  if ( x.nextClean() != '{' ) {
   main.Main.LOG.addE("JSONObject.load()" , new Exception(
                      "A JSONObject text must begin with '{'"));
  }
  for ( ;; ) {
   c = x.nextClean();
   switch ( c ) {
    case 0:
     main.Main.LOG.addE("JSONObject.load()" , new Exception(
                        "A JSONObject text must end with '}'"));
    case '}':
     return;
    default:
     x.back();
     key = x.nextValue().toString();
   }

// The key is followed by ':'.
   c = x.nextClean();
   if ( c != ':' ) {
    main.Main.LOG.addE("JSONObject.load()" , new Exception(
                       "Expected a ':' after a key"));
   }
   this.putOnce(key , x.nextValue());

// Pairs are separated by ','.
   switch ( x.nextClean() ) {
    case ';':
    case ',':
     if ( x.nextClean() == '}' ) {
      return;
     }
     x.back();
     break;

    case '}':
     return;
    default:
     main.Main.LOG.addE("JSONObject.load()" , new Exception(
                        "Expected a ',' or '}'"));
   }
  }
 }

 /**
  * Produce a JSONArray containing the names of the elements of this
  * JSONObject.
  *
  * @return A JSONArray containing the key strings, or null if the JSONObject
  *         is empty.
  */
 public JSONArray names () {
  JSONArray ja = new JSONArray();
  Iterator keys = this.keys();
  while ( keys.hasNext() ) {
   ja.put(keys.next());
  }
  return ja.length() == 0 ? null : ja;
 }

 /**
  * Produce a string from a Number.
  *
  * @param number
  *               A Number
  *
  * @return A String.
  *
  * @throws JSONException
  *                       If n is a non-finite number.
  */
 public static String numberToString ( Number number ) throws JSONException {
  if ( number == null ) {
   main.Main.LOG.addE("JSONObject.load()" , new JSONException("Null pointer"));
  }
  testValidity(number);

// Shave off trailing zeros and decimal point, if possible.
  String string = number.toString();
  if ( string.indexOf('.') > 0 && string.indexOf('e') < 0
       && string.indexOf('E') < 0 ) {
   while ( string.endsWith("0") ) {
    string = string.substring(0 , string.length() - 1);
   }
   if ( string.endsWith(".") ) {
    string = string.substring(0 , string.length() - 1);
   }
  }
  return string;
 }

 public Object opt ( String key ) {
  return key == null ? null : this.map.get(key);
 }

 /**
  * Get an optional boolean associated with a key. It returns false if there
  * is no such key, or if the value is not Boolean.TRUE or the String "true".
  *
  * @param key
  *            A key string.
  *
  * @return The truth.
  */
 public boolean optBoolean ( String key ) {
  return this.optBoolean(key , false);
 }

 /**
  * Get an optional boolean associated with a key. It returns the
  * defaultValue if there is no such key, or if it is not a Boolean or the
  * String "true" or "false" (case insensitive).
  *
  * @param key
  *                     A key string.
  * @param defaultValue
  *                     The default.
  *
  * @return The truth.
  */
 public boolean optBoolean ( String key , boolean defaultValue ) {
  try {
   return this.getBoolean(key);
  } catch ( Exception e ) {
   return defaultValue;
  }
 }

 /**
  * Get an optional double associated with a key, or NaN if there is no such
  * key or if its value is not a number. If the value is a string, an attempt
  * will be made to evaluate it as a number.
  *
  * @param key
  *            A string which is the key.
  *
  * @return An object which is the value.
  */
 public double optDouble ( String key ) {
  return this.optDouble(key , Double.NaN);
 }

 /**
  * Get an optional double associated with a key, or the defaultValue if
  * there is no such key or if its value is not a number. If the value is a
  * string, an attempt will be made to evaluate it as a number.
  *
  * @param key
  *                     A key string.
  * @param defaultValue
  *                     The default.
  *
  * @return An object which is the value.
  */
 public double optDouble ( String key , double defaultValue ) {
  try {
   return this.getDouble(key);
  } catch ( Exception e ) {
   return defaultValue;
  }
 }

 /**
  * Get an optional int value associated with a key, or zero if there is no
  * such key or if the value is not a number. If the value is a string, an
  * attempt will be made to evaluate it as a number.
  *
  * @param key
  *            A key string.
  *
  * @return An object which is the value.
  */
 public int optInt ( String key ) {
  return this.optInt(key , 0);
 }

 /**
  * Get an optional int value associated with a key, or the default if there
  * is no such key or if the value is not a number. If the value is a string,
  * an attempt will be made to evaluate it as a number.
  *
  * @param key
  *                     A key string.
  * @param defaultValue
  *                     The default.
  *
  * @return An object which is the value.
  */
 public int optInt ( String key , int defaultValue ) {
  try {
   return this.getInt(key);
  } catch ( Exception e ) {
   return defaultValue;
  }
 }

 /**
  * Get an optional JSONArray associated with a key. It returns null if there
  * is no such key, or if its value is not a JSONArray.
  *
  * @param key
  *            A key string.
  *
  * @return A JSONArray which is the value.
  */
 public JSONArray optJSONArray ( String key ) {
  Object o = this.opt(key);
  return o instanceof JSONArray ? ( JSONArray ) o : null;
 }

 /**
  * Get an optional JSONObject associated with a key. It returns null if
  * there is no such key, or if its value is not a JSONObject.
  *
  * @param key
  *            A key string.
  *
  * @return A JSONObject which is the value.
  */
 public JSONObject optJSONObject ( String key ) {
  Object object = this.opt(key);
  return object instanceof JSONObject ? ( JSONObject ) object : null;
 }

 /**
  * Get an optional long value associated with a key, or zero if there is no
  * such key or if the value is not a number. If the value is a string, an
  * attempt will be made to evaluate it as a number.
  *
  * @param key
  *            A key string.
  *
  * @return An object which is the value.
  */
 public long optLong ( String key ) {
  return this.optLong(key , 0);
 }

 /**
  * Get an optional long value associated with a key, or the default if there
  * is no such key or if the value is not a number. If the value is a string,
  * an attempt will be made to evaluate it as a number.
  *
  * @param key
  *                     A key string.
  * @param defaultValue
  *                     The default.
  *
  * @return An object which is the value.
  */
 public long optLong ( String key , long defaultValue ) {
  try {
   return this.getLong(key);
  } catch ( Exception e ) {
   return defaultValue;
  }
 }

 /**
  * Get an optional string associated with a key. It returns an empty string
  * if there is no such key. If the value is not a string and is not null,
  * then it is converted to a string.
  *
  * @param key
  *            A key string.
  *
  * @return A string which is the value.
  */
 public String optString ( String key ) {
  return this.optString(key , "");
 }

 /**
  * Get an optional string associated with a key. It returns the defaultValue
  * if there is no such key.
  *
  * @param key
  *                     A key string.
  * @param defaultValue
  *                     The default.
  *
  * @return A string which is the value.
  */
 public String optString ( String key , String defaultValue ) {
  Object object = this.opt(key);
  return object == null ? object.toString() : defaultValue;
 }

 private void populateMap ( Object bean ) {
  Class klass = bean.getClass();

// If klass is a System class then set includeSuperClass to false.
  boolean includeSuperClass = klass.getClassLoader() != null;

  Method[] methods = includeSuperClass ? klass.getMethods() : klass
          .getDeclaredMethods();
  for ( int i = 0 ; i < methods.length ; i += 1 ) {
   try {
    Method method = methods[i];
    if ( Modifier.isPublic(method.getModifiers()) ) {
     String name = method.getName();
     String key = "";
     if ( name.startsWith("get") ) {
      if ( "getClass".equals(name)
           || "getDeclaringClass".equals(name) ) {
       key = "";
      } else {
       key = name.substring(3);
      }
     } else if ( name.startsWith("is") ) {
      key = name.substring(2);
     }
     if ( key.length() > 0
          && Character.isUpperCase(key.charAt(0))
          && method.getParameterTypes().length == 0 ) {
      if ( key.length() == 1 ) {
       key = key.toLowerCase();
      } else if ( !Character.isUpperCase(key.charAt(1)) ) {
       key = key.substring(0 , 1).toLowerCase()
             + key.substring(1);
      }

      Object result = method.invoke(bean , ( Object[] ) null);
      if ( result != null ) {
       this.map.put(key , wrap(result));
      }
     }
    }
   } catch ( IllegalAccessException | IllegalArgumentException |
             InvocationTargetException ignore ) {
   }
  }
 }

 /**
  * Put a key/boolean pair in the void.
  *
  * @param key
  *              A key string.
  * @param value
  *              A boolean which is the value.
  *
  * @throws JSONException
  *                       If the key is null.
  */
 public void put ( String key , boolean value ) throws JSONException {
  this.put(key , value ? Boolean.TRUE : Boolean.FALSE);
 }

 /**
  * Put a key/value pair in the void, where the value will be a
  * JSONArray which is produced from a Collection.
  *
  * @param key
  *              A key string.
  * @param value
  *              A Collection value.
  *
  * @throws JSONException
  */
 public void put ( String key , Collection value ) throws JSONException {
  this.put(key , new JSONArray(value));

 }

 /**
  * Put a key/double pair in the void.
  *
  * @param key
  *              A key string.
  * @param value
  *              A double which is the value.
  *
  * @throws JSONException
  *                       If the key is null or if the number is invalid.
  */
 public void put ( String key , double value ) throws JSONException {
  this.put(key , new Double(value));

 }

 /**
  * Put a key/int pair in the void.
  *
  * @param key
  *              A key string.
  * @param value
  *              An int which is the value.
  *
  * @throws JSONException
  *                       If the key is null.
  */
 public void put ( String key , int value ) throws JSONException {
  this.put(key , new Integer(value));

 }

 /**
  * Put a key/long pair in the void.
  *
  * @param key
  *              A key string.
  * @param value
  *              A long which is the value.
  *
  * @throws JSONException
  *                       If the key is null.
  */
 public void put ( String key , long value ) throws JSONException {
  this.put(key , new Long(value));

 }

 /**
  * Put a key/value pair in the void, where the value will be a
  * void which is produced from a Map.
  *
  * @param key
  *              A key string.
  * @param value
  *              A Map value.
  *
  * @throws JSONException
  */
 public void put ( String key , Map value ) throws JSONException {
  this.put(key , new JSONObject(value));

 }

 /**
  * Put a key/value pair in the void. If the value is null, then the
  * key will be removed from the void if it is present.
  *
  * @param key
  *              A key string.
  * @param value
  *              An object which is the value. It should be of one of these
  *              types: Boolean, Double, Integer, JSONArray, void, Long,
  *              String, or the void.NULL object.
  *
  * @throws JSONException
  *                       If the value is non-finite number or if the key is null.
  */
 public void put ( String key , Object value ) throws JSONException {
  if ( key == null ) {
   main.Main.LOG.addE("JSONObject.load()" ,
                      new NullPointerException("Null key."));
  }
  if ( value != null ) {
   testValidity(value);
   this.map.put(key , value);
  } else {
   this.remove(key);
  }

 }

 /**
  * Put a key/value pair in the void, but only if the key and the value
  * are both non-null, and only if there is not already a member with that
  * name.
  *
  * @param key
  * @param value
  *
  * @throws JSONException
  *                       if the key is a duplicate
  */
 public void putOnce ( String key , Object value ) throws JSONException {
  if ( key != null && value != null ) {
   if ( this.get(key) != null ) {
    main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                       "Duplicate key \"" + key + "\""));
   }
   this.put(key , value);
  }

 }

 /**
  * Put a key/value pair in the void, but only if the key and the value
  * are both non-null.
  *
  * @param key
  *              A key string.
  * @param value
  *              An object which is the value. It should be of one of these
  *              types: Boolean, Double, Integer, JSONArray, void, Long,
  *              String, or the void.NULL object.
  *
  * @throws JSONException
  *                       If the value is a non-finite number.
  */
 public void putopt ( String key , Object value ) throws JSONException {
  if ( key != null && value != null ) {
   this.put(key , value);
  }

 }

 /**
  * Produce a string in double quotes with backslash sequences in all the
  * right places. A backslash will be inserted within </, producing <\/,
  * allowing JSON text to be delivered in HTML. In JSON text, a string cannot
  * contain a control character or an unescaped quote or backslash.
  *
  * @param string
  *               A String
  *
  * @return A String correctly formatted for insertion in a JSON text.
  */
 public static String quote ( String string ) {
  StringWriter sw = new StringWriter();
  synchronized ( sw.getBuffer() ) {
   try {
    return quote(string , sw).toString();
   } catch ( IOException ignored ) {
    // will never happen - we are writing to a string writer
    return "";
   }
  }
 }

 public static Writer quote ( String string , Writer w ) throws IOException {
  if ( string == null || string.length() == 0 ) {
   w.write("\"\"");
   return w;
  }

  char b;
  char c = 0;
  String hhhh;
  int i;
  int len = string.length();

  w.write('"');
  for ( i = 0 ; i < len ; i += 1 ) {
   b = c;
   c = string.charAt(i);
   switch ( c ) {
    case '\\':
    case '"':
     w.write('\\');
     w.write(c);
     break;
    case '/':
     if ( b == '<' ) {
      w.write('\\');
     }
     w.write(c);
     break;
    case '\b':
     w.write("\\b");
     break;
    case '\t':
     w.write("\\t");
     break;
    case '\n':
     w.write("\\n");
     break;
    case '\f':
     w.write("\\f");
     break;
    case '\r':
     w.write("\\r");
     break;
    default:
     if ( c < ' ' || ( c >= '\u0080' && c < '\u00a0' )
          || ( c >= '\u2000' && c < '\u2100' ) ) {
      w.write("\\u");
      hhhh = Integer.toHexString(c);
      w.write("0000" , 0 , 4 - hhhh.length());
      w.write(hhhh);
     } else {
      w.write(c);
     }
   }
  }
  w.write('"');
  return w;
 }

 /**
  * Remove a name and its value, if present.
  *
  * @param key
  *            The name to be removed.
  *
  * @return The value that was associated with the name, or null if there was
  *         no value.
  */
 public Object remove ( String key ) {
  return this.map.remove(key);
 }

 /**
  * Save this JSONObject to file.
  *
  * @param file
  *             Saving file .
  */
 public void save ( String file ) {
  try ( FileWriter t = new FileWriter(main.Main.mdir + file) ) {
   t.write(write());
   t.flush();
  } catch ( IOException ex ) {
   main.Main.LOG.addE("JSONObject.save()" , ex);
  }
 }

 /**
  * Try to convert a string into a number, boolean, or null. If the string
  * can't be converted, return the string.
  *
  * @param string
  *               A String.
  *
  * @return A simple JSON value.
  */
 public static Object stringToValue ( String string ) {
  Double d;
  if ( string.equals("") ) {
   return string;
  }
  if ( string.equalsIgnoreCase("true") ) {
   return Boolean.TRUE;
  }
  if ( string.equalsIgnoreCase("false") ) {
   return Boolean.FALSE;
  }
  if ( string.equalsIgnoreCase("null") ) {
   return null;
  }

  /*
   * If it might be a number, try converting it. If a number cannot be
   * produced, then the value will just be a string.
   */
  char b = string.charAt(0);
  if ( ( b >= '0' && b <= '9' ) || b == '-' ) {
   try {
    if ( string.indexOf('.') > -1 || string.indexOf('e') > -1
         || string.indexOf('E') > -1 ) {
     d = Double.valueOf(string);
     if ( !d.isInfinite() && !d.isNaN() ) {
      return d;
     }
    } else {
     Long myLong = new Long(string);
     if ( string.equals(myLong.toString()) ) {
      if ( myLong == myLong.intValue() ) {
       return myLong.intValue();
      } else {
       return myLong;
      }
     }
    }
   } catch ( Exception ignore ) {
   }
  }
  return string;
 }

 /**
  * Throw an exception if the object is a NaN or infinite number.
  *
  * @param o
  *          The object to test.
  *
  * @throws JSONException
  *                       If o is a non-finite number.
  */
 public static void testValidity ( Object o ) throws JSONException {
  if ( o != null ) {
   if ( o instanceof Double ) {
    if ( ( ( Double ) o ).isInfinite() || ( ( Double ) o ).isNaN() ) {
     main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                        "JSON does not allow non-finite numbers."));
    }
   } else if ( o instanceof Float ) {
    if ( ( ( Float ) o ).isInfinite() || ( ( Float ) o ).isNaN() ) {
     main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                        "JSON does not allow non-finite numbers."));
    }
   }
  }
 }

 /**
  * Make a JSON text of an Object value. If the object has an
  * value.toJSONString() method, then that method will be used to produce the
  * JSON text. The method is required to produce a strictly conforming text.
  * If the object does not contain a toJSONString method (which is the most
  * common case), then a text will be produced by other means. If the value
  * is an array or Collection, then a JSONArray will be made from it and its
  * toJSONString method will be called. If the value is a MAP, then a
  * JSONObject will be made from it and its toJSONString method will be
  * called. Otherwise, the value's toString method will be called, and the
  * result will be quoted.
  *
  * <p>
  * Warning: This method assumes that the data structure is acyclical.
  *
  * @param value
  *              The value to be serialized.
  *
  * @return a printable, displayable, transmittable representation of the
  *         object, beginning with <code>{</code>&nbsp;<small>(left
  *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
  *         brace)</small>.
  *
  * @throws JSONException
  *                       If the value is or contains an invalid number.
  */
 public static String valueToString ( Object value ) throws JSONException {
  if ( value == null ) {
   return "null";
  }
  if ( value instanceof JSONString ) {
   Object object;
   try {
    object = ( ( JSONString ) value ).toJSONString();
   } catch ( Exception e ) {
    main.Main.LOG.addE("JSONObject.load()" , new JSONException(e));
    return null;
   }
   if ( object instanceof String ) {
    return ( String ) object;
   }
   main.Main.LOG.addE("JSONObject.load()" , new JSONException(
                      "Bad value from toJSONString: " + object));
   return null;
  }
  if ( value instanceof Number ) {
   return numberToString(( Number ) value);
  }
  if ( value instanceof Boolean || value instanceof JSONObject
       || value instanceof JSONArray ) {
   return value.toString();
  }
  if ( value instanceof Map ) {
   return new JSONObject(( Map ) value).toString();
  }
  if ( value instanceof Collection ) {
   return new JSONArray(( Collection ) value).toString();
  }
  if ( value.getClass().isArray() ) {
   return new JSONArray(value).toString();
  }
  return quote(value.toString());
 }

 /**
  * Wrap an object, if necessary. If the object is null, return the NULL
  * object. If it is an array or collection, wrap it in a JSONArray. If it is
  * a map, wrap it in a JSONObject. If it is a standard property (Double,
  * String, et al) then it is already wrapped. Otherwise, if it comes from
  * one of the java packages, turn it into a string. And if it doesn't, try
  * to wrap it in a JSONObject. If the wrapping fails, then null is returned.
  *
  * @param object
  *               The object to wrap
  *
  * @return The wrapped value
  */
 public static Object wrap ( Object object ) {
  try {
   if ( object == null ) {
    return null;
   }
   if ( object instanceof JSONObject || object instanceof JSONArray
        || object instanceof JSONString
        || object instanceof Byte || object instanceof Character
        || object instanceof Short || object instanceof Integer
        || object instanceof Long || object instanceof Boolean
        || object instanceof Float || object instanceof Double
        || object instanceof String ) {
    return object;
   }

   if ( object instanceof Collection ) {
    return new JSONArray(( Collection ) object);
   }
   if ( object.getClass().isArray() ) {
    return new JSONArray(object);
   }
   if ( object instanceof Map ) {
    return new JSONObject(( Map ) object);
   }
   Package objectPackage = object.getClass().getPackage();
   String objectPackageName = objectPackage != null ? objectPackage
           .getName() : "";
   if ( objectPackageName.startsWith("java.")
        || objectPackageName.startsWith("javax.")
        || object.getClass().getClassLoader() == null ) {
    return object.toString();
   }
   return new JSONObject(object);
  } catch ( Exception exception ) {
   return null;
  }
 }

 static final void writeValue ( Writer writer , Object value )
         throws
         JSONException , IOException {
  if ( value == null ) {
   writer.write("null");
  } else if ( value instanceof JSONObject ) {
   ( ( JSONObject ) value ).write();
  } else if ( value instanceof JSONArray ) {
   ( ( JSONArray ) value ).write();
  } else if ( value instanceof Map ) {
   new JSONObject(( Map ) value).write();
  } else if ( value instanceof Collection ) {
   new JSONArray(( Collection ) value).write();
  } else if ( value.getClass().isArray() ) {
   new JSONArray(value).write();
  } else if ( value instanceof Number ) {
   writer.write(numberToString(( Number ) value));
  } else if ( value instanceof Boolean ) {
   writer.write(value.toString());
  } else if ( value instanceof JSONString ) {

   Object o;
   try {
    o = ( ( JSONString ) value ).toJSONString();
   } catch ( Exception e ) {
    main.Main.LOG.addE("JSONObject.load()" , new JSONException(e));
    return;
   }
   writer.write(o != null ? o.toString() : quote(value.toString()));

  } else {
   quote(value.toString() , writer);
  }
 }

 /**
  * Write the contents of the JSONObject as JSON text to a writer. For
  * compactness, no whitespace is added.
  * <p>
  * Warning: This method assumes that the data structure is acyclical.
  *
  * @param indent
  *
  * @return The writer.
  *
  * @throws JSONException
  */
 public String write ()
         throws JSONException {
  try {
   Writer writer = new StringWriter();// Writer writer ,
   final int length = this.length();
   Iterator keys = this.keys();
   writer.write('{');

   if ( length == 1 ) {
    this.map.keySet().stream().forEach(( String key ) -> {
     try {
      writer.write(quote(key));
      writer.write(':');
      writeValue(writer , this.map.get(key));
     } catch ( IOException ex ) {
      main.Main.LOG.addE("JSONObject.write()" , ex);
     }
    });
   } else if ( length != 0 ) {
    this.map.keySet().stream().forEach(( String key ) -> {
     try {
      writer.write(quote(key));
      writer.write(':');
      writeValue(writer , this.map.get(key));
      writer.write(',');
     } catch ( IOException ex ) {
      main.Main.LOG.addE("JSONObject.write()" , ex);
     }
    });
   }
   writer.write('}');
   return writer.toString();
  } catch ( IOException ex ) {
   main.Main.LOG.addE("JSONObject.load()" , ex);
   return null;
  }
 }
}
