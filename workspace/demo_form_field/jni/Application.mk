# Build both ARMv5TE and ARMv7-A machine code.
APP_PLATFORM :=android-8
APP_MODULES := fpdfembedsdk
APP_ABI := armeabi
APP_STL := stlport_shared
STLPORT_FORCE_REBUILD:=true