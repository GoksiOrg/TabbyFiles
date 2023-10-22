package tech.goksi.tabbyfiles.cli.configuration

import org.jline.utils.AttributedString
import org.springframework.context.annotation.Configuration
import org.springframework.shell.jline.PromptProvider

@Configuration
class ShellConfiguration : PromptProvider {
    override fun getPrompt(): AttributedString = AttributedString("tabby:>")
}