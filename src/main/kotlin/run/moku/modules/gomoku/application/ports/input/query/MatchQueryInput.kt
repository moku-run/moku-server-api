package run.moku.modules.gomoku.application.ports.input.query

interface MatchQueryInput {
    fun getQueueSize(): Int
}