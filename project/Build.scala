import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "ImageDB"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    //  "com.github.twitter" % "bootstrap" % "2.0.2"
    //	"c:" % "play-2.0" % "ImageDB" % "lib" % "imgscalr-lib-4.2.jar"
    //"base" % "lib" % "imgscalr-lib-4.2.jar"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    //  (base / "assets" / "stylesheets" / "bootstrap.min.css") +++ 
    //  (base / "assets" / "stylesheets" / "bootstrap-responsive.min.css")
    //	(base / "assets" / "imgscalr-lib-4.2.jar")   
    )

}
