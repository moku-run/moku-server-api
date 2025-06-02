package run.moku.modules.chat.domain.model

import run.moku.modules.chat.domain.entity.ChatSender
import run.moku.modules.chat.domain.value.ChatContent

class ChatMessageModel(
    val sender: ChatSender,
    val content: ChatContent,
) {

}