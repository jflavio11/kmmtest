package com.jflavio.kmmtest.android.di

import android.content.Context
import com.jflavio.kmmtest.shared.SpaceXRepository
import com.jflavio.kmmtest.shared.SpaceXRepositoryImpl
import com.jflavio.kmmtest.shared.cache.DatabaseDriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Modules
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  17/04/2021
 */

/**
 * Hilt
 *
 * =======================================================================================
 * Inject into Android classes
 * Once Hilt is set up in your Application class and an application-level component
 * is available, Hilt can provide dependencies to other Android classes that have
 * the @AndroidEntryPoint annotation.
 * If you annotate an Android class with @AndroidEntryPoint, then you also must
 * annotate Android classes that depend on it. For example, if you annotate a fragment,
 * then you must also annotate any activities where you use that fragment.
 *
 * =======================================================================================
 * Binding
 * To perform field injection, Hilt needs to know how to provide instances of the
 * necessary dependencies from the corresponding component. A binding contains the
 * information necessary to provide instances of a type as a dependency. One way to
 * provide binding information to Hilt is constructor injection. Use the @Inject
 * annotation on the constructor of a class to tell Hilt how to provide instances
 * of that class.
 *
 * =======================================================================================
 * Modules
 * A Hilt Module it informs Hilt how to provide instances of certain types. Unlike
 * Dagger modules, you must annotate Hilt modules with @InstallIn to tell Hilt which
 * Android class each module will be used or installed in.
 *
 * Dependencies that you provide in Hilt modules are available in all generated
 * components that are associated with the Android class where you install the
 * Hilt module.
 *
 * Note: Because Hilt's code generation needs access to all of the Gradle modules
 * that use Hilt, the Gradle module that compiles your Application class also needs
 * to have all of your Hilt modules and constructor-injected classes in its transitive
 * dependencies.
 *
 * =======================================================================================
 * Components
 * Dagger can create a graph of the dependencies in your project that it can use to
 * find out where it should get those dependencies when they are needed. To make Dagger
 * do this, you need to create an interface and annotate it with @Component. Dagger
 * creates a container as you would have done with manual dependency injection. Inside
 * the @Component interface, you can define functions that return instances of the classes
 * you need (i.e. UserRepository).
 *
 * @Component tells Dagger to generate a container with all the dependencies required to
 * satisfy the types it exposes. This is called a Dagger component; it contains a graph
 * that consists of the objects that Dagger knows how to provide and their respective
 * dependencies.
 *
 * To understand better about difference between Components and Modules:
 * https://stackoverflow.com/questions/48948687/difference-between-module-and-component-in-dagger2
 *
 * We can group dependencies in a module for example Network module can provide HTTPClient,
 * Retrofit,GSON, HTTP Logger etc. similarly we can have Database Module, Application Module,
 * or any feature specific module.
 *
 * Component is a bridge between Module(provider) and Activity/Fragment(Consumer) and it
 * can have more than one module.
 *
 * Basically:
 * - Modules are responsible for providing objects which can be injected.
 * - Components defines the connection between the provider of objects (modules) and the
 *   objects which express a dependency. The class for this connection is generated by Dagger.
 *
 * For each Android class in which you can perform field injection, there's an associated
 * Hilt component that you can refer to in the @InstallIn annotation. Each Hilt component
 * is responsible for injecting its bindings into the corresponding Android class.
 *
 * DataDiModules
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  05/09/2020
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class MainViewModelModule {

    @ActivityRetainedScoped
    @Provides
    fun bindRepository(@ApplicationContext context: Context): SpaceXRepository {
        return SpaceXRepositoryImpl(DatabaseDriverFactory(context))
    }

}