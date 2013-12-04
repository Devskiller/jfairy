import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.TRACE

appender("CONSOLE", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%-4relative [%thread] - %msg%n"
	}
}
logger("org.jfairy", TRACE, ["CONSOLE"])

root(TRACE, ["CONSOLE"])