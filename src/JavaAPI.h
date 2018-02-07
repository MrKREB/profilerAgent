/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class JavaAPI */

#ifndef _Included_JavaAPI
#define _Included_JavaAPI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     JavaAPI
 * Method:    start
 * Signature: (Ljava/lang/String;Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_JavaAPI_start
  (JNIEnv *, jclass, jstring, jstring, jint, jint);

/*
 * Class:     JavaAPI
 * Method:    stop
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_JavaAPI_stop
  (JNIEnv *, jclass, jstring, jstring);

/*
 * Class:     JavaAPI
 * Method:    status
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_JavaAPI_status
  (JNIEnv *, jclass, jstring);

#ifdef __cplusplus
}
#endif
#endif