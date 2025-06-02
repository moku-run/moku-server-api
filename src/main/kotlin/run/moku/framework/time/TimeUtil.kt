package run.moku.framework.time


import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeUtil {
    private val TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val KST: ZoneId = ZoneId.of("Asia/Seoul")
    private val UTC: ZoneId = ZoneId.of("UTC")

    val NANO_TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")

    fun nowKST(): LocalDateTime = Instant.now().atZone(KST).toLocalDateTime()

    fun convertTime(target: String): LocalDateTime =
        LocalDateTime.parse(target, TIME_FORMAT)

    fun convertUtcToKst(target: LocalDateTime?): LocalDateTime? {
        target
            ?.let {
                return target.atZone(UTC).withZoneSameInstant(KST).toLocalDateTime()
            }
            ?: run {
                return null
            }
    }
}