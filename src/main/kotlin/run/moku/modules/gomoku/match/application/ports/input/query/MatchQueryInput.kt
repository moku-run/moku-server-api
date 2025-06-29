package run.moku.modules.gomoku.match.application.ports.input.query

interface MatchQueryInput {
    fun getQueueSize(): Int
}