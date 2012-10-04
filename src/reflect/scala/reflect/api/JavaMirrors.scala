package scala.reflect
package api

/** A refinement of [[scala.reflect.api.Mirror]] for runtime reflection using JVM classloaders.
 *
 *  With this upgrade, mirrors become capable of converting Scala reflection artifacts (symbols and types)
 *  into Java reflection artifacts (classes) and vice versa. Consequently refined mirrors
 *  become capable of performing reflective invocations (getting/settings field values, calling methods, etc).
 *
 *  See [[scala.reflect.api.package the overview page]] for details on how to use runtime reflection.
 */
trait JavaMirrors { self: JavaUniverse =>

  /** In runtime reflection universes, runtime representation of a class is [[java.lang.Class]]. */
  type RuntimeClass = java.lang.Class[_]

  /** In runtime reflection universes, mirrors are JavaMirrors. */
  override type Mirror >: Null <: JavaMirror

  /** A refinement of [[scala.reflect.api.Mirror]] for runtime reflection using JVM classloaders.
   *
   *  With this upgrade, mirrors become capable of converting Scala reflection artifacts (symbols and types)
   *  into Java reflection artifacts (classes) and vice versa. Consequently refined mirrors
   *  become capable of performing reflective invocations (getting/settings field values, calling methods, etc).
   *
   *  See [[scala.reflect.api.package the overview page]] for details on how to use runtime reflection.
   */
  trait JavaMirror extends scala.reflect.api.Mirror[self.type] with RuntimeMirror {
    val classLoader: ClassLoader
    override def toString = s"JavaMirror with ${runtime.ReflectionUtils.show(classLoader)}"
  }

  /** Creates a runtime reflection mirror from a JVM classloader.
   *  See [[scala.reflect.api.package the overview page]] for details on how to use runtime reflection.
   */
  def runtimeMirror(cl: ClassLoader): Mirror
}
