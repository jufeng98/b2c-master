#include "com_javamaster_b2c_cloud_test_learn_java_jni_HelloNative.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_javamaster_b2c_cloud_test_learn_java_jni_HelloNative_greeting
  (JNIEnv* env, jclass cl)
  {
     printf("hello native world\n");
  }

  int main(int argc, char const *argv[])
  {
     printf("hello C/C++ world\n");
  }
  