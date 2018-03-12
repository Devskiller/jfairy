import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import static ch.qos.logback.classic.Level.TRACE

appender("CONSOLE", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%-4relative [%thread] - %msg%n"
	}
}
logger("io.codearte.jfairy", TRACE, ["CONSOLE"])

root(TRACE, ["CONSOLE"])
