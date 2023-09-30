export JAVA_PROGRAM_ARGS=`echo "$@"`
mvn -f Minty_miners_cse242/pom.xml exec:java -Dexec.args="$JAVA_PROGRAM_ARGS"