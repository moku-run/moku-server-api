package run.moku.modules.gomoku.match.application.ports.input.query

interface MatchQueryInputPort {
    fun canStart(): Boolean
}