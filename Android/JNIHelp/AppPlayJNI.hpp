// AppPlayJNI.hpp

#ifndef APPPLAYJNI_HPP
#define APPPLAYJNI_HPP

#include <jni.h>

namespace appplay
{

	struct JNIMethodInfo
	{
		JNIEnv *env;
		jclass classID;
		jmethodID methodID;
	};

	class JNIHelper
	{
	public:
		static void SetJavaVM (JavaVM *vm);
		static JavaVM *GetJavaVM ();

		static jclass GetClassID (const char *className, JNIEnv *env=0);
		static bool GetStaticMethodInfo (JNIMethodInfo &methodinfo,
			const char *className, const char *methodName, 
			const char *paramCode);
		static bool GetMethodInfo (JNIMethodInfo &methodinfo,
			const char *className, const char *methodName, 
			const char *paramCode);
		static std::string JString2string (jstring str);

	private:
		static JavaVM *msJavaVM;
	};

}

extern "C"
{
	extern const char* GetPackageNameJNI();

	extern void SetKeyboardStateJNI(int open);
	extern void OpenKeyboardJNI();
	extern void CloseKeyboardJNI();
}

#endif
