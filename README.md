#简介


使用编译时注解，优雅得实现Activity携带参数跳转



#使用方法


    class T {
        @BundleValue
        long id;
    }

    pack

    unpack



#Gradle


    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }

provided 'com.github.qbaowei.Go:go-compiler:1.0.5'

compile 'com.github.qbaowei.Go:go-core:1.0.5'