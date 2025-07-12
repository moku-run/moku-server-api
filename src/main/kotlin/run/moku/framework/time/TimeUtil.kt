package run.moku.framework.time


import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeUtil {
    private val KST: ZoneId = ZoneId.of("Asia/Seoul")
    private val UTC: ZoneId = ZoneId.of("UTC")

    private val DATE_TIME_FORMAT_ISO: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    private val NANO_TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    private val TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun nowKST(): LocalDateTime = Instant.now().atZone(KST).toLocalDateTime()
    fun nowKSTNanoTimeFormat(): String = Instant.now().atZone(KST).toLocalDateTime().format(TIME_FORMAT)
    fun nowKSTTimeFormat(): String = Instant.now().atZone(KST).toLocalDateTime().format(NANO_TIME_FORMAT)

    fun convertTime(target: String): LocalDateTime =
        LocalDateTime.parse(target, TIME_FORMAT)

    fun convertTimeDateString(target: String): LocalDateTime =
        LocalDate
            .parse(target)
            .atStartOfDay()

    fun convertDateString(target: String): LocalDate =
        LocalDate
            .parse(target)

    fun convertFormat(target: LocalDateTime): String = target.format(TIME_FORMAT)
    fun convertFormatISO(target: LocalDateTime): String = target.format(DATE_TIME_FORMAT_ISO)

    fun convertUtcToKst(target: LocalDateTime?): LocalDateTime? =
        target?.atZone(UTC)
            ?.withZoneSameInstant(KST)
            ?.toLocalDateTime()
}